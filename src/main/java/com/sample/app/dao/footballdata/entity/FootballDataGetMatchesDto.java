package com.sample.app.dao.footballdata.entity;

import lombok.Getter;

@Getter
public class FootballDataGetMatchesDto {

  private int competitionId;

  private MatchFilter matchFilter;

  public FootballDataGetMatchesDto(int competitionId) {
    this.competitionId = competitionId;
  }

  public FootballDataGetMatchesDto(int competitionId, MatchFilter matchFilter) {
    this.competitionId = competitionId;
    this.matchFilter = matchFilter;
  }
}
