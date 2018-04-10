/*
 * A program made by Joey Tepperman for CMPE212 Assignment 4 on March 23rd 2018
 * This program implements inheritance, polymorphism and hierarchies
 */

public class WoodScrew extends Screw {
	private static final long serialVersionUID = 3942865230028268608L;
	private String pointList[] = { "Double Cut", "Sharp", "Type 17" };
	private String point;

	public WoodScrew(double length, String diameter, String material, String finish, String head, String drive,
			String point, double unitPrice, int numPerUnit) throws IllegalFastener {
		super(length, diameter, material, finish, head, drive, unitPrice, numPerUnit);
		this.point = point;
		if (!isIn(point, pointList))
			throw new IllegalFastener("Illegal point!");
	} // WoodScrew Constructor

	public String toString() {
		return "Wood Screw - " + super.toString() + "Point: " + point;
	} // toString method

} // WoodScrew class
