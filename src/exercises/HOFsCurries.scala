package exercises


object HOFsCurries extends App {
  // 1
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

    def forEach(f: A => Unit): Unit
    def sort(f: (A, A) => Int): MyList[A]
//    def zipWith[B >: A](l: MyList[B], f: (A, B) => B): MyList[B]
    def zipWith[B, C](l: MyList[B], f: (A, B) => C): MyList[C]

    //def fold[B](start: B)(f: (A, B) => B): B
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

    /*
    def forEach(f: A => Unit): Unit = {
      def forEachUntil(l: MyList[A]): Unit = {
        if (!l.isEmpty) {
          f(l.head)
          forEachUntil(l.tail)
        }
      }
      forEachUntil(this)
    }
    */

    def sort(f: (A, A) => Int): MyList[A] = {
      def insert(x: A, sl: MyList[A]): MyList[A] = {
        if (sl.isEmpty) new Cons(x, Empty)
        else if (f(x, sl.head) <= 0) new Cons(x, sl)
        else new Cons(sl.head, insert(x, sl.tail))
      }

      val sortedTail = t.sort(f)
      insert(h, sortedTail)
    }

    /*
    def sort(f: (A, A) => Int): MyList[A] = {
      def sortBy(l: MyList[A], accumulator: MyList[A]): MyList[A] = {
        if (l.isEmpty) accumulator
        else {
          if (l.tail.isEmpty) sortBy(l.tail, accumulator.add(l.head))
          else {
            val res = f(l.head, l.tail.head)
            if (res <= 0) sortBy(l.tail, accumulator.add(l.head))
            else sortBy(new Cons(l.head, l.tail.tail), accumulator.add(l.tail.head))
          }
        }
      }
      sortBy(this, Empty)
    }
    */

    def zipWith[B, C](l: MyList[B], f: (A, B) => C): MyList[C] = {
      if (l.isEmpty) throw new RuntimeException("Lists do not have the same length!")
      else new Cons(f(h, l.head), t.zipWith(l.tail, f))
    }

    /*
    def zipWith[B>:A](l: MyList[B], f: (A, B) => B): MyList[B] = {
      def zipWithUntil(l1: MyList[A], l2: MyList[B], accumulator: MyList[B]): MyList[B] = {
        if (l1.isEmpty && l2.isEmpty) accumulator
        else {
          if (l1.isEmpty) zipWithUntil(Empty, l2.tail, accumulator ++ new Cons(l2.head, Empty))
          else if (l2.isEmpty) zipWithUntil(l1.tail, Empty, accumulator ++ new Cons(l1.head, Empty))
          else zipWithUntil(l1.tail, l2.tail, accumulator ++ new Cons(f(l1.head, l2.head), Empty))
        }
      }
      zipWithUntil(this, l, Empty)
    }
     */

    def fold[B](start: B)(f: (B, A) => B): B =
      t.fold(f(start, h))(f)


    /*
    def fold[B](start: B)(f: (A, B) => B): B = {
      def foldUntil(l: MyList[A], a: B): B = {
        if (l.isEmpty) a
        else foldUntil(l.tail, f(l.head, a))
      }
      foldUntil(this, start)
    }
    */
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
    /*
    def zipWith[B >: Nothing](l: MyList[B], f: (Nothing, B) => B): MyList[B] = {
      def zipWithUntil(l: MyList[B], accumulator: MyList[B]): MyList[B] = {
        if (l.isEmpty) accumulator
        else zipWithUntil(l.tail, accumulator ++ new Cons(l.head, Empty))
      }
      zipWithUntil(this, Empty)
    } */

    def fold[B](start: B)(f: (B, Nothing) => B): B = start
  }

  val intList: MyList[Int] = new Cons(4, new Cons(1, new Cons(3, new Cons(2, Empty))))
  val intList2: MyList[Int] = new Cons(1, new Cons(1, new Cons(1, new Cons(1, Empty))))
  val charList: MyList[String] = new Cons("4", new Cons("3", new Cons("2", new Cons("1", Empty))))

  intList.forEach((h: Int) => println(h+1))
  println(intList.sort((a: Int, b: Int) => b-a).toString)
  println(intList.zipWith(intList, (a: Int, b: Int) => a*b).toString)
  val folded = intList2.fold(0)((a,b) => a+b)
  println(folded)

  // 2
  def toCurry[A](f: (A, A) => A): A => A => A = {
    x: A => y: A => f(x,y)
  }


  def fromCurry[A](f: A => A => A): (A, A) => A = {
    (x: A, y: A) => f(x)(y)
  }

  // 3
  def compose[A, B, T](f: A => B, g: T => A): T => B = x => f(g(x))
  def andThen[A, B, C](f: A => B, g: B => C): A => C = x => g(f(x))

  /*
  def compose[A](f: A => A, g: A => A): A => A = (x: A) => f(g(x))
  def andThen[A](f: A => A, g: A => A): A  => A = (x: A) => g(f(x))
  */

}
