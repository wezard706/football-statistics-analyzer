package com.sample.app.dao.line.entity;

import com.sample.app.dao.line.enums.MessageType;
import lombok.Getter;

import java.util.List;

@Getter
public class LinePushMessageDto {

  private String to;

  private List<Message> messages;

  public LinePushMessageDto(String to, List<Message> messages) {
    this.to = to;
    this.messages = messages;
  }

  @Getter
  public static class Message {

    private MessageType type;

    private String text;

    public Message(MessageType type, String text) {
      this.type = type;
      this.text = text;
    }

    public String getType() {
      return this.type.getValue();
    }
  }
}
