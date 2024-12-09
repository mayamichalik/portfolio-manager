package com.conygre.spring.boot.service;

import com.conygre.spring.boot.entities.AccountDetails;

import java.util.List;

public interface AccountDetailsService {
    List<AccountDetails> getAccounts();

    AccountDetails getAccountDetailsById(int id);

    void addToAccounts(AccountDetails acc);

    void deleteAccountDetail(int id);

    void updateAccountDetails(AccountDetails acc);

    AccountDetails getAccountDetailsByUserId(Integer userId);
}
