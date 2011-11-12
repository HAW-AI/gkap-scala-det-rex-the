package com.github.haw.ai.gkap.graph

trait Graph[V, E] {
  def degree(v : Vertex[V]) : Int
  def isAdjacent(v : Vertex[V], w : Vertex[V]) : Boolean
  def isIncident(v : Vertex[V], e : Edge[E, V]) : Boolean
  def adjacent(v : Vertex[V]) : Set[Vertex[V]]
  def incident(v : Vertex[V]) : Set[Edge[E, V]]
  def toString : String
}

object Graph {
  def apply[V, E](vertices : Set[Vertex[V]], edges : Set[Edge[E, V]]) =
    new GraphImpl(vertices, edges)
}