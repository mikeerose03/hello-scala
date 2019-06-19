package exercises

import scala.annotation.tailrec

object RecursionExercise1 extends App{
  def concatNTimes(str: String, i: Int): String = {
    @tailrec
    def concat(s: String, n: Int): String =
      if (n == 1) s
      else concat(s + str, n - 1)
    concat(str, i)
  }

  //or
  def concat(str: String, n: Int, accumulator: String): String =
    if (n <= 0) accumulator
    else concat(str, n-1, accumulator+str)

  println(concat("hello", 3, ""))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else {
        if (n % t == 0) false
        else isPrimeUntil(t-1)
      }
    }
    isPrimeUntil(n/2)
  }

  /* //better
  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else
        if (t<=1) true
        else isPrimeUntil(t-1, n % t !=0 && isStillPrime)
    isPrimeUntil(n/2, true)
  }

   */
  println(isPrime(23))

  def fibonacci(n: Int): Int = {
    @tailrec
    def fibonacciUntil(accumulator: Int, num: Int, i: Int): Int =
      if (i >= n) accumulator
      else fibonacciUntil(accumulator+num, accumulator, i+1)
    fibonacciUntil(1, 1, 1)
  }

  println(fibonacci(5))
}

