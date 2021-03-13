package com.sample.app.dao.line.enums;

public enum MessageType {

  TEXT("text");

  private String value;

  MessageType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
