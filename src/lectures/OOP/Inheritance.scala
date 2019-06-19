package lectures.OOP

object Inheritance extends App {
  // single class inheritance
  // can only extend one class at a time
  class Animal {
    val creatureType = "wild"
    def eat = println("nomnom")
  }

  //inherits properties from Animal
  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  // private properties can only be accessed within the class
  // protected properties can be accessed by subclasses
  // public properties can be accessed by anyone

  val cat = new Cat
  cat.crunch

  // constructors

  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // overriding

  class Dog(override val creatureType: String) extends Animal {
    //    override val creatureType = "domestic"
    override def eat = { //super
      super.eat
      println("crunch, crunch")
    }
  }

  val dog = new Dog("wild")
  dog.eat
  println(dog.creatureType)

  //type substitution (polymorphism)
  val unknownAnimal: Animal = new Dog("K9")

  unknownAnimal.eat

  // overRIDING vs overLOADING
  // overRIDING means supplying a different implementation in derived classes
  // overLOADING means supplying multiple methods with the different signatures but with the same name and the same class

  // super

  // prevent overrides
  // 1 - use final. it will prevent a member/class from being overriden
  // note: using final on a class will tell compiler to disallow inheritance
  // e.g. final class Animal
  // 2 - seal the class.
  // note: sealing the class is a softer restriction. it will allow extention
  // on classes in the same file, but will prevent extension in other files.
  // e.g. sealed class Animal

}
