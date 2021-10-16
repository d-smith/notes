package org.ds.lmax;

import com.lmax.disruptor.EventFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PositionsDisruptor {
    public static class QuoteEvent {
        public String symbol;
        public double price;
        public final static EventFactory EVENT_FACTORY
                = () -> new QuoteEvent();


    }

    private static Logger LOG = LoggerFactory.getLogger(PositionsDisruptor.class);
    public static void main(String... args) {
        LOG.info("alive");
    }
}
