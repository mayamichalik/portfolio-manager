package com.conygre.spring.boot.integration;

import com.conygre.spring.boot.controllers.AccountDetailsController;
import com.conygre.spring.boot.entities.AccountDetails;
import com.conygre.spring.boot.service.AccountDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes=com.conygre.spring.boot.AppConfig.class)
@TestPropertySource(locations = "classpath:application-test.properties") // this is only needed because swagger breaks tests
public class TestAccountDetailsIntegration {
    @Autowired
    private AccountDetailsController controller;
    @Autowired
    private TestEntityManager manager;
    @Autowired
    AccountDetailsService accountService;
    private int accountDetailsId;
    private int invalidId;
    private int userId;

    @BeforeEach
    public  void setupDatabaseEntryForReadOnlyTests() {
        AccountDetails ad = new AccountDetails(100.1, "CAD", 1);
        AccountDetails result = manager.persist(ad);
        accountDetailsId = result.getId();
        invalidId = accountDetailsId + 1;
        userId = result.getUserId();

    }

    // Integration test - service and data layer
    @Test
    public void compactDiscServiceCanReturnAccounts() {
        Iterable<AccountDetails> discs = accountService.getAccounts();
        Stream<AccountDetails> stream = StreamSupport.stream(discs.spliterator(), false);
        Optional<AccountDetails> firstAcc = stream.findFirst();
        assertEquals(firstAcc.get().getId(), accountDetailsId);
        assertEquals(firstAcc.get().getCurrency(), "CAD");

    }

    // Integration test - controller and service layer
    @Test
    public void controllerCanReturnCDById() {
        ResponseEntity responseEntity = controller.findAdById(accountDetailsId);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        AccountDetails ad = (AccountDetails) responseEntity.getBody();
        assertEquals(ad.getId(), accountDetailsId);
    }
}
