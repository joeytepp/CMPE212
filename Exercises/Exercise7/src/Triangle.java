/* A program made by Joey Teppemran on 2018-04-09
 * An Encapsulated Triangle
 */
public class Triangle implements Comparable<Triangle> {
	private double side1, side2, side3;
	
	public Triangle(double side1, double side2, double side3) throws IllegalTriangleException {
		if(!validTriangle())
			throw new IllegalTriangleException("Invalid Triangle!");
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}
	
	public Triangle(double[] sides) throws IllegalTriangleException {
		this(sides[0], sides[1], sides[2]);
		if(sides.length != 3)
			throw new IllegalTriangleException("Invalid number of sides!");
	}
	
	private boolean validTriangle() {
		double[] sides = {side1, side2, side3};
		for(int i = 0; i<sides.length; i++) {
			if(sides[i]+sides[(i+1)%sides.length] <= sides[(i+2)%sides.length])
				return false;
		}
		return true;
	}
	
	public double getArea() {
		double s = (side1 + side2 + side3)/2;
		return Math.sqrt(s*(s-side1)*(s-side2)*s-side3);
	}
	
	public double getPerimeter() {
		return side1 + side2 + side3;
	}
	
	public double[] getSides() {
		double[] sides = {side1, side2, side3};
		return sides;
	}

	@Override
	public int compareTo(Triangle obj) {
		if(getArea() == obj.getArea())
			return 0;
		return (getArea()>obj.getArea())?1:-1;
	}
	
	public Triangle clone() {
		try {
			return new Triangle(side1, side2, side3);
		} catch (IllegalTriangleException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
}
