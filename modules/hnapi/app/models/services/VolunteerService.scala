package models.services

import com.google.inject.Inject
import models.dao.repository.VolunteerRepository
import models.dto.RegisterDto
import utils.Mapper

trait VolunteerService {
  def registerVolunteer(volunteer: RegisterDto): Long
}

class VolunteerServiceImpl @Inject() (val volunteerRepository: VolunteerRepository,
                                      val mapper: Mapper
                                     ) extends VolunteerService{
  override def registerVolunteer(volunteer: RegisterDto): Long = {
    volunteerRepository.register(mapper.registerToVolunteer(volunteer))
  }
}
