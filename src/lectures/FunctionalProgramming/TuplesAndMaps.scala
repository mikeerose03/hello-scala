package lectures.FunctionalProgramming

object TuplesAndMaps extends App {

  // Tuples - finite ordered lists
  val aTuple = (2, "hello, Scala") // Tuple2[Int, String] = [Int, String]

  println(aTuple._1) // 2
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("hello, Scala", 2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1) //syntax sugar
  println(phonebook)

  // map operations
  println(phonebook.contains("Jim"))
  println(phonebook("Jim"))
  println(phonebook("Mary")) // -1

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  // functionals on maps
  // map, flatMap, filter

  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phonebook.filterKeys(x => x.startsWith("J"))) // can be _.startsWith("J")
  // mapValues
  println(phonebook.mapValues(number => "0245-" + number))

  // conversions to  other collections
  println(phonebook.toList)
  println(List(("Daniel", 555)).toMap) // Map(Daniel -> 555)
  val names = List("Bob", "James", "Angela", "Jim")
  println(names.groupBy(name => name.charAt(0)))
}
