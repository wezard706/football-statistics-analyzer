package com.sample.app.dao;

import com.sample.app.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
public class FootballDataDao {

  private final RestTemplate restTemplate;

  private static final String URL = "https://api.football-data.org/v2/players";

  @Autowired
  public FootballDataDao(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public ResponseEntity<Player> getPlayer(String id) {
    UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(URL).path(id);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("X-Auth-Token", "36350698d5bf4b41aca0094ddfddffe1");
    HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
    return restTemplate.exchange(uriComponentsBuilder.build().toUri(), HttpMethod.GET, httpEntity, Player.class);
  }
}
