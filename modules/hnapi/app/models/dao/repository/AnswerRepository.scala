package models.dao.repository

import com.google.inject.Inject
import models.dao.entities.Answer
import models.dao.schemas.HNSchema
import models.dto.AnswerDto
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Table

trait AnswerRepository {
  def addAnswer(answer: AnswerDto): Unit

  def updateAnswerStatusByOrderId(orderId: Long): Unit
}

class AnswerRepositoryImpl @Inject()(orderRepository: OrderRepository) extends AnswerRepository {
  private val answers: Table[Answer] = HNSchema.answers

  override def addAnswer(answer: AnswerDto): Unit = transaction {
    if (!answers.exists(_.order_id == answer.orderId))
      answers.insert(
        Answer(
          answers.size + 1,
          answer.orderId,
          answer.volunteerId,
          answer.status
        )
      )

  }

  override def updateAnswerStatusByOrderId(orderId: Long): Unit = transaction {
    from(answers)(a =>
      where(a.order_id === orderId)
        select a
    ).headOption.map(a => {
      a.status = if (a.status == "Не принят") "В работе" else "Завершено"
      orderRepository.updateStatus(orderId, a.status)
      answers.insertOrUpdate(a)
    })
  }
}
