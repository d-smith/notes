package ds.org.nats;


import io.nats.client.*;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class NatsSource extends RichSourceFunction<String> {
    private static final Logger LOG = LoggerFactory.getLogger(NatsSource.class);
    private boolean running = true;
    private BlockingQueue<String> quoteQueue= new ArrayBlockingQueue<String>(1000);
    private Dispatcher dispatcher;
    private BlockingQueue<String> subscriptionQueue= new ArrayBlockingQueue<String>(50);

    public NatsSource() throws Exception {

    }

    public boolean addSubscription(String subject) throws InterruptedException {
        return subscriptionQueue.offer(subject, 300, TimeUnit.MILLISECONDS);
    }


    @Override
    public void run(SourceContext<String> sourceContext) throws Exception {
        LOG.info("Connect to nats");
        Connection nc = Nats.connect("nats://localhost:4222");

        dispatcher  = nc.createDispatcher(msg -> {
            String [] quoteTokens = msg.getSubject().split("\\.");
            String quoteString = quoteTokens[1] + "," + new String(msg.getData(), StandardCharsets.UTF_8);
            System.out.println("put " + quoteString);
            quoteQueue.put(quoteString);
        });

       //dispatcher.subscribe("quotes.>"); //next manage subscriptions

        System.out.println("subscribe");
        //Subscription sub = nc.subscribe("quotes.>");
        while(running) {
            if(subscriptionQueue.peek() != null) {
                String newSubject = subscriptionQueue.poll();
                if(newSubject != null) {
                    System.out.println("add subscription - " + newSubject);
                    dispatcher.subscribe(newSubject);
                }
            }

            System.out.println("take...");
            String qs = quoteQueue.take();
            System.out.println("took " + qs);
            if(qs != null) {
                sourceContext.collect(qs);
            }

            //Message msg = sub.nextMessage(Duration.ofMillis(300));
            //if(msg != null) {
            //    System.out.println("got " + msg.toString());
            //    String [] quoteTokens = msg.getSubject().split("\\.");
            //    System.out.println("subject is " + msg.getSubject());
            //    System.out.println(quoteTokens.length);
            //    System.out.println(quoteTokens[0]);
            //    String quoteString = quoteTokens[1] + "," + new String(msg.getData(), StandardCharsets.UTF_8);
            //    sourceContext.collect(quoteString);
            //} else {
            //    System.out.println("no message");
            //}

        }

    }

    @Override
    public void cancel() {
        this.running = false;
    }
}
