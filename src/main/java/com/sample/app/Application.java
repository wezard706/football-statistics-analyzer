package com.sample.app;

import com.sample.app.service.FootballInfoSendService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

  private final FootballInfoSendService footballInfoSendService;

  Application(FootballInfoSendService footballInfoSendService) {
    this.footballInfoSendService = footballInfoSendService;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) {
    footballInfoSendService.sendUpcomingMatches();
  }
}