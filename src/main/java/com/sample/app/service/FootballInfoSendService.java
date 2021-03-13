package com.sample.app.service;

import com.sample.app.dao.footballdata.entity.FootballDataGetMatchesDto;
import com.sample.app.dao.footballdata.entity.Match;
import com.sample.app.dao.footballdata.entity.MatchFilter;
import com.sample.app.dao.line.LineDao;
import com.sample.app.dao.line.entity.LinePushMessageDto;
import com.sample.app.dao.line.enums.MessageType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * サッカーの情報をLINEに送信するService
 */
public class FootballInfoSendService {

  private final FootballDataService footballDataService;

  private final LineDao lineDao;

  private final String userId;

  public FootballInfoSendService(FootballDataService footballDataService, LineDao lineDao, String userId) {
    this.footballDataService = footballDataService;
    this.lineDao = lineDao;
    this.userId = userId;
  }

  public void sendUpcomingMatches() {
    // プレミアリーグの直近予定の試合を取得
    LocalDate dateFrom = LocalDate.now();
    MatchFilter matchFilter = new MatchFilter.Builder().dateFrom(dateFrom).dateTo(dateFrom.plusDays(7)).build();
    FootballDataGetMatchesDto footballDataGetMatchesDto = new FootballDataGetMatchesDto(2021, matchFilter);
    List<Match> upcomingMatches = footballDataService.getMatches(footballDataGetMatchesDto);

    // Lineに送信
    List<LinePushMessageDto.Message> messages = new ArrayList<>();
    messages.add(new LinePushMessageDto.Message(MessageType.TEXT, makeMessage(upcomingMatches)));
    LinePushMessageDto linePushMessageDto = new LinePushMessageDto(userId, messages);
    lineDao.pushMessage(linePushMessageDto);
  }

  private String makeMessage(List<Match> matches) {
    StringBuilder sb = new StringBuilder();
    for (Match match : matches) {
      sb.append(String.format("%s, %s vs %s\n", match.getUtcDate(), match.getHomeTeam().getName(), match.getAwayTeam().getName()));
    }
    return sb.toString();
  }
}
