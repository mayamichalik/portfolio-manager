package com.conygre.spring.boot.service;

import com.conygre.spring.boot.entities.AccountDetails;
import com.conygre.spring.boot.entities.InfoStocks;
import com.conygre.spring.boot.entities.InfoStocks;

import java.util.List;
import java.util.Map;

public interface InfoStocksService {
    List<InfoStocks> getStocks();

    InfoStocks getInfoStocksByName(String name);

    void addToInfoStocks(InfoStocks stocks);

    void deleteInfoStocks(String name);

    void updateInfoStocks(InfoStocks stocks);

    List<InfoStocks> getTimeSeries(Map<String, String> parameters);
}
