package models.services

import com.google.inject.Inject
import models.dao.entities.Order
import models.dao.repository.OrderRepository
import models.dto.{CreateOrderDto, GetOrderDto}
import utils.Mapper

trait OrderService {
  def findOrdersByCityName(cityName: String): List[GetOrderDto]
  def addOrder(order: CreateOrderDto): Unit
  def updateOrder(id: Long, order: CreateOrderDto): Unit
  def deleteOrder(id: Long): Unit
  def findOrdersByVolunteer(volunteerId: Long): List[GetOrderDto]
}

class OrderServiceImpl @Inject()(val orderRepository: OrderRepository,
                                 val mapper: Mapper
                                ) extends OrderService {

  override def findOrdersByCityName(cityName: String): List[GetOrderDto] = {
    orderRepository.getOrdersByCity(cityName).map(mapper.modelToDto).toList
  }

  override def addOrder(order: CreateOrderDto): Unit= {
    orderRepository.createOrder(mapper.dtoToModel(order))
  }


  override def updateOrder(id: Long, order: CreateOrderDto): Unit = {
    orderRepository.updateOrder(id, mapper.dtoToModel(order))
  }

  override def deleteOrder(id: Long): Unit = {
    orderRepository.deleteOrder(id)
  }

  override def findOrdersByVolunteer(volunteerId: Long): List[GetOrderDto] = {
    orderRepository.getOrdersByVolunteerCity(volunteerId).map(order =>
      mapper.modelToDto(order)
    )
  }
}
