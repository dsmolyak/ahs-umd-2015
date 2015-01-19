//----------------------------------------------------------------------
// Best viewed with tabs every 4 columns
//----------------------------------------------------------------------

import java.io.FileInputStream;
import java.util.*;

/**
 * SharkTour
 * This program determines the optimal path for Lucy's car to 
 * travel through an underwater tunnel using a series of propeller 
 * bursts to boost the car past a number of stalagmites while seeing
 * the most number of sharks possible.
 *
 * The program inputs a description of the tunnel, location & size
 * of stalagmites in the tunnel, and the location & number of sharks 
 * that have been sighted.
 *
 */
public class SharkTour {

	private static void findPath(
			int tunnelDepth, 
			int tunnelLength, 
			int stalagmites[][],
			int sightings[][])
	{
		int numStalagmites = stalagmites.length;
		int numSightings = sightings.length;
		int optimalSharks = 0;
		String optimalPath = "";

		/* -------------------- INSERT CODE HERE ----------------------*/

		// examine each stalagmite
		for(int s = 0; s < numStalagmites; s++) {
			int stalagmiteSize = stalagmites[s][0];
			int stalagmiteDistance = stalagmites[s][1];

		}

		for(int s = 0; s < numSightings; s++) {
			int numSharks = sightings[s][0];
			int sharkDistance = sightings[s][1];
			int sharkDepth = sightings[s][2];

		}
		
		System.out.format("Saw %d sharks for sequence %s %n", optimalSharks, optimalPath);

		/* --------------------- END OF INSERTION ---------------------*/

	}

	/** 
	 *  Main Program
	 */
	public static void main(String[] args) throws Exception {

											// read input
		Scanner scanner = new Scanner(System.in);
		int numCases = scanner.nextInt();

		for(int t = 1; t <= numCases; t++) {
			scanner.next();											// Tunnel
			scanner.next();											// depth
			int tunnelDepth = scanner.nextInt();					// depth of tunnel
			scanner.next();											// length
			int tunnelLength = scanner.nextInt();					// length of tunnel	


			int nStalag = scanner.nextInt();						// n
			scanner.next();											// stalagmites
			int stalagmites[][] = new int[nStalag][2];

			for(int s = 0; s < nStalag; s++) {
				// line of form "n meter stalagmite d meters distant"
				stalagmites[s][0] = scanner.nextInt();				// n
				scanner.next();										// meter
				scanner.next();										// stalagmite
				stalagmites[s][1] = scanner.nextInt();				// d
				scanner.next();										// meters
				scanner.next();										// distant
			}

			int nSightings = scanner.nextInt();						// n
			scanner.next();											// sightings

			int sightings[][] = new int[nSightings][3];

			for(int s = 0; s < nSightings; s++) {
				// line of form "x sharks y meters distant z meters down"
				sightings[s][0] = scanner.nextInt();		// x
				scanner.next();								// sharks
				sightings[s][1] = scanner.nextInt();		// y
				scanner.next();								// meters
				scanner.next();								// distant
				sightings[s][2] = scanner.nextInt();		// z
				scanner.next();								// meters
				scanner.next();								// down
			}
			System.out.println("Case: " + t);	// echo input

			// find optimal path for shark tour
			findPath(tunnelDepth, tunnelLength, stalagmites, sightings);	
		}
		scanner.close();
	}
}

