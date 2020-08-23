package hw6;


/**
 * <b>MapPoint</b> represents an <b>immutable</b> and labeled point on an Integer-based coordinate plane,
 * with x,y coordinates. A MapPoint is labeled by two fields: an id Integer and a name String.
 * <p>
 */
public final class MapPoint implements Comparable<MapPoint> {
	
	/* holds the name of the point (can be empty string) */
	private final String name;
	/* holds the id of the point */
	private final Integer id;
	/* holds the x coordinate of the point */
	private final Integer x;
	/* holds the y coordinate of the point */
	private final Integer y;
	
	
	/**
	 * Abstraction Function:
	 * MapPoint p represents a labeled point where:
	 * p.x = its x coordinate
	 * p.y = its y coordinate
	 * p.name = its String label
	 * p.id = its Integer label
	 * 
	 * Representation Invariant for every MapPoint p:
	 * p.x != null
	 * p.y != null
	 * p.name != null
	 * p.id != null
	 * 
	 */

    /**
     * @param name_ The name of the MapPoint
     * @param id_ The id of the MapPoint
     * @param x_ The x coordinate of the MapPoint
     * @param y_ The y coordinate of the MapPoint
     * @effects Constructs a new MapPoint with 
     * 			name = name_, id = id_, x = x_, y = y_ 
     */
	public MapPoint(String name_, Integer id_, Integer x_, Integer y_) {
		name = name_;
		id = id_;
		x = x_;
		y = y_;
		checkRep();
	}
	
	
	/**
	 * @return a copy of this MapPoint's name (a String)
	 */
	public String name() {
		return new String(name);
	}
	
	/**
	 * @return a copy of this MapPoint's x coordinate (an integer)
	 */
	public Integer x() {
		return new Integer(x);
	}
	
	/**
	 * @return a copy of this MapPoint's y coordinate (an integer)
	 */
	public Integer y() {
		return new Integer(y);
	}
	
	/**
	 * @return a copy of this MapPoint's id (an integer)
	 */
	public Integer id() {
		return new Integer(id);
	}
	
	
	
	/** Compares two MapPoints lexicographically by name and secondarily by id
	@param p the MapPoint to be compared
	@requires p != null
	@return a negative number if this < p,
		    0 if this = p,
		    a positive number if this > p.
	*/
	@Override
	public int compareTo(MapPoint p) {
		Integer name_ = name.compareTo(p.name);
		Integer id_ = id.compareTo(p.id);
//		Integer x_ = x.compareTo(p.x);
		
		if (name_ != 0) return name_;
		else return id_;
//		else if (id_ != 0) return id_;
//		else if (x_ != 0) return x_;
//		else return y.compareTo(p.y);
	}
	
	
    /**
     * @return a string representing this point
     * in the format "[name],[id]"
     */
	@Override
	public String toString() {
		return String.format("%s,%d",name,id());
	}
	
	/**
     * Checks that the representation invariant holds (if any).
     */
    // Throws a RuntimeException if the rep invariant is violated.
    private void checkRep() throws RuntimeException {
        if (id == null) {
            throw new RuntimeException("id == null");
        }
        if (name == null) {
            throw new RuntimeException("name == null");
        }
        if (x == null || y == null) {
        	throw new RuntimeException("x and/or y is null");
        }


    }
    

}
