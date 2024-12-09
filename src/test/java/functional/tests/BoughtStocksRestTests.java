package functional.tests;

import com.conygre.spring.boot.entities.BoughtStocks;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class BoughtStocksRestTests {
    private RestTemplate template = new RestTemplate();

    @Test
    public void testFindAll() {
        List<BoughtStocks> stocks = template.getForObject("http://localhost:8080/api/BoughtStocks", List.class);
        assertThat(stocks.size(),  greaterThan(1));
    }

}
