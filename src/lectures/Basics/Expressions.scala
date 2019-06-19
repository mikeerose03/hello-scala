package lectures.Basics

object Expressions extends App {
  val x = 1+2 //expression
  println(x)

  println(2 + 3 * 4)

  println(1 == x)

  var aVariable = 2
  aVariable += 2

  // Instructions (DO) vs Expressions (VALUE)

  //IF expression
  val aCondition = true
  var aConditionedExpression = if (aCondition) 5 else 3
  println(aConditionedExpression)

  var i = 0
  val aWhile = while(i < 5) {
    i+=1
  } //NEVER WRITE CODE LIKE THIS IN SCALA

  val aWeirdValue = (aVariable = 4) //will return unit type
  //unit === void in scala

  //Code blocks

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }

  println(aCodeBlock)
}
