import java.util.ArrayList;
import java.util.Scanner;

public class LongestSubsequence {

    private static int solveLongestSubsequence(ArrayList<String> sequence) {
        int answer = -1;
    	
    	/* ------------------- INSERT CODE HERE ---------------------*/

        /* Given a list of Strings, each of which is an "X" or an "O", find the longest contiguous subsequence
           of X's. Set answer to be the number of X's.
         */

        int lengthOfCurrentSubsequenceOfOs = 0;
        for(String s : sequence) {
            if(s.equals("X")) 
                lengthOfCurrentSubsequenceOfOs++;
            else {
                if(answer < lengthOfCurrentSubsequenceOfOs) 
                    answer = lengthOfCurrentSubsequenceOfOs;
                lengthOfCurrentSubsequenceOfOs = 0;
            }
        }

        if(answer < lengthOfCurrentSubsequenceOfOs) 
            answer = lengthOfCurrentSubsequenceOfOs;

        /* -------------------- END OF INSERTION --------------------*/
    	
    	return answer;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numCases = sc.nextInt();

        for(int n = 0; n < numCases; n++)
        {
            int numLength = sc.nextInt();

            ArrayList<String> sequence = new ArrayList<String>();

            for(int i = 0; i < numLength; i++) 
                sequence.add(sc.next());
            
            System.out.println("The longest contiguous subsequence of X's is of length " + solveLongestSubsequence(sequence));
        }
    }
}
