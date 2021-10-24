package org.ds.lmax;

import io.nats.client.Dispatcher;
import io.nats.client.Message;
import io.nats.client.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class PositionMessageHandler implements MessageHandler {
    static Logger LOG = LoggerFactory.getLogger(PositionMessageHandler.class);

    private ArrayList<String> symbols;
    private Dispatcher quotesDispatcher;

    public PositionMessageHandler(Dispatcher quotesDispatcher) {
        symbols = new ArrayList<>();
        this.quotesDispatcher = quotesDispatcher;
    }

    @Override
    public void onMessage(Message msg) throws InterruptedException {
        String rawPosition = new String(msg.getData(), StandardCharsets.UTF_8);
        LOG.info("position is {}", rawPosition);

        String[] tokens = rawPosition.split(",");
        if(tokens.length != 3) {
            LOG.warn("raw position not tokenized into 3 tokens");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(tokens[2]);
        } catch(NumberFormatException nfe) {
            LOG.warn("unable to convert amount token to double");
            return;
        }

        String symbol = tokens[1];
        if(symbols.contains(symbol)) {
            LOG.info("have seen {}", symbol);
        } else {
            LOG.info("new symbol {} - subscribe dispatcher to quotes.{}", symbol, symbol);
            symbols.add(symbol);
            quotesDispatcher.subscribe("quotes." + symbol);
        }

    }
}
