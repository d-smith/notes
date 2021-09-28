package ds.org.helloflink;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

public class Table2 {
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
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

        DataStream<String> rawQuoteStream =
                env.socketTextStream("localhost", 9000);

        DataStream<Join1.Quote> quoteDataStream = rawQuoteStream.filter(new FilterFunction<String>() {
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
                .map(new MapFunction<String, Join1.Quote>() {
                    @Override
                    public Join1.Quote map(String s) throws Exception {
                        String[] tokens = s.split(",");
                        return new Join1.Quote(tokens[0], Double.parseDouble(tokens[1]));
                    }
                });

        quoteDataStream.print();


        Table quotesTable = tableEnv.fromDataStream(quoteDataStream, "symbol, price");

        Table results = tableEnv.sqlQuery("select * from " + quotesTable);
        tableEnv.toAppendStream(results, Quote.class).print();
        env.execute();
    }
}
