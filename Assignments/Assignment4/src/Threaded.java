/*
 * A program made by Joey Tepperman for CMPE212 Assignment 4 on March 23rd 2018
 * This program implements inheritance, polymorphism and hierarchies
 */
public class Threaded extends Fastener {
	private static final long serialVersionUID = -803616861648276403L;
	private String diameter; // New private attribute
	private String[] diameterList = { "#8-13", "#8-15", "#8-32", "#10-13", "#10-24", "#10-32", "1/4-20", "5/16-18",
			"3/8-16", "7/16-14", "1/2-13", "5/8-11", "3/4-10" };
	// A list of valid diameters
	
	public Threaded(String material, String finish, double unitPrice, int numPerUnit, String diameter)
			throws IllegalFastener {
		super(material, finish, unitPrice, numPerUnit);
		if (!isIn(diameter, diameterList))
			throw new IllegalFastener("Illegal diameter");
		this.diameter = diameter;

	} // Threaded Constructor

	@Override
	public String toString() {
		return super.toString() + ", Diameter: " + diameter;
	} // toString method

} // Threaded class
