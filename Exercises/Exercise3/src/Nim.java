import java.util.InputMismatchException;
import java.util.Scanner;

public class Nim {
	public static void main(String[] args) {
		int pileSize = (int)(Math.random()*90+10);
		int startPlayer = (int)Math.round(Math.random());
		int smartNess = (int)Math.round(Math.random());
		int userInput, compInput;
		boolean right = false;
		Scanner screen = new Scanner(System.in);
		if(smartNess>0) System.out.println("Smart Mode");
		else System.out.println("Random Mode");
		while(pileSize>1) {
			System.out.println(pileSize+" in the pot");
			if(startPlayer > 0) {
				//Player's turn
				while(!right) {
					System.out.println("Enter a number between 1 and "+pileSize/2);
					try {
						userInput = screen.nextInt();
						if(userInput<1 || userInput > pileSize/2) throw new InputMismatchException();
						pileSize -= userInput;
						right = true;
					}catch(InputMismatchException exc) {
						System.out.println("Please try again");
						screen.next();
					}
				}
			}
			else {
				if(smartNess>0) {
					int exponent = (int)(Math.log10(pileSize)/Math.log10(2)); 
					int intended = (int)Math.pow(2, exponent)-1;
					compInput = pileSize-intended;
					//compInput = 
					pileSize-=compInput;
				}else {
					compInput = (int)(Math.random()*(pileSize/2-1)+1);
					pileSize -= compInput;
				}
				System.out.println("Computer entered "+compInput);
			}
			
			startPlayer++;
			startPlayer%=2;
			right = false;
		}
		if(startPlayer > 0) System.out.println("You lose");
		else System.out.println("You win!");
		screen.close();
	}
}
