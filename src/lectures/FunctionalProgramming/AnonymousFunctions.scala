package lectures.FunctionalProgramming

object AnonymousFunctions extends App {

  val doubler = new Function1[Int, Int] {
    override def apply(x: Int): Int = x*2
  }


  // syntactic sugar also called as anonymous function (LAMBDA)
  val shortenedDoubler: Int => Int = x => x * 2

  // multiple parameters in a lambda
  val getSum: (Int, Int) => Int = (a, b) => a + b

  // no parameters
  val return3: () => Int = () => 3

  println(return3) // lectures.FunctionalProgramming.AnonymousFunctions$$$Lambda$8/1361960727@7a0ac6e3
  println(return3()) // 3 NOTE: For lambdas, '()' must not be omitted

  // curly braces with lambdas
  var stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR syntactic sugar
  val niceIncrement: Int => Int = _ + 1 // same as x => x+1
  val niceAdder: (Int, Int) => Int = _ + _ // same as (a,b) => a + b
  // NOTE: type must be defined for the compiler know


}
