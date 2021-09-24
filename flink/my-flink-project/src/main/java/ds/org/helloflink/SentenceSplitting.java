package ds.org.helloflink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class SentenceSplitting {

    public static void main(String[] args) throws Exception {

        final ParameterTool params = ParameterTool.fromArgs(args);

        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();

        env.getConfig().setGlobalJobParameters(params);

        DataStream<String> dataStream;

        if (params.has("input")) {
            System.out.println("Splitting sentences from a file");

            dataStream = env.readTextFile(params.get("input"));
        } else if (params.has("host") && params.has("port")) {
            System.out.println("Splitting sentences from a socket stream");

            dataStream = env.socketTextStream(
                    params.get("host"), Integer.parseInt(params.get("port")));
        } else {
            System.out.println("Use --host and --port to specify socket OR");
            System.out.println("Use --input to specify file input");

            System.exit(1);
            return;
        }

        System.out.println("Source initialized, split sentences");

        DataStream<String> wordDataStream = dataStream.flatMap(new SentenceSplitter());

        wordDataStream.print();

        env.execute("Splitting Words");
    }

    public static class SentenceSplitter implements FlatMapFunction<String, String> {

        public void flatMap(String sentence, Collector<String> out)
                throws Exception {

            for (String word: sentence.split(" ")) {
                out.collect(word);
            }
        }
    }
}
