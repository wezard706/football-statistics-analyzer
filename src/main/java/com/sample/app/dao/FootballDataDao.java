package com.sample.app.dao;

import com.sample.app.dao.client.FootballDataHttpClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class FootballDataDao {

  private final FootballDataHttpClient httpClient;

  public FootballDataDao(FootballDataHttpClient httpClient) {
    this.httpClient = httpClient;
  }

  public ResponseEntity<String> getPlayer(String id) {
    return httpClient.get("players/" + id, String.class);
  }
}
