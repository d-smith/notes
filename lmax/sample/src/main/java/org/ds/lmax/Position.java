package org.ds.lmax;

public class Position {
    private String owner;
    private String symbol;
    private double amount;

    public Position(String owner, String symbol, double amount) {
        this.owner = owner;
        this.symbol = symbol;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Position{" +
                "owner='" + owner + '\'' +
                ", symbol='" + symbol + '\'' +
                ", amount=" + amount +
                '}';
    }
}
