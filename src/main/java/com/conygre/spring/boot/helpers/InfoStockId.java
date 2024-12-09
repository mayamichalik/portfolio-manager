package com.conygre.spring.boot.helpers;

import java.io.Serializable;
import java.sql.Date;

public class InfoStockId implements Serializable {
    private String ticker_symbol;

    private Date dateTime;

    // default constructor
    public InfoStockId() {}

    public InfoStockId(String ticker_symbol, Date dateTime) {
        this.ticker_symbol = ticker_symbol;
        this.dateTime = dateTime;
    }

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof InfoStockId)) {
            return false;
        }
        InfoStockId isId = (InfoStockId) o;
        return this.ticker_symbol.equals(isId.ticker_symbol)
                && this.dateTime.equals(isId.dateTime);
    }
    @Override
    public int hashCode() {
        return this.ticker_symbol.hashCode();
    }
}
