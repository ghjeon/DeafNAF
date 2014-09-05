package models

import reactivemongo.api._

import play.modules.reactivemongo._
import reactivemongo.core.commands._
import play.api.libs.json._
import play.modules.reactivemongo.json.collection.JSONCollection
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.Play.current
import play.api.libs.iteratee.Iteratee
import scala.util.{Failure, Success}
import scala.collection.mutable

/**
 * Project IntelliJ IDEA
 * Module models
 * User: Gyuhyeon
 * Date: 2014. 9. 5.
 * Time: 오전 1:10
 */
case class Device(_id:String,
                  name:String,
                  latitude:Double,
                  longitude:Double,
                  state:String,
                  city:String,
                  address:String,
                  created:Long,
                  lastAccess:Long,
                  lastUpdate:Long,
                  lastChecked:Long,
                  lastStatus:String)

object DeviceFormatter {
  implicit val deviceFormat = Json.format[Device]
}

object Device {
  import DeviceFormatter._

  def collection:JSONCollection = ReactiveMongoPlugin.db.collection[JSONCollection]("devices")

  def set(d:Device):Future[LastError] = {
    val json = Json.toJson(d)

    collection.insert(json)
  }

  def findBy(index:Int, count:Int):Future[List[Device]] = {
    val json = Json.obj()
    val sort = Json.obj("created"->1)
    val cursor:Cursor[Device] = collection
      .find(json)
      .sort(sort)
      .options(QueryOpts().skip((index - 1) * count))
      .cursor[Device]

    cursor.collect[List](count)

  }

  def findAll():Future[List[Device]] = {
    val json = Json.obj()
    val sort = Json.obj("created"->1)
    val cursor:Cursor[Device] = collection.find(json).sort(sort).cursor[Device]

    cursor.collect[List]()
  }

  def findByState(state:String):Future[List[Device]] = {
    val json = Json.obj("state"->state)
    val sort = Json.obj("created"->1)

    val cursor:Cursor[Device] = collection.find(json).sort(sort).cursor[Device]

    cursor.collect[List]()
  }

  def findByCity(city:String):Future[List[Device]] = {
    val json = Json.obj("city"->city)
    val sort = Json.obj("created"->1)

    val cursor:Cursor[Device] = collection.find(json).sort(sort).cursor[Device]

    cursor.collect[List]()
  }

  def findByAddress(address:String):Future[List[Device]] = {
    val json = Json.obj("address"->address)
    val sort = Json.obj("created"->1)

    val cursor:Cursor[Device] = collection.find(json).sort(sort).cursor[Device]

    cursor.collect[List]()
  }

  def modify(d:Device) = {
    val json = Json.obj("_id"->d._id)
    val set = Json.obj("$set"->Json.toJson(d))
    val sort = Json.obj("created"->1)

    val cursor:Cursor[Device] = collection.find(json).sort(sort).cursor[Device]

    cursor.collect[List]()
  }

}
