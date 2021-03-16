package com.sample.app;

import com.sample.app.service.FootballInfoSendService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

  private final FootballInfoSendService footballInfoSendService;

  Application(FootballInfoSendService footballInfoSendService) {
    this.footballInfoSendService = footballInfoSendService;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}