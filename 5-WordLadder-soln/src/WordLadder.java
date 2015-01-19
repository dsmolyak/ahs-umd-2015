import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Vector;
import java.util.Collections;
import java.util.Random;

public class WordLadder {

    private static void expandFirst(Vector<Integer> queue, Vector<Integer> adjacent[], int[] prev) {
        int first = queue.remove(0).intValue();

        for(Integer i : adjacent[first]) {
            int next = i.intValue();

            if(prev[next] == -1) {
                queue.add(next);
                prev[next] = first;
            }
        }
    }

    private static boolean are_adjacent(String s1, String s2) {
        if(s1.length() != s2.length()) 
            return false;

        boolean found_one_difference = false;
        for(int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                if(found_one_difference) 
                    return false;
                found_one_difference = true;
            }
        }

        return found_one_difference;
    }

    static Vector<Integer> adjacent[];

    private static Vector<Integer> solveWordLadder(String[] dict, String start, String end) {
        /* ------------------- INSERT CODE HERE --------------------
         *
         * Your code should find the word ladder from "start" to "end" using only the words in the dictionary.
         * In case there are many word ladders from "start" to "end", return the shortest one.
         * If there is a tie (i.e., multiple ladders with the shortest length), return the one that comes
         * lexicographically first, starting from the first word in the ladder.
         *
         * */
        Vector<Integer> ret = new Vector<Integer>();

        int start_index = Arrays.asList(dict).indexOf(start);
        int end_index = Arrays.asList(dict).indexOf(end);

        Vector<Integer> queue = new Vector<Integer>();

        int[] prev = new int[dict.length];
        for(int i = 0; i < prev.length; i++)
            prev[i] = -1;

        queue.add(new Integer(start_index));

        expandFirst(queue, adjacent, prev);
        if(queue.isEmpty()) return null;

        while(queue.get(0).intValue() != end_index) {
            expandFirst(queue, adjacent, prev);
            if(queue.isEmpty()) return null;
        }

        /* Fill in ret. */
        int last = queue.get(0).intValue();

        ret.add(new Integer(last));
        while(last != start_index) {
            last = prev[last];
            ret.add(0, new Integer(last));
        }

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

            Arrays.sort(dict);

            adjacent = (Vector<Integer>[]) new Vector[dict.length];
            for(int ii = 0; ii < dict.length; ii++) {
                adjacent[ii] = new Vector<Integer>();

                for(int j = 0; j < dict.length; j++)
                    if((ii != j) && are_adjacent(dict[ii], dict[j]))
                        adjacent[ii].add(new Integer(j));
            }


//            for(int j = 0; j < numEntriesInDictionary; j++) 
//                for(int k = j+1; k < numEntriesInDictionary; k++) 
//                {
//                    Vector<Integer> v = solveWordLadder(dict, dict[j], dict[k]);
//
//                    if((v != null) && v.size() > 8) {
//                        System.out.print("Word ladder from " + dict[j] + " to " + dict[k] + ": " );
//                        System.out.print(dict[v.remove(0).intValue()]);
//                        for(Integer index : v)
//                            System.out.print(" --> " + dict[index.intValue()]);
//                        System.out.println();
//                    } 
//                    // if( (v == null) && !adjacent[j].isEmpty() && !adjacent[k].isEmpty()) 
//                        // System.out.println("No word ladder from " + dict[j] + " to " + dict[k] + " using the input dictionary.");
//                }
//
//            System.exit(1);
//
            int numPairs = sc.nextInt();
            for(int j = 0; j < numPairs; j++) 
            {
                String start = sc.next().toLowerCase();
                String end = sc.next().toLowerCase();

                Vector<Integer> v = solveWordLadder(dict, start, end);

                if(v != null) {
                    System.out.print("Word ladder from " + start + " to " + end + ": " );
                    System.out.print(dict[v.remove(0).intValue()]);
                    for(Integer index : v)
                        System.out.print(" --> " + dict[index.intValue()]);
                    System.out.println();
                } else {
                    System.out.println("No word ladder from " + start + " to " + end + " using the input dictionary.");
                }
            }
        }
    }
}
