package com.conygre.spring.boot.controllers;

import com.conygre.spring.boot.entities.BoughtStocks;
import com.conygre.spring.boot.entities.Stock;
import com.conygre.spring.boot.service.BoughtStocksService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/BoughtStocks")
public class BoughtStocksController {

    @Autowired
    private BoughtStocksService service;

    private static Logger logger = LogManager.getLogger(BoughtStocksController.class);

    @RequestMapping(method = RequestMethod.GET)
    public List<BoughtStocks> findAll() {
        return service.getStocks();
    }

    @GetMapping(value = "/{symbol}")
    public ResponseEntity<BoughtStocks> findStocksBySymbol(@PathVariable("symbol") String symbol){
        BoughtStocks stock = service.getBoughtStocksBySymbol(symbol);
        return (stock == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(stock, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{symbol}")
    public void deleteBoughtStocks(@PathVariable("symbol") String symbol) {
        service.deleteBoughtStocks(symbol);
    }

    @PostMapping()
    public void addBoughtStocks(@RequestBody BoughtStocks stocks) {
        service.addToBoughtStocks(stocks);
    }

    @PutMapping()
    public void updateBoughtStocks(@RequestBody BoughtStocks stocks) {
        service.updateBoughtStocks(stocks);
    }
}
