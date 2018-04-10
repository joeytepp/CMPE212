import java.util.InputMismatchException;
import java.util.Scanner;

public class Part4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int userNum;
		Scanner screen = new Scanner(System.in);
		System.out.print("Enter a number: ");
		try {
			userNum = screen.nextInt();
			userNum = (int) Math.pow(userNum, userNum);
			System.out.println("Your number to the power of your number is " + userNum);
			screen.close();
		}catch(InputMismatchException e) {
			System.out.println("Wrong input");
		}
	}

}
