package hw7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeSet;

import hw4.*;
import hw6.MapPoint;

public final class CampusParser {
	

	/**
	 * @returns a String[] representing the fields found in a line in a CSV file
	 * @throws IOException if file cannot be read or file not a CSV file
	 */
	private static String[] parseLine(int fieldslen, String line, String exc) throws IOException {
		String[] fields, fieldsComma, fieldsQuote;
		fields = new String[fieldslen];
			
		fieldsComma = line.split(",");
		if (fieldsComma.length != fieldslen) throw new IOException(exc);
		
		for (int i = 0; i < fieldslen; i++) {
			fieldsQuote = fieldsComma[i].split("\"");
			if (fieldsQuote.length != 1) throw new IOException(exc);
			fields[i] = fieldsQuote[0];
		}
		
		return fields;
	}

	
	/** For CampusPaths
		@param nodesFile The path to the "CSV" file that contains the <name,id,x-coordinate,y-coordinate>'s representing a building/intersection and its coordinates
		@param edgesFile The path to the "CSV" file that contains the <id1,id2> pairs representing edges between buildings/intersections
		@param graph The Graph to construct, usually an empty Graph with no nodes/edges
		@param pointsMap The map that maps building/intersection names to their corresponding MapPoint
		@param buildings The set of all MapPoints that represent buildings
		@param idsMap The map that maps building/intersection id's to their corresponding MapPoint
		@effects constructs Graph<Integer,Double> where an edge between 2 buildings/intersections has weight = Euclidean distance between them
		@throws IOException if file cannot be read of file not a CSV file
	*/
	public static void readData(String nodesFile, String edgesFile, Graph<String,Double> graph, HashMap<String, MapPoint> pointsMap, TreeSet<MapPoint> buildings, HashMap<Integer, MapPoint> idsMap)
			throws IOException {
		
		String name1, name2, inter, line, ioex;
		Integer id1, id2, x, y;
		
		String[] fields;
		
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(nodesFile));//read nodes file
		ioex = "File "+nodesFile+" not a CSV (\"NAME\",\"ID\",\"X\",\"Y\") file.";
		
		while ((line = reader.readLine()) != null) {
			
			fields = parseLine(4, line, ioex);
			
			name1 = fields[0];
			id1 = Integer.parseInt(fields[1]);
			x = Integer.parseInt(fields[2]);
			y = Integer.parseInt(fields[3]);
			
			MapPoint newPoint;

			if (name1.equals("")) { //intersection
				inter = String.format("Intersection %d", id1);
				graph.addNode(inter);
				
				newPoint = new MapPoint(inter, id1, x, y);
				pointsMap.put(inter, newPoint);
				
			} else { 				//building
				graph.addNode(name1);
				
				newPoint = new MapPoint(name1, id1, x, y);
				pointsMap.put(name1, newPoint);
				
				buildings.add(newPoint); // stores data for listing only buildings lexicographically
			}

			idsMap.put(id1, newPoint);
		}
		
		
		reader = new BufferedReader(new FileReader(edgesFile)); // read edges file
		ioex = "File "+edgesFile+" not a CSV (\"ID1\",\"ID2\") file.";
		
		while ((line = reader.readLine()) != null) {
			fields = parseLine(2, line, ioex);
		  
			id1 = Integer.parseInt(fields[0]);
			id2 = Integer.parseInt(fields[1]);
			
			MapPoint p1 = idsMap.get(id1);
			MapPoint p2 = idsMap.get(id2);
			
			if (p1 == null || p2 == null) throw new IOException("idsMap.get() failed");
			
			name1 = p1.name();
			name2 = p2.name();
			
			double distance = Math.hypot( p1.x() - p2.x()  ,  p1.y() - p2.y() );

			graph.addEdge(name1, name2, distance);
			graph.addEdge(name2, name1, distance);
		}
	  
	  
	  
	}


}
