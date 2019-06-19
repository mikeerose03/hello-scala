package exercises

object OOPExercise1 extends App {
  val john = new Writer("John", "Green", 1989)
  val theFaultInOurStars = new Novel("The Fault In Our Stars", 2014, john)

  println(john.fullname)

  var counter = new Counter(0)

  counter.increment(10).print
}

class Writer (firstname: String, surname: String, val year: Int) {

  def fullname(): String = firstname+" "+surname
}

class Novel (name: String, yearOfRelease: Int, author: Writer) {

  def authorAge(): Int = yearOfRelease - author.year

  def isWrittenBy(author: Writer): Boolean = this.author == author

  def copy(year: Int): Novel = new Novel(name, year, author)
}

class Counter (n: Int) {

  def count(): Int = n

  def increment(): Counter = new Counter(n+1) //immutability
  //whenever you need to modify a field, you need to declare it in a new instance

  def decrement(): Counter = new Counter(n-1)

  def increment(amount: Int): Counter = new Counter(n+amount)

  def decrement(amount: Int): Counter = new Counter(n-amount)

  def print = println(n)
}
