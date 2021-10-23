package org.ds.lmax.sourcedata;

import io.nats.client.Connection;
import io.nats.client.Nats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class QuotesPubExperiment {

    static Logger LOG = LoggerFactory.getLogger(QuotesPubExperiment.class);

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
        int noEvents = Integer.MAX_VALUE / 512;

        while(count < noEvents) {
            count++;
            if(count % 1000000 == 0) {
                LOG.info("count is {}", count);
            }
            int idx = (int) (Math.random() * subjects.length);
            String subject = subjects[idx];
            byte[] randoPrice = String.valueOf((Math.random() * 600)).getBytes(StandardCharsets.UTF_8);

            nc.publish(subject, randoPrice);
        }

        nc.flush(Duration.ofSeconds(3));
        long stopTime = System.currentTimeMillis();
        LOG.info("Did {} events in {} ms --> {} events per second",
                noEvents,
                stopTime - startTime,
                (1000.0 * noEvents)/(stopTime - startTime));


        LOG.info("{}", nc.getStatistics().toString());
        nc.close();
    }

}
