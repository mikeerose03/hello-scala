package exercises

/*
case class Network(people: Map[String, Person]) {
  def add(person: Person): Network = Network(people + (person.name -> person))
  def remove(person: Person): Network = Network(people.filterKeys(f => f.equals(person.name)))

  def addFriend(person: Person, friend: Person): Network = {
    val newNetwork = remove(person).add(person.addFriend(friend))
    newNetwork.remove(friend).add(friend.addFriend(person))
  }
  def unfriend(person: Person, friend: Person): Network = remove(person).add(person.unfriend(friend))

//  def mostFriendly(): String = people.reduce((p1: Person, p2: Person) => {
//    if (p1.friendsCount > p2.friendsCount) p1.name
//    else p2.name
//  })
  def friends(person: Person): String = person.allFriends
  def friendlessCount(): Int = people.filter(p => p._2.friendsCount == 0).toList.length
}

case class Person(val name: String, friends: Map[String, Person]) {
  def allFriends: String = friends.map(p => p._1).mkString(" ")
  def addFriend(person: Person): Person = Person(name, friends + (person.name -> person))
  def unfriend(person: Person): Person = Person(name, friends.filterKeys(f => f.equals(person.name)))

  def mutualFriends(person: Person): String =
    friends
      .filterKeys(f => person.friends.contains(f))
      .map(p => p._1).mkString(" ")
  def isFriend(person: Person): Boolean = friends.contains(person.name)
  def friendsCount(): Int = friends.toList.length
}
 */

object TuplesAndMaps extends App {

//  val ringo = Person("Ringo", Map())
//  val john: Person = Person("John", Map())
//  val paul: Person = Person("Paul", Map())
//  val george: Person = Person("George", Map())
//
//  var theBeatles: Network = Network(Map("John" -> john, "Paul" -> paul, "George" -> george, "Ringo" -> ringo))
//
//  println(ringo.addFriend(george))

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendA = network(a)
    val friendB = network(b)
    network + (a -> (friendA + b)) + (b -> (friendB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendA = network(a)
    val friendB = network(b)
    network + (a -> (friendA - b)) + (b -> (friendB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    }
    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))

  // jim, bob, mary
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Mary", "Bob")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.count(_._2.isEmpty)

  println(nPeopleWithNoFriends(testNet))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if(consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(network, "Mary", "Bob"))
}

