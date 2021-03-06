package com.sample.app;

import com.sample.app.dao.client.LineHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LineConfig {

  @Value("channel-access-token")
  private String channelAccessToken;

  @Bean
  public LineHttpClient lineHttpClient() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("Content-Type", "application/json");
    httpHeaders.set("Authorization", "Bearer " + channelAccessToken);
    return new LineHttpClient(new RestTemplate(), httpHeaders);
  }
}
