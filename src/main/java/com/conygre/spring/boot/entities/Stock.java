package com.conygre.spring.boot.entities;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;


@Entity
@Table(name="stocks")
public class Stock {
    @Id
    @Column(name="symbol")
    private String symbol;
    @Column(name="sname") private String name;
    @Column(name="currency") private String currency;
    @Column(name="stock_exchange") private String exchange;
    @Column(name="mic_code")
    @SerializedName(value="mic_code") private String micCode;
    @Column(name="country") private String country;
    @Column(name="stock_type") private String type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "symbol", referencedColumnName = "ticker_symbol")
    private Watchlist watchlist;


    public Stock() {}

    public Stock(String symbol, String name, String currency, String exchange, String micCode, String country, String type) {
        this.symbol = symbol;
        this.name = name;
        this.currency = currency;
        this.exchange = exchange;
        this.micCode = micCode;
        this.country = country;
        this.type = type;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getMicCode() {
        return micCode;
    }

    public void setMicCode(String micCode) {
        this.micCode = micCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
