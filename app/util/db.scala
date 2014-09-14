package util

/**
 * Created with IntelliJ IDEA.
 * User: Gyuhyeon
 * Date: 2014. 3. 4.
 * Time: 오전 10:43
 * To change this template use File | Settings | File Templates.
 */
object db {

  def getPageIndex(page:Int, count:Int):Int =
  {
    (page-1)*count
  }

  def SaltKey:String =
  {
    scala.util.Random.alphanumeric.take(10).mkString
  }

  def createKey(keySize: Int):String =
  {
    scala.util.Random.alphanumeric.take(keySize).mkString
  }

  def createSimpleKey(keySize: Int):Int =
  {
    scala.util.Random.nextInt(scala.math.pow(10, keySize).toInt)
  }

  def SHAPassKey(s:String, p: String):String =
  {
    val md = java.security.MessageDigest.getInstance("SHA-256")
    val key = s + p
    new sun.misc.BASE64Encoder().encode(md.digest(key.getBytes("UTF-8")))
  }

  def ArticleKey(s:String, p: String):String =
  {
    val md = java.security.MessageDigest.getInstance("SHA-256")
    val key = s + p
    var result = ""
    md.digest(key.getBytes("UTF-8")) map {
      uni =>
        result = result + Integer.toHexString(uni & 0xFF)
    }
    result
  }

}
