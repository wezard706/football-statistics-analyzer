package com.sample.app.dao.footballdata.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class FootballDataHttpClient {

  private final static Logger logger = LoggerFactory.getLogger(FootballDataHttpClient.class);

  private final RestTemplate restTemplate;

  private static final String BASE_URL = "https://api.football-data.org/v2/";

  public FootballDataHttpClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public <E> ResponseEntity<E> get(String path, HttpEntity<?> httpEntity, Class<E> responseType) {
    String url = BASE_URL + path;
    logger.info("get request: url=" + url);
    return restTemplate.exchange(url, HttpMethod.GET, httpEntity, responseType);
  }
}
