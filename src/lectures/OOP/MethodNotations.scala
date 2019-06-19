package lectures.OOP

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = s"$name, what the heck?!"
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent with the expression above
  // infix notation or operator notation. (an example of syntactic sugar)
  // This works only with methods containing a single parameter like the one above.

  //"Operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary + tom) //mary.+(tom)
  //the method "hangOutWith" acts like an operator between two things (in this case, mary and tom)

  println(1+1) // is same as println(1.+(2))

  //ALL OPERATORS ARE METHODS

  //prefix notation
  //all about unary operators

  val x = -1 // same as 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with a few operators (- + ~ !)

  println(!mary) // same as println(mary.unary_!)

  // postfix notation
  // can only be used in methods without parameters

  println(mary isAlive) // same as println(mary.isAlive)

  // apply
  println(mary()) //same as println(mary.apply())
  //when the compilers sees an class being written as a function, it
  //searches for the implementation of the "apply" method in the particular class.


}
