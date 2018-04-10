/*
 * CMPE212 Assignment 1
 * Created by Joey Tepperman (15jmt11 - 20014257) , submitted on February 1st
 * This program is a simulation of a dice game called 'Pig'
 * The user rolls two dice and plays against a computer player
 * The program implements methods, loops, conditionals and console I/O
 */
import java.util.Random;
import java.util.Scanner;

public class Assn1_15jmt11 {
	static Random generator = new Random(System.currentTimeMillis());
	public static void main(String[] args) throws InterruptedException{
		int dice1 = 0, dice2 = 0;	// Integers holding the numbers rolled by each die
		int scoreUser = 0, scoreComp = 0; // Integers storing the user and computer score
		double computerRand = 1;	// A variable that will use the random generator to determine if the 
								// computer passes on the turn or not
		boolean turn = true;	// A boolean to determine whose turn it is (true if it's player's turn)
		String input = "input"; // A string that will hold the user input
		Scanner scanner = new Scanner(System.in); 	// Creating the scanner for I/O
		System.out.print("Welcome to Pig! "); // Greeting the user
		
		while(!input.isEmpty()) {
			System.out.println("Press <enter> to play");
			input = scanner.nextLine();
		}	// Waiting until the user presses enter to begin the game
		
		promptUser(turn);	// Prompting the user based on whose turn it is
		
		while(scoreUser < 100 & scoreComp < 100) {
			if(turn) {
				input = scanner.nextLine();
				if(input.equals("r")) {
					dice1 = random(1,6);
					dice2 = random(1, 6);	// Rolling the dice
					System.out.println("You rolled a " + numberName(dice1) +" and a " + numberName(dice2)+ ".");
					if(dice1 == dice2) {
						if(dice1 == 1) {
							scoreUser += 25;
							System.out.print("SNAKE EYES! 25 added to your score. Score is now " + scoreUser);
						}	// The user rolled snake-eyes
						else {
							scoreUser += (dice1+dice2)*2;
							System.out.print("DOUBLES! "+(dice1+dice2)*2+" added to your score. Score is now " + scoreUser);
						}	// The user rolled doubles but not snake-eyes
							while(!input.isEmpty()) {
								System.out.print(". You must roll again. Press <enter>");
								input = scanner.nextLine();
							}	// The user must roll again since they got doubles
							
							System.out.println("Press r to roll.");	// Prompting the user to roll the dice
					}	// Checking for doubles
					else if(dice1 == 1 || dice2 == 1) {
						System.out.println("You rolled a 1 so it is now the computer's turn and your score is zero");
						scoreUser = 0;
						promptUser(!turn);
						computerRand = 1;
						turn = !turn;
					}	// When the use rolls a 1 that is not a double then their turn is over
					else {
						scoreUser += dice1+dice2;
						System.out.println((dice1)+(dice2)+" added to your score. Score is now " + scoreUser);
						// The user has rolled two unequal dice, both not 1
						while(!input.equals("y") && !input.equals("n") && scoreUser<100) {
							System.out.println("Press 'y' or 'n' to continue");
							input = scanner.nextLine();
						}	// The user is given the choice to continue the game
						if(input.equals("y")) {
							System.out.println("Press 'r' to roll");
						}else {
							promptUser(!turn);
							computerRand = 1;	// Must be 1 since the computer cannot hold on the first roll
							turn = !turn;
						}	// The actions based on whether the user selects yes or no
						
					}	// Standard rolling procedure
				}	// One the player has rolled the dice
			}	// Player's turn
			
			else {
				if(computerRand>0.5) {
					Thread.sleep(1500);
					System.out.println("Computer will now roll");	// Delaying and then prompting the user
					dice1 = random(1, 6);
					dice2 = random(1, 6);	// Rolling the dice
					Thread.sleep(1000);
					System.out.println("Computer rolled a "+dice1+ " and a "+dice2+".");	// Delaying and then 
																							// displaying the 
																							// numbers rolled
					Thread.sleep(1500);
					if(dice1 == dice2) {
						if(dice1 == 1) {
							scoreComp += 25;
							System.out.print("SNAKE EYES! 25 added to computer score. Score is now "+ scoreComp);
						}	// The computer rolled snake-eyes
						else {
							scoreComp+= (dice1+dice2)*2;
							System.out.print("DOUBLES! "+(dice1+dice2)*2+" added to computer score. Computer score is now "+scoreComp);
						}	// The computer rolled doubles but not snake-eyes
						System.out.println(". Computer must go again");
					}	// The computer rolled doubles
					else if(dice1 == 1 || dice2 == 1) {
							System.out.println("User turn now since computer rolled a 1. Computer score is also now zero");
							scoreComp = 0;
							promptUser(!turn);
							turn = !turn;
					}	// The computer rolled a 1 that was not doubles
					else {
							scoreComp+=dice1+dice2;
							System.out.println((dice1+dice2)+" added to computer score. Computer score is now "+scoreComp);
					}	// The computer didn't roll doubles or a 1
				}
				else {
					System.out.println("Computer will pass on this turn.");
					promptUser(!turn);
					turn = !turn;
				}	// Based on the value of computerRand the computer has decided to pass on this turn
				
				if(dice1 != dice2)
					computerRand = generator.nextDouble();	// Resetting computerRand
				else
					computerRand = 1;	// The computer must go again if the computer rolled doubles
			}	// Computer's turn
		}	// Game functionality that only takes place when both scores are below 100
		scanner.close();
		if(scoreUser>100)
			System.out.println("You win!");
		else
			System.out.println("Computer wins!");	// Displaying the winner based on the final score
	}	// main method
	
	// This method will return a random number between a and b
	private static int random(int a, int b) {
		int rand = (int)Math.round(generator.nextDouble()*(b-a));	// Generating the random integer
		return a+rand;	// Returning the random number
	}	// random method
	
	// This method will convert a number between 1 and 6 to a word
	private static String numberName(int a) {
		String[] names = {"one", "two", "three", "four", "five", "six"}; // An array containing 
																		 // the names of the numbers
		if(a > 6 || a < 1) {
			return "";
		}	// If the integer value is out of bounds then return a blank string
		return names[a - 1];	// Returning the number as a string
		
	}	// numberName method
	
	// This method will prompt the user as to whose turn it is
	private static void promptUser(boolean turn) {
		if(turn)
			System.out.println("Player's turn! Press 'r' to roll!");
		else
			System.out.print("Computer's turn! ");
	}	// promptUser method
}