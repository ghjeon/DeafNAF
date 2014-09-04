package models

import play.api.libs.json._

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

object Device {

  def set(d:Device) = {

  }

  def findBy(index:Int, count:Int) = {

  }

  def findAll() = {

  }

  def findByState(state:String) = {

  }

  def findByCity(city:String) = {

  }

  def findByAddress(address:String) = {

  }

  def modify(d:Device) = {

  }

}
