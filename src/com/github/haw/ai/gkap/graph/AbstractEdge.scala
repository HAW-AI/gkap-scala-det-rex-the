package com.github.haw.ai.gkap.graph

abstract case class AbstractEdge[E, V](left : Vertex[V], right : Vertex[V], content : E, isDirected : Boolean) extends Edge[E, V] {
  def vertices : Set[Vertex[V]] = Set(left, right)
  
  def otherVertex(v : Vertex[V]) : Option[Vertex[V]] = v match {
    case `left`  => Some(right)
    case `right` => Some(left)
    case _       => None
  }
  
  override def toString : String =
    "E(" + content + ", " + verticesString + ")"
  
  def verticesString : String
}
