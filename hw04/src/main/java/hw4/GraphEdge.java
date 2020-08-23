package hw4;



/**
 * <b>GraphEdge</b> represents an <b>immutable</b> directed edge to be used in a directed labeled multi-graph.
 * Only the edge's label and destination node name are stored. The edge's
 * source node name is found within the graph's list of nodes and is used
 * to reference that node's outgoing edges; typical of adjacency list representation.
 * <p>
 */
public final class GraphEdge<N extends Comparable<N>, L extends Comparable<L>> implements Comparable<GraphEdge<N, L>> {
	
	/* holds the label of the edge */
	private final L label;
	
	/* holds the name of the destination node of the edge  */
	private final N dest;

	/**
	 * Abstraction Function:
	 * GraphEdge e represents a directed edge where:
	 * e.label = label of the edge that may represent its weight
	 * e.dest = name of destination node
	 * 
	 * 
	 * Representation Invariant for every GraphEdge e:
	 * e.dest != null
	 * e.label != null
	 * 
	 */
	
	
	
//    /**
//     * @param d The GraphEdge's destination node name
//     * @effects Constructs a new GraphEdge with
//     * 			label = "" (empty string),
//     * 			dest = d
//     */
//	public GraphEdge(N d) {
//		dest = d;
//		label = (L) new String();
//		checkRep();
//	}
//	
	
    /**
     * @param d The GraphEdge's destination node name
     * @param l The GraphEdge's label
     * @effects Constructs a new GraphEdge with
     * 			label = l,
     * 			dest = d
     */
	public GraphEdge(N d, L l) {
		dest = d;
		label = l;
		checkRep();
	}

	
//    /**
//     * @param e The GraphEdge to copy
//     * @effects Constructs a new GraphEdge with
//     * 			label = e.label,
//     * 			dest = e.dest
//     */
//	public GraphEdge(GraphEdge<N, L> e) {
//		dest = e.dest;
//		label = e.label;
//		checkRep();
//	}
	
	
    /**
     * @return the name of this edge's destination node
     */
	public N getDest() {
		return dest;
	}
	
    /**
     * @return the label of this edge
     */
	public L getLabel() {
		return label;
	}
	
	
    /**
     * @return the String representing the name of this edge's destination node
     */
	public String getDestString() {
		return dest.toString();
	}
	
    /**
     * @return the String representing the label of this edge
     */
	public String getLabelString() {
		return label.toString();
	}
		
	
    /** Standard hashCode function.
    	@return an int that all objects equal to this will also
    	return.
	*/
	@Override
	public int hashCode() {
//	    return (dest.concat(label)).hashCode();
	    return dest.hashCode() + label.hashCode();
	}
	
	
    /** Compares two GraphEdges by lexicographically by
     * destination node name and secondarily by label
	    @param e the GraphEdge to be compared
	    @requires e != null
	    @return a negative number if this < e,
	    0 if this = e,
	    a positive number if this > e.
	*/
	@Override
	public int compareTo(GraphEdge<N,L> e) {
		int dest_diff = dest.compareTo(e.dest);
	    if (dest_diff == 0) {
	        return label.compareTo(e.label);
	    } else {
	    	return dest_diff;
	    }
	}
	

	
    /** Standard equality operation.
	    @param obj The object to be compared for equality.
	    @return true if and only if 'obj' is an instance of a GraphEdge
	    and 'this' and 'obj' represent identical edges,
	    where "identical" means having same label and destination node
	*/
	@Override
	public boolean equals(/*@Nullable*/ Object obj) {
	    if (obj instanceof GraphEdge<?, ?>) {
	    	GraphEdge<?, ?> e = (GraphEdge<?, ?>) obj;
//	        return this.compareTo(e) == 0;
//	        return this.equals(e);
	    	return dest.equals(e.dest) && label.equals(e.label);
	    } else {
	        return false;
	    }
	}
	
	
	/**
     * Checks that the representation invariant holds (if any).
     */
    // Throws a RuntimeException if the rep invariant is violated.
    private void checkRep() throws RuntimeException {
        if (dest == null) {
            throw new RuntimeException("dest == null");
        }
//        if (dest == "") {
//            throw new RuntimeException("dest is empty string");
//        }
        if (label == null) {
        	throw new RuntimeException("label == null");
        }

    }
    
    

}
