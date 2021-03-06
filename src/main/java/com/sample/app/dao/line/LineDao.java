package com.sample.app.dao.line;

import com.sample.app.dao.line.client.LineHttpClient;
import com.sample.app.dao.line.entity.PushMessageBody;
import org.springframework.stereotype.Repository;

@Repository
public class LineDao {

  private final LineHttpClient lineHttpClient;

  public LineDao(LineHttpClient lineHttpClient) {
    this.lineHttpClient = lineHttpClient;
  }

  public void pushMessage(PushMessageBody pushMessageBody) {
    this.lineHttpClient.post("bot/message/push", pushMessageBody);
  }
}
