package com.github.haw.ai.gkap.graph

import util._ 

final class DirectedEdge[E, V](left : Vertex[V], right : Vertex[V], content : E) extends AbstractEdge[E, V](left, right, content, true) {
  def isReachable(from : Vertex[V], to : Vertex[V]) : Boolean =
    from == left && to == right
    
  override def equals(o : Any) : Boolean = o match {
    case that : AnyRef if this eq that => true
    case DirectedEdge(l, r, c) => l == left && r == right && c == content
    case _ => false
  }
  
  override def hashCode : Int =
    41 * (
      41 * (
        41 + left.hashCode
      ) + right.hashCode
    ) + content.hashCode
}

object DirectedEdge {
  def apply[E, V](left : Vertex[V], right : Vertex[V], content : E) =
    new DirectedEdge(left, right, content)
  
  def unapply[E, V](edge : Edge[E, V]) : Option[(Vertex[V], Vertex[V], E)] =
    someIf(edge.isDirected, (edge.left, edge.right, edge.content))
}

