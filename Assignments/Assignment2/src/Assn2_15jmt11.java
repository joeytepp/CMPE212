import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

/*
 * CMPE212 Assignment 2, 2018-02-16
 * Created by Joey Tepperman - 20014257
 * The following program implements File I/O, arrays, 
 * loops, conditionals and methods to solve a real-world problem
 */

public class Assn2_15jmt11 {

	public static void main(String[] args) {
		System.out.println("Collecting Motor Data");	// Opening prompt for the user
		try {
			prompt();	// Running the user prompt method
		} catch (IOException e) {
			System.out.println("An error occured.");
		}	// Catching any exceptions and prompting the user if they occur
	}
	
	// A method that runs the program with console output for the user
	private static void prompt() throws IOException {
		double[][] data = fileToArray("src/Logger.csv");
		System.out.print("Writing Data");
		for(int i = 1; i<=7; i++) {
			writeReport(data, i);	// Writing the data to the CSVs
			System.out.print(".");
		}	// Writing 7 reports for the 7 motors
		System.out.println("Success!");
	}	// prompt method
	
	// The following method will write a report based on the inputed data
	private static void writeReport(double[][] ds, int motorNum) throws IOException {
		PrintWriter printWriter = new PrintWriter(new File("src/Motor"+String.valueOf(motorNum)+".csv"));
		String textData = "start (sec), finish (sec), current (amps)\r\n";
		textData += recognizeCycle(ds, motorNum-1);
		printWriter.write(textData);
		printWriter.close();
	}	// writeReport method
	
	// The following method will recognize any cycles in the motor data and return a CSV formatted string of data
	private static String recognizeCycle(double[][] array, int col) {
		String returnString = "";	// The string of CSV formatted data that will be return
		boolean currentExceeded = false;	// A boolean checking to see if the current has exceeded 7 amps in a cylce
		boolean cycle = false;	// A boolean to recognize if a cycle is currently in place
		double sum = 0;	// A variable to hold the sum of all the currents collected in a cycle
		int startIndex = 0;	// A variable to hold the starting index of every cycle
		for(int i = 1; i<array.length; i++) {
			if(!cycle) {
				if(array[i][col] > 1 && array[i-1][col] < 1) {
					startIndex = i;
					sum += array[i][col];
					if(!currentExceeded) currentExceeded = array[i][col] > 8;
					cycle = true;
				}	// If there is no current cycle and a cycle begins
			} else {
				if(array[i][col] > 1) {
					sum += array[i][col];
					if(!currentExceeded) currentExceeded = array[i][col] > 8;
				}	// If a cycle is currently in place and continuing
				else {
					returnString += String.valueOf(startIndex) + ", ";
					returnString += String.valueOf(i-1) + ", ";
					returnString += roundNum(sum/(i-startIndex));
					if(currentExceeded) returnString += ", ***Current Exceeded***";
					returnString += "\r\n";
					sum = 0;
					cycle = false;
					currentExceeded = false;
				}	// If a cycle is currently in place but is ending at this index
			}
			
		}
		if (returnString.isEmpty()) return "NOT USED";	// If there is no motor data to be collected
		return returnString;	// If there is data to be collected  for the motor
	}
	
	// The following method will convert the text inside a CSV file to an array 
	private static double[][] fileToArray(String filePath) throws IOException {
		FileReader fileReader = new FileReader(filePath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);	// Creating the necessary readers for the file
		String line = bufferedReader.readLine();	// Creating a variable that will be used to traverse the CSV file
		double rawData[][] = new double[1000][7];	// An array in which the collected data will be stored
		int counter = 0;	// A counter for the index of the array
		String lineArray[] = new String[8];	// A string that will be used to convert each individual line of text to an array
		while(line != null) {
			//System.out.println("Line: "+line);
			lineArray = line.split(", ");
			for(int i = 1; i<lineArray.length; i++) {
				rawData[counter][i-1] = Double.parseDouble(lineArray[i]);
			}	// Adding each individual line as an array into the 2D array
			line = bufferedReader.readLine();
			counter++;
		}	// This loops through the entire file and adds the data to the rawData array
		bufferedReader.close();
		return rawData;	// 2D Array is returned
	}	// fileToArray Method
	
	// The following method will return a formatted string to 3 decimal places for any double
	private static String roundNum(double num) {
		DecimalFormat decimalFormat = new DecimalFormat("#.###");
		return decimalFormat.format(num);
	}	// roundNum method
}	// Assn2_15jmt11 Class