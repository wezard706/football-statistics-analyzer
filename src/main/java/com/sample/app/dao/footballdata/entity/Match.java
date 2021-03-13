package com.sample.app.dao.footballdata.entity;

import com.sample.app.dao.footballdata.enums.Status;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
public class Match {

  @NotNull
  private int id;

  @NotNull
  private Season season;

  @NotNull
  private LocalDateTime utcDate;

  @NotNull
  private Status status;

  @NotNull
  private int matchday;

  @NotNull
  private String stage;

  @NotNull
  private String group;

  @NotNull
  private String lastUpdated;

  @Valid
  @NotNull
  private Score score;

  @NotNull
  private Team homeTeam;

  @NotNull
  private Team awayTeam;

  @Getter
  static class Season {

    @NotNull
    private int id;

    @NotNull
    private String startDate;

    @NotNull
    private String endDate;

    @NotNull
    private int currentMatchday;
  }

  @Getter
  public static class Score {

    private String winner;

    @NotNull
    private String duration;

    @Valid
    @NotNull
    private ScoreDetail fullTime;

    @Valid
    @NotNull
    private ScoreDetail halfTime;

    @Valid
    @NotNull
    private ScoreDetail extraTime;

    @Getter
    public static class ScoreDetail {

      private Integer homeTeam;

      private Integer awayTeam;
    }
  }

  private void setUtcDate(String strUtcDate) {
    this.utcDate = ZonedDateTime.parse(strUtcDate).withZoneSameInstant(ZoneId.of("Asia/Tokyo")).toLocalDateTime();
  }
}
