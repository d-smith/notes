package org.ds.lmax.sourcedata;

import io.nats.client.Connection;
import io.nats.client.Nats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class QuotesPub2 {

    static Logger LOG = LoggerFactory.getLogger(QuotesPub2.class);

    public static void main(String... args) throws Exception {
        String[] subjects =  {
                "quotes.DWAC",
                "quotes.SNDL",
                "quotes.CEI",
                "quotes.FAMI",
                "quotes.KMDN"
        };

        Connection nc = Nats.connect("nats://localhost:4222");

        int count = 0;
        long startTime = System.currentTimeMillis();

        for(;;) {
            count++;
            if(count % 100000 == 0) {
                LOG.info("count is {}", count);
                long stopTime = System.currentTimeMillis();
                LOG.info("Publishing at {} quotes/sec", count * 1000.0 / (stopTime - startTime));
                startTime = stopTime;
                count = 0;
            }
            int idx = (int) (Math.random() * subjects.length);
            String subject = subjects[idx];
            byte[] randoPrice = String.valueOf((Math.random() * 600)).getBytes(StandardCharsets.UTF_8);

            nc.publish(subject, randoPrice);
        }
    }

}
