package models.dto

import play.api.libs.json.Json

case class AnswerDto(
                      var orderId: Long,
                      var volunteerId: Long,
                      var status: String
                    )
object AnswerDto {
  implicit val writes = Json.writes[AnswerDto]
  implicit val reads = Json.reads[AnswerDto]
}