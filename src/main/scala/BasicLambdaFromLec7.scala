object BasicLambdaFromLec7 {
//  λf. λx.f(f x) or λfx.f(f x)
  val lambdaExp = (f:Int=>Int)=>(x:Int)=>f(f(x))

  val _f: Int=>Int = x=>x+1
  val ff: Int=>Int  = i=>_f(_f(i))
  class DoubleApp extends Function1[Int,Int] {
    override def apply(v1: Int): Int = ff(v1)
  }

  def applyFuncNtimes(n: Int, f:Int=>Int): Int=>Int = {
    require(n>=0)
    if n == 0 then (x:Int)=>x else (i:Int)=>applyFuncNtimes(n-1,f)(f(i))
  }
  // applyFuncNtimes(3, x+1)
//  s1: i=>applyFuncNtimes(2,x+1)f(i)
//  s2: i=>(i=>applyFuncNtimes(1,x+1)f(i)))f(i))
//  s3: i=>(i=>(i=>applyFuncNtimes(0,x+1)f(i))f(i)))f(i))
//  s4: i=>(i=>(i=>((x:Int)=>x) f(i))f(i)))f(i))
//

  def composer[A,B,C](f: B =>C, g: A =>B): A=>C =
    (i:A)=>f(g(i))

  val fff: Int => Int = (i:Int)=> _f(ff(i))
  
  def plainOldFunction(i:Int):Int = i+1
  def plainOldFunction4Functions(f: String=>Int=>String, s:String, p:Int):Int=>String =
    (i:Int)=>f(s)(p).concat("::cs474")
  
  val intVar:Int = 6
  val result = plainOldFunction(intVar)
  
  def main(args: Array[String]): Unit = {
    println {
      plainOldFunction4Functions((s: String) => (i: Int) => (s.length + i).toString, "Mark", 10)(2)
    }
    println(lambdaExp(x=>x+10)(2))
    println(_f(2))
    println(ff(2))
    println(fff(2))
    val d = new DoubleApp
    println(d(2))
    println("fN: " + applyFuncNtimes(3, x=>x+1)(10))
    println{
      composer((i:Int)=>i.toString.reverse, (s:String)=>s.length)("Mark CS474 Isaac")
    }
  }
}
