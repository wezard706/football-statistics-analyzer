package com.sample.app.dao.footballdata.filter;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class MatchFilter {

  private LocalDate dateFrom;

  private LocalDate dateTo;

  private String stage;

  private String status;

  private String matchDay;

  private String group;

  private String season;

  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  private MatchFilter(Builder builder) {
    this.dateFrom = builder.dateFrom;
    this.dateTo = builder.dateTo;
    this.stage = builder.stage;
    this.status = builder.status;
    this.matchDay = builder.matchDay;
    this.group = builder.group;
    this.season = builder.season;
  }

  public String toQueryString() {
    StringBuilder sb = new StringBuilder();
    if (Objects.nonNull(dateFrom)) {
      addQuerySet("dateFrom", dateFrom.format(dateTimeFormatter), sb);
    }
    if (Objects.nonNull(dateTo)) {
      addQuerySet("dateTo", dateTo.format(dateTimeFormatter), sb);
    }
    if (!StringUtils.isEmpty(stage)) {
      addQuerySet("stage", stage, sb);
    }
    if (!StringUtils.isEmpty(status)) {
      addQuerySet("status", status, sb);
    }
    if (!StringUtils.isEmpty(matchDay)) {
      addQuerySet("matchday", matchDay, sb);
    }
    if (!StringUtils.isEmpty(group)) {
      addQuerySet("group", group, sb);
    }
    if (!StringUtils.isEmpty(season)) {
      addQuerySet("season", season, sb);
    }
    return sb.toString();
  }

  private void addQuerySet(String queryKey, String queryValue, StringBuilder sb) {
    if (sb.length() == 0) {
      sb.append("?");
    } else {
      sb.append("&");
    }
    sb.append(queryKey).append("=").append(queryValue);
  }

  public static class Builder {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String stage;
    private String status;
    private String matchDay;
    private String group;
    private String season;

    public Builder dateFrom(LocalDate dateFrom) {
      this.dateFrom = dateFrom;
      return this;
    }

    public Builder dateTo(LocalDate dateTo) {
      this.dateTo = dateTo;
      return this;
    }

    public Builder stage(String stage) {
      this.stage = stage;
      return this;
    }

    public Builder status(String status) {
      this.status = status;
      return this;
    }

    public Builder matchDay(String matchDay) {
      this.matchDay = matchDay;
      return this;
    }

    public Builder group(String group) {
      this.group = group;
      return this;
    }

    public Builder season(String season) {
      this.season = season;
      return this;
    }

    public MatchFilter build() {
      return new MatchFilter(this);
    }
  }
}