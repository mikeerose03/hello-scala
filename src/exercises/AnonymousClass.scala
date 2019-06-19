package exercises

object AnonymousClass extends App {

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

  class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](element: B): MyList[B] = new Cons(element, this)
    def printElements: String =
      if (t.isEmpty) "" + h
      else h + " " + t.printElements

    def map[B](transformer: MyTransformer[A, B]): MyList[B] =
      new Cons(transformer.transform(h), t.map(transformer))

    def filter(predicate: MyPredicate[A]): MyList[A] =
      if (predicate.test(h)) new Cons(h, t.filter(predicate))
      else t.filter(predicate)

    def ++[B>:A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

    def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
      transformer.transform(h) ++ t.flatMap(transformer)

    /*
      [1,2].flatMap(n => [n, n+1])
      = [1,2] ++ [2].flatMap(n => [n, n+1])
      = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
      = [1,2] ++ [2,3] ++ Empty
      = [1,2,2,3]
     */
  }

  object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B >: Nothing](element: B): MyList[B] = new Cons(element, this)
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


/*
  trait MyPredicate[-T] {
    def test(p: T): Boolean
  }

  trait MyTransformer[-A, +B] {
    def transform(p: A): B
  }

  abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): MyList[B]
    def printElements: String
    override def toString: String = "["+ printElements + "]"

    def map[B >: A](transformer: MyTransformer[A, B]): MyList[B]
    def filter(predicate: MyPredicate[A]): MyList[A]
    def flatMap[B >: A](transformer: MyTransformer[A, B]): MyList[B]
  }

  class EvenPredicate extends MyPredicate[Int] {
    def test(p: Int): Boolean = p%2 == 0
  }

  class StringToInt extends MyTransformer[String, Int] {
    def transform(p: String): Int = p.toInt
  }

  class IntToSubList extends MyTransformer[Int, MyList[Int]] {
    def transform(p: Int): MyList[Int] = new Cons(p, new Cons(p+1, Empty))
  }

  class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](element: B): MyList[B] = new Cons(element, this)
    def printElements: String =
      if (t.isEmpty) "" + h
      else h + " " + t.printElements

    def map[B >: A](transformer: MyTransformer[A, B]): MyList[B] = {
      def transformMap(list: MyList[A], accumulator: MyList[B]): MyList[B] = {
        if (list.isEmpty) accumulator
        else transformMap(list.tail, accumulator.add(transformer.transform(list.head)))
      }
      transformMap(this, Empty)
    }

    def filter(predicate: MyPredicate[A]): MyList[A] = {
      def predicateFilter(list: MyList[A], accumulator: MyList[A]): MyList[A] = {
        if (list.isEmpty) accumulator
        else {
          if (predicate.test(list.head)) predicateFilter(list.tail, accumulator.add(list.head))
          else predicateFilter(list.tail, accumulator)
        }
      }
      predicateFilter(this, Empty)
    }

    def flatMap[B >: A](transformer: MyTransformer[A, B]): MyList[B] = {
      def map[B >: A](list: MyList[A], accumulator: MyList[B]): MyList[B] = {
        if (list.isEmpty) accumulator
        else map(list.tail, new Cons(list.head, accumulator.add(transformer.transform(list.head))))
      }
      map(this, Empty)
    }
  }

  object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B >: Nothing](element: B): MyList[B] = new Cons(element, this)
    def printElements: String = ""

    def map[B >: Nothing](transformer: MyTransformer[Nothing, B]): MyList[Nothing] = this
    def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = this
    def flatMap[B >: Nothing](transformer: MyTransformer[Nothing, B]): MyList[B] = this
  }

  val intList: MyList[Int] = new Cons(4, new Cons(3, new Cons(2, new Cons(1, Empty))))
  val charList: MyList[String] = new Cons("4", new Cons("3", new Cons("2", new Cons("1", Empty))))

  val evenPredicate = new EvenPredicate
  val stringToInt = new StringToInt
  val intToSubList = new IntToSubList
  println(intList.filter(evenPredicate).toString)
  println(intList.flatMap(intToSubList).toString)
  println(charList.map(stringToInt).toString)

 */

  val intList: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, new Cons(4, Empty))))
  val charList: MyList[String] = new Cons("4", new Cons("3", new Cons("2", new Cons("1", Empty))))

  println(intList.map(new MyTransformer[Int, Int] {
    override def transform(p: Int): Int = p * 2
  }).toString) // same as println(intList.map((p: Int) => p * 2).toString)

  println(intList.filter(new MyPredicate[Int] {
    override def test(p: Int): Boolean = p % 2 == 0
  }).toString) // same as println(intList.filter((p: Int) => p % 2 == 0).toString)

  println(intList.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(p: Int): MyList[Int] = new Cons(p, new Cons(p + 1, Empty))
  }).toString)

}
