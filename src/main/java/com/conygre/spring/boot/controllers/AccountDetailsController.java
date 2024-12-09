package com.conygre.spring.boot.controllers;

import com.conygre.spring.boot.entities.AccountDetails;
import com.conygre.spring.boot.service.AccountDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/accountdetails")
public class AccountDetailsController {

    @Autowired
    private AccountDetailsService service;

    private static Logger logger = LogManager.getLogger(AccountDetailsController.class);

    @RequestMapping(method = RequestMethod.GET)
    public List<AccountDetails> findAll() {
        return service.getAccounts();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDetails> findAdById(@PathVariable("id") int id) {
        AccountDetails accountDetails = service.getAccountDetailsById(id);
        if (accountDetails == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(accountDetails, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAd(@PathVariable("id") int id) {
        service.deleteAccountDetail(id);
    }

    @PostMapping()
    public void addAd(@RequestBody AccountDetails acc) {
        service.addToAccounts(acc);
    }

    @PutMapping()
    public void updateAd(@RequestBody AccountDetails acc) {
        service.updateAccountDetails(acc);
    }
}
