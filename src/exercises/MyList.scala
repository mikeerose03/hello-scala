package exercises

abstract class MyList {

  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(element: Int): MyList
  def printElements: String
  override def toString: String = "["+ printElements + "]"
}

object Empty extends MyList {

  def head: Int = throw new NoSuchElementException
  def tail: MyList =  throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(element: Int): MyList = new Cons(element, Empty)
  def printElements: String = ""
}

class Cons(h: Int, t: MyList) extends MyList {

  def head: Int = h
  def tail: MyList =  t
  def isEmpty: Boolean = false
  def add(element: Int): MyList = new Cons(element, this)
  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
}

object ListTest extends App {
  val linkedList = new Cons(1, new Cons(2, new Cons(3, Empty)))

  println(linkedList.head)
  println(linkedList.add(4).head)
  println(linkedList.toString)
}

/* My answer
abstract class MyList(items: Array[Int] = new Array[Int](0)) {
  def head: Int
  def tail: Int
  def isEmpty: Boolean
  def add(item: Int): MyList
  def toString: String
}

class List(items: Array[Int] = new Array[Int](0)) extends MyList {
  def head: Int = items(0)

  def tail: Int = items.last

  def isEmpty: Boolean = items.length == 0

  def add(item: Int): List = new List(items ++ Array(item))

  override def toString: String = {
    def toStringRecurse(accumulator: String, array: Array[Int]): String = {
      println("dropeed", accumulator)
      if (array.length <= 0) accumulator
      else toStringRecurse(array.dropRight(1).toString() + accumulator, array.dropRight(1))
    }
    toStringRecurse("", items)
  }

  def apply(): Unit = println(items)
}

object ListExercise extends App {

  var l = new List(Array(1,2))
//  println("test", l.toString)
  l()
}
*/

