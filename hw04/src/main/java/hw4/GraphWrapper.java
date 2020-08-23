package hw4;

import java.util.Iterator;
import java.util.LinkedList;

public final class GraphWrapper {
	//not an ADT
	
	/* holds instance of Graph<String,String> */
	private Graph<String,String> graph;
	
    /**
     * @effects Constructs a new GraphWrapper with empty graph (no nodes)
     */
	public GraphWrapper() {
		graph = new Graph<String,String>();
	}
	
    /**Adds a new node to the graph if it doesn't already exist
     * 
     * @param nodeName The name of the new node to add
     * @modifies this.graph
     * @effects adds a new node to this.graph's collection of nodes with name = nodeName if it doesn't already exist.
     * @returns true if successful in adding new node, false otherwise
     */
	public void addNode(String nodeData) {
		graph.addNode(nodeData);
		
	}
	
    /**Adds a new edge to the graph if its starting and ending nodes both exist
     * and it doesn't already exist
     * 
     * @param parentName The starting node's name
     * @param childName The ending node's name
     * @modifies node with name = parentName in this.nodes
     * @effects adds a new edge to a node in this.graph's collection of nodes with name = nodeName if its starting and ending nodes both exist
     * 			and it doesn't already exist.
     * @returns true if successful in adding new node, false otherwise
     */
	public void addEdge(String parentNode, String childNode, String edgeLabel) {
		graph.addEdge(parentNode, childNode, edgeLabel);
	}
	
	
    /**
     * @return an Iterator<String> over a list of Strings representing this.graph's collection of nodes
     * 			and maintains the same ordering implemented in GraphNode's Comparable interface
     */
	public Iterator<String> listNodes() {
		LinkedList<String> list = graph.nodeNames();
		Iterator<String> ret = list.iterator();
		return ret;
	}
	
    /**
     * @param parentNode The name of the node in question
     * @return an Iterator<String> over a list of Strings representing the node's collection of edges (if the node exists)
     * 			formated as [e.dest]([e.label]) where e is an arbitrary edge,
     * 			and maintains the same ordering implemented in GraphEdges' Comparable interface
     */
	public Iterator<String> listChildren(String parentNode) {
		GraphNode<String,String> n = graph.getNode(parentNode);
		LinkedList<String> list = n.edgeStrings();
		Iterator<String> ret = list.iterator();
		return ret;
	}
	
	
	
}