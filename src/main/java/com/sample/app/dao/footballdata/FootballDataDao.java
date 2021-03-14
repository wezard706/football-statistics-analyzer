package com.sample.app.dao.footballdata;

import com.sample.app.BeanValidator;
import com.sample.app.dao.footballdata.client.FootballDataHttpClient;
import com.sample.app.dao.footballdata.entity.FootballDataGetMatchesDto;
import com.sample.app.dao.footballdata.entity.GetMatchesResponse;
import com.sample.app.dao.footballdata.entity.Match;
import com.sample.app.dao.footballdata.entity.MatchFilter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FootballDataDao {

  private final FootballDataHttpClient httpClient;

  private final String apiToken;

  public FootballDataDao(FootballDataHttpClient httpClient, String apiToken) {
    this.httpClient = httpClient;
    this.apiToken = apiToken;
  }

  public List<Match> getMatches(FootballDataGetMatchesDto footballDataGetMatchesDto) {
    String queryString = Optional.ofNullable(footballDataGetMatchesDto.getMatchFilter()).map(MatchFilter::toQueryString).orElse("");
    String path = "competitions/" + footballDataGetMatchesDto.getCompetitionId() + "/matches" + queryString;
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("X-Auth-Token", apiToken);
    ResponseEntity<GetMatchesResponse> responseEntity = httpClient.get(path, new HttpEntity<>(httpHeaders), GetMatchesResponse.class);

    // バリデーション
    if (responseEntity.hasBody()) {
      BeanValidator.validate(responseEntity.getBody());
      return responseEntity.getBody().getMatches();
    }
    return Collections.emptyList();
  }
}
