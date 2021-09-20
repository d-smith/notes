package ds.org.helloflink;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class LifeExpectancyTracking {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> inputStream =
                //env.readTextFile("src/main/resources/life_exp.csv");
                env.readTextFile("/Users/ds/notes/flink/my-flink-project/src/main/resources/life_exp.csv");
        DataStream<String> filteredData = inputStream.filter((String value) -> {
           String[] tokens = value.split((","));

           //Build in a demo pause
           try {
               Thread.sleep(10000);
           } catch(InterruptedException e) {
               System.out.println(e);
           }

           return Double.parseDouble(tokens[3]) >= 75;
        });

        filteredData.print();

        env.execute("Filter country details");
    }
}
