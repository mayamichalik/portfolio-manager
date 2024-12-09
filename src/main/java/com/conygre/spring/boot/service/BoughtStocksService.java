package com.conygre.spring.boot.service;

import com.conygre.spring.boot.entities.BoughtStocks;

import java.util.List;

public interface BoughtStocksService {
    List<BoughtStocks> getStocks();

    BoughtStocks getBoughtStocksBySymbol(String symbol);

    void addToBoughtStocks(BoughtStocks stocks);

    void deleteBoughtStocks(String symbol);

    void updateBoughtStocks(BoughtStocks stocks);
}
