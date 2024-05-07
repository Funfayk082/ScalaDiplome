package controllers

import play.api.mvc.{Action, AnyContent, Controller}

class OrderController extends Controller{
  def test: Action[AnyContent] = Action {
    Ok("test")
  }
}
