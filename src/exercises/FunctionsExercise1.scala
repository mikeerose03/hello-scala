package exercises

object FunctionsExercise1 extends App {

  def greet(name: String, age: Int): String = "Hi, my name is " + name + " and I am " + age + " years old."

  println(greet("Mikee", 23))

  def factorial(n: Int): Int = {
    if (n <= 0) n
    else n * factorial(n-1)
  }

  println(factorial(5))

  def fibonacci(n: Int): Int = {
    if (n <= 1) 1
    else fibonacci(n-1) + fibonacci(n-2)
  }

  println(fibonacci(5))

  def isPrime(n: Int): Boolean = {
    def isPrimeRecursive(n: Int, i: Int): Boolean = {
      if (i == n) n > 1
      else {
        if (n % i == 0) false
        else isPrimeRecursive(n, i+1)
      }
    }
    isPrimeRecursive(n, 2)
  }

  /*
    def isPrime(n: Int): Boolean = {
      def isPrimeUntil(t: Int): Boolean = {
        if (t <= 1) true
        else n % t != 0 && isPrimeUntil(t-1)
      }
      isPrimeUntil(n/2)
    }
  */


  println(isPrime(23))
}
