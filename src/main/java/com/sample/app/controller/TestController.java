package com.sample.app.controller;

import com.sample.app.service.FootballInfoSendService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TestController {

  private final FootballInfoSendService footballInfoSendService;

  public TestController(FootballInfoSendService footballInfoSendService) {
    this.footballInfoSendService = footballInfoSendService;
  }

  @GetMapping("/")
  public String printMessage() {
    System.out.println("hello");
    return "こんにちは！";
  }

  @PostMapping("/footballInfoSendService/sendUpcomingMatches")
  public void test() {
    footballInfoSendService.sendUpcomingMatches();
  }
}
