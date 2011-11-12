package com.github.haw.ai.gkap.graph

final case class VertexImpl[V](content : V) extends Vertex[V] {
  override def toString : String =
    "V(" + content + ")"
}
