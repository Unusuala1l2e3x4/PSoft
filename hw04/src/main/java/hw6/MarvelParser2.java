package hw6;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import hw4.*;

public final class MarvelParser2 {

//	/** @param: filename The path to the "CSV" file that contains the <hero, book> pairs                                                                                                
//        @param: charsInBooks The Map that stores parsed <book, Set-of-heros-in-book> pairs;
//        	    usually an empty Map
//        @param: chars The Set that stores parsed characters; usually an empty Set.
//        @effects: adds parsed <book, Set-of-heros-in-book> pairs to Map charsInBooks;
//        		  adds parsed characters to Set chars
//        @throws: IOException if file cannot be read of file not a CSV file                                                                                     
//	 */
	
	/** For MarvelPaths2
	 *  @param: filename The path to the "CSV" file that contains the <hero, book> pairs                                                                                                
	    @param: graph The Graph to construct, usually an empty Graph with no nodes/edges
	    @effects: constructs Graph<String,Double> according to the <hero, book> pairs,
	    			where an edge between 2 hero's has weight = 1 / (number of books both hero's are part of)
	    @throws: IOException if file cannot be read of file not a CSV file                                                                                     
	 */
	public static void readData(String filename, Graph<String,Double> graph) 
			throws IOException {
		
		Map<String, HashSet<String>> charsInBooks = new HashMap<String,HashSet<String>>();
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(filename));
	    
	    Map<String, Map<String, Integer>> edgesBookCount = new HashMap<String, Map<String, Integer>>();
	    
	    String char1, char2, book, line = null;
	    
	    while ((line = reader.readLine()) != null) {
	         int i = line.indexOf("\",\"");
	         if ((i == -1) || (line.charAt(0)!='\"') || (line.charAt(line.length()-1)!='\"')) {
	        	 throw new IOException("File "+filename+" not a CSV (\"HERO\",\"BOOK\") file.");
	         }             
	         char1 = line.substring(1,i);
	         book = line.substring(i+3,line.length()-1);
	
	         // Adds the char1 to the set for book
	         HashSet<String> s = charsInBooks.get(book);
	         if (s == null) {
	           s = new HashSet<String>();
	           charsInBooks.put(book, s);
	         } else if (s.contains(char1)) { // skip duplicate char in book
	        	 continue;
	         }
	         
	         // Adds the node for the char1 to the graph. If char1 already in, addNode has no effect.
	         graph.addNode(char1);
	         
	         // update counts with existing characters in the set for the book, then add char1
	         Iterator<String> it = s.iterator();
	         
	         Map<String,Integer> char_count;
	         Integer count;
	         while (it.hasNext()) {
	        	 char2 = it.next();
	        	 char_count = edgesBookCount.get(char1);
	        	 if (char_count == null) char_count = new HashMap<String, Integer>();
	        	 count = char_count.get(char2);
	        	 if (count == null) count = 0; // if new pair
	        	 char_count.put(char2, count + 1); // increment count for pair by 1
	        	 edgesBookCount.put(char1, char_count);
	        	 
	        	 char_count = edgesBookCount.get(char2);
	        	 if (char_count == null) char_count = new HashMap<String, Integer>();
	        	 count = char_count.get(char1);
	        	 if (count == null) count = 0; // if new pair
	        	 char_count.put(char1, count + 1); // increment count for pair by 1
	        	 edgesBookCount.put(char2, char_count);
	         }
	         s.add(char1);
	    }
	    
	    // create weighted edges based on multiplicative inverse of counts
	    Entry<String, Map<String, Integer>> next;
	    Entry<String, Integer> next2;
	    Iterator<Entry<String, Map<String, Integer>>> itr1 = edgesBookCount.entrySet().iterator();
	    Iterator<Entry<String, Integer>> itr2;
	    while (itr1.hasNext()) {
	    	next = itr1.next();
	    	char1 = next.getKey();
	    	
	    	itr2 = next.getValue().entrySet().iterator();
	    	while (itr2.hasNext()) {
	    		next2 = itr2.next();
	    		char2 = next2.getKey();
	    		graph.addEdge(char1, char2, 1.0 / next2.getValue().doubleValue() );
	    	}
	    	
       	 	
	    }
	}
	
	
}
