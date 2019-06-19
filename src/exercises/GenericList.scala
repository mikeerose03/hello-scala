package exercises

object GenericList extends App {

  abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): MyList[B]
    def printElements: String
    override def toString: String = "["+ printElements + "]"
  }

  class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](element: B): MyList[B] = new Cons(element, this)
    def printElements: String =
      if (t.isEmpty) "" + h
      else h + " " + t.printElements
  }

  object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B >: Nothing](element: B): MyList[B] = new Cons(element, this)
    def printElements: String = ""
  }

/*
  abstract class MyList[+A] {
    def add[B >: A](element: B): MyList[B]
    def isEmpty: Boolean
    def printElements: String
    override def toString: String = "["+ printElements + "]"
  }

  class Cons[+A](h: A, t: MyList[A]) extends MyList {
    def add[A](element: A): MyList[A] = new Cons(element, this)
    def isEmpty: Boolean = false
    def printElements: String =
      if (t.isEmpty) "" + h
      else h + " " + t.printElements
  }

  object Empty extends MyList {
    def add[A](element: A): MyList[A] = new Cons(element, Empty)
    def isEmpty: Boolean = true
    def printElements: String = ""
  }



//  object MyList {
//    def empty[A]: MyList[A] = new MyList
//  }
*/
  val intList: MyList[Any] = new Cons(1, Empty)
  val charList: MyList[Any] = new Cons("b", Empty)

  println(intList.add(2).toString)
  println(charList.add("a").toString)
}
