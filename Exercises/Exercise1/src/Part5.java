import java.io.IOException;

public class Part5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] buffer = new byte[80];
		int numRead = -1;
		System.out.print("Please enter something");
		try {
			numRead = System.in.read(buffer);
		}catch(IOException e) {
			System.out.println("Should not have got here");
		}
		System.out.println("You typed in " + numRead + " characters.");
		System.out.println("As bytes:");
		for(int i = 0; i<numRead; i++)
			System.out.print(buffer[i] + "\t");
	}

}
