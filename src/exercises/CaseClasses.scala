package exercises

object CaseClasses extends App {
  abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): MyList[B]
    def printElements: String
    override def toString: String = "["+ printElements + "]"

    def map[B](transformer: MyTransformer[A, B]): MyList[B]
    def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
    def filter(predicate: MyPredicate[A]): MyList[A]

    def ++[B>:A](list: MyList[B]): MyList[B]
  }

  case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](element: B): MyList[B] = Cons(element, this)
    def printElements: String =
      if (t.isEmpty) "" + h
      else h + " " + t.printElements

    def map[B](transformer: MyTransformer[A, B]): MyList[B] =
      new Cons(transformer.transform(h), t.map(transformer))

    def filter(predicate: MyPredicate[A]): MyList[A] =
      if (predicate.test(h)) Cons(h, t.filter(predicate))
      else t.filter(predicate)

    def ++[B>:A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)

    def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
      transformer.transform(h) ++ t.flatMap(transformer)
  }

  case object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B >: Nothing](element: B): MyList[B] = Cons(element, this)
    def printElements: String = ""

    def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
    def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
    def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

    def ++[B>:Nothing](list: MyList[B]): MyList[B] = list
  }

  trait MyPredicate[-T] {
    def test(p: T): Boolean
  }

  trait MyTransformer[-A, B] {
    def transform(p: A): B
  }

  val intList: MyList[Int] = Cons(1, Cons(2, Cons(3, Cons(4, Empty))))
  val intList2: MyList[Int] = Cons(1, Cons(2, Cons(3, Cons(4, Empty))))

  val charList: MyList[String] = Cons("4", Cons("3", Cons("2", Cons("1", Empty))))

  println(intList.map((p: Int) => p * 2).toString)
  println(intList.filter((p: Int) => p % 2 == 0).toString)
  println(intList.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(p: Int): MyList[Int] = Cons(p, Cons(p + 1, Empty))
  }).toString)

  println(intList == intList2) //true
}
