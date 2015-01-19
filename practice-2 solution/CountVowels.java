import java.util.ArrayList;
import java.util.Scanner;

public class CountVowels {

    private static int countVowels(String s) {
        int c = -1;

		/* ------------------- INSERT CODE HERE ---------------------*/

        c = 0;

        for (int i = 0, n = s.length(); i < n; i++) {
              if("AEIOUaeiou".indexOf(s.charAt(i)) != -1) 
                  c++;
        }

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
	}
}
