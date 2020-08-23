package hw6;

import java.util.Iterator;
import java.util.LinkedList;

import hw4.GraphEdge;
import hw4.GraphNode;

/**
 * <b>GraphPathDijkstra</b> represents an <b>immutable</b> path of a weighted graph
 * containing a list collection of GraphEdge<String,Double> objects representing the path
 * and a GraphNode<String,Double> representing the path's destination and stores
 * a collection of its outgoing edges. Each edge in the path stores its edge weight and its
 * destination node name. The path's source node is not stored; it is found in an execution
 * of MarvelPaths2.findPath() (Dijkstra's shortest path-finding algorithm between two nodes)
 * where all path objects share the same source node.
 */
public class GraphPathDijkstra implements Comparable<GraphPathDijkstra> {
	

	/* holds the destination node of this path */
	private final GraphNode<String,Double> dest;
	
	/* holds a list of edges representing this path */
	private final LinkedList<GraphEdge<String,Double>> path;


	/**
	 * Abstraction Function:
	 * GraphPathDijkstra p represents a path of a weighted graph where:
	 * p.dest = GraphNode<String,Double> representing the path's destination and stores
	 * 			a collection of its outgoing edges
	 * p.path = list collection of GraphEdge<String,Double> objects representing the path.
	 * 			If empty, this path's destination node (p.dest) = this path's source node
	 * 
	 * 
	 * Representation Invariant for every GraphPathDijkstra p:
	 * p.dest != null
	 * p.path != null
	 * If p.path not empty, name of destination node of last edge in p.path = name of p.dest
	 * 
	 */
	
	
	/** Constructor with destination as arg
	 * 
	 * @param n The path's destination node
	 * @requires n != null
	 * @effects Constructs a new path with dest = n
	 */
	public GraphPathDijkstra(GraphNode<String,Double> n) {
		dest = n;
		path = new LinkedList<GraphEdge<String,Double>>();
		checkRep();
	}
	
	
	/** Constructor with destination as arg
	 * 
	 * @param n The path's destination node
	 * @param p The path's list of edges
	 * @requires n != null, p != null
	 * @effects Constructs a new path with dest = n
	 */
	public GraphPathDijkstra(GraphNode<String,Double> n, LinkedList<GraphEdge<String,Double>> p) {
		dest = n;
		path = p;
		checkRep();
	}


	/** 
	 * @param e The GraphEdge to add to new path
	 * @param n The new path's destination node
	 * @requires e.dest = n.name
	 * @returns new path with dest = n, path = this.path + e
	 */
	public GraphPathDijkstra appendEdgeNewDest(GraphEdge<String,Double> e, GraphNode<String,Double> n) {
		LinkedList<GraphEdge<String,Double>> p = new LinkedList<GraphEdge<String,Double>>(path);
		p.add(e);
		GraphPathDijkstra ret = new GraphPathDijkstra(n, p);
		return ret;
	}
	
	
	/** 
	 * @returns a Double representing the total cost of this path
	 */
	public Double totalCost() {
		Double ret = 0.0;
		if (path.isEmpty()) return ret;
		for (GraphEdge<String, Double> e : path) {
			ret += e.getLabel();
		}
		return ret;
	}
	
	
	/** 
	 * @return a formatted String representing the total cost of this path
	 */
	public String totalCostString() {
		return String.format("%.3f", totalCost());
	}
	

	/** 
	 * @return the destination node of this path
	 */
	public GraphNode<String, Double> getDestNode() {
		return dest;
	}
	
	
	/** 
	 * @return a GraphEdge<String,Double> iterator over this.path
	 */
	public Iterator<GraphEdge<String,Double>> pathIterator() {
		return path.listIterator();
	}
	
	
	/** Compares two paths by total cost, then by edge count, then by destination node name
    @param p The path to compare this with
    @requires p != null
    @return a negative number if this < p,
		    0 if this = p,
		    a positive number if this > p.
	*/
	@Override
	public int compareTo(GraphPathDijkstra p) {
		Integer edgeCountDiff = path.size() - p.path.size();
		Double a = totalCost();
		Double b = p.totalCost();
		if (a < b) return -1; 
		else if (a > b) return 1;
		else if (edgeCountDiff == 0) {
			return dest.compareTo(p.dest);
		} else {
			return edgeCountDiff;
		}
	}
	


	/** Standard equality operation
	 * 
	 * @param p The path to compare this with
	 * @requires p != null
	 * @return true if this and o are equal as specified by compareTo(), false otherwise
	 */
	public boolean equals(GraphPathDijkstra p) {
		return compareTo(p) == 0;
	}
	
	
	/**
     * Checks that the representation invariant holds (if any).
     */
    // Throws a RuntimeException if the rep invariant is violated.
    private void checkRep() throws RuntimeException {
        if (path == null) {
            throw new RuntimeException("path == null");
        }
        if (dest == null) {
        	throw new RuntimeException("dest == null");
        }
        if (!path.isEmpty() && !dest.getNameString().equals(path.getLast().getDestString())) {
        	throw new RuntimeException("path dest != last edge's dest");
        }
        
    }
	
	

	
	

}
