package models.dao.entities

import org.squeryl.KeyedEntity

case class Answer(
                 var id: Long,
                 var order_id: Long,
                 var volunteer_id: Long,
                 var status: String
                 )
extends KeyedEntity[Long]
