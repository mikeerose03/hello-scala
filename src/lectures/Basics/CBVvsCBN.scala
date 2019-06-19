package lectures.Basics

object CBVvsCBN extends App {

  def calledByValue(x: Long): Unit = {
    println("called by value " + x)
    println("called by value " + x)
  }

  def calledByName(x: => Long): Unit = {
    println("called by value " + x)
    println("called by value " + x)
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())
}
