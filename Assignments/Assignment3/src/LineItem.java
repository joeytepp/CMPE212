import java.io.Serializable;

/**
 * <p>
 * This is a class for ordering several pizzas
 * 
 * @author Joey Tepperman
 *
 */
public class LineItem implements Serializable, Comparable<LineItem> {
	private static final long serialVersionUID = 5723581601036010490L;
	Pizza pizza;
	int number;

	/**
	 * A constructor where number and pizza are given
	 * 
	 * @param number
	 *            the number of pizzas being ordered
	 * @param pizza
	 *            the pizza being ordered
	 * @throws IllegalPizza
	 *             if the number is invalid
	 */
	public LineItem(int number, Pizza pizza) throws IllegalPizza {
		if (pizza == null)
			throw new IllegalPizza("Invalid Pizza");
		setNumber(number);
		this.pizza = pizza;
	}

	/**
	 * A constructor that defaults to 1 pizza being ordered
	 * 
	 * @param pizza
	 *            the pizza being ordered
	 * @throws IllegalPizza
	 */
	public LineItem(Pizza pizza) throws IllegalPizza {
		if (pizza == null)
			throw new IllegalPizza("Invalid Pizza");
		this.pizza = pizza;
		this.number = 1;
	}

	/**
	 * A mutator for the number of pizzas
	 * 
	 * @param number
	 *            the number of pizzas
	 * @throws IllegalPizza
	 *             if the number is invalid
	 */
	public void setNumber(int number) throws IllegalPizza {
		if (number < 1 || number > 100)
			throw new IllegalPizza("Number of pizzas must be between 1 and 100");
		this.number = number;
	}

	/**
	 * An accessor for the number of pizzas
	 * 
	 * @return the number of pizzas
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * an accessor for the pizza
	 * 
	 * @return the pizza
	 * @throws IllegalPizza
	 */
	public Pizza getPizza() {
		return pizza;
	}

	/**
	 * Calculates the cost of buying number of the pizza specified
	 * 
	 * @return the total cost in dollars
	 */
	public double getCost() {
		double discount = 0;
		if (number >= 10 && number <= 20)
			discount = 0.1;
		else if (number > 20)
			discount = 0.15;
		return number*pizza.getCost() * (1.0 - discount);
	}

	/**
	 * The compareTo method (from Comparable)
	 */
	@Override
	public int compareTo(LineItem lineItem) {
		return (int) (lineItem.getCost() - getCost());
		
	}

	/**
	 * Converts the LineItem to a string
	 */
	public String toString() {
		if (number < 10)
			return " " + String.valueOf(number) + " " + pizza;
		return String.valueOf(number) + " " + pizza;
	}
}
