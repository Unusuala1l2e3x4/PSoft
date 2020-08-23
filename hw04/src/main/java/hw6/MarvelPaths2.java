package hw6;


import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import hw4.*;
//import hw7.*;

public class MarvelPaths2 {
	//not an ADT
	
	/* holds instance of Graph<String,Double> */
	protected Graph<String,Double> graph;
	
	/* holds HashMap that maps node names (buildings/intersections) to MapPoints */
	protected HashMap<String, MapPoint> pointsMap;
	
	/* holds Strings representing compass directions, starting from NorthEast and going clockwise */
	private static final String[] directions = new String[] {"NorthEast", "East", "SouthEast", "South", "SouthWest", "West", "NorthWest", "North"};
	

	
    /**
     * @effects Constructs a new MarvelPaths2 with empty graph (no nodes)
     */
	public MarvelPaths2() {
		graph = new Graph<String,Double>();
	}
	
	/**
	 *  @param: filename The path to the "CSV" file that contains <hero, book> pairs
	    @effects: constructs this.graph according to the <hero, book> pairs,
	    			where an edge between 2 hero's has label = weight = 1 / (number of books both hero's are part of)                                    
	 */
	public MarvelPaths2(String file) {
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
		GraphNode<String, Double> n = graph.getNode(parentNode);
		LinkedList<String> list = n.edgeStrings();
		Iterator<String> ret = list.iterator();
		return ret;
	}
	
	/**
	 *  @param: filename The path to the "CSV" file that contains the <hero, book> pairs
	    @effects: clears then constructs this.graph according to the <hero, book> pairs,
	    			where an edge between 2 hero's has label = weight = 1 / (number of books both hero's are part of)
	    @throws: IOException if file cannot be read or file not a CSV file                                                                                     
	 */
	public void createNewGraph(String file) {
		graph = new Graph<String,Double>();
		try {
			MarvelParser2.readData(file, graph);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
    /**
     * @param start The starting MapPoint
     * @param end The ending MapPoint
     * @return a double representing the angle of the bearing from start to end
     */
	private static double calcBearingDegree(MapPoint start, MapPoint end) {
	    double theta = Math.atan2(end.y() - start.y(), end.x() - start.x());
	    theta += Math.PI/2.0;
	    double angle = Math.toDegrees(theta);
	    if (angle < 0) {
	        angle += 360;
	    }
	    return angle;
	}
	
    /**
     * @param start The node name of the starting MapPoint
     * @param end The node name of the ending MapPoint
     * @return a String representing the compass direction of the bearing from start to end
     */
	public String getDirection(String start, String end) {
		MapPoint a = pointsMap.get(start);
		MapPoint b = pointsMap.get(end);
		if (a == null || b == null) return null;
		
		double angle = calcBearingDegree(a, b);
		double indexDouble = (angle - 22.5)/45.0;
		Integer index;
		
		if (indexDouble < 0) index = 7;
		else index = (int) indexDouble;
		
		return directions[index];
	}
	
	
    /**
     * 
     * @param char1 The starting hero's name
     * @param char2 The destination hero's name
     * @returns a String representing the shortest path between the nodes representing char1 and char2, if they both exist
     */
	public String findPath(String char1, String char2) {
		String ret = "";
		
		PriorityQueue<GraphPathDijkstra> active = new PriorityQueue<GraphPathDijkstra>();
		Map<GraphNode<String,Double>, Double> finished = new HashMap<GraphNode<String,Double>, Double>();
		
		GraphNode<String,Double> start = graph.getNode(char1);
		GraphNode<String,Double> dest = graph.getNode(char2);
		
		if (start == null || dest == null) {
			if (start == null) {
//				ret = ret.concat("unknown character " + char1 + "\n");
				ret = ret.concat("Unknown building: [" + char1 + "]\n");
			}
			if (dest == null && !char1.equals(char2)) {
//				ret = ret.concat("unknown character " + char2 + "\n");
				ret = ret.concat("Unknown building: [" + char2 + "]\n");
			}
			return ret;
		}
//		ret = ret.concat("path from " + char1 + " to " + char2 + ":\n");
		ret = ret.concat("Path from " + char1 + " to " + char2 + ":\n");
		
		active.add(new GraphPathDijkstra(start)); // setup
		
		if (char1.equals(char2)) {
			ret = ret.concat(String.format("Total distance: %s pixel units.\n", active.peek().totalCostString()));
			return ret;
		}
		
		GraphPathDijkstra minPath, newPath, foundPath = null;
		GraphNode<String,Double> minDest, edgeDest;
		
		// Dijkstra
		while (!active.isEmpty()) {
			minPath = active.poll();
			minDest = minPath.getDestNode();
			if (minDest.equals(dest)) {
				foundPath = minPath;
				break;
			} else if (finished.containsKey(minDest)) {
				continue;
			}
			for (GraphEdge<String,Double> e : minDest.edgeSet()) { // e = (minDest, child)
				edgeDest = graph.getNode(e.getDest()); // edgeDest = child
				if ( finished.get( edgeDest ) == null ) { // if child not in finished
					newPath = minPath.appendEdgeNewDest(e, edgeDest);
					active.add(newPath);
				}
			}
			finished.put(minPath.getDestNode(), minPath.totalCost());
		}
		
		// print path
		if (foundPath != null) {
			Iterator<GraphEdge<String,Double>> it = foundPath.pathIterator();
			GraphEdge<String,Double> edge;
			String source = start.getName();
			while (it.hasNext()) {
				edge = it.next();
//				ret = ret.concat(source + " to " + edge.getDest() + " with weight " + String.format("%.3f", edge.getLabel()) + "\n");
				ret = ret.concat("\tWalk " + getDirection(source, edge.getDest()) + " to (" + edge.getDest() + ")\n");
				source = edge.getDest();
			}
//			ret = ret.concat(String.format("total cost: %s\n", foundPath.totalCostString()));
			ret = ret.concat(String.format("Total distance: %s pixel units.\n", foundPath.totalCostString()));
		} else {
//			ret = ret.concat("no path found\n");
			ret = String.format("There is no path from %s to %s.\n", char1, char2);
		}
		return ret;
	}
	
	
	
	
	
	
	
}