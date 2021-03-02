package com.sample.app;

import com.sample.app.dao.FootballDataDao;
import com.sample.app.dao.entity.GetMatchesResponse;
import com.sample.app.dao.entity.Match;
import com.sample.app.dao.filter.MatchFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;

import javax.validation.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@SpringBootApplication
public class Application implements CommandLineRunner {

  private final FootballDataDao footballDataDao;

  Application(FootballDataDao footballDataDao) {
    this.footballDataDao = footballDataDao;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) {
    // プレミアリーグの直近予定の試合
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.now();
    String dateFrom = localDate.format(dateTimeFormatter);
    String dateTo = localDate.plusDays(7).format(dateTimeFormatter);
    MatchFilter matchFilter = new MatchFilter.Builder().dateFrom(dateFrom).dateTo(dateTo).build();
    ResponseEntity<GetMatchesResponse> response = footballDataDao.getMatches("2021", matchFilter);
    if (response.hasBody()) {
      validate(response.getBody());

      for (Match match : response.getBody().getMatches()) {
        ZonedDateTime utcDateTime = ZonedDateTime.parse(match.getUtcDate());
        ZonedDateTime tokyoDateTime = utcDateTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        System.out.println(String.format("%s, %s vs %s", tokyoDateTime, match.getHomeTeam().getName(), match.getAwayTeam().getName()));
      }
    }
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