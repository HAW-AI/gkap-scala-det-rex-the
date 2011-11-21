package com.github.haw.ai.gkap.graph

case class GraphImpl[V, E](override val vertices : Set[Vertex[V]], override val edges : Set[Edge[E, V]]) extends Graph[V, E] {
  def degree(v : Vertex[V]) : Int =
    edges.filter(_.vertices.contains(v)).size
   
  def isAdjacent(v : Vertex[V], w : Vertex[V]) : Boolean =
    edges.exists(_.vertices == Set(v,w))
  
  def isIncident(v : Vertex[V], e : Edge[E, V]) : Boolean =
    e.vertices.contains(v)
      
  def adjacent(v : Vertex[V]) : Set[Vertex[V]] =
    vertices.filter(isAdjacent(v, _)) - v
  
  def incident(v : Vertex[V]) : Set[Edge[E, V]] =
    edges.filter(isIncident(v, _))
  
  override def toString : String =
    "G({" + vertices.mkString(", ") + "}, {" + edges.mkString(", ") + "})"
}
