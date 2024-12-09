package com.conygre.spring.boot.controller;

import com.conygre.spring.boot.controllers.BoughtStocksController;
import com.conygre.spring.boot.entities.BoughtStocks;
import com.conygre.spring.boot.repos.BoughtStocksRepository;
import com.conygre.spring.boot.service.BoughtStocksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest // use an in memory database
@ContextConfiguration(classes=com.conygre.spring.boot.AppConfig.class)
@TestPropertySource(locations = "classpath:application-test.properties") // this is only needed because swagger breaks tests
public class TestBoughtStocksControllerUnitTest {
    //@TestConfiguration
    // Comment out above to prevent using mocks with tests other than first two
    protected static class Config {
        @Bean
        @Primary
        public BoughtStocksRepository repo() {
            return mock(BoughtStocksRepository.class);
        }

        @Bean
        @Primary
        public BoughtStocksService service() {
            BoughtStocks stock = new BoughtStocks();
            List<BoughtStocks> stocks = new ArrayList<>();
            stocks.add(stock);

            BoughtStocksService service = mock(BoughtStocksService.class);
            when(service.getStocks()).thenReturn(stocks);
            return service;
        }

        @Bean
        @Primary
        public BoughtStocksController controller() {
            return new BoughtStocksController();
        }
    }
    @Autowired
    private BoughtStocksController controller;

    @Disabled
    @Test
    public void testFindAll() {
        Iterable<BoughtStocks> stocks = controller.findAll();
        Stream<BoughtStocks> stream = StreamSupport.stream(stocks.spliterator(), false);
        assertThat(stream.count(), equalTo(1L));
    }

}
