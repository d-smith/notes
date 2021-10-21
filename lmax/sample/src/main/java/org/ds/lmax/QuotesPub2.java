package org.ds.lmax;

import io.nats.client.Connection;
import io.nats.client.Nats;

import java.nio.charset.StandardCharsets;

public class QuotesPub2 {
    public static void main(String... args) throws Exception {
        String[] subjects =  {
                "quotes.DWAC",
                "quotes.SNDL",
                "quotes.CEI",
                "quotes.FAMI",
                "quotes.KMDN"
        };

        Connection nc = Nats.connect("nats://localhost:4222");


        for(;;) {
            int idx = (int) (Math.random() * subjects.length);
            String subject = subjects[idx];
            byte[] randoPrice = String.valueOf((Math.random() * 600)).getBytes(StandardCharsets.UTF_8);

            nc.publish(subject, randoPrice);
        }
    }

}
