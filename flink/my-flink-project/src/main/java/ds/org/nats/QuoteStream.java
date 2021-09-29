package ds.org.nats;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class QuoteStream {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();

        NatsSource natsSource = new NatsSource();
        DataStream<String> quoteStream = env.addSource(natsSource);
        quoteStream.print();
        boolean added = natsSource.addSubscription("quotes.a");
        System.out.println(added);

        added = natsSource.addSubscription("quotes.b");
        System.out.println(added);

        env.execute();


    }
}
