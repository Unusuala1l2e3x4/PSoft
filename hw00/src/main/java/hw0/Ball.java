/**
 * This is part of HW0: Environment Setup and Java Introduction.
 */
package hw0;

import java.awt.Color;

/**
 * This is a simple object that has a volume.
 */
// You may not make Ball implement the Comparable interface.
public class Ball {

    private double volume;   
    private Color color;

    /**
     * Constructor that creates a new ball object with the specified volume and color.
     * @param volume the volume of the new ball object
     * @param color the color of the new ball object
     */
    public Ball(double volum, Color colo) {
        volume = volum;
        color = colo;
    }
    
    /**
     * Constructor that creates a new ball object with the specified volume given by a string.
     * @param volume A string representing the volume of the new object.
     */
    public Ball(String volum, Color colo) {
    	volume = Double.valueOf(volum);
//		this(Double.parseDouble(volume), color);
    	color = colo;
    }    

    /**
     * Returns the volume of the ball.
     * @return the volume of the ball.
    */
    public double getVolume() {
//        return 0;
    	return volume;
    }
    
    /**
     * Returns the color of the ball.
     * @return the color of the ball.
    */
    public Color getColor() {
//        return null;
    	return color;
    }

}


