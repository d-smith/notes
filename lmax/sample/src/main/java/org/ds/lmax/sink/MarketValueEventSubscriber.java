package org.ds.lmax.sink;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;
import org.ds.lmax.QuotesMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarketValueEventSubscriber {
    private static Logger LOG = LoggerFactory.getLogger(MarketValueEventSubscriber.class);

    public static void main(String... args) throws Exception {
        Connection nc = Nats.connect("nats://localhost:4222");
        Dispatcher dispatcher = nc.createDispatcher(msg -> {
                LOG.info(msg.toString());
        });

        dispatcher.subscribe("mvupdates");
    }


}
