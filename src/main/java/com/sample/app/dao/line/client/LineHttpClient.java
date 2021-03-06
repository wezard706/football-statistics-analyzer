package com.sample.app.dao.line.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class LineHttpClient {

  private static final Logger logger = LoggerFactory.getLogger(LineHttpClient.class);

  private final RestTemplate restTemplate;

  private static final String BASE_URL = "https://api.line.me/v2/";

  public LineHttpClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public void post(String path, HttpEntity<?> httpEntity) {
    String url = BASE_URL + path;
    logger.info("post request: url=" + url);
    restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
  }
}
