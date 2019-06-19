package lectures.FunctionalProgramming

object MapFlatmapFilterFor extends App {

  val list = List(1,2,3)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair))


  def combine[A, B](l1: List[A], l2: List[B], acc: List[String]): List[String] = {
    def combineUntil(l1: A, l2: List[B], a: List[String]): List[String] = {
      if (l2.isEmpty) a
      else combineUntil(l1, l2.tail, a :+ l1+""+l2.head)
    }
    if (l1.isEmpty) acc
    else combine(l1.tail, l2, acc ++ combineUntil(l1.head, l2, List()))
  }

  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')
  val colors = List("black", "white")

  // "iterations"
  val combinations = numbers.flatMap(n => chars.flatMap(c => colors.map(cl => "" + c + n + cl)))
  println(combine(chars, numbers, List()))
  println(combinations)

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers if n % 2 == 0 // (if-guard) filters to keep only even nums
    c <- chars
    cl <- colors
  } yield "" + c + n + cl

  for {
    n <- numbers
  } println(n)

  println(forCombinations)

  // syntax overload
  list.map { x =>
    x*2
  }


}
