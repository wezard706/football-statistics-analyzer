package com.sample.app.dao

import com.sample.app.Application
import com.sample.app.dao.footballdata.FootballDataDao
import com.sample.app.dao.footballdata.client.FootballDataHttpClient
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer, classes = [Application])
class FootballDataDaoSTest extends Specification {

  @Autowired
  private FootballDataDao target

  @SpringBean
  private FootballDataHttpClient footballDataHttpClient = Mock()

  @Unroll
  def "getMatches"() {
    // FIXME 実装する
  }
}