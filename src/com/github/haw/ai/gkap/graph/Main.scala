package com.github.haw.ai.gkap.graph

import scala.collection.immutable.Set

object Main {
  def main(args : Array[String]) : Unit = {
    val v = Vertex(1)
    val w = Vertex(2)
    val e = UndirectedEdge(v, w, "EDGE")
    val g : Graph[Int,String] = Graph(Set(v,w), Set(e))
    
    println(g)
  }
}


