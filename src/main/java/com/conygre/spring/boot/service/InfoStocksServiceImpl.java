package com.conygre.spring.boot.service;

import com.conygre.spring.boot.entities.InfoStocks;
import com.conygre.spring.boot.helpers.DatabaseHelper;
import com.conygre.spring.boot.repos.InfoStocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.text.SimpleDateFormat;

@Service
@Transactional (propagation = Propagation.REQUIRED)
public class InfoStocksServiceImpl implements InfoStocksService {
    @Autowired
    InfoStocksRepository repo;
    @Autowired
    DatabaseHelper dbHelper;

    @Autowired
    public void setRepo(InfoStocksRepository repo) { this.repo = repo; }

    @Autowired
    public void setHelper(DatabaseHelper dbHelper) { this.dbHelper = dbHelper; }

    @Override
    public List<InfoStocks> getStocks() {
        return repo.findAll();
    }

    @Override
    public InfoStocks getInfoStocksByName(String name) {
        return null;
    }

    @Override
    public void addToInfoStocks(InfoStocks stocks) {

    }

    @Override
    public void deleteInfoStocks(String name) {

    }

    @Override
    public void updateInfoStocks(InfoStocks stocks) {

    }

    @Override
    public List<InfoStocks> getTimeSeries(Map<String, String> parameters) {
        String symbol = parameters.get("symbol");
        Date min = getEarliestDataForStock(symbol);
        Date max =getLatestDataForStock(symbol);

        List<InfoStocks> result = null;

        if (min == null) {
            // we don't have data for this stock
            repo.saveAllAndFlush(dbHelper.fetchTimeSeries(parameters));
            result = repo.findBySymbol(symbol);
        } else if (parameters.containsKey("start_date") && parameters.containsKey("end_date")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date start_date;
            Date end_date;
            try {
                start_date =dateFormat.parse(parameters.get("start_date"));
                end_date =dateFormat.parse(parameters.get("end_date"));
            } catch (Exception e) {
                return null;
            }

            if (max.compareTo(start_date) <= 0 || max.compareTo(end_date) < 0) {
                // We need to get more recent data
                parameters.put("start_date", dateFormat.format(max));
                List<InfoStocks> temp = dbHelper.fetchTimeSeries(parameters);
                repo.saveAllAndFlush(temp);
            }
            if (min.compareTo(start_date) > 0 || min.compareTo(end_date) >= 0) {
                // We need to get older data
                parameters.put("end_date", dateFormat.format(min));
                parameters.put("start_date", dateFormat.format(start_date));
                List<InfoStocks> temp = dbHelper.fetchTimeSeries(parameters);
                repo.saveAllAndFlush(temp);
            }
            result = repo.findInInterval(symbol, dateFormat.format(start_date), dateFormat.format(end_date));
        }
        return result;
    }

    private Date getEarliestDataForStock(String symbol) {
        InfoStocks record = repo.min(symbol);
        return (record == null)? null : record.getDateTime();
    }

    private Date getLatestDataForStock(String symbol) {
        InfoStocks record = repo.max(symbol);
        return (record == null)? null : record.getDateTime();
    }
}