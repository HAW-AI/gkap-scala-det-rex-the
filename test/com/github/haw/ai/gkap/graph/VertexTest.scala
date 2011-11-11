package com.github.haw.ai.gkap.graph;

import org.junit.Assert._;

import org.junit.Before;
import org.junit.Test;

import com.github.haw.ai.gkap.graph._;

class VertexTest {
    var i1, i2, i3, i4 : Int = _
    var s1, s2 : String = _
    var v1, v2, v3 : Vertex[Int] = _
    var v4, v5 : Vertex[String] = _
    
    @Before
    def setUp = {
        i1 = -1;
        i2 = 0;
        i3 = 9;
        i4 = -255;
        s1 = "";
        s2 = "foobar";
        // Integer content
        v1 = Vertex(i1);
        v2 = Vertex(i2);
        v3 = Vertex(i3);
        // String content
        v4 = Vertex(s1);
        v5 = Vertex(s2);
    }

    @Test
    def testContent = {
        assertEquals(i1, v1.content);
        assertEquals(i2, v2.content);
        assertEquals(i3, v3.content);
        assertEquals(s1, v4.content);
        assertEquals(s2, v5.content);
    }
}
