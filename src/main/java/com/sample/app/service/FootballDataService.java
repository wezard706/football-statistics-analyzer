package com.sample.app.service;

import com.sample.app.BeanValidator;
import com.sample.app.dao.footballdata.FootballDataDao;
import com.sample.app.dao.footballdata.entity.GetMatchesResponse;
import com.sample.app.dao.footballdata.entity.Match;
import com.sample.app.dao.footballdata.filter.MatchFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FootballDataService {

  private final FootballDataDao footballDataDao;

  public FootballDataService(FootballDataDao footballDataDao) {
    this.footballDataDao = footballDataDao;
  }

  public List<Match> getMatches(int competitionId, MatchFilter matchFilter) {
    ResponseEntity<GetMatchesResponse> response = footballDataDao.getMatches(competitionId, matchFilter);
    if (response.hasBody()) {
      BeanValidator.validate(response.getBody());
      return response.getBody().getMatches();
    }
    return Collections.emptyList();
  }
}
