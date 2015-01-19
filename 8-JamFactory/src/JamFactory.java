import java.util.*;
import java.io.*;
import java.math.*;


public class JamFactory{
	public static void main(String args[]){
		Scanner cin = new Scanner(System.in);
		while (cin.hasNextInt()){
			int n = cin.nextInt();	// number of vats
			if (n == 0)
				break;
			int m = cin.nextInt();	// number of pipes
			int a = cin.nextInt();	// source vat 1
			int b = cin.nextInt();	// source vat 2
			int c = cin.nextInt();	// bottling machine

			/* ------------------- INSERT CODE HERE ---------------------*/

			int minCost = 0;
			for (int i=1;i<=m;i++){
				int x = cin.nextInt();	// vat x
				int y = cin.nextInt();	// vat y
				int z = cin.nextInt();	// cost of pipe between x to y
			}








			// System.out.format("Cannot connect %d and %d to %d %n",a,b,c);
			System.out.format("Cost of connecting %d and %d to %d is %d %n",a,b,c,minCost);

			/* -------------------- END OF INSERTION --------------------*/
		}
		cin.close();
	}
}
