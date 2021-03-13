package com.sample.app.dao.line.config;

import com.sample.app.dao.line.LineDao;
import com.sample.app.dao.line.client.LineHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LineConfig {

  @Value("${line.channel-access-token}")
  private String channelAccessToken;

  @Bean
  public LineHttpClient lineHttpClient() {
    return new LineHttpClient(new RestTemplate());
  }

  @Bean
  public LineDao lineDao(LineHttpClient lineHttpClient) {
    return new LineDao(lineHttpClient, channelAccessToken);
  }
}
