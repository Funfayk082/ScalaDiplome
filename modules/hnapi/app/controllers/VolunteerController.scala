package controllers

import com.google.inject.Inject
import models.services.VolunteerService
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, Controller}
import utils.Mapper

class VolunteerController @Inject()(volunteerService: VolunteerService,
                                    mapper: Mapper) extends Controller {

  def register: Action[AnyContent] = Action { request =>
    Created(Json.toJson(volunteerService.registerVolunteer(
      mapper.jsonToVolunteerDto(
        request.body.asJson
      ))))
  }
}
