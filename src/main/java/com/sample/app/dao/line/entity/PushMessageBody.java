package com.sample.app.dao.line.entity;

import lombok.Getter;

import java.util.List;

@Getter
public class PushMessageBody {

  private String to;

  private List<Message> messages;

  public PushMessageBody(String to, List<Message> messages) {
    this.to = to;
    this.messages = messages;
  }

  @Getter
  public static class Message {

    public Message(String type, String text) {
      this.type = type;
      this.text = text;
    }

    private String type;

    private String text;
  }
}
