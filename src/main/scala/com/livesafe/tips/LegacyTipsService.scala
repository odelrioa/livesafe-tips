package com.livesafe.tips

import com.livesafe.tips.models.Tip
import org.joda.time.DateTime

import scala.concurrent.Future
import scala.io.Source

object LegacyTipsService {

  import scala.concurrent.ExecutionContext.Implicits.global

  def fetchLegacy1Tips: Future[List[Tip]] = Future {
    val lines = Source.fromResource("legacy-1-tips.csv").getLines()
    lines.drop(1).map { line =>
      val Array(tipId, created, tipType, msg) = line.split(",").map(_.trim)
      Tip(
        tipId,
        created.toLong,
        tipType,
        msg
      )
    }.toList
  }
  def fetchLegacy2Tips: Future[List[Tip]] = Future {
    val lines = Source.fromResource("legacy-2-tips.csv").getLines()
    lines.drop(1).map { line =>
      val Array(tipId, message, createdAt, tipType) = line.split(",").map(_.trim)
      Tip(
        tipId,
        DateTime.parse(createdAt).toInstant.getMillis,
        tipType,
        message
      )
    }.toList
  }

}
