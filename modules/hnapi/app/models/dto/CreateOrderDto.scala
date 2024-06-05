package models.dto

import play.api.libs.json.Json

case class CreateOrderDto(
                           cityName: String,
                           commentary: String,
                           status: String,
                           geolocation: String,
                           address: String
                         )

object CreateOrderDto {
  implicit val writes = Json.writes[CreateOrderDto]
  implicit val reads = Json.reads[CreateOrderDto]
}
