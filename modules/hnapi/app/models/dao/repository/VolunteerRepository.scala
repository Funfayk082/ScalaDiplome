package models.dao.repository

import models.dao.entities.Volunteer
import models.dao.schemas.HNSchema
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Table

trait VolunteerRepository {
  def register(volunteer: Volunteer): Long

  def getVolunteerById(id: Long): Option[Volunteer]
}

class VolunteerRepositoryImpl extends VolunteerRepository{
  private val volunteers: Table[Volunteer] = HNSchema.volunteers

  override def register(volunteer: Volunteer): Long = transaction {
    volunteer.id = volunteers.size + 1
    volunteers.insert(volunteer).id
  }


  override def getVolunteerById(id: Long): Option[Volunteer] = transaction {
    from(volunteers)(v => where(v.id === id) select v).headOption
  }
}
