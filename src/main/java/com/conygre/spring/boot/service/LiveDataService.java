package com.conygre.spring.boot.service;

import com.conygre.spring.boot.helpers.StockPrice;

public interface LiveDataService {

    StockPrice getLivePriceForStock(String symbol);
}
