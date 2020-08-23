package hw7;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

import hw4.Graph;
import hw6.*;

public class CampusPathsModel extends MarvelPaths2 {
	//not an ADT
	
	
	/* holds TreeSet of MapPoints that represent buildings, ordering as described by MapPoint's implementation of Comparable */
	private TreeSet<MapPoint> buildings;
	/* maps building/intersection id's to their corresponding MapPoint */
	private HashMap<Integer, MapPoint> idsMap;
	
	
	/**
		@param: nodesFile The path to the "CSV" file that contains the <name,id,x-coordinate,y-coordinate>'s representing a building/intersection and its coordinates
		@param: edgesFile The path to the "CSV" file that contains the <id1,id2> pairs representing edges between buildings/intersections
		@effects: constructs Graph<Integer,Double> where an edge between 2 buildings/intersections has weight = Euclidean distance between them                                 
	 */
	public CampusPathsModel(String nodesFile, String edgesFile) {
		createNewGraph(nodesFile, edgesFile);
	}

	/**
	 *  @param filename The path to the "CSV" file that contains the <hero, book> pairs
	    @effects clears then constructs this.graph according to the <hero, book> pairs,
	    			where an edge between 2 hero's has label = weight = 1 / (number of books both hero's are part of)
	    @throws IOException if file cannot be read or file not a CSV file                                                                                     
	 */
	public void createNewGraph(String file1, String file2) {
		super.graph = new Graph<String,Double>();
		super.pointsMap = new HashMap<String, MapPoint>();
		buildings = new TreeSet<MapPoint>();
		idsMap = new HashMap<Integer, MapPoint>();
		try {
			CampusParser.readData(file1, file2, super.graph, super.pointsMap, buildings, idsMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    /**
     * @return an Iterator<MapPoint> over all points representing buildings, sorted lexicographically by their names
     */
	public Iterator<MapPoint> buildingsIterator() {
		return buildings.iterator();
	}

    /**
     * @param arg An Integer representing the id of the building to search for
     * @return the name of the building or null if is not found
     */
	public String getBuildingById(Integer arg) {
		MapPoint p1;
		
		p1 = idsMap.get(arg);
		
		if (p1 == null) return null;
		else if (! buildings.contains(p1) ) return null;
		else return p1.name();
	}
	
	
	


}
