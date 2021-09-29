package ds.org.nats;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

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

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> rawQuoteStream =
                env.socketTextStream("localhost", 9000);


        rawQuoteStream.filter(new RawPositionFilterFunction())
            .map(new RawPositionsMapper())
            .print();

        env.execute();

    }


}
