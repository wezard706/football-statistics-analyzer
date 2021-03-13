package com.sample.app.service;

import com.sample.app.BeanValidator;
import com.sample.app.dao.footballdata.FootballDataDao;
import com.sample.app.dao.footballdata.entity.FootballDataGetMatchesDto;
import com.sample.app.dao.footballdata.entity.GetMatchesResponse;
import com.sample.app.dao.footballdata.entity.Match;
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

  public List<Match> getMatches(FootballDataGetMatchesDto footballDataGetMatchesDto) {
    ResponseEntity<GetMatchesResponse> response = footballDataDao.getMatches(footballDataGetMatchesDto);
    if (response.hasBody()) {
      BeanValidator.validate(response.getBody());
      return response.getBody().getMatches();
    }
    return Collections.emptyList();
  }
}
