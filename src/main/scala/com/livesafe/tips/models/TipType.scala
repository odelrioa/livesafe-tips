package com.livesafe.tips.models

import play.api.libs.json.{Json, Reads, Writes}

//An Enumeration as a way of defining a finite set of values for Tip Type
object TipType extends Enumeration {
  type TipType = Value
  val Arson,              //Arson
      Disturbance,        //Disturbance
      Smell,              //Smell
      Suspicious,         //Suspicious
      SuspiciousActivity, //Suspicious Activity
      Violence            //Violence
  = Value

  implicit val tipTypeWrites = Writes.enumNameWrites
  implicit val tipTypeReads = Reads.enumNameReads(TipType)
}
