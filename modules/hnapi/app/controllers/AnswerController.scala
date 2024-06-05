package controllers

import com.google.inject.Inject
import controllers.Assets.{Created, Ok}
import models.services.AnswerService
import play.api.mvc.{Action, AnyContent}
import play.mvc.Controller
import utils.Mapper

class AnswerController @Inject() (answerService: AnswerService,
                                  mapper: Mapper) extends Controller{
  def addAnswer: Action[AnyContent] = Action { implicit request =>
    answerService.addAnswer(mapper.jsonToAnswerDto(request.body.asJson))
    Created
  }

  def updateAnswer(orderId: Long): Action[AnyContent]  = Action  { implicit request  =>
    answerService.updateAnswerStatusByOrderId(orderId)
    Ok
  }
}
