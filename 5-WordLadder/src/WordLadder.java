import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Vector;
import java.util.Collections;
import java.util.Random;

public class WordLadder {

    private static Vector<String> solveWordLadder(String[] dict, String start, String end) {
        /* ------------------- INSERT CODE HERE --------------------
         *
         * Your code should find the word ladder from "start" to "end" using only the words in the dictionary.
         * In case there are many word ladders from "start" to "end", return the shortest one.
         *
         * If there is a tie (i.e., multiple ladders with the shortest length), return the one that comes
         * lexicographically first, starting from the first word in the ladder.
         *
         * The ladder, if one exists, should be returned in "ret". The first entry in "ret" should be 
         * the starting word, and the last entry in it should be the ending word. We have already populated
         * those two in ret. 
         *
         * If there is no ladder between the two words, ret should be set to 'null'.
         *
         * */
        Vector<String> ret = new Vector<String>();

        ret.add(start);
        ret.add(end);

        

        
        
        return ret;

        /* -------------------- END OF INSERTION --------------------*/
    }


    static Random r = new Random(0);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numTests = sc.nextInt();

        /* Each test case begins with a dictionary, and then contains a list of pairs of words. */

        for (int i = 0; i < numTests; i++) {
            int numEntriesInDictionary = sc.nextInt();
            String[] dict = new String[numEntriesInDictionary];

            for(int j = 0; j < numEntriesInDictionary; j++)
                dict[j] = sc.next().toLowerCase();

            int numPairs = sc.nextInt();
            for(int j = 0; j < numPairs; j++) 
            {
                String start = sc.next().toLowerCase();
                String end = sc.next().toLowerCase();

                Vector<String> v = solveWordLadder(dict, start, end);

                if(v != null) {
                    System.out.print("Word ladder from " + start + " to " + end + ": " );
                    System.out.print(v.remove(0));
                    for(String s : v)
                        System.out.print(" --> " + s);
                    System.out.println();
                } else {
                    System.out.println("No word ladder from " + start + " to " + end + " using the input dictionary.");
                }
            }
        }
    }
}
