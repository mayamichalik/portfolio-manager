package com.conygre.spring.boot.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="BoughtStocks")

public class BoughtStocks implements Serializable {
    @Column(name = "stock_name")
    private String stock_name;

    // add attributes for all the remaining properties
    @Column(name = "amount_invested")
    private Double amount_invested;
    @Id
    @Column(name = "ticker_symbol")
    private String ticker_symbol;
    @Column(name = "volume")
    private Integer volume;

    public BoughtStocks() {
    }

    public BoughtStocks(String n, Double a, String t, Integer v) {
        stock_name = n;
        amount_invested = a;
        ticker_symbol = t;
        volume = v;

    }

    public String getStock_name() {
        return stock_name;
    }
    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public Double getAmount_invested() {
        return amount_invested;
    }

    public void setAmount_invested(Double amount_invested) {
        this.amount_invested = amount_invested;
    }

    public String getTicker_symbol() {
        return ticker_symbol;
    }

    public void setTicker_symbol(String ticker_symbol) {
        this.ticker_symbol = ticker_symbol;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}