package com.sample.app.dao.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class LineHttpClient {

  private static final Logger logger = LoggerFactory.getLogger(LineHttpClient.class);

  private final RestTemplate restTemplate;

  private final HttpHeaders httpHeaders;

  private static final String BASE_URL = "https://api.line.me/v2/";

  public LineHttpClient(RestTemplate restTemplate, HttpHeaders httpHeaders) {
    this.restTemplate = restTemplate;
    this.httpHeaders = httpHeaders;
  }

  public void post(String path) {
    String url = BASE_URL + path;
    logger.info("get request: url=" + url);
    restTemplate.exchange(BASE_URL + path, HttpMethod.POST, new HttpEntity<>(httpHeaders), String.class);
  }
}
