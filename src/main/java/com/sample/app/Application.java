package com.sample.app;

import com.sample.app.service.FootballService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

  private final FootballService footballService;

  Application(FootballService footballService) {
    this.footballService = footballService;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) {
    footballService.sendUpcomingMatchesToLine();
  }
}