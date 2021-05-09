package com.livesafe.tips.models

case class Tip(
                id: String,
                created: Long,
                `type`: String,
                message: String
              )
