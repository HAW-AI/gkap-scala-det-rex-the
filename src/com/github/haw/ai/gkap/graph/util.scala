package com.github.haw.ai.gkap.graph

object util {
    def someIf[A](cond : Boolean, x : => A) : Option[A] =
      if (cond) Some(x) else None
}