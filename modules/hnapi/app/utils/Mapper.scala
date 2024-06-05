package utils

import models.dao.entities.{Order, Volunteer}
import models.dto.{AnswerDto, CreateOrderDto, GetOrderDto, RegisterDto}
import play.api.libs.json.JsValue

trait Mapper {
  def dtoToModel(dto: CreateOrderDto): Order
  def modelToDto(model: Order): GetOrderDto
  def jsonToOrderDto(json: Option[JsValue]): CreateOrderDto
  def jsonToAnswerDto(json: Option[JsValue]): AnswerDto
  def jsonToVolunteerDto(json: Option[JsValue]): RegisterDto
  def registerToVolunteer(register: RegisterDto): Volunteer
}

class OrderMapper extends Mapper {

  override def dtoToModel(dto: CreateOrderDto): Order =
    Order(
      0,
      dto.cityName,
      dto.commentary,
      dto.status,
      dto.geolocation,
      dto.address
    )


  override def modelToDto(model: Order): GetOrderDto = GetOrderDto(
    model.id,
    model.cityName,
    model.commentary,
    model.status,
    model.geolocation,
    model.address
  )

  override def jsonToOrderDto(json: Option[JsValue]): CreateOrderDto = {
    json.head.as[CreateOrderDto]
  }

  override def jsonToAnswerDto(json: Option[JsValue]): AnswerDto = {
    json.head.as[AnswerDto]
  }

  override def jsonToVolunteerDto(json: Option[JsValue]): RegisterDto = {
    json.head.as[RegisterDto]
  }

  override def registerToVolunteer(register: RegisterDto): Volunteer = {
    Volunteer(
    0,
    register.fullName,
    register.cityName,
    register.contactNumber
    )
  }

}
