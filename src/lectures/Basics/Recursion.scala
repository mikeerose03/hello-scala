package lectures.Basics

object Recursion extends App {

  def factorial(n: Int): Int = {
    if (n <= 0) n
    else n * factorial(n-1) //STACK RECURSION
  }

  def anotherFactorial(n: Int): Int = {
    def factorialHelper(i: Int, accumulator : Int): Int = {
      if (i <= 1) accumulator
      else factorialHelper(i-1, i*accumulator) //TAIL RECURSION
    }
    factorialHelper(n, 1)
  }

  println(anotherFactorial(5))

  //NOTE: WHEN YOU NEED LOOPS, USE TAIL RECURSION
}
