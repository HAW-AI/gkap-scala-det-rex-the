package com.github.haw.ai.gkap.graph.algorithms

import com.github.haw.ai.gkap.graph._

object ShortestPath {
  def dijkstra[V, E](g: Graph[V, E], from: Vertex[V], to: Vertex[V], l: Edge[E, V] => Int) : (List[Vertex[V]], Int) =
    Dijkstra.dijkstra(g, from, to, l)
}