package com.conygre.spring.boot.repository;

import com.conygre.spring.boot.entities.AccountDetails;
import com.conygre.spring.boot.repos.AccountDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes=com.conygre.spring.boot.AppConfig.class)
@TestPropertySource(locations = "classpath:application-test.properties") // this is only needed because swagger breaks tests
public class TestAccountDetailsRepository {
    @Autowired
    private TestEntityManager manager;
    @Autowired
    private AccountDetailsRepository repo;

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

    @Test
    public void canRetrieveADById() {
        Optional<AccountDetails> account = repo.findById(accountDetailsId);
        assertTrue(account.isPresent());

        AccountDetails temp = account.get();
        assertEquals(temp.getId(), accountDetailsId);

    }

    @Test
    public void canHandleNotFoundAD() {
        Optional<AccountDetails> account = repo.findById(invalidId);
        assertFalse(account.isPresent());
    }

    @Test
    public void canRetrieveADByUserId() {
        Optional<AccountDetails> account = repo.findByUserId(userId);
        assertTrue(account.isPresent());

        AccountDetails temp = account.get();
        assertEquals(temp.getUserId(), userId);
    }
}
