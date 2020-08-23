package hw5;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.LinkedList;

import hw4.*;


public final class MarvelPaths {
	//not an ADT
	
	/* holds instance of Graph<String,String> */
	private Graph<String,String> graph;
	
    /**
     * @effects Constructs a new MarvelPaths with empty graph (no nodes)
     */
	public MarvelPaths() {
		graph = new Graph<String,String>();
	}
	
	/**
	 *  @param: filename The path to the "CSV" file that contains the <hero, book> pairs
	    @effects: constructs this.graph according to the <hero, book> pairs,
	    			where an edge between 2 hero's has label = name of book they are both found in                                                                       
	 */
	public MarvelPaths(String file) {
		createNewGraph(file);
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
	
	/**
	 *  @param: filename The path to the "CSV" file that contains <hero, book> pairs
	    @effects: clears then constructs this.graph according to the <hero, book> pairs,
	    			where an edge between 2 hero's has label = name of book they are both found in
	    @throws: IOException if file cannot be read or file not a CSV file                                                                                     
	 */
	public void createNewGraph(String file) {
		graph = new Graph<String,String>();
		try {
			MarvelParser.readData(file, graph);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    /**
     * 
     * @param char1 The starting hero's name
     * @param char2 The destination hero's name
     * @returns a String representing the shortest path between the nodes representing char1 and char2, if they both exist
     */
	public String findPath(String char1, String char2) {
		String ret = "";
		GraphNode<String,String> start = graph.getNode(char1);
		GraphNode<String,String> dest = graph.getNode(char2);
		if (start == null || dest == null) {
			if (start == null) {
				ret = ret.concat("unknown character " + char1 + "\n");
			}
			if (dest == null && !char1.equals(char2)) {
				ret = ret.concat("unknown character " + char2 + "\n");
			}
			return ret;
		}	
		ret = ret.concat("path from " + char1 + " to " + char2 + ":\n");
		if (char1.equals(char2)) return ret;
		
		GraphNode<String,String> currentNode, nextNode;
		LinkedList<GraphEdge<String,String>> foundPath = null;
		GraphEdge<String,String> edge;
		
		LinkedList<GraphNode<String,String>> queue = new LinkedList<GraphNode<String,String>>();
		Map<GraphNode<String,String>, LinkedList<GraphEdge<String,String>>> nodePaths = new HashMap<GraphNode<String,String>, LinkedList<GraphEdge<String,String>>>();
		
		// setup
		queue.add(start);
		nodePaths.put(start, new LinkedList<GraphEdge<String,String>>());
		
		// BFS
		while (!queue.isEmpty()) {
			currentNode = queue.removeFirst(); //dequeue first
			
			if (currentNode.equals(dest)) {
				foundPath = nodePaths.get(currentNode);
				break;
			}

			Iterator<GraphEdge<String,String>> it = currentNode.edgeSet().iterator();
			while (it.hasNext()) {
				edge = it.next();
				nextNode = graph.getNode( edge.getDest() );
				
				if (! nodePaths.containsKey(nextNode)) {
					LinkedList<GraphEdge<String,String>> nextPath = new LinkedList<GraphEdge<String,String>>( nodePaths.get(currentNode) );
					nextPath.add(edge); // add new edge
					nodePaths.put(nextNode, nextPath);
					queue.add(nextNode);
				}
			}
		}
		
		// print path
		if (foundPath != null) {
			Iterator<GraphEdge<String,String>> it = foundPath.listIterator();
			String source = start.getName();
			while (it.hasNext()) {
				edge = it.next();
				ret = ret.concat(source + " to " + edge.getDest() + " via " + edge.getLabel() + "\n");
				source = edge.getDest();
			}
		} else {
			ret = ret.concat("no path found\n");
		}
		return ret;
	}


	
}