package lectures.FunctionalProgramming

object HOFsCurries extends App {

  // val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = ???
  // higher order function (HOF)
  // e.g. map, flatmap, filter in MyList

  // function that applies a function n times over a given value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x)))

  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n-1, f(x))

  val plusOne = (x: Int) => x+1
  println(nTimes(plusOne, 3, 0)) // 3

  // ntb(f, n) = x=> f(f(f...(x)))
  // increment10 = ntb(plusOne, 10) = x => plusOne(plusOne...(x))
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n-1)(f(x))

  println(nTimesBetter(plusOne, 10)(0)) // 10

  // curried functions
  val superAdder: Int => (Int => Int) = x => y => x + y
  val add3 = superAdder(3)
  println(add3(10)) // 13

  // functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI)) // 3.14
  println(preciseFormat(Math.PI)) // 3.14159265
}
