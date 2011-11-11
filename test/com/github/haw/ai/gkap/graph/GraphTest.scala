package com.github.haw.ai.gkap.graph;

import scala.collection.immutable.Set
import scala.collection.mutable.{Set => MSet}

import org.junit.Assert._
import org.junit.Before;
import org.junit.Test;

import com.github.haw.ai.gkap.graph._

class GraphTest {
    var g1, g2 : Graph[String, String] = _
    var v1, v2, v3, v4, v5 : Vertex[String] = _
    var e1, e2, e3, e4, e5 : Edge[String, String] = _
    
    @Before
    def setup() {
        var edges1 : MSet[Edge[String, String]] = MSet()
        var vertices1 : MSet[Vertex[String]] = MSet()
        
        var edges2 : MSet[Edge[String, String]] = MSet()
        var vertices2 : MSet[Vertex[String]] = MSet()
        
        this.v1 = Vertex("V1");
        this.v2 = Vertex("V2");
        this.v3 = Vertex("V3");
        this.v4 = Vertex("V4");
        this.v5 = Vertex("V5");
        this.e1 = DirectedEdge(this.v1, this.v1, "E1");
        this.e2 = DirectedEdge(this.v1, this.v2, "E2");
        this.e3 = DirectedEdge(this.v2, this.v3, "E3");
        this.e4 = DirectedEdge(this.v5, this.v3, "E4");
        this.e5 = DirectedEdge(this.v1, this.v5, "E5");
        
        edges1.add(e1);
        vertices1.add(v1);
        this.g1 = Graph(vertices1.toSeq.toSet, edges1.toSeq.toSet)
        
        vertices2.add(v1);
        vertices2.add(v2);
        vertices2.add(v3);
        vertices2.add(v4);
        vertices2.add(v5);
        edges2.add(e1);
        edges2.add(e2);
        edges2.add(e3);
        edges2.add(e4);
        edges2.add(e5);
        this.g2 = Graph(vertices2.toSeq.toSet, edges2.toSeq.toSet)
    }
    
    @Test
    def testDegree() = {
        // public int degree(Vertex<V> vertex);
        assertEquals(1, this.g1.degree(this.v1));
        
        assertEquals(3, this.g2.degree(this.v1));
        assertEquals(2, this.g2.degree(this.v2));
        assertEquals(2, this.g2.degree(this.v3));
    }
    
    @Test
    def testIsAdjacent() = {
        // public boolean isAdjacent(Vertex<V> vertex, Vertex<V> otherVertex);
        assertTrue(this.g1.isAdjacent(v1, v1));
        assertFalse(this.g1.isAdjacent(v1, v2));
        
        assertTrue(this.g2.isAdjacent(v1, v1));
        assertTrue(this.g2.isAdjacent(v1, v2));
        assertTrue(this.g2.isAdjacent(v2, v1));
        assertTrue(this.g2.isAdjacent(v2, v3));
        assertTrue(this.g2.isAdjacent(v3, v2));
        assertTrue(this.g2.isAdjacent(v3, v5));
        assertTrue(this.g2.isAdjacent(v5, v3));
        assertTrue(this.g2.isAdjacent(v1, v5));
        assertTrue(this.g2.isAdjacent(v5, v1));
        assertFalse(this.g2.isAdjacent(v2, v5));
        assertFalse(this.g2.isAdjacent(v5, v2));
    }
    
    @Test
    def testAdjacent() = {
        assertEquals(Set(), this.g1.adjacent(v1));
        assertEquals(Set(v2, v5), this.g2.adjacent(v1));
        assertEquals(Set(v1, v3), this.g2.adjacent(v2));
        assertEquals(Set(v1, v3), this.g2.adjacent(v5));
    }
    
    @Test
    def testIsIncident() = {
        // public boolean isIncident(Vertex<V> vertex, Edge<E,V> edge);
        assertTrue(g1.isIncident(v1, e1));
        assertTrue(g2.isIncident(v1, e2));
        assertTrue(g2.isIncident(v1, e5));
    }
    
    @Test
    def testIncident() = {
        // public Set<Edge<E,V>> incident(Vertex<V> vertex);
        var i1 : MSet[Edge[String, String]] = MSet()
        i1.add(e1);
        
        var i2 : MSet[Edge[String, String]] = MSet()
        i2.add(e2); i2.add(e3);
        
        assertEquals(i1, g1.incident(v1));
        
        assertEquals(i2, g2.incident(v2));
    }
}
