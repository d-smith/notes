package org.ds.lmax.sink;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;
import org.ds.lmax.rates.RateLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuoteSubscriber {
    private static Logger LOG = LoggerFactory.getLogger(QuoteSubscriber.class);

    public static void main(String... args) throws Exception {
        Connection nc = Nats.connect("nats://localhost:4222");
        RateLogger rateLogger = new RateLogger(1000, LOG);
        Dispatcher dispatcher = nc.createDispatcher(msg -> {
                rateLogger.count();
        });

        dispatcher.subscribe("quotes.>");
    }


}
