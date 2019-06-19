package exercises

object MethodNotationsExercise1 extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    //1
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie, age)
    def unary_! : String = s"$name, what the heck?!"
    //2
    def unary_+ : Person = new Person(name, favoriteMovie, age+1)
    def isAlive: Boolean = true

    //3
    def learns(lesson: String): String = s"$name learns $lesson"
    def learnsScala : String = this learns "scala"

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    //5
    def apply(n: Int): String = s"$name watched $favoriteMovie $n times."
  }

  //1
  val mary = new Person("Mary", "Inception", 14)
  println((mary + "the rockstar")())

  //2
  println((+mary).age)

  //3
  println(mary learnsScala)

  //4
  println(mary(2))
}
