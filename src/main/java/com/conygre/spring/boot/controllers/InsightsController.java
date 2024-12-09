package com.conygre.spring.boot.controllers;

import com.conygre.spring.boot.helpers.Insights;
import com.conygre.spring.boot.service.InsightsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/insights")
public class InsightsController {
        @Autowired
        private InsightsService service;
        private static Logger logger = LogManager.getLogger(AccountDetailsController.class);

        @GetMapping(value = "/{symbol}")
        public ResponseEntity<Insights> getInsightsBySymbol(@PathVariable("symbol") String symbol) {
            Insights insights = service.getInsightsForStock(symbol);
            if (insights == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity<>(insights, HttpStatus.OK);
            }
        }
}
