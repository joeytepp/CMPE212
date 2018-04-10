
/*
 * A program made by Joey Tepperman for CMPE212 Assignment 4 on March 23rd 2018
 * This program implements inheritance, polymorphism and hierarchies
 */

import java.io.Serializable;

public class Fastener implements Serializable {
	private static final long serialVersionUID = -3250176970915222282L;
	private String materialsList[] = { "Brass", "Stainless Steel", "Steel" };
	// A list of acceptable values for the material
	private String material;
	private String finish;
	private double unitPrice;
	private int numPerUnit;
	// Private attributes for fasteners

	public Fastener(String material, String finish, double unitPrice, int numPerUnit) throws IllegalFastener {
		if (numPerUnit < 1 || numPerUnit > 10000) {
			throw new IllegalFastener("Illegal number per unit!");
		}
		if (!isIn(material, materialsList))
			throw new IllegalFastener("Illegal material!");
		if (!material.equalsIgnoreCase("Steel") && !finish.equalsIgnoreCase("Plain"))
			throw new IllegalFastener("Illegal finish");
		checkFinishes(material, finish);
		this.material = material;
		this.finish = finish;
		this.unitPrice = unitPrice;
		this.numPerUnit = numPerUnit;
	} // Fastener constructor

	@Override
	public String toString() {
		return ("Material: " + material + ", Finish: " + finish + ", Unit Price: " + String.valueOf(unitPrice)
				+ ", Number per Unit: " + numPerUnit);

	} // ToString method

	// A boolean to check if a String can be found in an array of Strings
	public boolean isIn(String test, String[] array) {
		for (String str : array) {
			if (str.equals(test))
				return true;
		}
		return false;
	} // isIn method

	// A boolean to check if a double can be found in an array of doubles
	public boolean isIn(double test, double[] array) {
		for (double d : array) {
			if (d == test)
				return true;
		}
		return false;
	} // isIn method

	// A method to check to ensure that the finish is valid
	public void checkFinishes(String material, String finish) throws IllegalFastener {
		String finishesForSteel[] = { "Chrome", "Hot Dipped Galvanized", "Plain", "Yellow Zinc", "Zinc" };
		if (material.equalsIgnoreCase("Steel") && !isIn(finish, finishesForSteel)) {
			System.out.println(isIn(finish, finishesForSteel) + " , " + finish);

			throw new IllegalFastener("Illegal finish");
		}
	} // checkFinishes method

	// A method that returns the cost of the order as a String
	public String getOrderCost(int orderSize) {
		return String.valueOf(orderSize * numPerUnit * unitPrice);
	} // getOrderCost method

}
