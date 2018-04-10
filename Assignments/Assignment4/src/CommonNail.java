/*
 * A program made by Joey Tepperman for CMPE212 Assignment 4 on March 23rd 2018
 * This program implements inheritance, polymorphism and hierarchies
 */
public class CommonNail extends Nail {
	private static final long serialVersionUID = 1560281886225810698L;
	private String[] sizeList = { "6D", "8D", "10D", "12D", "16D", "60D" };
	private double[] lengthList = { 2, 2.5, 3, 3.25, 3.5, 6 };
	private double[] gaugeList = { 2, 8, 9, 10.25, 11.5 };
	// Checking for validity

	public CommonNail(String size, double length, double gauge, String finish, double unitPrice, int numPerUnit)
			throws IllegalFastener {
		super(size, length, gauge, finish, unitPrice, numPerUnit);
		if (!isIn(size, sizeList) || !isIn(length, lengthList) || !isIn(gauge, gaugeList))
			throw new IllegalFastener("");
	} // CommonNail Constructor

	@Override
	public void checkFinishes(String material, String finish) throws IllegalFastener {
		if (!finish.equalsIgnoreCase("Bright") && !finish.equalsIgnoreCase("Hot Dipped Galvanized"))
			throw new IllegalFastener("Illegal finish");
	} // checkFinishes method

	@Override
	public String toString() {
		return "Common Nail - " + super.toString();
	} // toString method

} // CommonNail class
