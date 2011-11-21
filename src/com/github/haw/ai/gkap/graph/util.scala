package com.github.haw.ai.gkap.graph

object util {
    def someIf[A](cond : Boolean, x : => A) : Option[A] =
      if (cond) Some(x) else None
    
    def until[A](p : A => Boolean, x : A)(f : A => A) : A =
      if (p(x)) x else until(p, f(x))(f)
}