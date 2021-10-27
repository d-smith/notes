package org.ds.lmax.rates;

import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class RateLogger {
    private int countThreshold;
    private Logger logger;
    AtomicInteger count;
    long start;

    public RateLogger(int countThreshold, Logger logger) {
        this.countThreshold = countThreshold;
        this.logger = logger;
        count = new AtomicInteger();
        start = System.currentTimeMillis();
    }

    public void count() {
        int current = count.incrementAndGet();
        if(current == countThreshold) {
            long stop = System.currentTimeMillis();
            logger.info("Counted {} events in {} ms -> {} per second",
                    current, stop - start, 1000.0 * current / (stop - start));
            count.set(0);
            start = stop;
        }
    }
}
