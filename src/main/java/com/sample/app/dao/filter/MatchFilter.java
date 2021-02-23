package com.sample.app.dao.filter;

import org.springframework.util.StringUtils;

public class MatchFilter {

  private String dateFrom;

  private String dateTo;

  private String stage;

  private String status;

  private String matchDay;

  private String group;

  private String season;

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
    if (!StringUtils.isEmpty(dateFrom)) {
      addQuerySet("dateFrom", dateFrom, sb);
    }
    if (!StringUtils.isEmpty(dateTo)) {
      addQuerySet("dateTo", dateTo, sb);
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
    private String dateFrom;
    private String dateTo;
    private String stage;
    private String status;
    private String matchDay;
    private String group;
    private String season;

    public Builder dateFrom(String dateFrom) {
      this.dateFrom = dateFrom;
      return this;
    }

    public Builder dateTo(String dateTo) {
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
