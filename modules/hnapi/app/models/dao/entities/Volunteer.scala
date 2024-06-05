package models.dao.entities

import org.squeryl.KeyedEntity

case class Volunteer(
                    var id: Long,
                    var full_name: String,
                    var city_name: String,
                    var contact_number: String
                    )
extends KeyedEntity[Long]
