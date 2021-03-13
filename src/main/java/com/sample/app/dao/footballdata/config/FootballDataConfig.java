package com.sample.app.dao.footballdata.config;

import com.sample.app.dao.footballdata.FootballDataDao;
import com.sample.app.dao.footballdata.client.FootballDataHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FootballDataConfig {

  @Value("${football-data.api-token}")
  private String apiToken;

  @Bean
  public FootballDataHttpClient footballDataHttpClient() {
    return new FootballDataHttpClient(new RestTemplate());
  }

  @Bean
  public FootballDataDao footballDataDao(FootballDataHttpClient footballDataHttpClient) {
    return new FootballDataDao(footballDataHttpClient, apiToken);
  }
}
