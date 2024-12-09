package com.conygre.spring.boot.controllers;

import com.conygre.spring.boot.entities.Stock;
import com.conygre.spring.boot.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    StockService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Stock> findAll() { return service.getStocks(); }

    @GetMapping(value = "/{symbol}")
    public ResponseEntity<Stock> findStockBySymbol(@PathVariable("symbol") String symbol) {
        Stock stock = service.getStockBySymbol(symbol);
        return (stock == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(stock, HttpStatus.OK);
    }

    @PostMapping()
    public void addStock(@RequestBody Stock stock) { service.addToStocks(stock); }

}
