package org.ds.lmax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class PositionsMonitor {
    private static Logger LOG = LoggerFactory.getLogger(PositionsMonitor.class);

    private Map<String,Position> positions;

    public PositionsMonitor() {
        this.positions = new HashMap<>();
    }

    public void addPosition(Position p) {
        LOG.info("adding position {}", p);
        positions.put(p.getSymbol(), p);
    }


}
