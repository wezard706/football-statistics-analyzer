package com.sample.app.service;

import com.sample.app.dao.footballdata.FootballDataDao;
import com.sample.app.dao.line.LineDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FootballServiceConfig {

  @Value("${line.user-id}")
  private String userId;

  @Bean
  public FootballService footballService(FootballDataDao footballDataDao, LineDao lineDao) {
    return new FootballService(footballDataDao, lineDao, userId);
  }
}
