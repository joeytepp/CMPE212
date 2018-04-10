import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * This is a class for organizing and customizing Pizzas
 * <p>
 * This program was made by Joey Tepperman for submission on March 9th 2018 at
 * Queen's University in CMPE212
 * <p>
 * This class is an example of encapsulation and incorporates several common
 * methods such as equals() and clone()
 * <p>
 * 
 * @author Joey Tepperman
 */
public class Pizza implements Serializable {
	private static final long serialVersionUID = -1224664535578018569L;
	private String size;
	private String cheese;
	private String mushrooms;
	private String pepperoni;

	/**
	 * A constructor for a Pizza where all the ingredients are given
	 * 
	 * @param size
	 *            a String detailing the size of the pizza
	 * @param cheese
	 *            a String detailing the cheese content of the pizza
	 * @param mushrooms
	 *            a String detailing the mushroom content of the pizza
	 * @param pepperoni
	 *            a String detailing the mushroom content of the pizza
	 * @throws IllegalPizza
	 */
	public Pizza(String size, String cheese, String mushrooms, String pepperoni) throws IllegalPizza {
		String[] validSizes = { "small", "medium", "large" };
		String[] validCheese = { "single", "double", "triple" };
		String[] validToppings = { "none", "single", "double" };
		if (!contains(validSizes, size) || !contains(validCheese, cheese) || !contains(validToppings, mushrooms)
				|| !contains(validToppings, pepperoni))
			throw new IllegalPizza("Invalid Pizza!");
		if(!mushrooms.equals("none") && pepperoni.equals("none"))
			throw new IllegalPizza("Invalid Pizza (it's a matter of taste)!");
		this.size = size.toLowerCase();
		this.cheese = cheese.toLowerCase();
		this.mushrooms = mushrooms.toLowerCase();
		this.pepperoni = pepperoni.toLowerCase();
	}

	/**
	 * A constructor for pizza where none of the parameters are given
	 */
	public Pizza() {
		this.size = "small";
		this.cheese = "single";
		this.mushrooms = "none";
		this.pepperoni = "single";
	}

	/**
	 * This method checks to see if a specific value is present in an array
	 * 
	 * @param array
	 *            the array of values being tested
	 * @param value
	 *            the value being looked for in the array
	 * @return true if the value is present in the array, false otherwise
	 */
	private boolean contains(String[] array, String value) {
		for (String string : array) {
			if (string.equalsIgnoreCase(value))
				return true;
		}
		return false;
	}

	/**
	 * This method determines if a given object is an identical Pizza
	 * 
	 * @param object
	 *            the object being compared to the current pizza
	 */
	public boolean equals(Object object) {
		if (object instanceof Pizza) {
			Pizza otherObject = (Pizza) object;
			if (otherObject.size.equals(size) && otherObject.cheese.equals(cheese)
					&& otherObject.mushrooms.equals(mushrooms) && otherObject.pepperoni.equals(pepperoni))
				return true;
			else
				return false;
		} else
			return false;
	}

	/**
	 * This method creates an identical pizza to the current one
	 * 
	 * @return a cloned pizza
	 */
	public Pizza clone() {
		try {
			return new Pizza(size, cheese, mushrooms, pepperoni);
		} catch (IllegalPizza e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	/**
	 * A method for calculating the cost of the pizza
	 * 
	 * @return the cost of the pizza in dollars
	 */
	public double getCost() {
		String toppings[] = { cheese, mushrooms, pepperoni };
		double cost = 0;
		if (size.equals("small"))
			cost += 7;
		else if (size.equals("medium"))
			cost += 9;
		else
			cost += 11;
		for (int i = 0; i < toppings.length; i++) {
			if (i == 0) {
				if (toppings[i].equals("double"))
					cost += 1.5;
				if (toppings[i].equals("triple"))
					cost += 3;
			} else {
				if (toppings[i].equals("single"))
					cost += 1.5;
				if (toppings[i].equals("double"))
					cost += 3;
			}
		}
		return cost;
	}

	/**
	 * A method to convert a pizza to a string
	 * 
	 * @return a string representation of a pizza
	 */
	@Override
	public String toString() {
		String parameters[] = { size, cheese, mushrooms, pepperoni };
		String names[] = { "pizza", "cheese", "mushrooms", "pepperoni" };
		String output = "";
		for (int i = 0; i < parameters.length; i++) {
			if (parameters[i].equals("none"))
				output += "no ";
			else
				output += parameters[i] + " ";
			output += names[i];
			if (i < parameters.length - 1)
				output += ", ";
		}
		NumberFormat formatter = new DecimalFormat("#0.00");
		return output + ". Cost: $" + formatter.format(getCost()) + " each.";
	}

}
