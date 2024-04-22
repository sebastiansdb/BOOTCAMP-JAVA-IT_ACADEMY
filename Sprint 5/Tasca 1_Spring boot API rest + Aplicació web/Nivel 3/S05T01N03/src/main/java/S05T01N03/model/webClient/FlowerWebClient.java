package S05T01N03.model.webClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
@Configuration
public class FlowerWebClient {

    @Bean
    public WebClient getFlowerWebClient () {
        return WebClient.builder().baseUrl("http://localhost:9001").build();    // FlowerWebClient
    }
}
