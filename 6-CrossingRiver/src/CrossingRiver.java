import java.util.*;
import java.io.*;
import java.math.*;

public class CrossingRiver{
	private static long l,c,d[];
	private static int n;

	public static void main(String args[]){

		Scanner cin = new Scanner(System.in);
		long jumpRange = 0;
		long numJumps = 0;
		long minCost = 0;

		while (cin.hasNextInt()){
			l = cin.nextInt();			// width of river
			if (l == 0)
				break;
			c = cin.nextInt();			// cost of each jump
			n = cin.nextInt();			// number of rocks
			
			d = new long[n+2];
			d[n+1] = l;
			for (int i=1;i<=n;i++)
				d[i] = cin.nextInt();	// distance of rock

	        /* ------------------- INSERT CODE HERE ---------------------*/


			
			
			
			
			
			
	        /* -------------------- END OF INSERTION --------------------*/

			System.out.format("Minimum cost %d achieved with %d jumps of range %d %n",minCost,numJumps,jumpRange);
		}
	}

}
