package ds.org.helloflink;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.EventTimeSessionWindows;
import org.apache.flink.streaming.api.windowing.assigners.ProcessingTimeSessionWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

public class Join1 {

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

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> rawQuoteStream =
                env.socketTextStream("localhost", 9000);

        DataStream<Quote> quoteDataStream = rawQuoteStream.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String s) throws Exception {
                try {
                    String[] tokens = s.split(",");
                    Double.valueOf(tokens[1]);
                    return tokens.length == 2;
                } catch (Throwable t) {
                    return false;
                }
            }
        })
                .map(new MapFunction<String, Quote>() {
                    @Override
                    public Quote map(String s) throws Exception {
                        String[] tokens = s.split(",");
                        return new Quote(tokens[0], Double.parseDouble(tokens[1]));
                    }
                });

        DataStream<String> rawPositions = env.socketTextStream("localhost", 9001);

        DataStream<Tuple3<String, String, Double>> positions = rawPositions.map(new MapFunction<String, Tuple3<String, String, Double>>() {
            @Override
            public Tuple3<String, String, Double> map(String s) throws Exception {
                String[] tokens = s.split(",");
                return Tuple3.of(tokens[0], tokens[1], Double.parseDouble(tokens[2]));
            }
        });


        quoteDataStream.print();
        positions.print();


        DataStream<Tuple4<String, String, Double, Double>> joined =
                positions.join(quoteDataStream)
                        .where(getPositionStreamSymbol -> getPositionStreamSymbol.f1)
                        .equalTo(getQuoteKey -> getQuoteKey.symbol)
                        //.window(ProcessingTimeSessionWindows.withGap(Time.seconds(30)))
                        .window(TumblingProcessingTimeWindows.of(Time.seconds(30)))
                        .apply(new JoinFunction<Tuple3<String, String, Double>, Quote, Tuple4<String, String, Double, Double>>() {
                            @Override
                            public Tuple4<String, String, Double, Double> join(Tuple3<String, String, Double> positionTuple, Quote quote) throws Exception {
                                System.out.println("apply called");
                                return Tuple4.of(positionTuple.f0, positionTuple.f1, positionTuple.f2, quote.price);
                            }
                        });

        joined.print();

        env.execute();

    }
}
