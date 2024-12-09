package com.conygre.spring.boot.controllers;

import com.conygre.spring.boot.entities.InfoStocks;
import com.conygre.spring.boot.service.InfoStocksService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/InfoStocks")
public class InfoStocksController {

    @Autowired
    private InfoStocksService service;

    private static Logger logger = LogManager.getLogger(InfoStocksController.class);

    @RequestMapping(method = RequestMethod.GET)
    public List<InfoStocks> findAll() {
        return service.getStocks();
    }

    @GetMapping(value = "/{symbol}")
    public ResponseEntity<List<InfoStocks>> findStockInfoBySymbol(@PathVariable("symbol") String symbol,
        @RequestParam(required = false) String start_date, @RequestParam(required = false) String end_date){

        Map<String, String> parameters = new HashMap<>();
        parameters.put("symbol", symbol);
        parameters.put("interval", "1day");

        if (start_date != null) parameters.put("start_date", start_date);
        if (end_date != null) parameters.put("end_date", end_date);

        List<InfoStocks> data = service.getTimeSeries(parameters);

        if (data == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{name}")
    public void deleteInfoStocks(@PathVariable("name") String name) {
        service.deleteInfoStocks(name);
    }

    @PostMapping()
    public void addInfoStocks(@RequestBody InfoStocks stocks) {
        service.addToInfoStocks(stocks);
    }

    @PutMapping()
    public void updateInfoStocks(@RequestBody InfoStocks stocks) {
        service.updateInfoStocks(stocks);
    }
}