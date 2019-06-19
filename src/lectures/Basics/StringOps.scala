package lectures.Basics

object StringOps extends App {
  val str = "Hello Scala"

  println(str.charAt(1))
  println(str.split(" ").toList)
  println(str.reverse)

  val aNumberString = "2"
  val aNumber = aNumberString.toInt
  println('a' /*prepend*/ +: aNumberString :+ /*append*/  'z')

  //Scala Specific: String interpolators

  val name = "Mikee"
  val age = 23

  //S interpolators
  val greeting = s"Hello my name is $name and I am $age years old"
  val anotherGreeting = s"Hello my name is $name and I will be turning ${age+1} years old"
  //you can add any expression inside ${ }

  println(greeting)
  println(anotherGreeting)

  //F interpolators
  val speed = 1.2f
  val myth = f"$name can eat $speed%2.2f burgers per minute." //%(characters.precision)f

  println(myth)

  //raw interpolators //ignores escape characters in the string. otherwise injected chars will get escaped
  println(raw"This is a \n newline") //will not escape characters
  val escaped = "This is a \n newline"
  println(raw"$escaped") //will get escaped
}
