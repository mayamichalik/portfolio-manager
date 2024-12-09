package com.conygre.spring.boot.controller;

import com.conygre.spring.boot.controllers.AccountDetailsController;
import com.conygre.spring.boot.entities.AccountDetails;
import com.conygre.spring.boot.repos.AccountDetailsRepository;
import com.conygre.spring.boot.service.AccountDetailsService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes=com.conygre.spring.boot.AppConfig.class)
@TestPropertySource(locations = "classpath:application-test.properties") // this is only needed because swagger breaks tests
public class TestAccountDetailsController {
    // @TestConfiguration
    // NOTE: Comment out above to prevent using mocks with tests outside of this file
    protected static class Config {
        @Bean
        @Primary
        public AccountDetailsRepository repo() {
            return mock(AccountDetailsRepository.class);
        }

        @Bean
        @Primary
        public AccountDetailsService service() {
            AccountDetails ad = new AccountDetails();
            List<AccountDetails> ads = new ArrayList<>();
            ads.add(ad);

            AccountDetailsService service = mock(AccountDetailsService.class);
            when(service.getAccounts()).thenReturn(ads);
            when(service.getAccountDetailsById(1)).thenReturn(ad);
            return service;
        }

        @Bean
        @Primary
        public AccountDetailsController controller() {
            return new AccountDetailsController();
        }
    }
    @Autowired
    private AccountDetailsController controller;

    @Disabled
    @Test
    public void testFindAll() {
        Iterable<AccountDetails> cds = controller.findAll();
        Stream<AccountDetails> stream = StreamSupport.stream(cds.spliterator(), false);
        assertThat(stream.count(), equalTo(1L));
    }

    @Disabled
    @Test
    public void testCdById() {
        AccountDetails cd = controller.findAdById(1).getBody();
        assertNotNull(cd);
    }
}
