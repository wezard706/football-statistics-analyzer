package com.sample.app.dao.footballdata.entity;

import lombok.Getter;

import javax.validation.Valid;
import java.util.List;

@Getter
public class GetMatchesResponse {

  @Valid
  private long count;

  @Valid
  private List<Match> matches;
}
