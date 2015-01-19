import java.util.Scanner;
import java.util.Arrays;


public class WordSwap {
    private static int costToSwap(String word1, String word2) {

        /* ------------------- INSERT CODE HERE ---------------------*/

        /* Return the number of coins earned (as a positive number) or to be paid (as negative number). 
           For example, if word1 = "a", and word2 = "d", you should return -3.
           Whereas if word1 = "d" and word1 = "a", you should return 3.
         */

        int result = 0;
        for (int pos=0; pos < word1.length(); pos++) {
            result += 
                word1.charAt(pos)
                -
                word2.charAt(pos);
        }	

        return result;

        /* -------------------- END OF INSERTION --------------------*/
    }

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numOfTestCases = sc.nextInt();

		for (int testCase=0; testCase < numOfTestCases; testCase++) {
			String word1 = sc.next();
			String word2 = sc.next();

			int cost = costToSwap(word1, word2);

			System.out.print(
				"Swapping letters to make " +
				word1 + 
				" look like " +
				word2 
			);
			if (cost > 0) {
				System.out.println(
					" earned " + cost + " coins."
				);
			}
			else if (cost < 0) {
				System.out.println(
					" cost " + ((-1)*(cost)) + " coins."
				);
			}
			else {
				System.out.println(
					" was FREE."
				);
			}

		}
		
		sc.close();
	}
}

