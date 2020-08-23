package hw5;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import hw4.*;

public final class MarvelParser {

//	/** @param: filename The path to the "CSV" file that contains the <hero, book> pairs                                                                                                
//        @param: charsInBooks The Map that stores parsed <book, Set-of-heros-in-book> pairs;
//        	    usually an empty Map
//        @param: chars The Set that stores parsed characters; usually an empty Set.
//        @effects: adds parsed <book, Set-of-heros-in-book> pairs to Map charsInBooks;
//        		  adds parsed characters to Set chars
//        @throws: IOException if file cannot be read of file not a CSV file                                                                                     
//	 */
	
	/** For MarvelPaths
	 *  @param: filename The path to the "CSV" file that contains the <hero, book> pairs                                                                                                
	    @param: graph The Graph to construct, usually an empty Graph with no nodes/edges
	    @effects: constructs Graph<String,String> according to the <hero, book> pairs,
	    			where an edge between 2 hero's has label = name of book they are both found in
	    @throws: IOException if file cannot be read or file not a CSV file                                                                                     
	 */
    public static void readData(String filename, Graph<String,String> graph) 
    		throws IOException {
    	
    	Map<String, LinkedList<String>> charsInBooks = new HashMap<String,LinkedList<String>>();
    	@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = null;
        
        while ((line = reader.readLine()) != null) {
             int i = line.indexOf("\",\"");
             if ((i == -1) || (line.charAt(0)!='\"') || (line.charAt(line.length()-1)!='\"')) {
            	 throw new IOException("File "+filename+" not a CSV (\"HERO\",\"BOOK\") file.");
             }             
             String character = line.substring(1,i);
             String book = line.substring(i+3,line.length()-1);

             // Adds the node for the character to the graph. If character already in, addNode has no effect.
             graph.addNode(character);

             // Adds the character to the set for book
             LinkedList<String> s = charsInBooks.get(book);
             if (s == null) {
               s = new LinkedList<String>();
               charsInBooks.put(book, s);
             }
             // create edges with existing characters in the set for the book, then add character
             Iterator<String> it = s.listIterator();
             String it_string;
             while (it.hasNext()) {
            	 it_string = it.next();
            	 graph.addEdge(it_string, character, book );
            	 graph.addEdge(character, it_string, book );
             }
             s.add(character);
             
        }
    }
    
   


//    public static void main(String[] arg) {
//
//    	String file = arg[0];
//
//    	try {
//    		Map<String, Set<String>> charsInBooks = new HashMap<String,Set<String>>();
//    		Set<String> chars = new HashSet<String>();
//    		readData(file,charsInBooks,chars);
//    		System.out.println("Read "+chars.size()+" characters who appear in "+charsInBooks.keySet().size() +" books.");
//
//    	} catch (IOException e) {
//    		e.printStackTrace();
//    	}
//
//    }
}
