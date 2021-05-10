package com.livesafe.tips.models

import com.livesafe.tips.models.TipType.TipType
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import play.api.libs.json._

case class Tip(
                id: String,
                created: DateTime,
                `type`: TipType,
                message: String
              )

object Tip {

  //This implicit for joda DateTimes, could be in a generic Json Support Util to be used across the application
  //Putting it here because for the exercise purpose
  implicit val jodaDateTimeReads = Reads[DateTime](js =>
    js.validate[String].map[DateTime](dtString => DateTime.parse(dtString, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")))
  )
  implicit val jodaDateTimeWrites: Writes[DateTime] = new Writes[DateTime] {
    def writes(d: DateTime): JsValue = JsString(d.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")))
  }
  implicit val tipWrites = Json.writes[Tip]
  implicit val tipReads = Json.reads[Tip]
}
