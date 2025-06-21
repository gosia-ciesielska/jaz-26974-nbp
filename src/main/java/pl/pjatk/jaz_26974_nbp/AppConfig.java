package pl.pjatk.jaz_26974_nbp;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("JAZ_26974_NBP API")
                        .version("1.0.0"));
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
