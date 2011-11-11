package com.github.haw.ai.gkap.graph

import util._

import scala.collection.immutable.Set


final class UndirectedEdge[E, V](left : Vertex[V], right : Vertex[V], content : E) extends AbstractEdge[E, V](left, right, content, false) {
  def isReachable(from : Vertex[V], to : Vertex[V]) : Boolean =
    vertices.contains(from) && vertices.contains(to)
    
  override def equals(o : Any) : Boolean = o match {
    case that : AnyRef if this eq that => true
    case UndirectedEdge(vs, c) => vs == vertices && c == content
    case _ => false
  }
  
  override def hashCode : Int =
    41 * (
      41 + vertices.hashCode
    ) + content.hashCode
}

object UndirectedEdge {
  def apply[E, V](left : Vertex[V], right : Vertex[V], content : E) =
    new UndirectedEdge(left, right, content)
  
//  def unapply[E, V](edge : Edge[E, V]) : Option[(Vertex[V], Vertex[V], E)] =
//    someIf(!edge.isDirected, (edge.left, edge.right, edge.content))
    
  def unapply[E, V](edge : Edge[E, V]) : Option[(Set[Vertex[V]], E)] =
    someIf(!edge.isDirected, (edge.vertices, edge.content))
}