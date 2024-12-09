package com.conygre.spring.boot.helpers;

import com.conygre.spring.boot.entities.AccountDetails;
import com.conygre.spring.boot.entities.BoughtStocks;

public class Transaction {
    private AccountDetails account;
    private BoughtStocks stock;

    public Transaction(AccountDetails account, BoughtStocks stock) {
        this.account = account;
        this.stock = stock;
    }

    public AccountDetails getAccount() {
        return account;
    }

    public void setAccount(AccountDetails account) {
        this.account = account;
    }

    public BoughtStocks getStock() {
        return stock;
    }

    public void setStock(BoughtStocks stock) {
        this.stock = stock;
    }
}
