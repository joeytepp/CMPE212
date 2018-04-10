/*
 * A program made by Joey Tepperman for CMPE212 Assignment 4 on March 23rd 2018
 * This program implements inheritance, polymorphism and hierarchies
 */
public class WingNut extends Nut {
	private static final long serialVersionUID = -4347416424634481774L;

	public WingNut(String diameter, String material, String finish, double unitPrice, int numPerUnit)
			throws IllegalFastener {
		super(material, finish, unitPrice, numPerUnit, diameter);
	} // WingNut Constructor

	@Override
	public String toString() {
		return "Wing Nut - " + super.toString();
	} // toString method
} // WingNut Class