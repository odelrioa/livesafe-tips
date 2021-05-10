package com.livesafe.tips

import java.util.UUID

import com.livesafe.tips.Service.Tip
import com.livesafe.tips.repositories.TipRepository

import scala.concurrent.Future

trait Service {

  def getAll(): Future[List[Tip]]
  def getById(id: UUID): Future[Tip]

}

class TipService(tipRepository: TipRepository) extends Service {

  /**
   * FIXME: Replace this with your implementation. This stub is provided as an example of how to load the legacy data from [[LegacyTipsService]]
   *
   * @return
   */
  def getAll(): Future[List[Tip]] = {
    tipRepository.getAll()
  }

    /**
   * FIXME: Replace this with your implementation. This stub is provided as an example of how to load the legacy data from [[LegacyTipsService]]
     *
     * @return
   */
  def getById(id: UUID): Future[Tip] = {
    tipRepository.get(id.toString)
  }

}

object Service {
  /**
   * FIXME: We need a proper data model for 'Tip'!
   */
  type Tip = com.livesafe.tips.models.Tip
}
