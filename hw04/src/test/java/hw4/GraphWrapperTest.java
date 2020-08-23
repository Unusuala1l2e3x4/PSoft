package hw4;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;



public final class GraphWrapperTest {
	
	///////////////////////////////////////////////////////////////////////////////////////
	////  Helper Functions
	///////////////////////////////////////////////////////////////////////////////////////
	
	private void assertLexiUnequal(GraphEdge<String,String> a, GraphEdge<String,String> b) {
		assertTrue(a.compareTo(b) != 0);
		assertTrue( ! a.equals(b) );
	}
	
	private void assertLexiLesser(GraphEdge<String,String> smaller, GraphEdge<String,String> larger) {
		assertTrue(smaller.compareTo(larger) < 0);
		assertTrue(larger.compareTo(smaller) > 0);
	}
	
	private void assertLexiEqual(GraphEdge<String,String> a, GraphEdge<String,String> b) {
		assertEquals(a, b);
		assertTrue(a.compareTo(b) == 0);
		assertTrue( a.equals(b) );
		assertEquals(a.hashCode(), b.hashCode()); 
	}
	
	private void assertLexiUnequal(GraphNode<String,String> a, GraphNode<String,String> b) {
		assertTrue(a.compareTo(b) != 0);
		assertTrue( ! a.equals(b) );
	}
	
	private void assertLexiLesser(GraphNode<String,String> smaller, GraphNode<String,String> larger) {
		assertTrue(smaller.compareTo(larger) < 0);
		assertTrue(larger.compareTo(smaller) > 0);
	}
	
	private void assertLexiEqual(GraphNode<String,String> a, GraphNode<String,String> b) {
		assertEquals(a, b);
		assertTrue(a.compareTo(b) == 0);
		assertTrue( a.equals(b) );
		assertTrue(a.hasEqualName(b));
	}
	

	
	///////////////////////////////////////////////////////////////////////////////////////
	////  GraphEdge<String,String> Test
	///////////////////////////////////////////////////////////////////////////////////////
	
	GraphEdge<String,String> edge_a,edge_b,edge_c,edge_a_label1,edge_a_label2,edge_a_label1_2,edge_b_label1,edge_b_label2,edge_b_label1_2,edge_c_label1,edge_c_label2,edge_c_label1_2;
	
	@Before
	public void setUp(){
		// edges
		edge_a = new GraphEdge<String,String>("A", "");
		edge_b = new GraphEdge<String,String>("B", "");
		edge_c = new GraphEdge<String,String>("C", "");
	
		edge_a_label1 = new GraphEdge<String,String>("A", "label1");
		edge_a_label1_2 = new GraphEdge<String,String>("A", "label1");
		edge_a_label2 = new GraphEdge<String,String>("A", "label2");
		
		edge_b_label1 = new GraphEdge<String,String>("B", "label1");
		edge_b_label1_2 = new GraphEdge<String,String>("B", "label1");
		edge_b_label2 = new GraphEdge<String,String>("B", "label2");
		
		edge_c_label1 = new GraphEdge<String,String>("C", "label1");
		edge_c_label1_2 = new GraphEdge<String,String>("C", "label1");
		edge_c_label2 = new GraphEdge<String,String>("C", "label2");
		
		// nodes
		node_a = new GraphNode<String,String>("A");
		node_a_2 = new GraphNode<String,String>("A");
		node_b = new GraphNode<String,String>("B");
		node_b_2 = new GraphNode<String,String>("B");
		node_c = new GraphNode<String,String>("C");
		node_c_2 = new GraphNode<String,String>("C");
	}

	
	@Test
	public void testTwoArgConstructorEdges() {
		new GraphEdge<String,String>("A", "");
		new GraphEdge<String,String>("B", "");
		new GraphEdge<String,String>("A", "label1");
		new GraphEdge<String,String>("B", "label1");
	}
	
	
	@Test
	public void testGetDest() {
		assertEquals("A", edge_a.getDest());
		assertEquals("A", edge_a_label1.getDest());
		assertEquals("A", edge_a_label2.getDest());
	}
	
	@Test
	public void testGetLabel() {
		assertEquals("", edge_a.getLabel());
		assertEquals("label1", edge_a_label1.getLabel());
		assertEquals("label2", edge_a_label2.getLabel());
		assertEquals("label1", edge_b_label1.getLabel());
		assertEquals("label2", edge_b_label2.getLabel());
	}
	
	@Test
	public void testEqualEdges() {
		assertLexiEqual(edge_a_label1, edge_a_label1_2);
		assertLexiEqual(edge_b_label1, edge_b_label1_2);
	}
	
	@Test
	public void testUnequalEdges() {
		assertLexiUnequal(edge_a, edge_b);
		assertLexiUnequal(edge_a_label1, edge_b_label1);
		assertLexiUnequal(edge_a_label2, edge_b_label2);
		assertLexiUnequal(edge_a_label1, edge_a_label2);
		assertLexiUnequal(edge_b_label1, edge_b_label2);
	}
	
	@Test
	public void testLexicographicEdges() {
		assertLexiLesser( edge_a_label1, edge_a_label2 );
		assertLexiLesser( edge_a_label2, edge_b_label1 );
		assertLexiLesser( edge_b_label1, edge_b_label2 );
		assertLexiLesser( edge_b_label2, edge_c_label1 );
		assertLexiLesser( edge_c_label1, edge_c_label2 );
	}
	



	///////////////////////////////////////////////////////////////////////////////////////
	////  GraphNode<String,String> Test
	///////////////////////////////////////////////////////////////////////////////////////
	
	GraphNode<String,String> node_a,node_a_2,node_b,node_b_2,node_c,node_c_2;
	
	@Test
	public void testConstructorNode() {
		new GraphNode<String,String>("A");
		new GraphNode<String,String>("B");
	}
	
	@Test
	public void testEmptyNode() {
		GraphNode<String,String> node_test = new GraphNode<String,String>("test");
		assertEquals(node_test.outDegree(), 0);
	}
	
	
	@Test
	public void testGetName() {
		assertEquals("A", node_a.getName());
		assertEquals("B", node_b.getName());
		assertEquals("C", node_c.getName());
	}
	
	
	@Test
	public void testAddEdges() {
		GraphNode<String,String> test = new GraphNode<String,String>("A");
		
		assertTrue( test.addEdge(edge_a_label1) );
		assertTrue( test.addEdge(edge_b_label1) );
		assertTrue( test.addEdge(edge_c_label1) );
		
		assertTrue( test.addEdge(edge_a_label2) );
		assertTrue( test.addEdge(edge_b_label2) );
		assertTrue( test.addEdge(edge_c_label2) );
		
		assertFalse( test.addEdge(edge_a_label1) );
		assertFalse( test.addEdge(edge_a_label1_2) );
		assertFalse( test.addEdge(edge_b_label1) );
		assertFalse( test.addEdge(edge_b_label1_2) );
		assertFalse( test.addEdge(edge_c_label1) );
		assertFalse( test.addEdge(edge_c_label1_2) );
		
		assertEquals( test.outDegree(), 6 );
		
	}
	
	@Test
	public void testNodesEqualByName() {
		assertLexiEqual(node_a_2, node_a);
		assertTrue(node_a_2.hasSameEdges(node_a));
		
		GraphNode<String,String> test = new GraphNode<String,String>("A");
		assertTrue(test.hasSameEdges(node_a));
		
		assertLexiEqual(test, node_a);
		assertTrue( test.outDegree() == node_a.outDegree() );
		
		test.addEdge(edge_b);
		assertLexiEqual(test, node_a);
		assertTrue( test.outDegree() > node_a.outDegree() );
		
		test.addEdge(edge_b_label1);
		assertLexiEqual(test, node_a);
		assertTrue( test.outDegree() > node_a.outDegree() );
		
		test.addEdge(edge_b_label2);
		assertLexiEqual(test, node_a);
		assertTrue( test.outDegree() > node_a.outDegree() );
		
		
		assertFalse(test.hasSameEdges(node_a));
		
	}
	
	
	@Test
	public void testUnequalNodes() {
		assertLexiUnequal(node_a, node_b);
		assertLexiUnequal(node_b, node_c);
	}
	
	@Test
	public void testLexicographicNodes() {
		assertLexiLesser(node_a, node_b);
		assertLexiLesser(node_b, node_c);
	}
	


 	///////////////////////////////////////////////////////////////////////////////////////
	////  Graph<String,String> Test
	///////////////////////////////////////////////////////////////////////////////////////
	

	@Test
	public void testConstructorGraph() {
		new Graph<String,String>();
	}
	
	@Test
	public void testAddNode() {
		Graph<String,String> test = new Graph<String,String>();
		assertTrue(test.addNode("A"));
		assertTrue(test.addNode("B"));
		assertFalse(test.addNode("A"));
		assertFalse(test.addNode("B"));
	}
	
	@Test
	public void testAddEdgeNonidentical() {
		Graph<String,String> test = new Graph<String,String>();
		assertFalse(test.addEdge("A", "B", "label1"));
		
		assertTrue(test.addNode("A"));
		assertFalse(test.addEdge("A", "B", "label1"));
		
		assertTrue(test.addNode("B"));
		assertTrue(test.addEdge("A", "B", "label1"));
		
		assertFalse(test.addEdge("A", "B", "label1"));
		assertTrue(test.addEdge("A", "B", "label2"));
	}
	
	@Test
	public void testGetNodeExisting() {
		Graph<String,String> test = new Graph<String,String>();
		
		test.addNode("A");
		test.addNode("B");
		test.addNode("C");

		assertNotNull(test.getNode("A"));
		assertNotNull(test.getNode("B"));
		assertNotNull(test.getNode("C"));
		assertNull(test.getNode("D"));
	}
	
	@Test
	public void testGetNodeWithEdges() {
		Graph<String,String> test = new Graph<String,String>();
		
		test.addNode("A");
		test.addNode("B");
		test.addNode("C");
		
		test.addEdge("A", "B", "label1");
		test.addEdge("B", "C", "label2");
		test.addEdge("A", "C", "label3");
		
		GraphNode<String,String> a = test.getNode("A");
		GraphNode<String,String> b = test.getNode("B");
		GraphNode<String,String> c = test.getNode("C");
		
		assertEquals(a, node_a);
		assertEquals(b, node_b);
		assertEquals(c, node_c);
		
		assertEquals(a.outDegree(), 2);
		assertEquals(b.outDegree(), 1);
		assertEquals(c.outDegree(), 0);
	}
	
	


	///////////////////////////////////////////////////////////////////////////////////////
	////  GraphWrapper Test
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testConstructorWrapper() {
		new GraphWrapper();
	}
	
	@Test
	public void testWrapperListNodes() {
		GraphWrapper test = new GraphWrapper();
		test.addNode("A");
		test.addNode("B");
		test.addNode("C");
		Iterator<String> i = test.listNodes();
		assertEquals(i.next(), "A");
		assertEquals(i.next(), "B");
		assertEquals(i.next(), "C");
	}
	
	
	@Test
	public void testWrapperListChildren() {
		GraphWrapper test = new GraphWrapper();
		test.addNode("A");
		test.addNode("B");
		test.addEdge("A", "B", "label1");
		test.addEdge("A", "B", "label2");
		test.addEdge("A", "B", "label3");
		
		
		Iterator<String> i = test.listChildren("A");
		assertEquals(i.next(), "B(label1)");
		assertEquals(i.next(), "B(label2)");
		assertEquals(i.next(), "B(label3)");
	}
	
}