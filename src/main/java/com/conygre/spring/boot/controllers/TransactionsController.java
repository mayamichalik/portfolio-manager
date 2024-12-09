package com.conygre.spring.boot.controllers;

import com.conygre.spring.boot.entities.AccountDetails;
import com.conygre.spring.boot.entities.BoughtStocks;
import com.conygre.spring.boot.helpers.Transaction;
import com.conygre.spring.boot.service.AccountDetailsService;
import com.conygre.spring.boot.service.BoughtStocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Transactional(propagation = Propagation.REQUIRED)
@RequestMapping("/api/transaction")
public class TransactionsController {
    @Autowired
    private AccountDetailsService accountDetailsService;
    @Autowired
    private BoughtStocksService boughtStocksService;

    @PostMapping()
    public void executeTransaction(@RequestBody Transaction transaction) {
        if (transaction != null) {
            AccountDetails account = transaction.getAccount();
            BoughtStocks stock = transaction.getStock();
            String symbol = stock.getTicker_symbol();

            this.accountDetailsService.updateAccountDetails(account);
            if (stock.getVolume() == 0) {
                this.boughtStocksService.deleteBoughtStocks(symbol);
            } else if (this.boughtStocksService.getBoughtStocksBySymbol(symbol) != null) {
                this.boughtStocksService.updateBoughtStocks(stock);
            } else {
                this.boughtStocksService.addToBoughtStocks(stock);
            }
        }
    }

}
