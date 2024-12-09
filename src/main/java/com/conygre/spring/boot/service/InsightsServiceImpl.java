package com.conygre.spring.boot.service;

import com.conygre.spring.boot.helpers.DatabaseHelper;
import com.conygre.spring.boot.helpers.Insights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class InsightsServiceImpl implements InsightsService{
    @Autowired
    DatabaseHelper dbHelper;

    @Override
    public Insights getInsightsForStock(String symbol) {
        return dbHelper.fetchInsights(symbol);
    }
}
