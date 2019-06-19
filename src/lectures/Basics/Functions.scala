package lectures.Basics

object Functions  extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b //string concatenation
  }

  def aParameterlessFunction(): Int = 42

  def aRepeatedFunction(str: String, n: Int): String = {
    if (n == 1) str
    else str + aRepeatedFunction(str, n-1)
  }
  println(aFunction("hello", 42))
  println(aParameterlessFunction)
  println(aRepeatedFunction("hello", 3))

  // Instead of using loops, use RECURSIVE functions
  // RECURSIVE FUNCTIONS NEED RETURN TYPE!
  // As best practice, always specify return type for functions
}
