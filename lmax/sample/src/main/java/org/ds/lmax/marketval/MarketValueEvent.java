package org.ds.lmax.marketval;

import com.lmax.disruptor.EventFactory;
import org.ds.lmax.Position;
import org.ds.lmax.PositionsDisruptor;

public class MarketValueEvent {

    public Position position;
    public PositionsDisruptor.QuoteEvent quoteEvent;

    public final static EventFactory EVENT_FACTORY
            = () -> new MarketValueEvent();
}
