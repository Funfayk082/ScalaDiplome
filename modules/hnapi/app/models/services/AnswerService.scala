package models.services

import com.google.inject.Inject
import models.dao.repository.AnswerRepository
import models.dto.AnswerDto
import utils.Mapper

trait AnswerService {
  def addAnswer(answer: AnswerDto): Unit

  def updateAnswerStatusByOrderId(orderId: Long): Unit
}

class AnswerServiceImpl @Inject()(val answerRepository: AnswerRepository,
                                  val mapper: Mapper
                                 ) extends AnswerService {

  override def addAnswer(answer: AnswerDto): Unit = {
    answerRepository.addAnswer(answer)
  }

  override def updateAnswerStatusByOrderId(orderId: Long): Unit = {
    answerRepository.updateAnswerStatusByOrderId(orderId)
  }
}
