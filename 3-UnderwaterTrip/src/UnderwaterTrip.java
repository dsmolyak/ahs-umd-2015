//----------------------------------------------------------------------
// Best viewed with tabs every 4 columns
//----------------------------------------------------------------------

import java.util.*;

/**
 * UnderwaterTrip
 * This program determines whether it is possible to for Lucy's car to 
 * travel through an underwater tunnel using a series of propeller bursts
 * to boost the car past a number of stalagmites.
 *
 * The program inputs a description of the tunnel, proposed propeller 
 * actions, and stalagmites in the tunnel, then prints the outcome of 
 * the trip. 
 *
 */
public class UnderwaterTrip {
	private static void tunnelTrip(
			int tunnelDepth, 
			int tunnelLength, 
			int stalagmites[][],
			char[][] actions)
	{
		int numStalagmites = stalagmites.length;
		int numActions = actions.length;

		/* -------------------- INSERT CODE HERE ----------------------*/

		// examine each stalagmite
		for(int s = 0; s < numStalagmites; s++) {
			int stalagmiteSize = stalagmites[s][0];
			int stalagmiteDistance = stalagmites[s][1];

		}

		// examine each path
		for (int a = 0; a < numActions; a++) {

			// examine actions in path
			for (int i = 0; i < tunnelLength-1; i++) {
				switch (actions[a][i]) {
				case '>':
				case '^': 
				case 'v':
					break;
				}
			}

			// decide on outcome following path
			
			String actionLine = new String(actions[a]);
			System.out.format("Sequence %s ", actionLine);

			// System.out.println("Crashed into tunnel ceiling");
			// System.out.println("Crashed into tunnel floor");
			// System.out.println("Crashed into stalagmite");
			System.out.println("Reached end of tunnel");
		}

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

			int nActions = scanner.nextInt();						// n
			scanner.next();											// actions

			char actions[][] = new char[nActions][tunnelLength-1];	// get actions
			for (int a = 0; a < nActions; a++) {
				String actionLine = scanner.next();
				for (int j = 0; j < tunnelLength-1; j++) 
					actions[a][j] = actionLine.charAt(j);
			}

			System.out.println("Case: " + t);	// echo input

			tunnelTrip(tunnelDepth, tunnelLength, stalagmites, actions);	// check trip
		}
		scanner.close();
	}
}

