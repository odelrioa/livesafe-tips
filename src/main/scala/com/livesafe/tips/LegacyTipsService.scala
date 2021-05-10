package com.livesafe.tips

import com.livesafe.tips.models.{Tip, TipType}
import org.joda.time.DateTime

import scala.concurrent.Future
import scala.io.Source

object LegacyTipsService {

  import scala.concurrent.ExecutionContext.Implicits.global

  def fetchLegacy1Tips: Future[List[Tip]] = Future {
    val lines = Source.fromResource("legacy-1-tips.csv").getLines()
    //Drops first line because it is the head of the csv
    lines.drop(1).map { line =>
      val Array(tipId, created, tipType, msg) = line.split(",").map(_.trim)
      Tip(
        tipId,
        new DateTime(created.toLong), //Assuming that always is a correct TimeStamp (Epoc)
        TipType.withName(tipType),
        msg
      )
    }.toList
  }
  def fetchLegacy2Tips: Future[List[Tip]] = Future {
    val lines = Source.fromResource("legacy-2-tips.csv").getLines()
    //Drops first line because it is the head of the csv
    lines.drop(1).map { line =>
      val Array(tipId, message, createdAt, tipType) = line.split(",").map(_.trim)
      Tip(
        tipId,
        DateTime.parse(createdAt), //Assuming that always is a correct DateTime format
        TipType.withName(tipType.capitalize),
        message
      )
    }.toList
  }

}
