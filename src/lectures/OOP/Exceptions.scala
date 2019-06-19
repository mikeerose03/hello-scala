package lectures.OOP

object Exceptions extends App {
  val x: String = null

  // println(x.length)
  // this ^^ will crash with NullPointerException

  // 1. Throwing exceptions

  // val exception: String = throw new NullPointerException
  // throwable classes extend the throwable class.
  // Exception and Error are the major throwable subtypes

  // 2. Catching exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for u")
    else 42

  // try catch

  val potentialFail = try { // this is an AnyVal type
    //code that might throw exception
    getInt(false)
  } catch {
    case e: RuntimeException => println("caught a runtime exception")
  } finally {
    // optional
    // does not influence the return type of this expression
    // use finally only for side effects e.g. logging to a file
    // code that will get executed no matter what
    println("finally")
  }

  println(potentialFail) // 42

  // 3. Defining custom Exceptions

  class MyException extends Exception
  val exception = new MyException
  // throw exception // program will crash
}
