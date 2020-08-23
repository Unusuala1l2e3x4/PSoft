package hw4;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;


/**
 * <b>Graph</b> represents a <b>mutable</b> directed labeled multi-graph
 * containing a set of GraphNode<N,L> objects. GraphNodes store the represented node's
 * name and its set of edges, which are out-edges. Each edge in a GraphNode<N,L> is a GraphEdge object that
 * stores its edge label and its destination node name (a child node of the current GraphNode<N,L>).
 * <p>
 */
public class Graph<N extends Comparable<N>, L extends Comparable<L>> {
	
	/* holds this graph's collection of nodes and their edges */
	private TreeSet<GraphNode<N,L>> nodes;

	/**
	 * Abstraction Function:
	 * Graph g represents a graph where
	 * g.nodes = its collection of nodes represented by GraphNode<N,L> objects,
	 * which each store its out-edges as a collection
	 * 
	 * 
	 * Representation Invariant for every Graph g:
	 * g.nodes != null
	 * g.nodes does not contain duplicates and nulls (handled by GraphNode<N,L> comparable)
	 * 
	 */
	

    /**
     * @effects Constructs a new Graph with empty set of nodes
     */
	public Graph() {
		nodes = new TreeSet<GraphNode<N,L>>();
		checkRep();
	}
	
	
    /**Adds a new node to the graph if it doesn't already exist
     * 
     * @param nodeName The name of the new node to add
     * @modifies this.nodes
     * @effects adds a new node to this.nodes with name = nodeName if it doesn't already exist.
     * @returns true if successful in adding new node, false otherwise
     */
	public boolean addNode(N nodeName) {
		boolean ret = nodes.add(new GraphNode<N,L>(nodeName));
		checkRep();
		return ret;
	}
	
	
    /**Adds a new edge to the graph if its starting and ending nodes both exist
     * and it doesn't already exist
     * 
     * @param parentName The starting node's name
     * @param childName The ending node's name
     * @modifies node with name = parentName in this.nodes
     * @effects adds a new edge to a node in this.nodes with name = nodeName if its starting and ending nodes both exist
     * 			and it doesn't already exist.
     * @returns true if successful in adding new node, false otherwise
     */
	public boolean addEdge(N parentName, N childName, L label) {
		GraphNode<N,L> p = new GraphNode<N,L>(parentName);
		GraphNode<N,L> c = new GraphNode<N,L>(childName);
		GraphNode<N,L> parent = nodes.floor(p);
		GraphNode<N,L> child = nodes.floor(c);
		checkRep();
		if (parent == null || child == null) return false;
		if (! p.hasEqualName(parent)) return false;
		else if (! c.hasEqualName(child)) return false;
		else return parent.addEdge(new GraphEdge<N,L>(childName, label));
	}
	
	
    /**
     * @return a list of Strings representing this.nodes,
     * 			formated as n.name where n is an arbitrary node,
     * 			and maintains the same order as in this.nodes
     */
	public LinkedList<String> nodeNames() {
		Iterator<GraphNode<N,L>> i = nodes.iterator();
		LinkedList<String> ret = new LinkedList<String>();
		while (i.hasNext()) {
			ret.add(i.next().getNameString());
		}
		checkRep();
		return ret;
	}
	

    /**
     * @param nodeName The name of the node to get
     * @return a GraphNode<N,L> in this.nodes, or null if it does not exist
     */
	public GraphNode<N,L> getNode(N nodeName) {
		GraphNode<N,L> n = new GraphNode<N,L>(nodeName);
		GraphNode<N,L> ret = nodes.floor(n);
		if (ret == null || !ret.hasEqualName(n)) return null;
		else return ret;
	}
	
 
	
	/**
     * Checks that the representation invariant holds (if any).
     */
    // Throws a RuntimeException if the rep invariant is violated.
    private void checkRep() throws RuntimeException {
        if (nodes == null) {
            throw new RuntimeException("nodes == null");
        }
        
    }
	

}
