package exercises

object Exceptions extends App {
  // val outOfMemoryError = throw new OutOfMemoryError()
  // val array = Array.ofDim(Int.MaxValue) //will crash to OutOfMemoryError
  // val stackOverflowError = throw new StackOverflowError()
  /*
    def infinite: Int = 1 + infinite
    val noLimit = infinite
   */ // will crash to StackOverflowError
  class OverflowException extends Exception
  class UnderflowException extends Exception
  class MathCalculationException extends Exception
  object PocketCalculator {
    def add(n1: Int, n2: Int): Int = {
      val sum = n1 + n2
      /* if (n1 > Int.MaxValue || n1 > Int.MaxValue || sum == Int.MinValue)
        throw new OverflowException
      else sum */
      if (n1 > 0 && n2 > 0 && sum < 0)
        throw new OverflowException
      else if (n1 < 0 && n2 < 0 && sum > 0) throw new UnderflowException
      else sum
    }
    def subtract(n1: Int, n2: Int): Int = {
      val difference = n1 - n2
      /* if (n1 < Int.MinValue || n1 < Int.MinValue || difference == Int.MaxValue)
        throw new OverflowException
      else difference
       */
      if (n1 > 0 && n2 < 0 && difference < 0)
        throw new OverflowException
      else if (n1 < 0 && n2 > 0 && difference > 0) throw new UnderflowException
      else difference
    }
    def multiply(n1: Int, n2: Int): Int = { //n1*n2
      val product = n1 * n2
      if (n1 > 0 && n2 > 0 && product < 0) throw new OverflowException
      else if (n1 < 0 && n2 < 0 && product > 0) throw new OverflowException
      else if (n1 > 0 && n2 < 0 && product > 0) throw new UnderflowException
      else if (n1 < 0 && n2 > 0 && product > 0) throw new UnderflowException
      else product
    }
    def divide(n1: Int, n2: Int): Int =  { //n1/n2
      if (n2 == 0) throw new MathCalculationException
      else n1/n2
    }
  }

//  println(PocketCalculator.add(Int.MaxValue, 1))
//  println(PocketCalculator.subtract(Int.MinValue, 1))
  println(PocketCalculator.divide(1,0))
}
