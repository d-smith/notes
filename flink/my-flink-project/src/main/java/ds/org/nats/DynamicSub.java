package ds.org.nats;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.*;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.state.KeyedStateFunction;
import org.apache.flink.streaming.api.datastream.BroadcastStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.KeyedBroadcastProcessFunction;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DynamicSub {
    public static class Position {
        public String owner;
        public String symbol;
        public Double amount;

        public Position(){}

        public Position(String owner, String symbol, Double amount) {
            this.owner = owner;
            this.symbol = symbol;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "owner='" + owner + '\'' +
                    ", symbol='" + symbol + '\'' +
                    ", amount=" + amount +
                    '}';
        }
    }

    public static class RawPositionFilterFunction implements FilterFunction<String> {

        @Override
        public boolean filter(String s) throws Exception {
            try {
                String[] tokens = s.split(",");
                Double.valueOf(tokens[2]);
                return tokens.length == 3;
            } catch(Throwable t) {
                return false;
            }
        }
    }

    public static class RawPositionsMapper implements MapFunction<String,Position> {

        @Override
        public Position map(String s) throws Exception {
            String[] tokens = s.split(",");
            return new Position(tokens[0], tokens[1], Double.valueOf(tokens[2]));
        }
    }

    public static class Quote {
        public String symbol;
        public Double price;

        public Quote() {
        }

        public Quote(String symbol, Double price) {
            this.symbol = symbol;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Quote{" +
                    "symbol='" + symbol + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

    public static class QuoteMapper implements MapFunction<String,Quote> {

        @Override
        public Quote map(String s) throws Exception {
            String[] tokens = s.split(",");
            return new Quote(tokens[0], Double.valueOf(tokens[1]));
        }
    }

    public static class QuoteEvaluator extends KeyedBroadcastProcessFunction<String,Position,Quote, Tuple2<String,Double>> {

        private static final Logger LOG = LoggerFactory.getLogger(QuoteEvaluator.class);
        // broadcast state descriptor
        MapStateDescriptor<Void, Quote> quoteDesc;

        MapState<String, Map<String,Position>> positionsState;



        @Override
        public void open(Configuration conf) {
            quoteDesc = new MapStateDescriptor<>("quotes", Types.VOID, Types.POJO(Quote.class));

            MapStateDescriptor<String,Map<String,Position>> positionsStateDesc =
                    new MapStateDescriptor<>("position", Types.STRING,
                            TypeInformation.of(
                                    new TypeHint<Map<String,Position>>() {
                                    }
                            )
                    );

            positionsState = getRuntimeContext().getMapState(positionsStateDesc);
        }

        @Override
        public void processElement(Position position, ReadOnlyContext readOnlyContext, Collector<Tuple2<String, Double>> collector) throws Exception {
            System.out.println("processElement " + position);
            LOG.warn("you seen my cones");

            // get current quote from broadcast state
            Quote quote = readOnlyContext
                    .getBroadcastState(this.quoteDesc)
                    .get(null);
            System.out.println(quote);

            if(position != null && quote != null && position.symbol.equals(quote.symbol)) {
                collector.collect(Tuple2.of(position.owner, position.amount * quote.price));
            }

            //Store it

            if(!positionsState.isEmpty()) {
                Map<String,Position> positionsWithSymbol = positionsState.get(position.symbol);
                if(positionsWithSymbol == null) {
                    Map<String,Position> m = new HashMap<>();
                    m.put(position.owner,position);
                    positionsState.put(position.symbol, m);
                } else {
                    positionsWithSymbol.put(position.owner, position);
                }
            } else {
                Map<String,Position> m = new HashMap<>();
                m.put(position.owner,position);
                positionsState.put(position.symbol,m);
            }

        }

        @Override
        public void processBroadcastElement(Quote quote, Context context, Collector<Tuple2<String, Double>> collector) throws Exception {
            System.out.println("processBroadcastElement " + quote);
            // store the new quote by updating the broadcast state
            BroadcastState<Void, Quote> bcState = context.getBroadcastState(quoteDesc);
            bcState.put(null, quote);

            //Go through the positions associated the current symbol...

            MapStateDescriptor<String,Map<String,Position>> positionsStateDesc =
                    new MapStateDescriptor<>("position", Types.STRING,
                            TypeInformation.of(
                                    new TypeHint<Map<String,Position>>() {
                                    }
                            )
                    );

            context.applyToKeyedState(positionsStateDesc, new KeyedStateFunction<String, MapState<String, Map<String, Position>>>() {
                @Override
                public void process(String s, MapState<String, Map<String, Position>> stringMapMapState) throws Exception {

                    Map<String,Position> positionsWithSymbols =  stringMapMapState.get(quote.symbol);
                    if(positionsWithSymbols != null) {
                        positionsWithSymbols.values().forEach(position -> collector.collect(
                                Tuple2.of(position.owner, position.amount * quote.price)));
                    }

                }
            });

        }
    }


    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();

        //Set up the positions stream
        DataStream<String> rawPositionsStream =
                env.socketTextStream("localhost", 9000);


        DataStream<Position> positions = rawPositionsStream.filter(new RawPositionFilterFunction())
            .map(new RawPositionsMapper());

        //Set up the quotes stream
        NatsSource natsSource = new NatsSource();
        DataStream<String> rawQuoteStream = env.addSource(natsSource);
        DataStream<Quote> quoteStream = rawQuoteStream
                .map(new QuoteMapper());
        boolean added = natsSource.addSubscription("quotes.>");

        //Keyed positions stream
        KeyedStream<Position, String> positionsByAccount =
                positions.keyBy((KeySelector<Position,String>) position -> position.owner);

        positionsByAccount.print();

        //Quote broadcast
        MapStateDescriptor<Void,Quote> broadcastDescriptor =
                new MapStateDescriptor<Void, Quote>("quotes", Types.VOID,Types.POJO(Quote.class));

        BroadcastStream<Quote> quotes = quoteStream.broadcast(broadcastDescriptor);

        //Connect the streams
        DataStream<Tuple2<String,Double>> balanceCalcStream =
                positionsByAccount
                        .connect(quotes)
                        .process(new QuoteEvaluator());

        balanceCalcStream.print();

        env.execute();

    }


}
