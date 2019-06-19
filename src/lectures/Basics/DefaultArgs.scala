package lectures.Basics

object DefaultArgs extends App {

  def factorial(n: Int, acc: Int = 1 /* default value */): Int =
    if (n <= 0) acc
    else factorial(n-1, n*acc)

  val fact10 = factorial(5)
  println(fact10)

  def greeting(name: String = "Superman", age: Int = 24): String =
    "Hi, my name is " + name + " and I am " + age + " years old."

  println(greeting(age=15))
}
