package lectures.FunctionalProgramming

object WhatsFunction extends App {

  // PURPOSE: use functions as first class elements
  // PROBLEM: we come from an oop world
  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))
  val toInt = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(toInt("2") + 2)

  val getSum: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a+b
  }

  // Function Types Function2[A,B,R] === (A,B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS OR INSTANCES OF CLASSES DERIVING FROM FUNCTION1, FUNCTION2, etc...
}

trait MyFunction[A,B] {
  def apply(element: A): B
}
