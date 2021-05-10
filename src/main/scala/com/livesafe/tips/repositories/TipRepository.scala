package com.livesafe.tips.repositories

import com.livesafe.tips.{LegacyTipsService, Repository}
import com.livesafe.tips.Service.Tip
import scala.concurrent.ExecutionContext.Implicits.global

class TipRepository extends Repository[Tip](_.id) {
  //As a default constructor this will execute when the repository is instantiated
  for {
    legacy1Tips <- LegacyTipsService.fetchLegacy1Tips
    legacy2Tips <- LegacyTipsService.fetchLegacy2Tips
  } yield {
    legacy1Tips.foreach(save(_))
    legacy2Tips.foreach(save(_))
  }
}
