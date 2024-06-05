package models.dao.entities

import org.squeryl.KeyedEntity

case class Order(
                var id: Long,
                var cityName: String,
                var commentary: String,
                var status: String,
                var geolocation: String,
                var address: String
                )
extends KeyedEntity[Long]
