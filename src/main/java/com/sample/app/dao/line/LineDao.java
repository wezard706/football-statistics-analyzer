package com.sample.app.dao.line;

import com.sample.app.dao.line.client.LineHttpClient;
import com.sample.app.dao.line.entity.LinePushMessageDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class LineDao {

  private final LineHttpClient lineHttpClient;

  private final String channelAccessToken;

  public LineDao(LineHttpClient lineHttpClient, String channelAccessToken) {
    this.lineHttpClient = lineHttpClient;
    this.channelAccessToken = channelAccessToken;
  }

  public void pushMessage(LinePushMessageDto body) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("Content-Type", "application/json");
    httpHeaders.set("Authorization", "Bearer " + channelAccessToken);
    lineHttpClient.post("bot/message/push", new HttpEntity<>(body, httpHeaders));
  }
}
