package com.conygre.spring.boot.service;

import com.conygre.spring.boot.helpers.Insights;

public interface InsightsService {
    Insights getInsightsForStock(String symbol);
}
