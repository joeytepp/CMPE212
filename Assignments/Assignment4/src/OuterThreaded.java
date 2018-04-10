/*
 * A program made by Joey Tepperman for CMPE212 Assignment 4 on March 23rd 2018
 * This program implements inheritance, polymorphism and hierarchies
 */
public class OuterThreaded extends Threaded {
	private static final long serialVersionUID = -280701619669446573L;
	private double length;
	// private attributes

	public OuterThreaded(String material, String finish, double unitPrice, int numPerUnit, String diameter,
			double length) throws IllegalFastener {
		super(material, finish, unitPrice, numPerUnit, diameter);
		this.length = length;
		if (!validLength())
			throw new IllegalFastener("Illegal length!");
	} // OuterThreaded Constructor

	@Override
	public String toString() {
		return super.toString() + ", Length: " + String.valueOf(length);
	} // toString method

	private boolean validLength() {
		for (double i = 0.5; i <= 6; i += 0.25) {
			if (length == i)
				return true;
		}
		for (double i = 6; i <= 11; i += 0.5) {
			if (length == i)
				return true;
		}
		for (int i = 11; i <= 20; i++) {
			if (length == i)
				return true;
		}
		return false;

	} // validLength method

} // OuterThreaded class
