/**
 * 
 */
package hw7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import hw6.MapPoint;

/**
 * 
 *
 */
public class CampusPaths {
	//not an ADT
	
	private CampusPathsModel campuspaths;
	
	/**
		@param nodesFile The path to the "CSV" file that contains the <name,id,x-coordinate,y-coordinate>'s representing a building/intersection and its coordinates
		@param edgesFile The path to the "CSV" file that contains the <id1,id2> pairs representing edges between buildings/intersections
		@effects constructs Graph<Integer,Double> where an edge between 2 buildings/intersections has weight = Euclidean distance between them                                 
	 */
	public CampusPaths(String file1, String file2) {
		campuspaths = new CampusPathsModel(file1, file2);
	}
	
    /**
     * @effects prints the names and id's of all buildings lexicographically
     */
	public void printBuildings() {
		Iterator<MapPoint> it = campuspaths.buildingsIterator();
		MapPoint p;
		while (it.hasNext()) {
			p = it.next();
			System.out.println(p.toString());
		}
	}
	
    /**
     * @param arg The id of the building to search for
     * @return the name of the building or null if is not found
     */
	public String getBuildingById(Integer arg) {
		return campuspaths.getBuildingById(arg);
	}
	
    /**
     * @param building1 The name of the starting building
     * @param building2 The name of the destination building
     * @effects prints the path starting from building1 to building2, if it exists
     */
	public void printPath(String building1, String building2) {
		System.out.print(campuspaths.findPath(building1, building2));
	}
	
    /**
     * @param str The string to determine if numeric
     * @return true if str represents a numeric value, false otherwise
     */
	private static boolean isNumeric(final String str) {
		if (str == null || str.length() == 0) return false;
		return str.chars().allMatch(Character::isDigit);
	}

	
	/**
	 * @param object (placeholder)
	 * @effects runs the interface
	 */
	public static void main(Object object) {
		
		CampusPaths data = new CampusPaths("data/RPI_map_data_Nodes.csv", "data/RPI_map_data_Edges.csv");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char arg = 0;
		String r1 = null, r2 = null;
		String building1, building2, argStr = null;
		
		while (true) {
			try {
				argStr = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (argStr != null && argStr.length() == 1) {
				arg = argStr.charAt(0);
			}
			
			if (arg == 'b') { // print buildings
				data.printBuildings();
			} else if (arg == 'r') { // find path
				System.out.print("First building id/name, followed by Enter: ");
				try {
					r1 = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				if (isNumeric(r1)) {
					building1 = data.getBuildingById(Integer.parseInt(r1));
				} else {
					building1 = r1;
				}
				
				System.out.print("Second building id/name, followed by Enter: ");
				try {
					r2 = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				if (isNumeric(r2)) {
					building2 = data.getBuildingById(Integer.parseInt(r2));
				} else {
					building2 = r2;
				}
				
				if (building1 != null && building2 != null) data.printPath(building1, building2);
				else {
					if (building1 == null) System.out.printf("Unknown building: [%s]\n", r1);
					if (building2 == null && !r1.equals(r2)) System.out.printf("Unknown building: [%s]\n", r2);
				}
				
			} else if (arg == 'm') { // list menu options
				System.out.println("b -- list all buildings");
				System.out.println("r -- find directions for shortest route between two buildings");
				System.out.println("m -- display a menu of all commands");
				System.out.println("q -- quit");
			} else if (arg == 'q') { // quit
				return;
			} else if (arg == '\n' || arg == 0) {
				continue;
			} else {
				System.out.println("Unknown option");
			}



		}


		

	}

}
