package exercises

object AnonymousFunctions extends App {

  //1
  abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): MyList[B]
    def printElements: String
    //polymorphic call
    override def toString: String = "["+ printElements + "]"

    //higher-order functions
    def map[B](transformer: A => B): MyList[B]
    def flatMap[B](transformer: A => MyList[B]): MyList[B]
    def filter(predicate: A => Boolean): MyList[A]

    def ++[B>:A](list: MyList[B]): MyList[B]
  }

  class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](element: B): MyList[B] = new Cons(element, this)
    def printElements: String =
      if (t.isEmpty) "" + h
      else h + " " + t.printElements

    def map[B](transformer: A => B): MyList[B] =
      new Cons(transformer(h), t.map(transformer))

    def filter(predicate: A => Boolean): MyList[A] =
      if (predicate(h)) new Cons(h, t.filter(predicate))
      else t.filter(predicate)

    def ++[B>:A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

    def flatMap[B](transformer: A => MyList[B]): MyList[B] =
      transformer(h) ++ t.flatMap(transformer)
  }

  object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B >: Nothing](element: B): MyList[B] = new Cons(element, this)
    def printElements: String = ""

    def map[B](transformer: Nothing => B): MyList[B] = Empty
    def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
    def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

    def ++[B>:Nothing](list: MyList[B]): MyList[B] = list
  }

  val intList: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, new Cons(4, Empty))))
  val charList: MyList[String] = new Cons("4", new Cons("3", new Cons("2", new Cons("1", Empty))))

  println(intList.map(_ * 2).toString)
  println(intList.filter(_ % 2 == 0).toString)
  println(intList.flatMap((p: Int) => new Cons(p, new Cons(p + 1, Empty))).toString)

  // 2
  def incrementBy(n1: Int) = (n2: Int) => n1+n2
  println(incrementBy(2)(2))
}
