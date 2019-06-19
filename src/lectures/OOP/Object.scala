package lectures.OOP

object Object {
  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  object Person { //type + is its only instance
    // "static" or "class" level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("bobby")
  }
  class Person(val name: String) {
    // instance level functionality

  }
  // class Person and object Person are COMPANIONS

  // Scala Applications = Scala Object with
  // def main(args: Array[String]): Unit

  def main(args: Array[String]): Unit = {

    //SCALA OBJECT = SINGLETON INSTANCE
    val mary = new Person("mary")
    val john = new Person("john")
    println(mary == john)

    val person1 = Person
    val person2 = Person
    println(person1 == person2)

    val bobbie = Person(mary, john) // Person.apply(mary, john)

  }
}
