package com.github.haw.ai.gkap.graph;

/**
* @author Till Theis <till.theis@haw-hamburg.de>
*/
import org.junit.Assert._

import org.junit.Before;
import org.junit.Test;
import com.github.haw.ai.gkap.graph._

import java.util.Arrays.asList

class DirectedEdgeTest {
    var c1, c2, c3, c4 : Int = _
    var c5, c6 : String = _
    
    var v1, v2, v3 : Vertex[Int] = _
    var v4, v5 : Vertex[String] = _
    
    var e1, e2, e3 : Edge[Int, Int] = _
    var e4, e5, e6 : Edge[Int, String] = _
    
    @Before
    def setUp : Unit = {
        c1 = -1;
        c2 = 0;
        c3 = 9;
        c4 = -255;
        
        c5 = "";
        c6 = "hans";
        
        v1 = Vertex(c1);
        v2 = Vertex(c2);
        v3 = Vertex(c3);

        v4 = Vertex(c5);
        v5 = Vertex(c6);
        
        e1 = DirectedEdge(v1, v2, c2);
        e2 = DirectedEdge(v2, v3, c1);
        e3 = DirectedEdge(v3, v1, c3);

        e4 = DirectedEdge(v4, v5, c2);
        e5 = DirectedEdge(v5, v4, c4);
        e6 = DirectedEdge(v4, v4, c4);
    }
    
    @Test
    def testContent : Unit = {
        assertEquals(c2, e1.content);
        assertEquals(c1, e2.content);
        assertEquals(c3, e3.content);
        assertEquals(c2, e4.content);
        assertEquals(c4, e5.content);
    }

    @Test
    def testVertices : Unit = {
        assertEquals(e1.vertices, Set(v1, v2))
        assertEquals(e2.vertices, Set(v3, v2))
        assertEquals(e3.vertices, Set(v1, v3))
        assertEquals(e4.vertices, Set(v4, v5))
        assertEquals(e5.vertices, Set(v4, v5))
    }

    @Test
    def testVerticesSize : Unit = {
        assertEquals(2, e1.vertices.size);
        assertEquals(2, e2.vertices.size);
        assertEquals(2, e3.vertices.size);
        assertEquals(2, e4.vertices.size);
        assertEquals(2, e5.vertices.size);
        assertEquals(1, e6.vertices.size);
    }

    @Test
    def testIsReachable : Unit = {
        assertTrue(e1.isReachable(v1, v2));
        assertTrue(e2.isReachable(v2, v3));
        assertTrue(e3.isReachable(v3, v1));
        assertTrue(e4.isReachable(v4, v5));
        assertTrue(e5.isReachable(v5, v4));
        
        assertTrue(e6.isReachable(v4, v4));
    }
    
    @Test
    def testIsReachableNegative : Unit = {
        assertFalse(e1.isReachable(v2, v1));
        assertFalse(e2.isReachable(v3, v2));
        assertFalse(e3.isReachable(v1, v3));
        assertFalse(e4.isReachable(v5, v4));
        assertFalse(e5.isReachable(v4, v5));

        assertFalse(e1.isReachable(v2, v3));
        assertFalse(e2.isReachable(v1, v2));
        assertFalse(e3.isReachable(v2, v2));
    }

}