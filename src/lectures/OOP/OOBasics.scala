package lectures.OOP

object OOBasics extends App {
  var person = new Person("Mikee", 23)
  println(person.age)
  person.greet("John")
}

//constructor
class Person(name: String, val age: Int) {
  //body
  //NOTE: values are variables inside a class body are considered as fields.

  //method
  def greet(name: String): Unit = println(s"${this.name} says hi to $name")
  //overloading
  def greet(): Unit = println(s"Hi, my name is $name")

  //overloading constructors/multiple constructors
  def this(name: String) = this(name, 0)

}

//class parameters are not fields!
//class parameter and class fields are not the same and behave differently
//to convert the parameter into a field, add val before it.
