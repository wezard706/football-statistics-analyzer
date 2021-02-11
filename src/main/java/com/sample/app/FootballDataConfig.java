package com.sample.app;

import com.sample.app.dao.client.FootballDataHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FootballDataConfig {

  @Value("${football-data.http-host}")
  private String httpHost;

  @Value("${football-data.api-token}")
  private String apiToken;

  @Bean
  public FootballDataHttpClient footballDataHttpClient() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("X-Auth-Token", apiToken);
    return new FootballDataHttpClient(new RestTemplate(), httpHost, httpHeaders);
  }
}
