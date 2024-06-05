package models.dto

import play.api.libs.json.{Json, Reads, Writes}

case class RegisterDto(
                      var fullName: String,
                      var cityName: String,
                      var contactNumber: String
                      )

object RegisterDto {
  implicit val reads: Reads[RegisterDto] = Json.reads[RegisterDto]
  implicit val writes: Writes[RegisterDto] = Json.writes[RegisterDto]
}
