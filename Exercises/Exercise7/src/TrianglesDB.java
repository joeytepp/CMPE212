/*
 * This class is provided to test your Triangle object.
 * Your output should match the output appended to this class, except
 * that your toString() method might generate a different looking output.
 * 
 * Here are a few interesting features of this class:
 * 		- The use of an ArrayList to store the Triangles.
 * 		- The use of a comma delimited file to store the raw data.
 * 		- The use of the Files class to read the file.
 * 		- The use of a StringTokenizer object to help parse the strings read from the datafile.
 * 		- The use of try/catch blocks throughout the program.
 *
 * by Alan McLeod
 */
import java.util.StringTokenizer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TrianglesDB {
	
	// An ArrayList is used to store the triangles.
	private static ArrayList<Triangle> db = new ArrayList<Triangle>();
	
	// This method appends the triangle created from the provided data
	// to the end of the database.  If the data is not legal, the
	// triangle will not be created.
	public static void addTriangle (double a, double b, double c) {
		try {
			Triangle t = new Triangle(a, b, c);
			db.add(t);
		} catch (IllegalTriangleException e) {
			System.out.println(e.getMessage());
		}
	} // end addTriangle
	
	// This method removes the triangle at the position provided as
	// a parameter.
	public static void removeTriangle (int position) {
		if (position < db.size())
			db.remove(position);
	} // end removeTriangle
	
	// Displays the contents of the database
	public static void showDB () {
		for (int i = 0; i < db.size(); i++)
			System.out.println(db.get(i));
	} // end showDB
	
    // Java 7 file reading code.
	public static String[] readText(String filename) {
        ArrayList<String> contents = null;
        Path file = Paths.get(filename);
		if (Files.exists(file, LinkOption.NOFOLLOW_LINKS) && Files.isReadable(file) )
			try {
				contents = (ArrayList<String>)Files.readAllLines(file);
			} catch (IOException err) {
                System.err.format("IOException: %s%n", err);
                System.err.println("Unable to read file: " + filename);
                return null;
			}
		else {
            System.out.println("Unable to open file: " + filename);
            return null;
		}
        return contents.toArray(new String[0]);
    } // end readText

	// This method loads the data from filename into the database.
	public static void loadDB (String filename) {
		int numTokens;
		double a, b, c;
		StringTokenizer st;
		Triangle t = null;
		String[] fileContents = readText(filename);
		for (String aLine : fileContents) {
			st = new StringTokenizer(aLine, "\n\t\r, ");
			numTokens = st.countTokens();
			if (numTokens == 3) {
				try {
					a = Double.parseDouble(st.nextToken());
					b = Double.parseDouble(st.nextToken());
					c = Double.parseDouble(st.nextToken());
					t = new Triangle(a, b, c);
					db.add(t);
				} catch (NumberFormatException e) {
					System.out.println("Illegal side data.");
				} catch (IllegalTriangleException e) {
					System.out.println(e.getMessage());
				} // end try/catch
			} // end if	
		} // end for-each		
	} // end loadDB
	
	public static void main (String[] args) {
		
		// Remember to put the data file in the root of the Eclipse project folder.
        String filename = "TrianglesData.csv";
        
        System.out.println("Carrying out some unit tests:");
		Triangle t1 = null;
		Triangle t2 = null;
		Triangle t3 = null;
		
		try{
			t1 = new Triangle(4, 5, 6);
			t2 = new Triangle(7, 8, 9);
			t3 = new Triangle(t1.getSides());
		} catch (IllegalTriangleException e) {
            System.out.println("Problem creating Triangles!");
		}
		
		System.out.println("Testing Triangle Object:");
        System.out.println(t1.equals(t2));
		System.out.println(t1.equals(t3));
		System.out.println(t1.compareTo(t2));
		System.out.println(t2.compareTo(t1));
		System.out.println(t1.compareTo(t3));
		System.out.printf("Area: %.4f\n", t1.getArea());
		System.out.println("Perimeter: " + t1.getPerimeter());
		
		System.out.println("\nLoading data:");
		loadDB(filename);
		
		System.out.println("\nData as loaded:");
		showDB();
		
		System.out.println("\nAdding Triangles.");
		addTriangle(9, 2.5, 11);
		addTriangle(4.0, 7.0, 9.0);
		addTriangle(1, 1, 20);
		
		System.out.println("Removing Triangles.");
        removeTriangle(2);
		removeTriangle(3);
		removeTriangle(20);
		
		System.out.println("\nAfter additions and deletions:");
		showDB();
		
	} // end main
	
} // end TrianglesDB class
/*
 OUTPUT:
 Carrying out some unit tests:
 Testing Triangle Object:
 false
 true
 -1
 1
 0
 Area: 9.9216
 Perimeter: 15.0
 
 Loading data:
 Illegal triangle sides: 1.0, 1.0, 10.0
 Illegal triangle sides: 10.0, 2.0, 1.0
 
 Data as loaded:
 A Triangle with sides:   3.1,   6.7,   8.1
 A Triangle with sides:   3.5,   7.1,   6.0
 A Triangle with sides:   6.2,   4.0,   8.7
 A Triangle with sides:   3.0,   1.0,   2.2
 A Triangle with sides:   1.0,   2.5,   3.0
 A Triangle with sides:   6.0,   2.0,   7.0
 A Triangle with sides:   3.0,   4.0,   4.5
 A Triangle with sides:   5.0,   4.0,   3.0
 A Triangle with sides:   2.6,   9.0,   7.0
 A Triangle with sides:   2.0,   8.0,   9.0
 A Triangle with sides:  10.0,   2.0,  11.0
 
 Adding triangles:
 Illegal triangle sides: 1.0, 1.0, 20.0
 
 After additions and deletions:
 A Triangle with sides:   3.1,   6.7,   8.1
 A Triangle with sides:   3.5,   7.1,   6.0
 A Triangle with sides:   3.0,   1.0,   2.2
 A Triangle with sides:   6.0,   2.0,   7.0
 A Triangle with sides:   3.0,   4.0,   4.5
 A Triangle with sides:   5.0,   4.0,   3.0
 A Triangle with sides:   2.6,   9.0,   7.0
 A Triangle with sides:   2.0,   8.0,   9.0
 A Triangle with sides:  10.0,   2.0,  11.0
 A Triangle with sides:   9.0,   2.5,  11.0
 A Triangle with sides:   4.0,   7.0,   9.0
 
 */
