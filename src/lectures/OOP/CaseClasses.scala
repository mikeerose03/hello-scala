package lectures.OOP

object CaseClasses extends App {
  /*
  * Case Classes
  * are an ideal solution for defining classes and companions in one go
  * */

  case class Person(name: String, age: Int)
  /*
    this declaration does lots of things.. like:
    1. Class params are converted to fields
    2. sensible toString //println(bob.toString) = println(bob) = Person(Bob, 24)
    // this is another type of syntactic sugar
    3. equals and hashCode are implemented OUT OF THE BOX.
   */

  val jim = new Person("jim", 34)
  val jim2 = new Person("jim", 34)
  println(jim == jim2) // true (for noncase classes, println will return false)

  // 4. Case Classes have handy copy methods
  val jim3 = jim.copy(age = 45) //person("jim", 45)

  // 5. Case Classes have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23) // Person.apply(...)

  // 6. CCs (Case Classes) are serializable
  // This is very important when using AKKA

  //7. CCs have extractor patterns
  // CCs can be used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The United Kingdom of GB and NI"
  }
}
