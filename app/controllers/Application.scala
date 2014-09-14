package controllers

import util.dummy._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.ExecutionContext.Implicits.global

object Application extends Controller {

  import models.DeviceFormatter._

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def get(lastUpdate:Long) = Action.async {
    request =>
      models.Device.findByLastUpdate(lastUpdate).map {
        data =>
          Ok(Json.obj("result"->"OK", "data"->Json.toJson(data)))
      }
  }

  def manage_DeviceList(index:Int, count:Int) = Action.async {
    request =>
      models.Device.findBy(index, count).flatMap {
        data=>
          models.Device.countAll().map {
            total=>
              Ok(views.html.device.list(data, index, (total/count)+1))
          }
      }
  }

  def manage_DeviceAdd() = Action {
    Ok(views.html.device.create())
  }

  def manage_DeviceModify() = Action {
    Ok("")
  }

  def setDevice() = Action.async(parse.urlFormEncoded) {
    request =>
      val body = request.body
      val name = body.getOrElse("name", dummyList)(0).toString
      val latitude = body.getOrElse("latitude", dummyListDouble).toString.toDouble
      val longitude = body.getOrElse("longtitude",dummyListDouble).toString.toDouble
      val bearing = body.getOrElse("bearing", dummyList).toString
      val state = body.getOrElse("state", dummyList)(0).toString
      val city = body.getOrElse("city", dummyList)(0).toString
      val address = body.getOrElse("address", dummyList)(0).toString
      val created = System.currentTimeMillis()
      val lastAccess:Long = 0
      val lastUpdate = System.currentTimeMillis()
      val lastChecked = System.currentTimeMillis()
      val lastStatus = "A"

      val device = models.Device(created.toString, name, latitude, longitude, bearing, state, city, address, created, lastAccess, lastUpdate, lastChecked, lastStatus)
      models.Device.set(device).map {
        status =>
          if(status.ok)
            Redirect("/device/list/1")
          else
            InternalServerError("작업에 실패했습니다.")
      }
  }

  def delDevice(_id:String) = Action.async {
    request =>
      models.Device.delete(_id).map {
        status =>
          if(status.ok)
            Ok(Json.obj("result"->"OK"))
          else
            Ok(Json.obj("result"->"Fail", "message"->status.errMsg.getOrElse("UNEXPECTED_EXCEPTION").toString))
      }
  }

  def modDevice() = Action.async(parse.urlFormEncoded) {
    request =>
      val body = request.body
      val _id = body.getOrElse("_id", dummyList)(0).toString
      models.Device.findById(_id).flatMap {
        target=>
          val name = body.getOrElse("name", dummyList)(0).toString
          val latitude = body.getOrElse("latitude", dummyListDouble).toString.toDouble
          val longitude = body.getOrElse("longitude",dummyListDouble).toString.toDouble
          val bearing = body.getOrElse("bearing", dummyList)(0).toString
          val state = body.getOrElse("state", dummyList)(0).toString
          val city = body.getOrElse("city", dummyList)(0).toString
          val address = body.getOrElse("address", dummyList)(0).toString
          val created = target(0).created
          val lastAccess = target(0).lastAccess
          val lastUpdate = System.currentTimeMillis()
          val lastChecked = target(0).lastChecked
          val lastStatus = body.getOrElse("lastStatus", dummyList)(0).toString

          val device = models.Device(_id, name, latitude, longitude, bearing, state, city, address, created, lastAccess, lastUpdate, lastChecked, lastStatus)
          models.Device.modify(device).map {
            status =>
              if(status.ok)
                Ok(Json.obj("result"->"OK"))
              else
                Ok(Json.obj("result"->"Fail", "message"->status.errMsg.getOrElse("UNEXPECTED_EXCEPTION").toString))
          }
      }

  }

}