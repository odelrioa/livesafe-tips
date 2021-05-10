package com.livesafe.tips

import com.livesafe.tips.repositories.TipRepository

object TipsBoot extends App {

  //This will execute the default constructor as a way of having the in-memory database filled and ready once the app starts
  //With more time maybe an idea would be to have a job or file watcher that would populate a real database with
  //the information pulled from CSV files. Every time a file is updated or a new one is detected,
  //the job/watcher would process the data and bulk load to the data base
  val tipRepository = new TipRepository()

  /**
   * Fixme: Provide your own tip service
   */
  val tipService = new TipService(tipRepository)

  val tipHttpService = new TipsHttpService(tipService)
  tipHttpService.start()
}
