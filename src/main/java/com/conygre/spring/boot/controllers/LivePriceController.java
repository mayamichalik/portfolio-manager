package com.conygre.spring.boot.controllers;

import com.conygre.spring.boot.entities.AccountDetails;
import com.conygre.spring.boot.helpers.StockPrice;
import com.conygre.spring.boot.service.AccountDetailsService;
import com.conygre.spring.boot.service.LiveDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/livedata")
public class LivePriceController {

    @Autowired
    private LiveDataService service;

    private static Logger logger = LogManager.getLogger(AccountDetailsController.class);

    @GetMapping(value = "/{symbol}")
    public ResponseEntity<StockPrice> findAdById(@PathVariable("symbol") String symbol) {
        StockPrice price = service.getLivePriceForStock(symbol);
        if (price == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(price, HttpStatus.OK);
        }
    }
}
