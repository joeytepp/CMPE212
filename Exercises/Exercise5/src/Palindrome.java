import java.util.Scanner;

public class Palindrome {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		String word;
		System.out.println("Enter a word");
		word = scanner.nextLine();
		System.out.print("That word is ");
		if(!isPalindrome(word)) System.out.print("not ");
		System.out.println("a palindrome.");
		scanner.close();
		
	}
	
	private static String remove(String string, String c) {
		return string.replace(c, "");
	}	// This method removes a substring from a string
	
	private static String convertWord(String input) {
		input = input.trim();// Removing spaces
		input = input.toLowerCase();	// Converting to lower case
		int index = 0;
		while(index<input.length()) {
			if(!Character.isLetter(input.charAt(index)))
				input = remove(input, String.valueOf(input.charAt(index)));
			else index++;
		}	// Removing any non letters
		
		return input;	// Returning the formatted input
	}	// A method that formats a string to be properly checked for palindromes
	
	private static boolean isPalindrome(String string) {
		string = convertWord(string);	// Properly formatting the input
		int len = string.length();
		for(int i = 0; i<len/2; i++) {
			if(string.charAt(i) != string.charAt(len-i-1)) return false;
		}
		return true;
	}
}
