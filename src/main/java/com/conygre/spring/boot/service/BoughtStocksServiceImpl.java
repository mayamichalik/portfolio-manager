package com.conygre.spring.boot.service;

import com.conygre.spring.boot.entities.BoughtStocks;
import com.conygre.spring.boot.entities.Stock;
import com.conygre.spring.boot.repos.BoughtStocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional (propagation = Propagation.REQUIRED)
public class BoughtStocksServiceImpl implements BoughtStocksService {
    @Autowired
    BoughtStocksRepository repo;
    @Override
    public List<BoughtStocks> getStocks() {
        return repo.findAll();
    }

    @Override
    public BoughtStocks getBoughtStocksBySymbol(String symbol) {
        Optional<BoughtStocks> result = repo.findById(symbol);
        return result.orElse(null);
    }

    @Override
    public void addToBoughtStocks(BoughtStocks stocks) {
        this.repo.save(stocks);
    }

    @Override
    public void deleteBoughtStocks(String symbol) {
        repo.deleteById(symbol);
    }

    @Override
    public void updateBoughtStocks(BoughtStocks stocks) {
        if (getBoughtStocksBySymbol(stocks.getTicker_symbol()) != null) {
            this.repo.save(stocks);
        }
    }
}
