package lectures.FunctionalProgramming

import scala.util.Random

object Sequences extends App {

  // Sequences
  /*
  * A (very) general interface for data structures that
  *   - have a very well defined order
  *   - can be indexed
  *
  * Supports various operations:
  *   - apply, iterator, length, reverse for indexing and iterating
  *   - concatenation, appending, prepending
  *   - a lot of other: grouping, sorting, zipping sorting, slicing
  *
  * */

  val aSequence = Seq(1,2,3,4)
  // Sequence is actually a list
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(4,5,6))
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)
  val anotherRange: Seq[Int] = 1 until 3

  (1 to 3).foreach(x => println("hello"))
  anotherRange.foreach(x => println("world"))

  // List
  /*
  * A Linear immutable linked list
  *   - head, tail, isEmpty methods are fast: O(1)
  *   - most operations are O(n): length, reverse
  *
  * Sealed - has two subtypes
  *   - object Nil (empty)
  *   - class ::
  * */

  val aList = List(1,2,3)
  val prepended1 = 0 :: aList
  val prepended2 = 0 +: aList :+ 4
  // note: the colon ':' is always on the side of the list
  println(prepended1)
  println(prepended2)

  val apples = List.fill(5)("apple")
  println(apples)
  println(aList.mkString("-"))

  // arrays
  /*
  * The equivalent of simple Java arrays
  *   - can be manually constructed with predefined length
  *   - can be mutated (updated in place)
  *   - are interoperable with Java's T[] arrays
  *   - indexing is fast
  * */

  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  threeElements.foreach(print)

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" ")) // implicit conversion

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers
  println(numbersSeq)

  // Vector
  /*
  * The default implementation for immutable sequences
  *   - effective constant indexed read and write
  *   - fast element addition: append/prepend
  *   - implemented as fixed-branched trie (branch factor 32)
  *   - good performance for large sizes
  * */
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vectors vs lists
  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random()
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  println(getWriteTime(numbersList))
  // pro: lists keeps references to tail
  // con: updating an element int he middle takes long
  println(getWriteTime(numbersVector))
  // pro: vector depth of the tree is small
  // con: needs to replace an entire 32-element chunk

  // note: vector is the default implementation of sequence
}

