package com.sample.app;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.sample.app.service.FootballInfoSendService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@LineMessageHandler
public class Application {

  private final FootballInfoSendService footballInfoSendService;

  Application(FootballInfoSendService footballInfoSendService) {
    this.footballInfoSendService = footballInfoSendService;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @EventMapping
  public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
    System.out.println("event: " + event);
    final String userId = event.getSource().getUserId();
    System.out.println("userId=" + userId);
    footballInfoSendService.sendUpcomingMatches(userId);
    final String originalMessageText = event.getMessage().getText();
    return new TextMessage(originalMessageText);
  }

  @EventMapping
  public void handleDefaultMessageEvent(Event event) {
    System.out.println("event: " + event);
  }
}