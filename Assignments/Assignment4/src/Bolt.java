/*
 * A program made by Joey Tepperman for CMPE212 Assignment 4 on March 23rd 2018
 * This program implements inheritance, polymorphism and hierarchies
 */
public class Bolt extends OuterThreaded {
	private static final long serialVersionUID = -8024643226050121385L;

	public Bolt(String material, String finish, double unitPrice, int numPerUnit, String diameter, double length)
			throws IllegalFastener {
		super(material, finish, unitPrice, numPerUnit, diameter, length);
	} // Bolt Constructor

} // Bolt Class