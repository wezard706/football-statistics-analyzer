package com.sample.app.dao

import com.sample.app.Application
import com.sample.app.dao.client.FootballDataHttpClient
import com.sample.app.dao.footballdata.FootballDataDao
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

  def "getPlayer"() {
    setup:
    def playerId = "44"
    1 * footballDataHttpClient.get(_ as String, _ as Class<Object>) >> {
      def args1 = it.get(0)
      args1 == "players/" + playerId
      def args2 = it.get(1)
      args2 == String.class
      return
    }

    when:
    target.getPlayer(playerId)

    then:
    notThrown(Exception)
  }

  @Unroll
  def "getPlayer NG(#testCase)"() {
    setup:
    0 * footballDataHttpClient.get(_ as String, _ as Class<Object>)

    when:
    target.getPlayer(playerId)

    then:
    thrown(NullPointerException)

    where:
    testCase         | playerId
    "playerId=empty" | ""
    "playerId=null"  | null
  }

  @Unroll
  def "getMatches"() {
    // FIXME 実装する
  }
}