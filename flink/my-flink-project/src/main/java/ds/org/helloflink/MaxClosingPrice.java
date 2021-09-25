package ds.org.helloflink;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class MaxClosingPrice {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> inputData = env.readTextFile("src/main/resources/MSFT_2020.csv");

        DataStream<String> stockRecords = inputData.filter(
                (FilterFunction<String>) line ->
                        !line.contains("Date,Open,High,Low,Close,Adj Close,Volume,Name")
        );

        DataStream<Tuple3<String, String, Double>> closePrices = stockRecords.map(
                new MapFunction<String, Tuple3<String, String, Double>>() {
                    @Override
                    public Tuple3<String, String, Double> map(String s) throws Exception {
                        String[] tokens = s.split(",");
                        return new Tuple3<>(tokens[7], tokens[0], Double.parseDouble(tokens[5]));
                    }
                }
        );

        closePrices.keyBy(value -> value.f0).flatMap(
                new MaxClosingPriceFn()).print();

        env.execute();

    }

    public static class MaxClosingPriceFn extends RichFlatMapFunction<Tuple3<String,String, Double>,Tuple2<String,Double>> {

        private transient ValueState<Tuple2<String, Double>> maxClose;

        @Override
        public void flatMap(Tuple3<String, String, Double> input, Collector<Tuple2<String,Double>> collector) throws Exception {
            Tuple2<String,Double> maxClosePrice = maxClose.value();
            if(maxClosePrice == null) {
                maxClose.update(Tuple2.of(input.f1, input.f2));
            } else {
                if(input.f2 > maxClosePrice.f1) {
                    maxClose.update(Tuple2.of(input.f1, input.f2));
                }
            }

            collector.collect(maxClose.value());
        }

        @Override
        public void open(Configuration config) throws Exception {
            ValueStateDescriptor<Tuple2<String,Double>> descriptor =
                    new ValueStateDescriptor<Tuple2<String,Double>>(
                            "MaxPrice",
                            TypeInformation.of(
                                    new TypeHint<Tuple2<String,Double>>() {
                                    }));
            maxClose = getRuntimeContext().getState(descriptor);
        }
    }
}
