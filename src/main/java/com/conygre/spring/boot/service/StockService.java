package com.conygre.spring.boot.service;

import com.conygre.spring.boot.entities.Stock;

import java.util.List;

public interface StockService {
    List<Stock> getStocks();

    Stock getStockBySymbol(String symbol);

    void addToStocks(Stock stock);
}
