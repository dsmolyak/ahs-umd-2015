import java.util.*;

public class CountVowels {

    private static int countVowels(String s) {
        int c = -1;

		/* ------------------- INSERT CODE HERE ---------------------*/


        
        
        
        
        
        
		/* -------------------- END OF INSERTION --------------------*/

        return c;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numCases = sc.nextInt();

		for(int i = 0; i < numCases; i++)
		{
			String s = sc.next();
            System.out.println("The number of vowels in " + s + " is " + countVowels(s) + ".");
		}
		
		sc.close();
	}
}
