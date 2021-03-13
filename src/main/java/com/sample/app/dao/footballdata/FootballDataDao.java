package com.sample.app.dao.footballdata;

import com.sample.app.dao.footballdata.client.FootballDataHttpClient;
import com.sample.app.dao.footballdata.entity.GetMatchesResponse;
import com.sample.app.dao.footballdata.filter.MatchFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FootballDataDao {

  private final FootballDataHttpClient httpClient;

  public FootballDataDao(FootballDataHttpClient httpClient) {
    this.httpClient = httpClient;
  }

  public ResponseEntity<String> getPlayer(int playerId) {
    return httpClient.get("players/" + playerId, String.class);
  }

  public ResponseEntity<GetMatchesResponse> getMatches(int competitionId, MatchFilter matchFilter) {
    String queryString = Optional.ofNullable(matchFilter).map(MatchFilter::toQueryString).orElse("");
    return httpClient.get("competitions/" + competitionId + "/matches" + queryString, GetMatchesResponse.class);
  }
}
