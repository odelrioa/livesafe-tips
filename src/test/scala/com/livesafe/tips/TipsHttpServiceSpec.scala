package com.livesafe.tips

import java.util.UUID

import scala.concurrent.Future
import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.livesafe.tips.Service.Tip
import com.livesafe.tips.models.{Tip, TipType}
import org.joda.time.DateTime
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FreeSpec, Matchers}

class TipsHttpServiceSpec extends FreeSpec with Matchers with MockFactory with ScalatestRouteTest {

  val mockTipService: TipService = mock[TipService]

  val service = new TipsHttpService(mockTipService)

  "TipsHttpService endpoints" - {
    "/tips" in {
      (mockTipService.getAll _).expects().returns(Future.successful(Nil))
      Get("/tips") ~> service.routes ~> check {
        status shouldBe StatusCodes.OK
      }
    }
    "/tips/:id" in {
      val testTipId = UUID.randomUUID()
      val testTipIdStr = testTipId.toString
      val tipType = TipType.withName("Disturbance")
      (mockTipService.getById _).expects(testTipId).returns(Future.successful(Tip(testTipIdStr, DateTime.now(), tipType, "test message")))
      // FIXME: Finish this test please
      Get(s"/tips/$testTipId") ~> service.routes ~> check {
        status shouldBe StatusCodes.OK
        contentType shouldEqual ContentTypes.`application/json`
        responseAs[String] contains testTipIdStr
      }
    }
  }
}
