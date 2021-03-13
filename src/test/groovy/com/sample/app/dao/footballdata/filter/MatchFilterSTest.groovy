package com.sample.app.dao.footballdata.filter

import com.sample.app.Application
import com.sample.app.dao.footballdata.entity.MatchFilter
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer, classes = [Application])
class MatchFilterSTest extends Specification {

  @Unroll
  def "getMatches(#testCase)"() {
    when:
    def result = matchFilter.toQueryString()

    then:
    result == expectation

    where:
    testCase     | matchFilter   | expectation
    "0 filter"   | emptyFilter() | ""
    "1 filter"   | oneFilter()   | "?dateFrom=2020-01-01"
    "2 filter"   | twoFilter()   | "?dateFrom=2020-01-01&dateTo=2020-01-01"
    "all filter" | allFilter()   | "?dateFrom=2020-01-01&dateTo=2020-01-01&stage=REGULAR_SEASON&status=FINISHED&matchday=21&group=Regular Season&season=2020"
  }

  def emptyFilter() {
    return new MatchFilter.Builder().build()
  }

  def oneFilter() {
    return new MatchFilter.Builder().dateFrom("2020-01-01").build()
  }

  def twoFilter() {
    return new MatchFilter.Builder().dateFrom("2020-01-01").dateTo("2020-01-01").build()
  }

  def allFilter() {
    return new MatchFilter.Builder().dateFrom("2020-01-01").dateTo("2020-01-01").stage("REGULAR_SEASON").status("FINISHED").matchDay("21").group("Regular Season").season("2020").build()
  }
}
