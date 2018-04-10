/**
 * This is an exception that is used in the Pizza class
 * @author Joey Tepperman
 *
 */
public class IllegalPizza extends Exception{
	private static final long serialVersionUID = 4593713417540203652L;

	/**
	 * The constructor for IllegalPizza
	 * @param message the message that accompanies the thrown error
	 */
	public IllegalPizza(String message) {
		super(message);
	}
}
