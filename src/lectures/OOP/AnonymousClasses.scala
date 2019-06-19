package lectures.OOP

object AnonymousClasses extends App {
  abstract class Animal {
    def eat: Unit
  }

  // anonymous class
  val funnyAnimal: Animal = new Animal  {
    override def eat: Unit = println("ahahahahaha")
  }

  /*
   class AnonymousClasses$$annon$1 extends Animal {
    override def eat: Unit = println("ahahahahaha")
   }

   val funnyAnimal: Animal = new AnonymousClasses$$annon$1
   */

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi, my is Jim, how can I be of help?")
  }


}
