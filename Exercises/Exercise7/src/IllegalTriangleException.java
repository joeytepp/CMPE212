
public class IllegalTriangleException extends Exception {
	private static final long serialVersionUID = 3434188206049412758L;

	public IllegalTriangleException(String message) {
		super(message);
	}
	
	public IllegalTriangleException() {
		this("Illegal Triangle!");
	}
}
