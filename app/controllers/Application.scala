package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def get(lastUpdate:Long) = Action {
    Ok("")
  }

  def manage_DeviceList() = Action {
    Ok("")
  }

  def manage_DeviceAdd() = Action {
    Ok("")
  }

  def manage_DeviceModify() = Action {
    Ok("")
  }

  def setDevice() = Action {
    Ok("")
  }

  def delDevice() = Action {
    Ok("")
  }

  def modDevice() = Action {
    Ok("")
  }

}