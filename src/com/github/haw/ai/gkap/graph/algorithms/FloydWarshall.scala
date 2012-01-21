package com.github.haw.ai.gkap.graph.algorithms

import scala.collection.immutable.Set

import com.github.haw.ai.gkap.graph._

object FloydWarshall {
  def floydWarshall[V, E](g: Graph[V, E], from: Vertex[V], to: Vertex[V], l: Edge[E, V] => Int): (List[Vertex[V]], Int) = {
    val vs = g.vertices.toArray
    val d = Seq.fill(vs.length)(Seq.fill(vs.length)(Int.MaxValue).toArray).toArray
    val t = Seq.fill(vs.length)(Seq.fill(vs.length)(0).toArray).toArray
    val idxs = 0 until vs.length

    for (i <- idxs; j <- idxs) {
      val (left, right) = (vs(i), vs(j))
      if (i == j) d(i)(j) = 0
      else g.edges.find(_ match {
        case DirectedEdge(`left`, `right`, _) => true
        case UndirectedEdge(edgeVs, _) => edgeVs == Set(left, right)
        case _ => false
      }).foreach { e =>
        d(i)(j) = l(e)
      }
    }

    for (i <- idxs) {
      for (j <- idxs; k <- idxs) {
        if (i != j && i != k) {

          val (oldDist, newDistA, newDistB) = (d(j)(k), d(j)(i), d(i)(k))
          if (newDistA != Int.MaxValue && newDistB != Int.MaxValue &&
              oldDist > newDistA + newDistB)
          {
            d(j)(k) = newDistA + newDistB
            t(j)(k) = i+1
          }
        }
      }
      
      if (d(i)(i) < 0) return (Nil, -1)
    }

    val (fromIdx, toIdx) = (indexOf(vs, from), indexOf(vs, to))
    val pathPoints = path(t, fromIdx+1, toIdx+1).map(x => vs(x-1))
    val pathLength = d(fromIdx)(toIdx)
    
    (pathPoints, pathLength)
  }

  def printMatrix[T](m: Array[Array[T]], len: Int): Unit = {
    for (i <- (0 until len)) {
      for (j <- (0 until len)) {
        print(m(i)(j) + ", ")
      }
      println
    }
    println
  }

  def indexOf[T](xs: Array[T], x: T): Int = {
    for (i <- (0 until xs.length)) {
      if (xs(i) == x) return i;
    }
    -1;
  }
  
  def path(t : Array[Array[Int]], from : Int, to : Int) : List[Int] = {
    if (from == to || from == 0 || to == 0)
      Nil
    else {
      val through = t(from-1)(to-1)
      if (through == 0) List(from, to)
      else path(t, from, through) ::: path(t, through, to).tail
    }
  }

}