package functional.tests;

import com.conygre.spring.boot.entities.AccountDetails;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountDetailsRestTests {
    private RestTemplate template = new RestTemplate();

    @Test
    public void testFindAll() {
        List<AccountDetails> cds = template.getForObject("http://localhost:8080/api/accountdetails", List.class);
        assertThat(cds.size(),  equalTo(1));
    }

    @Test
    public void testCdById() {
        AccountDetails ad = template.getForObject
                ("http://localhost:8080/api/accountdetails/1", AccountDetails.class);
        assertEquals(ad.getId(), 1);
        assertEquals(ad.getCurrency(), "USD");
        assertEquals(ad.getBalance(), 100000);
    }

}
