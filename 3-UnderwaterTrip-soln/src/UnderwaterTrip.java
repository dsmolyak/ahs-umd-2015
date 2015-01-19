//----------------------------------------------------------------------
// Best viewed with tabs every 4 columns
//----------------------------------------------------------------------

import java.util.Scanner;

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

		int tunnel[][] = new int[tunnelDepth][tunnelLength]; // initialize
		for (int i=0; i< tunnelDepth; i++)
			for (int j=0; j<tunnelLength; j++)
				tunnel[i][j] = 0;

		for(int s = 0; s < numStalagmites; s++) {
			int stalagmiteSize = stalagmites[s][0];
			int stalagmiteDistance = stalagmites[s][1];

			for (int i=0; i<stalagmiteSize; i++)
				tunnel[tunnelDepth-1-i][stalagmiteDistance] = -1;
		}


		for (int a = 0; a < numActions; a++) {

			int newTunnel[][] = new int[tunnelDepth][tunnelLength]; // initialize

			for (int i=0; i< tunnelDepth; i++)
				for (int j=0; j<tunnelLength; j++)
					newTunnel[i][j] = tunnel[i][j];


			int depth = 0;		// starting depth

			tunnel[0][0] = -2;

			String actionLine = new String(actions[a]);
			System.out.format("Sequence %s ", actionLine);

			int i = 0;
			for (i = 0; i < tunnelLength-1; i++) {
				switch (actions[a][i]) {
				case '>':
					break;
				case '^': 
					depth--; break;
				case 'v':
					depth++; break;
				default:
					System.out.format("Illegal action %c %n", actions[i]);
					break;
				}

				if (depth < 0) {
					System.out.println("Crashed into tunnel ceiling");
					break;
				}
				if (depth >= tunnelDepth) {
					System.out.println("Crashed into tunnel floor");
					break;
				}
				if (tunnel[depth][i+1] == -1) {
					System.out.println("Crashed into stalagmite");
					break;
				}
				newTunnel[depth][i+1] = -2;
			}

			if (i == tunnelLength-1) 
				System.out.println("Reached end of tunnel");

			boolean debug = false;
			if (debug)
				prettyPrintTunnel(newTunnel);		// print the path through the tunnel
		}
	}


	/** 
	 *  Pretty print the tunnel
	 */
	private static void prettyPrintTunnel(int[][] tunnel)
	{
		int nRows = tunnel.length;
		int nCols = tunnel[0].length;

		System.out.print("      ");
		for (int j = 0; j < nCols; j++) {	// print top boundary
			System.out.format("%5d",j);
		}
		System.out.println("");

		System.out.print("      ");
		for (int j = 0; j < nCols; j++) {	// print top boundary
			System.out.print("----+");
		}
		System.out.println("");


		for (int i = 0; i < nRows; i++) {

			System.out.format("%4d: ",i);

			// print row contents
			for (int j = 0; j < nCols; j++) {
				switch (tunnel[i][j]) {
				case 0:
					System.out.format("     ", tunnel[i][j]); break;
				case -1:
					System.out.format("    S", tunnel[i][j]); break;
				case -2:
					System.out.format("    >", tunnel[i][j]); break;
				default:
					System.out.format("%5d", tunnel[i][j]); break;
				}
			}
			System.out.println("");

		}

		System.out.print("      ");
		for (int j = 0; j < nCols; j++) {	// print bottom boundary
			System.out.print("----+");
		}
		System.out.println("");

	}

	/* --------------------- END OF INSERTION ---------------------*/

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

