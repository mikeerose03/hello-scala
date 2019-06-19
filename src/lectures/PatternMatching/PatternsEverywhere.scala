package lectures.PatternMatching

object PatternsEverywhere extends App {

  // big idea #1
  try {
    // code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }

  // catches are actually MATCHES

  // big idea #2
  val list = List(1,2,3,4)
  val evenOnes = for {
    x <- list if x % 2 == 0 // ?!
  } yield (10*x)

  // generators are also based on PATTERN MATCHING
  val tuples = List((1,2), (3,4))
  val filterTuples = for {
    (first, second) <-tuples
  } yield first * second
  // case classes, :: operators, etc.

  // big idea #3
  val tuple = (1,2,3)
  val (a, b, c) = tuple
  // multiple value definitions based on PATTERN MATCHING
  // ALL THE POWER is available

  val head :: tail = list
  println(head)
  println(tail)

  // big idea #4
  // partial function
  // is also based on pattern matching

  val mappedList = list.map {
    case v if c % 2 == 0 => s"$v is even"
    case 1 => "the one"
    case _ => "something else"
  } // this is called a partial function literal

  println(mappedList)
}
