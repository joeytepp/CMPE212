/*
 * A program made by Joey Tepperman for CMPE212 Assignment 4 on March 23rd 2018
 * This program implements inheritance, polymorphism and hierarchies
 */

public class Nail extends Fastener {
	private static final long serialVersionUID = -4148762975257780291L;
	private String size;
	private double length;
	private double gauge;
	// Private attributes for Nail
	
	public Nail(String size, double length, double gauge, String finish, double unitPrice, int numPerUnit) throws IllegalFastener {
		super("Steel", finish, unitPrice, numPerUnit);
		this.size = size;
		this.length = length;
		this.gauge = gauge;
	}	// Nail Constructor
	
	@Override
	public String toString() {
		return super.toString() + ", Size:" + size+", Length: "+ 
				String.valueOf(length)+", " + "Gauge: "+String.valueOf(gauge);
	}	// toString method

}	// Nail Class
