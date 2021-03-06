package com.sample.app.dao.footballdata.entity;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class Team {

  @NotNull
  private int id;

  @NotNull
  private String name;
}
