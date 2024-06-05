package controllers

import com.google.inject.Inject
import models.services.OrderService
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, Controller}
import utils.Mapper

class OrderController @Inject()(orderService: OrderService, mapper: Mapper) extends Controller {

  def findOrdersByCityName(cityName: String): Action[AnyContent] = Action {
    Ok(Json.toJson(orderService.findOrdersByCityName(cityName)))
  }

  def addOrder: Action[AnyContent] = Action { implicit request =>
    val created = request.body.asJson
    orderService.addOrder(mapper.jsonToOrderDto(created))
    Created
  }

  def findOrdersByVolunteer: Action[AnyContent] = Action { implicit request =>
    Ok(Json.toJson(request.headers.get("VolunteerId").map { id =>
      orderService.findOrdersByVolunteer(id.toLong)
    }))
  }
}
