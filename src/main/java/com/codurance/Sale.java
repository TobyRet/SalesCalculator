package com.codurance;

public class Sale {
    private final String product;
    private final double price;
    private final int quanitySold;

    public Sale(String product, double price, int quantitySold) {
        this.product = product;
        this.price = price;
        this.quanitySold = quantitySold;
    }

    public String productName() {
        return product;
    }

    public double price() {
        return price;
    }

    public int quanitySold() {
        return quanitySold;
    }
}
