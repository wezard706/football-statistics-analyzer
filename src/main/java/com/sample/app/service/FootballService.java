package com.sample.app.service;

import com.sample.app.dao.footballdata.FootballDataDao;
import com.sample.app.dao.footballdata.entity.GetMatchesResponse;
import com.sample.app.dao.footballdata.entity.Match;
import com.sample.app.dao.footballdata.filter.MatchFilter;
import com.sample.app.dao.line.LineDao;
import com.sample.app.dao.line.entity.PushMessageBody;
import org.springframework.http.ResponseEntity;

import javax.validation.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class FootballService {

  private final FootballDataDao footballDataDao;

  private final LineDao lineDao;

  private final String userId;

  public FootballService(FootballDataDao footballDataDao, LineDao lineDao, String userId) {
    this.footballDataDao = footballDataDao;
    this.lineDao = lineDao;
    this.userId = userId;
  }

  public void sendUpcomingMatchesToLine() {
    List<Match> upcomingMatches = getUpcomingMatches();

    StringBuilder sb = new StringBuilder();
    for (Match match : upcomingMatches) {
      ZonedDateTime utcDateTime = ZonedDateTime.parse(match.getUtcDate());
      ZonedDateTime tokyoDateTime = utcDateTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
      sb.append(String.format("%s, %s vs %s\n", tokyoDateTime.toLocalDateTime(), match.getHomeTeam().getName(), match.getAwayTeam().getName()));
    }

    List<PushMessageBody.Message> messages = new ArrayList<>();
    messages.add(new PushMessageBody.Message("text", sb.toString()));
    lineDao.pushMessage(new PushMessageBody(userId, messages));
  }

  private List<Match> getUpcomingMatches() {
    // プレミアリーグの直近予定の試合
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.now();
    String dateFrom = localDate.format(dateTimeFormatter);
    String dateTo = localDate.plusDays(7).format(dateTimeFormatter);
    MatchFilter matchFilter = new MatchFilter.Builder().dateFrom(dateFrom).dateTo(dateTo).build();
    ResponseEntity<GetMatchesResponse> response = footballDataDao.getMatches("2021", matchFilter);
    if (response.hasBody()) {
      validate(response.getBody());
      return response.getBody().getMatches();
    }
    return Collections.emptyList();
  }

  private void validate(GetMatchesResponse getMatchesResponse) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<GetMatchesResponse>> violations = validator.validate(getMatchesResponse);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
