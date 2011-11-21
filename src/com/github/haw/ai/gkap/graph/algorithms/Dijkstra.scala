package com.github.haw.ai.gkap.graph.algorithms

import com.github.haw.ai.gkap.graph._
import com.github.haw.ai.gkap.graph.util.until

object Dijkstra {
  //                  vs,             dists,               preds
  type StepData[V] = (Set[Vertex[V]], Map[Vertex[V], Int], Map[Vertex[V], Option[Vertex[V]]])

  def dijkstra[V, E](g: Graph[V, E], from: Vertex[V], to: Vertex[V], l: Edge[E, V] => Int) : (List[Vertex[V]], Int) = {
    def allOK(t: StepData[V]) = t._1.size == 0
    val (_, dists, preds) = until(allOK, initData(g, from)) { args =>
      val (vs, dists, preds) = args
      val minV = vs.minBy(dists)
      val ves  = neighbors(g, minV)

      val (dists_, preds_) = ves.foldLeft(dists, preds) { (dp, ve) =>
        val ((d, p), (v, e)) = (dp, ve)
        val dist = dists(minV) + l(e)

        dists(v) > dist match {
          case true => (d + ((v, dist)), p + ((v, Some(minV))))
          case false => dp
        }
      }

      (vs - minV, dists_, preds_)
    }

    (path(from, to, preds), dists(to))
  }

  def path[V](from: Vertex[V], v: Vertex[V], preds: Map[Vertex[V], Option[Vertex[V]]]): List[Vertex[V]] = {
    def rPath(v: Vertex[V]): List[Vertex[V]] = v match {
      case `from` => List(v)
      case _ => v :: rPath(preds(v).get)
    }

    rPath(v).reverse
  }

  def initData[V, E](g: Graph[V, E], v: Vertex[V]): StepData[V] = {
    val dists = g.vertices.zip(Seq.fill(g.edges.size - 1)(Int.MaxValue)).toMap + ((v, 0))
    val preds = g.vertices.zip(Seq.fill(g.edges.size - 1)(None)).toMap + ((v, Some(v)))
    (g.vertices, dists, preds)
  }

  def neighbors[V, E](g: Graph[V, E], v: Vertex[V]): Set[(Vertex[V], Edge[E, V])] = {
    val es = g.incident(v).filter(e => e.isReachable(v, e.otherVertex(v).get))
    val vs = es.map(_.otherVertex(v).get)
    vs.zip(es)
  }
}