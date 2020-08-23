package hw4;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * <b>GraphNode</b> represents a <b>mutable</b> node in a directed labeled multi-graph, 
 * with an <b>immutable</b> name and a <b>mutable</b> collection of <b>immutable</b> GraphEdge
 * objects representing its outgoing edges.
 * <p>
 */
public class GraphNode<N extends Comparable<N>, L extends Comparable<L>> implements Comparable<GraphNode<N,L>> {
	
	
	/* holds the name of the node */
	private final N name;
	
	/* holds this node's collection of outgoing edges  */
	private TreeSet<GraphEdge<N,L>> edges;
	

	/**
	 * Abstraction Function:
	 * GraphNode n represents a node of a graph where
	 * n.name = name of the node
	 * n.edges = collection of node's outgoing edges
	 * 
	 * 
	 * Representation Invariant for every GraphNode n:
	 * n.name != null
	 * n.edges != null
	 * n.edges does not contain duplicates and nulls (handled by GraphEdge comparable)
	 * 
	 * 
	 * 
	 */
	

	
	
    /**
     * @param n The GraphNode's name
     * @effects Constructs a new GraphNode with name = n,
     * 			and empty set of edges
     */
	public GraphNode(N n) {
		name = n;
		edges = new TreeSet<GraphEdge<N,L>>();
		checkRep();
	}
	
    /**
     * @param e The GraphEdge to add to this node
     * @modifies this.edges
     * @effects Adds edge e to this.edges if it doesn't already exist
     * @return true if operation successful, false otherwise
     */
	public boolean addEdge(GraphEdge<N,L> e) {
		checkRep();
		boolean ret = edges.add(e);
		checkRep();
		return ret;
	}
	
	
    /**
     * @return the name of this node
     */
	public N getName() {
		return name;
	}
	
    /**
     * @return the name of this node
     */
	public String getNameString() {
		return name.toString();
	}
	
    /**
     * @return an int representing number of outgoing edges
     */
	public int outDegree() {
		return edges.size();
	}
	
	
	
    /** Determines whether two different nodes have the identical set of edges
    	@param n The GraphNode to be compared with
    	@return true if and only if this.edges is the same set of GraphEdges as n.edges
	*/
	public boolean hasSameEdges (GraphNode<N,L> n) {
		return edges.equals(n.edges);
	}
	
	
    /**
     * @return a list of Strings representing this.edges,
     * 			formated as [e.dest]([e.label]) where e is an arbitrary edge,
     * 			and maintains the same order as in this.edges
     */
	public LinkedList<String> edgeStrings () {
		Iterator<GraphEdge<N,L>> i = edges.iterator();
		LinkedList<String> ret = new LinkedList<String>();
		while (i.hasNext()) {
			GraphEdge<N,L> e = i.next();
			String label;
			if (e.getLabel() instanceof Double) {
				label = String.format("%.3f", e.getLabel());
			} else {
				label = e.getLabelString();
			}
			String node_label = e.getDestString().concat("(").concat(label).concat(")");
			ret.add( node_label );
		}
		checkRep();
		return ret;
	}
	
	
    /**
     * @return a SortedSet copy of this node's edges
     * 
     */
	public SortedSet<GraphEdge<N,L>> edgeSet() {
		return (SortedSet<GraphEdge<N,L>>) new TreeSet<GraphEdge<N,L>>(edges);
	}
	
	
	
    /** Standard hashCode function.
		@return an int that all equal GraphNodes will return
	*/
	@Override
	public int hashCode() {
	    return name.hashCode() + edges.hashCode();
	}
	
	
    /** Compares two GraphNodes lexicographically by name
	    @param n the GraphNode to be compared
	    @requires n != null
	    @return a negative number if this < n,
			    0 if this = n,
			    a positive number if this > n.
	*/
	@Override
	public int compareTo(GraphNode<N,L> n) {
	    return name.compareTo(n.name);
	}
	
	
	
    /** 
    @param n the GraphNode to be compared
    @requires n != null
    @return true if this and n have equal name, false otherwise
*/
	public boolean hasEqualName(GraphNode<N,L> n) {
		return name.compareTo(n.name) == 0;
	}
	
	
    /** Standard equality operation
	    @param obj The object to be compared for equality.
	    @return true if and only if 'obj' is an instance of a GraphNode
			    and 'this' and 'obj' represent identical nodes
			    where "identical" means having same name
	*/
	@Override
	public boolean equals(/*@Nullable*/ Object obj) {
		if (obj instanceof GraphNode<?,?>) {
	    	GraphNode<?,?> e = (GraphNode<?,?>) obj;
//	        return this.compareTo(e) == 0;
//	    	return this.equals(e);
	    	return name.equals(e.name);
	    } else {
	        return false;
	    }
	}

	


	/**
     * Checks that the representation invariant holds (if any).
     */
    // Throws a RuntimeException if the rep invariant is violated.
    private void checkRep() throws RuntimeException {
        if (name == null) {
            throw new RuntimeException("name == null");
        }
//        if (name == "") {
//            throw new RuntimeException("name is empty string");
//        }
        if (edges == null) {
        	throw new RuntimeException("edges == null");
        }
        
    }
	
	
}



