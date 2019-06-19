package exercises

import scala.util.{Random, Try}

object HandlingExceptions extends App {

  val host = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("CONNECTION INTERRUPTED")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("SOMEONE ELSE TOOK THE PORT")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))


  }

  def getPage(url: String): Unit = {
      Try(HttpService.getConnection(host, port))
        .flatMap(c => Try(c.get(url)).map(println))
  }

//  getPage("www.google.com")

  for {
    c <- Try(HttpService.getConnection(host, port))
    p <- Try(c.get("www.google.com"))
  } yield renderHTML(p)

  val possibleConnection = HttpService.getSafeConnection(host, port)
  val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  possibleHTML.foreach(renderHTML)

  // shorthand

  HttpService.getSafeConnection(host, port)
    .flatMap(connection => connection.getSafe("/html"))
    .foreach(renderHTML)

  // for-comprehension
  for {
    connection <- HttpService.getSafeConnection(host, port)
    html <- connection.getSafe("/home")
  } renderHTML(html)
}
