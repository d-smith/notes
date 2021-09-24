package ds.org.helloflink;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class KeyedStreams {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> inputData = env.readTextFile("src/main/resources/TechStocks_2020.csv");

        DataStream<String> stockRecords = inputData.filter(
                (FilterFunction<String>) line ->
                        !line.contains("Date,Open,High,Low,Close,Adj Close,Volume,Name")
        );

        DataStream<Tuple2<String, Double>> closePrices = stockRecords.map(
                new MapFunction<String, Tuple2<String, Double>>() {
                    @Override
                    public Tuple2<String, Double> map(String s) throws Exception {
                        String[] tokens = s.split(",");
                        String[] dateTokens = tokens[0].split("-");
                        String stockNameMonth = tokens[7] + "-" + dateTokens[1];
                        //return new Tuple2<>(tokens[7], Double.parseDouble(tokens[5]));
                        return new Tuple2<>(stockNameMonth, Double.parseDouble(tokens[5]));
                    }
                }
        );

        KeyedStream<Tuple2<String, Double>, String> keyedClosePrices = closePrices.keyBy(value -> value.f0);

        keyedClosePrices.print();

        env.execute();
    }
}
