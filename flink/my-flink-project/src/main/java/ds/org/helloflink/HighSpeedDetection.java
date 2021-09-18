package ds.org.helloflink;

import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import static org.apache.flink.configuration.RestOptions.*;


public class HighSpeedDetection {
    public static void main(String[] args) throws Exception {

        /*
        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();
        */

        Configuration conf = new Configuration();
        conf.setInteger(PORT, 8082);

        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.createLocalEnvironment(2, conf);


        ExecutionConfig executionConfig = env.getConfig();

        executionConfig.disableAutoGeneratedUIDs();

        System.out.println("***Autogeneratod UIDs: " + executionConfig.hasAutoGeneratedUIDsEnabled());
        System.out.println("***Parallelism: " + executionConfig.getParallelism());
        System.out.println("***Max Parallelism: " + executionConfig.getMaxParallelism());
        System.out.println("***Execution Mode: " + executionConfig.getExecutionMode());
        System.out.println("***Restart Strategy: " + executionConfig.getRestartStrategy());


        DataStream<String> dataStream = env
                .socketTextStream("localhost", 9000).uid("car-speed-read")
                .filter(new Filter())
                .name("high-speed filter operation").uid("car-speed-filter");
        dataStream.print().uid("car-speed-print");

        env.execute("car speed detection");
    }

    public static class Filter implements FilterFunction<String> {

        @Override
        public boolean filter(String input) throws Exception {
            try {
                String[] tokens = input.split(",");
                if (tokens.length == 2 && Float.parseFloat(tokens[1].trim()) > 65) {
                    return true;
                }
            } catch (Throwable t) {
            }

            return false;
        }
    }
}
