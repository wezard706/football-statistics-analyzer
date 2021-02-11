package com.sample.app.dao.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class FootballDataHttpClient {

  private final static Logger logger = LoggerFactory.getLogger(FootballDataHttpClient.class);

  private final RestTemplate restTemplate;

  private final String baseUri;

  private final HttpHeaders httpHeaders;

  public FootballDataHttpClient(RestTemplate restTemplate, String baseUri, HttpHeaders httpHeaders) {
    this.restTemplate = restTemplate;
    this.baseUri = String.format("https://%s/v2/", baseUri);
    this.httpHeaders = httpHeaders;
  }

  public <E> ResponseEntity<E> get(String path, Class<E> responseType) {
    logger.info("get request: uri=" + baseUri + path);
    return restTemplate.exchange(baseUri + path, HttpMethod.GET, new HttpEntity<>(httpHeaders), responseType);
  }
}
