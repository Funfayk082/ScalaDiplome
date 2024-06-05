package models.dto

import play.api.libs.json.{Json, Reads, Writes}

case class GetOrderDto(
                      id: Long,
                      cityName: String,
                      commentary: String,
                      status: String,
                      geolocation: String,
                      address: String
                      )

object GetOrderDto {
  implicit val writes: Writes[GetOrderDto] = Json.writes[GetOrderDto]
  implicit val reads: Reads[GetOrderDto] = Json.reads[GetOrderDto]
}
