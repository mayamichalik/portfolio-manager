package com.conygre.spring.boot.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "watchlist")
public class Watchlist implements Serializable {

    @Id
    @Column(name="ticker_symbol")
    private String ticker_symbol;

    public Watchlist () {}

    public Watchlist(String ticker_symbol) {

        this.ticker_symbol = ticker_symbol;
    }

    public String getTicker_symbol() {
        return ticker_symbol;
    }

    public void setTicker_symbol(String ticker_symbol) {
        this.ticker_symbol = ticker_symbol;
    }
}
