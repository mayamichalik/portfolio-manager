package com.conygre.spring.boot.service;

import com.conygre.spring.boot.helpers.DatabaseHelper;
import com.conygre.spring.boot.helpers.StockPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class LiveDataServiceImpl implements LiveDataService{
    @Autowired
    DatabaseHelper dbHelper;

    @Override
    public StockPrice getLivePriceForStock(String symbol) {
        return dbHelper.fetchLiveStockPrice(symbol);
    }
}
