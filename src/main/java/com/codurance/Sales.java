package com.codurance;

public class Sales {
    private final String product;
    private final double price;
    private final int quantitySold;

    public Sales(String product, double price, int quantitySold) {
        this.product = product;
        this.price = price;
        this.quantitySold = quantitySold;
    }

    public String productName() {
        return product;
    }

    public double price() {
        return price;
    }

    public int quantitySold() {
        return quantitySold;
    }
}
