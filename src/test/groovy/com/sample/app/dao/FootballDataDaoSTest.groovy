package com.sample.app.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
class FootballDataDaoSTest extends Specification {

  @Autowired
  private FootballDataDao footballDataDao

  def "getPlayer"() {
    expect:
    def result = footballDataDao.getPlayer("44")
  }
}
