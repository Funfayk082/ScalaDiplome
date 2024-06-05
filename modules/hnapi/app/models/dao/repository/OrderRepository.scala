package models.dao.repository

import com.google.inject.Inject
import models.dao.entities.{Answer, Order}
import models.dao.schemas.HNSchema
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Table

trait OrderRepository {
  def getOrdersByCity(city: String): List[Order]

  def createOrder(order: Order): Unit

  def updateOrder(id: Long, order: Order): Unit

  def deleteOrder(id: Long): Unit

  def updateStatus(id: Long, status: String): Unit

  def getOrdersByVolunteerCity(id: Long): List[Order]
}

class OrderRepositoryImpl @Inject() (volunteerRepository: VolunteerRepository) extends OrderRepository {
  private val orders: Table[Order] = HNSchema.orders
  private val answers: Table[Answer] = HNSchema.answers

  override def getOrdersByCity(city: String): List[Order] =
    transaction {
      from(orders)(o =>
        where(o.cityName === city)
          select o
      ).toList.filter(o => !answers.map(_.order_id).toList.contains(o.id))
    }

  override def createOrder(order: Order): Unit =
    transaction {
      order.id = orders.size + 1
      orders.insert(order)
    }

  override def updateOrder(id: Long, order: Order): Unit =
    transaction {
      order.id = id
      orders.update(order)
    }

  override def deleteOrder(id: Long): Unit =
    transaction {
      orders.deleteWhere(_.id === id)
    }


  override def updateStatus(id: Long, status: String): Unit = transaction {
    from(orders)(o => where(o.id === id) select o).map(finded => {
      finded.status = status
      orders.update(finded)
    }
    )
  }

  override def getOrdersByVolunteerCity(id: Long): List[Order] = transaction {
    volunteerRepository.getVolunteerById(id).map(volunteer => {
      from(orders)(o => {
        where(o.cityName === volunteer.city_name and
        o.status === "Не принят") select o
      }).toList
    }).getOrElse(List.empty[Order])
  }
}
