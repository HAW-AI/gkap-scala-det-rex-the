package com.github.haw.ai.gkap.graph

import scala.collection.immutable.Set

trait Edge[E, V] {
  def content : E
  def vertices : Set[Vertex[V]]
  def left : Vertex[V]
  def right : Vertex[V]
  def otherVertex(v : Vertex[V]) : Option[Vertex[V]]
  def isReachable(from : Vertex[V], to : Vertex[V]) : Boolean
  def isDirected : Boolean
  def toString : String
}


object UndirectedLoop {
  def apply[E, V](vertex : Vertex[V], content : E) =
    UndirectedEdge(vertex, vertex, content)
}

object DirectedLoop {
  def apply[E, V](vertex : Vertex[V], content : E) =
    DirectedEdge(vertex, vertex, content)
}