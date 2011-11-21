package com.github.haw.ai.gkap.graph.algorithms

import org.junit.Assert._

import org.junit.Before
import org.junit.Test

import com.github.haw.ai.gkap.graph._
import com.github.haw.ai.gkap.graph.algorithms.Dijkstra.dijkstra

class DijkstraTest {
  var g : Graph[Int, Int] = _
  var v1, v2, v3, v4, v5, v6 : Vertex[Int] = _
  var e12, e16, e23, e25, e26, e34, e35, e36, e45, e56 : Edge[Int, Int] = _
  var l : (Edge[Int, Int] => Int) = _

  @Before
  def setUp = {
    v1 = Vertex(1)
    v2 = Vertex(2)
    v3 = Vertex(3)
    v4 = Vertex(4)
    v5 = Vertex(5)
    v6 = Vertex(6)

    e12 = UndirectedEdge(v1, v2, 1)
    e16 = UndirectedEdge(v1, v6, 3)
    e23 = UndirectedEdge(v2, v3, 5)
    e25 = UndirectedEdge(v2, v5, 3)
    e26 = UndirectedEdge(v2, v6, 2)
    e34 = UndirectedEdge(v3, v4, 1)
    e35 = UndirectedEdge(v3, v5, 2)
    e36 = UndirectedEdge(v3, v6, 2)
    e45 = UndirectedEdge(v4, v5, 3)
    e56 = UndirectedEdge(v5, v6, 1)

    g = Graph(Set(v1, v2, v3, v4, v5, v6), Set(e12, e16, e23, e25, e26, e34, e35, e36, e45, e56))

    l = _.content
  }

  @Test
  def testDijkstra: Unit = {
    assertEquals((List(v1, v6, v3, v4), 6), dijkstra(g, v1, v4, l))
  }
}