package exercises

import java.util.Random

object Options extends App {

  val config: Map[String, String] = Map (
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected"
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Some(new Connection)
      else None
    }
  }

  val host = config.get("host")
  val port = config.get("port")
/*
  host match {
    case Some(h) => {
      port match {
        case Some(p) => {
          val connection = Connection(h, p)
          connection match {
            case Some(c) => println(c.connect)
            case None => println("")
          }
        } case None => println("")
      }
    } case None => println("")
  }
  */

  /*
  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  val connectionStatus = connection.map(c => c.connect)
  connectionStatus.foreach(println)
  */
  // chained calls
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for-comprehensions
  val connectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect

  connectionStatus.foreach(println)
}




