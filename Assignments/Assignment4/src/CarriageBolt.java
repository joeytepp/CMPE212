/*
 * A program made by Joey Tepperman for CMPE212 Assignment 4 on March 23rd 2018
 * This program implements inheritance, polymorphism and hierarchies
 */
public class CarriageBolt extends Bolt {
	private static final long serialVersionUID = -3440558740476702974L;

	public CarriageBolt(double length, String diameter, String material, String finish, double unitPrice,
			int numPerUnit) throws IllegalFastener {
		super(material, finish, unitPrice, numPerUnit, diameter, length);
	} // CarriageBolt Constructor

	@Override
	public String toString() {
		return "Carriage Bolt - " + super.toString();
	} // toString method

} // CarriageBolt class
