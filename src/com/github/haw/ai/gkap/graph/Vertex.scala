package com.github.haw.ai.gkap.graph

trait Vertex[V] {
  def content : V
}

object Vertex {
  def apply[V](content : V) = new VertexImpl(content)
}
