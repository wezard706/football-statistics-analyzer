package com.sample.app.dao

import com.sample.app.Application
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer, classes = [Application])
class FootballDataDaoSTest extends Specification {

  @Autowired
  private FootballDataDao footballDataDao

  def "getPlayer"() {
    expect:
    def result = footballDataDao.getPlayer("44")
    println(result)
  }
}
