package lectures.OOP

object Generics extends App {

  class MyList[+A] {
    // use the type A
    def add[B >: A](element: B): MyList[B] = ???
    /*
    * A = Cat
    * B = Dog
    */
  }

  trait MyTrait[A] {}

  class MyMap[Key, Value]
  val listOfInt = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  // note: Object cannot be type parametirized

  object MyList {
    def empty[A]: MyList[A] = new MyList
  }
  val emptyListOfInt = MyList.empty[Int]

  // variants problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. a List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? => RETURN A LIST OF ANIMALS (def add[B >: A](element: B): MyList[B])

  // 2. List[Cat] != List[Dog] = INVARIANCE
  class InvariantList[A]
  // val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat] //WRONG!

  // 3. CONTRAVARIANCE
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  class Cage[A <: Animal](animal: A) //<: means subclass of animal and >: for super class of animal
  val cage = new Cage(new Dog)

  class Car
  //val newCage = new Cage(new Car) WRONG!

}
