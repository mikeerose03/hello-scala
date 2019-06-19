package exercises

object MapFlatMapFilterFor extends App {
  abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): MyList[B]
    def printElements: String
    override def toString: String = "["+ printElements + "]"

    def map[B](transformer: A => B): MyList[B]
    def flatMap[B](transformer: A => MyList[B]): MyList[B]
    def filter(predicate: A => Boolean): MyList[A]

    def ++[B>:A](list: MyList[B]): MyList[B]

    def forEach(f: A => Unit): Unit
    def sort(f: (A, A) => Int): MyList[A]
    def zipWith[B, C](l: MyList[B], f: (A, B) => C): MyList[C]
    def fold[B](start: B)(f: (B, A) => B): B
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

    def forEach(f: A => Unit): Unit = {
      f(h)
      t.forEach(f)
    }

    def sort(f: (A, A) => Int): MyList[A] = {
      def insert(x: A, sl: MyList[A]): MyList[A] = {
        if (sl.isEmpty) new Cons(x, Empty)
        else if (f(x, sl.head) <= 0) new Cons(x, sl)
        else new Cons(sl.head, insert(x, sl.tail))
      }

      val sortedTail = t.sort(f)
      insert(h, sortedTail)
    }

    def zipWith[B, C](l: MyList[B], f: (A, B) => C): MyList[C] = {
      if (l.isEmpty) throw new RuntimeException("Lists do not have the same length!")
      else new Cons(f(h, l.head), t.zipWith(l.tail, f))
    }


    def fold[B](start: B)(f: (B, A) => B): B =
      t.fold(f(start, h))(f)

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

    def forEach(f: Nothing => Unit): Unit = ()
    def sort(f: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
    def zipWith[B, C](l: MyList[B], f: (Nothing, B) => C): MyList[C] = {
      if (!l.isEmpty) throw new RuntimeException("Lists do not have the same length!")
      else Empty
    }
    def fold[B](start: B)(f: (B, Nothing) => B): B = start
  }

  val numbers = new Cons(1, new Cons(2, new Cons(3, new Cons(4, Empty))))
  val chars = new Cons('a', new Cons('b', new Cons('c', new Cons('d', Empty))))

  // 1. MyList in for-comprehension
  val forCombinations = for {
    n <- numbers
    c <- chars
  } yield "" + c + n

  println(forCombinations)

  // 2.

  abstract class Maybe[+T] {
    def map[B](transformer: T => B): Maybe[B]
    def flatMap[B](transformer: T => Maybe[B]): Maybe[B]
    def filter(predicate: T => Boolean): Maybe[T]
  }

  case object MaybeNot extends Maybe[Nothing] {
    def map[B](transformer: Nothing => B): Maybe[B] = MaybeNot
    def flatMap[B](transformer: Nothing => Maybe[B]): Maybe[B] = MaybeNot
    def filter(predicate: Nothing => Boolean): Maybe[Nothing] = MaybeNot
  }

  case class Just[+T](value: T) extends Maybe[T] {
    def map[B](transformer: T => B): Maybe[B] = new Just(transformer(value))
    def flatMap[B](transformer: T => Maybe[B]): Maybe[B] = transformer(value)
    def filter(predicate: T => Boolean): Maybe[T] =
      if (predicate(value)) this
      else MaybeNot
  }

  /*
  abstract class ACollection[+T] {
    def head: T
    def map[B](transformer: T => B): ACollection[B]
    def flatMap[B](transformer: T => ACollection[B]): ACollection[B]
    def filter(predicate: T => Boolean): ACollection[T]
  }

  class Maybe[+T](el: T) extends ACollection[T] {
    def head: T = el
    def map[B](transformer: T => B): ACollection[B] = new Maybe(transformer(el))
    def flatMap[B](transformer: T => ACollection[B]): ACollection[B] = transformer(el)
    def filter(predicate: T => Boolean): ACollection[T] =
      if (predicate(el)) new Maybe(el)
      else EmptyCollection
  }

  object EmptyCollection extends ACollection[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def map[B](transformer: Nothing => B): ACollection[B] = EmptyCollection
    def flatMap[B](transformer: Nothing => ACollection[B]): ACollection[B] = EmptyCollection
    def filter(predicate: Nothing => Boolean): ACollection[Nothing] = EmptyCollection
  }
   */

  val just3 = new Just(3)
  println(just3.map(_ * 2))
  println(just3.flatMap(x => Just(x % 2 == 0)))
  println(just3.filter(x => x % 2 == 0))
}
