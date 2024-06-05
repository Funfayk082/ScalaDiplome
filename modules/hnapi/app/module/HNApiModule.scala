package module

import di.AppModule
import models.dao.repository.{AnswerRepository, AnswerRepositoryImpl, OrderRepository, OrderRepositoryImpl, VolunteerRepository, VolunteerRepositoryImpl}
import models.services.{AnswerService, AnswerServiceImpl, OrderService, OrderServiceImpl, VolunteerService, VolunteerServiceImpl}
import utils.{Mapper, OrderMapper}

class HNApiModule extends AppModule{

  override def configure(): Unit = {
    bindSingleton[OrderRepository, OrderRepositoryImpl]
    bindSingleton[OrderService, OrderServiceImpl]
    bindSingleton[AnswerRepository, AnswerRepositoryImpl]
    bindSingleton[AnswerService, AnswerServiceImpl]
    bindSingleton[VolunteerRepository, VolunteerRepositoryImpl]
    bindSingleton[VolunteerService, VolunteerServiceImpl]
    bindSingleton[Mapper, OrderMapper]
  }
}
