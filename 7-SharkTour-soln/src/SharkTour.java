//----------------------------------------------------------------------
// Best viewed with tabs every 4 columns
//----------------------------------------------------------------------

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
		// int numStalagmites = stalagmites.length;
		// int numSightings = sightings.length;

		/* -------------------- INSERT CODE HERE ----------------------*/

		int tunnel[][] = buildTunnel(tunnelDepth, tunnelLength, stalagmites, sightings);

		//  optimal path (for seeing sharks) from this point
		String soln[][] = new String[tunnelDepth][tunnelLength];

		// optimal number of sharks seen from this point
		int sharks[][] = new int[tunnelDepth][tunnelLength];

		// initialize

		for (int i=0; i< tunnelDepth; i++)
			for (int j=0; j<tunnelLength; j++) {
				soln[i][j] = null;
				sharks[i][j] = 0;
			}

		search(tunnel,soln,sharks,0,0);
		System.out.format("Saw %d sharks for sequence %s %n", sharks[0][0], soln[0][0]);

		boolean debug = false;
		if (debug)
			tunnelTrip(tunnelDepth, tunnelLength,stalagmites,sightings,soln[0][0]);

	}

	private static void search(
			int tunnel[][], 
			String soln[][],
			int sharks[][],
			int depth,
			int distance)
	{
		int tunnelHeight = tunnel.length;
		int tunnelLength = tunnel[0].length;

		if (soln[depth][distance] != null) {	// already solved
			return;
		} 
		else if (tunnel[depth][distance] == Integer.MAX_VALUE) {
			soln[depth][distance] = "X";
			sharks[depth][distance] = Integer.MIN_VALUE;
			return;
		} 
		else if (distance == tunnelLength-1) {
			soln[depth][distance] = "";
			sharks[depth][distance] = tunnel[depth][distance];
			return;
		} 
		else {
			int upSharks = -1;
			String upSoln = "";

			int downSharks = -1;
			String downSoln = "";

			int forwardSharks = -1;
			String forwardSoln = "";

			if (depth > 0) {
				search(tunnel,soln,sharks,depth-1,distance+1);
				upSoln = "^"+soln[depth-1][distance+1];;
				upSharks = sharks[depth-1][distance+1] + tunnel[depth][distance] ;
			}  

			if (depth < tunnelHeight-1) {
				search(tunnel,soln,sharks,depth+1,distance+1);
				downSoln = "v"+soln[depth+1][distance+1];
				downSharks = sharks[depth+1][distance+1] + tunnel[depth][distance] ;
			}

			search(tunnel,soln,sharks,depth,distance+1);
			forwardSoln = ">"+soln[depth][distance+1];
			forwardSharks = sharks[depth][distance+1] + tunnel[depth][distance] ;

			if ((downSharks > upSharks) && (downSharks > forwardSharks)) {
				soln[depth][distance] = downSoln;
				sharks[depth][distance] = downSharks;
			} else if ((upSharks > downSharks) && (upSharks > forwardSharks)) {
				soln[depth][distance] = upSoln;
				sharks[depth][distance] = upSharks;
			} else {
				soln[depth][distance] = forwardSoln;
				sharks[depth][distance] = forwardSharks;
			}
		}
	}

	private static int[][] buildTunnel(
			int tunnelHeight, 
			int tunnelLength, 
			int stalagmites[][],
			int sightings[][])
	{
		int numStalagmites = stalagmites.length;
		int numSightings = sightings.length;

		// information on stalagmites & shark sightings in tunnel
		int tunnel[][] = new int[tunnelHeight][tunnelLength]; 

		// initialize

		for (int i=0; i< tunnelHeight; i++)
			for (int j=0; j<tunnelLength; j++) {
				tunnel[i][j] = 0; 		// no sharks or stalagmites (yet)

			}

		for(int s = 0; s < numStalagmites; s++) {
			int stalagmiteSize = stalagmites[s][0];
			int stalagmiteDistance = stalagmites[s][1];

			for (int i=0; i<stalagmiteSize; i++)
				tunnel[tunnelHeight-1-i][stalagmiteDistance] = Integer.MAX_VALUE;
		}

		for(int s = 0; s < numSightings; s++) {
			int numSharks = sightings[s][0];
			int sharkDistance = sightings[s][1];
			int sharkDepth = sightings[s][2];

			// System.out.format("shark distance %d shark depth %d %n",sharkDistance, sharkDepth);

			tunnel[sharkDepth][sharkDistance] = numSharks;
		}
		return tunnel;
	}


	private static void tunnelTrip(
			int tunnelHeight, 
			int tunnelLength, 
			int stalagmites[][],
			int sightings[][],
			String action)
	{
		int tunnel[][] = buildTunnel(tunnelHeight, tunnelLength, stalagmites, sightings);

		char actions[] = new char[tunnelLength-1];	// get actions
		for (int j = 0; j < tunnelLength-1; j++) 
			actions[j] = action.charAt(j);

		int depth = 0;		// starting depth
		int sharksSeen = tunnel[0][0];

		if (tunnel[0][0] == 0)
			tunnel[0][0] = Integer.MIN_VALUE;
		else if (tunnel[0][0] > 0)
			tunnel[0][0] = -tunnel[0][0];

		int i = 0;
		for (i = 0; i < tunnelLength-1; i++) {

			switch (actions[i]) {
			case 'v':
				depth++; break;
			case '^': 
				depth--; break;
			case '>': 
				break;
			default:
				System.out.format("Illegal action %c %n", actions[i]);
				break;
			}

			if (depth < 0) {
				System.out.println("Crashed into tunnel ceiling");
				break;
			}
			if (depth >= tunnelHeight) {
				System.out.println("Crashed into tunnel floor");
				break;
			}
			if (tunnel[depth][i+1] == Integer.MAX_VALUE) {
				System.out.println("Crashed into stalagmite");
				break;
			}

			if (tunnel[depth][i+1] == 0)
				tunnel[depth][i+1] = Integer.MIN_VALUE;
			else if (tunnel[depth][i+1] > 0) {
				sharksSeen += tunnel[depth][i+1];
				tunnel[depth][i+1] = -tunnel[depth][i+1];
			}
		}

		// if (i == tunnelLength-1)
		//	System.out.println("Reached end of tunnel");

		System.out.format("Saw %d sharks on path %s %n", sharksSeen, action);
		prettyPrintTunnel(tunnel);			// print the path through the tunnel
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
				case Integer.MAX_VALUE:
					System.out.format("    X", tunnel[i][j]); break;
				case Integer.MIN_VALUE:
					System.out.format("    -", tunnel[i][j]); break;
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

