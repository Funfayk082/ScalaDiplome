package models.dao.schemas

import models.dao.entities.{Answer, Order, Volunteer}
import org.squeryl.{Schema, Table}

object HNSchema extends Schema{
  val orders: Table[Order] = table[Order]
  val volunteers: Table[Volunteer] = table[Volunteer]
  val answers: Table[Answer] = table[Answer]
}
