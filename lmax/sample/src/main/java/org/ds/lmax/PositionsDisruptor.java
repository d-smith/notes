package org.ds.lmax;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.ThreadFactory;

public class PositionsDisruptor {

    public static class QuoteEvent {


        public String symbol;
        public double price;
        public Instant timestamp;

        public final static EventFactory EVENT_FACTORY
                = () -> new QuoteEvent();

        public QuoteEvent() {
            timestamp = Instant.now();
        }

        @Override
        public String toString() {
            return "QuoteEvent{" +
                    "symbol='" + symbol + '\'' +
                    ", price=" + price +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }

    public static class QuotePrinterConsumer implements EventHandler<QuoteEvent> {
        private static Logger LOG = LoggerFactory.getLogger(QuotePrinterConsumer.class);

        @Override
        public void onEvent(QuoteEvent quoteEvent, long seq, boolean b) throws Exception {
           LOG.info("onEvent sequence is {} value event is {}", seq, quoteEvent);
        }
    }

    private static Logger LOG = LoggerFactory.getLogger(PositionsDisruptor.class);

    public static void main(String... args) throws Exception {
        LOG.info("alive");
        String natsEndpoints = System.getenv("NATS_ENDPOINT");
        if(natsEndpoints == null) {
            LOG.error("NATS_ENDPOINT not set in environment... exiting");
            System.exit(1);
        }

        //Need a nats connection
        Connection nc = Nats.connect("nats://localhost:4222");

        //Need disrupter setup
        ThreadFactory threadFactory = DaemonThreadFactory.INSTANCE;

        WaitStrategy waitStrategy = new BusySpinWaitStrategy();
        Disruptor<QuoteEvent> disruptor
                = new Disruptor<>(
                QuoteEvent.EVENT_FACTORY,
                256,
                threadFactory,
                ProducerType.SINGLE,
                waitStrategy);

        ArrayList<EventHandler> handlers = new ArrayList<>();
        handlers.add(new QuotePrinterConsumer());
        disruptor.handleEventsWith(handlers.toArray(new QuotePrinterConsumer[0]));

    RingBuffer<QuoteEvent> ringBuffer = disruptor.start();

        // Dispatcher - nats to the disrupter
        Dispatcher dispatcher  = nc.createDispatcher(msg -> {
            LOG.info("quote is {}", msg.toString());
            String[] subjectParts = msg.getSubject().split("\\.");
            if(subjectParts.length != 2) {
                LOG.warn("ignoring {}", msg);
                return;
            }

            double price = 0.0;
            try {
                price = Double.parseDouble(new String(msg.getData(), StandardCharsets.UTF_8));
            } catch(Throwable t) {
                LOG.warn("Unable to parse price in msg {}", msg);
                return;
            }



            long sequenceId = ringBuffer.next();
            QuoteEvent quoteEvent = ringBuffer.get(sequenceId);
            quoteEvent.symbol = subjectParts[1];
            quoteEvent.price = price;
            ringBuffer.publish(sequenceId);


        });

        dispatcher.subscribe("quotes.>");


    }
}
