package org.ds.lmax;

import io.nats.client.Message;
import io.nats.client.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PositionMessageHandler implements MessageHandler {
    static Logger LOG = LoggerFactory.getLogger(PositionMessageHandler.class);
    @Override
    public void onMessage(Message msg) throws InterruptedException {
        LOG.info("position is {}", msg.toString());
    }
}
