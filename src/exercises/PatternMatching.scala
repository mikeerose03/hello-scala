package exercises

object PatternMatching extends App {
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def interpret(exp: Expr): String = {
    exp match {
      case Number(n) => s"$n"
      case Sum(n1, n2) => s"${interpret(n1)} + ${interpret(n2)}"
      case Prod(n1, n2) => s"${interpret(n1)} * ${interpret(n2)}"
    }
  }

  def show(exp: Expr): String = exp match {
    case Number(n) => s"$n"
    case Sum(n1, n2) => s"${show(n1)} + ${show(n2)}"
    case Prod(n1, n2) => {
      def maybeShowParentheses(exp: Expr): String = exp match {
        case Prod(_, _) => show(exp)
        case Number(_) => show(exp)
        case _ => s"(${show(exp)})"
      }

      maybeShowParentheses(n1) + " * " + maybeShowParentheses(n2)
    }
    case _ => ""
  }


  println(interpret(Sum(Number(3), Number(4))))
  println(interpret(Prod(Number(3), Number(4))))
  println(interpret(Prod(Sum(Number(3), Number(4)), Number(4))))

  println(show(Sum(Number(3), Number(4))))
  println(show(Prod(Number(3), Number(4))))
  println(show(Prod(Sum(Number(3), Number(4)), Number(4))))
}
