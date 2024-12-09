package com.conygre.spring.boot.helpers;

public class StockPrice {
    private String symbol;
    private Double price;

    StockPrice(String symbol, Double price) {
        this.symbol = symbol;
        this.price = price;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

}
