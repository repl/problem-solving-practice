package com.example.distributedsearch

import org.springframework.boot.{SpringApplication, autoconfigure}
import org.springframework.context.annotation.{Bean, Configuration}

@autoconfigure.SpringBootApplication
class DistributedSearchApplication

object DistributedSearchApplication {
  def main(args: Array[String]): Unit =
    SpringApplication.run(classOf[DistributedSearchApplication], args: _*)
}

@Configuration
class SearchConfiguration {
  @Bean
  def scalaJacksonModule(): com.fasterxml.jackson.databind.Module =
    com.fasterxml.jackson.module.scala.DefaultScalaModule

  @Bean(destroyMethod = "close")
  def searchCoordinator(): SearchCoordinator = SearchCoordinator.localDemo()
}
