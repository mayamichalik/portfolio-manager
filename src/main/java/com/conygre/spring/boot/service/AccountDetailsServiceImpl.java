package com.conygre.spring.boot.service;

import com.conygre.spring.boot.entities.AccountDetails;
import com.conygre.spring.boot.repos.AccountDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AccountDetailsServiceImpl implements AccountDetailsService{

    @Autowired
    private AccountDetailsRepository repo;

	@Autowired
	public void setRepo(AccountDetailsRepository repo) {
		this.repo = repo;
	}

    @Override
    public List<AccountDetails> getAccounts() {
        return repo.findAll();
    }

    @Override
    public AccountDetails getAccountDetailsById(int id) {
        Optional<AccountDetails> result = repo.findById(id);
        return result.isPresent()? result.get() : null;
    }

    @Override
    public void addToAccounts(AccountDetails acc) {
        repo.save(acc);
    }

    @Override
    public void deleteAccountDetail(int id) {
        repo.deleteById(id);
    }

    @Override
    public void updateAccountDetails(AccountDetails acc) {
        if (getAccountDetailsById(acc.getId()) != null) {
            repo.save(acc);
        }
    }

    @Override
    public AccountDetails getAccountDetailsByUserId(Integer userId) {
        Optional<AccountDetails> result = repo.findByUserId(userId);
        return result.isPresent()? result.get() : null;
    }
}
