package org.ds.lmax.marketval;

import com.lmax.disruptor.EventHandler;
import io.nats.client.Connection;

import java.nio.charset.StandardCharsets;

public class MarketValueEventConsumer implements EventHandler<MarketValueEvent> {


    private final String MARKET_VALUE_TOPIC = "mvupdates";
    private Connection nc;

    public MarketValueEventConsumer(Connection nc) {
        this.nc = nc;
    }

    public String formatMarketValue(String owner, String symbol, double value) {
        return "MarketValueUpdate{" +
                "owner='" + owner + '\'' +
                ", symbol='" + symbol + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public void onEvent(MarketValueEvent marketValueEvent, long l, boolean b) throws Exception {
        double positionValue = marketValueEvent.position.getAmount() * marketValueEvent.quoteEvent.price;

        nc.publish(MARKET_VALUE_TOPIC,
                formatMarketValue(marketValueEvent.position.getOwner(),
                        marketValueEvent.position.getSymbol(),
                        positionValue).getBytes(StandardCharsets.UTF_8)
        );

    }
}
