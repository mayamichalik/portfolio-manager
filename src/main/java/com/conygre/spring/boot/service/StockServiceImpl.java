package com.conygre.spring.boot.service;

import com.conygre.spring.boot.entities.Stock;
import com.conygre.spring.boot.helpers.DatabaseHelper;
import com.conygre.spring.boot.repos.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class StockServiceImpl implements StockService {
    @Autowired
    private StockRepository repo;
    @Autowired
    private DatabaseHelper dbHelper;

    @Autowired
    public void setRepo(StockRepository repo) { this.repo = repo; }

    @Autowired
    public void setHelper(DatabaseHelper dbHelper) { this.dbHelper = dbHelper; }

    @Override
    public List<Stock> getStocks() {
        List<Stock> stocks = repo.findAll();
        if (stocks.isEmpty()) {
            stocks = dbHelper.fetchAllStocks();
            repo.saveAllAndFlush(stocks);
        }
        return stocks;
    }

    @Override
    public Stock getStockBySymbol(String symbol) {
        Optional<Stock> result = repo.findById(symbol);
        return result.orElse(null);
    }

    @Override
    public void addToStocks(Stock stock) {
        repo.save(stock);
    }
}
