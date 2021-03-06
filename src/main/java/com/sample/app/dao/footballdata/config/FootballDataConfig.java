package com.sample.app.dao.footballdata.config;

import com.sample.app.dao.footballdata.client.FootballDataHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FootballDataConfig {

  @Value("${football-data.api-token}")
  private String apiToken;

  @Bean
  public FootballDataHttpClient footballDataHttpClient() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("X-Auth-Token", apiToken);
    return new FootballDataHttpClient(new RestTemplate(), httpHeaders);
  }
}
