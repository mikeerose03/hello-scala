package lectures.OOP

import exercises.Writer
import playground.{PrinceCharming, Princess => Cinderella /* alias */ } // playground._ imports all
import java.util.Date
import java.sql.{ Date => SqlDate }

object PackagingAndImports extends App {
  // package members are accessible by their simple name

  // packages can be imported
  val writer = new Writer("George", "Martin", 55)

  // packages are  in hierarchy
  // matching folder structure.

  // package object
  // a package can only contain one package of the same name

  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming
  val princess = new Cinderella //Princess class

  // problem: the compiler will assume that this is the date referred in the first import
  val date = new Date
  // first option to solve this is to use fully qualified name
  // val sqlDate = new java.sql.Date(2018, 5, 4)
  //val sqlDate = new SqlDate

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef = println, ???
}
