//----------------------------------------------------------------------
// Best viewed with tabs every 4 columns
//----------------------------------------------------------------------

import java.io.FileInputStream;
import java.util.*;

/**
 * Brick Laying
 * This program determines whether it is possible to tile a rectangular
 * wall in the form of an integer grid with forbidden squares by a
 * collection of 2x1 bricks, which may be rotated by any multiple of 90
 * degrees.
 *
 * The program inputs a description of the wall and forbidden squares
 * and prints the solution with an indication of the number of uncovered 
 * squares.
 *
 */
public class BrickLaying {

	/* This function solves the brick-laying problem. It takes as input
	 * a 2-dimensional array of chars, representing the available ('O')
	 * and forbidden ('X') squares of the grid. The output is returned
	 * in the same array. Horizontal bricks are indicated by a pair
	 * ('<','>'), one to the left of the other, and vertical bricks by
	 * a pair ('^','v'), one above the other. For example:
	 *
	 * Input array:
	 *   O X O O
	 *   O O X O
	 *   X X O O
	 *   O O O X
	 *   O O O O
	 *
	 * (Possible) Output array (leaving one unfilled square):
	 *   ^ X < >
	 *   v O X ^
	 *   X X ^ v
	 *   < > v X
	 *   < > < >
	 *   
	 * We provide a function that prints this out in a prettier way.
	 * The above output would appear as follows:
	 *  +---+---+---+---+
	 *  |   | X |       |
	 *  +   +---+---+---+
	 *  |   | O | X |   |
	 *  +---+---+---+   +
	 *  | X | X |   |   |
	 *  +---+---+   +---+
	 *  |       |   | X |
	 *  +---+---+---+---+
	 *  |       |       |
	 *  +---+---+---+---+
	 */
	private static void layBricks(char[][] wall)
	{
		/* -------------------- INSERT CODE HERE ----------------------*/
		
		/* --------------------- HOW IT WORKS --------------------------
		 * The solution is based on a process called augmentation. (This
		 * method is used in a number of network optimization problems,
		 * notably network flow and maximum bipartite matching. This
		 * problem is actually a special case of bipartite matching.)
		 * Suppose that there are at least two available squares that
		 * are not covered by the current set of bricks. The program
		 * determines whether there is an alternating path of bricks
		 * that starts at one unfinished square and ends at the other.
		 * (Two adjacent uncovered squares is a special case involving
		 * an alternating path of a single brick.)
		 *
		 * The process of computing augmenting paths is done by a
		 * modification of the standard depth-first search (DFS), but in
		 * a graph whose vertices are the non-forbidden squares and
		 * whose edges join each vertex to its (up to four) neighboring
		 * squares. The DFS alternately goes to any unvisited neighbor,
		 * then from this neighbor to the neighbor covered by the same
		 * brick. It repeats this until arriving at an available square.
		 *
		 * If so, it alternately replaces bricks along the path (thus
		 * producing a solution with one more brick).
		 *
		 * Example:
		 *
		 *    Alternating path: (with 6 bricks)
		 *
		 *    O < >
		 *        ^         ^ < > O
		 *        V < > < > v
		 *
		 *    Augmentation: (with 7 bricks)
		 *
		 *    < > ^
		 *        v         < > < >
		 *        < > < > < >
		 *
		 * It repeats this either until all squares are covered or until
		 * no such path remains. It can be shown that if there are
		 * uncovered squares and no alternating path exists, then the
		 * existing solution covers as many squares as possible.
		 *
		 * We don't use any clever data structures to perform the DFS.
		 * We employ a recursive version of DFS, and we embed the DFS
		 * information within the array. In particular, the function
		 * DFSvisit(i,j) visits the neighbors of entry (i,j) in cyclic
		 * order and use the values ('N', 'E', 'S', 'W') to indicate the
		 * direction of the predecessor of this node in the search. The
		 * start node of the sequence is marked with 'Z'. When nodes are
		 * visited, we provide them with special values to indicate that
		 * they have been visited.
		 */

		int nRows = wall.length;			// number of rows
		int nCols = wall[0].length;			// number of columns
		boolean isDone = false;
		while (!isDone) {					// augment until no more
			boolean foundPath = false;
			for (int i = 0; i < nRows; i++) {
				for (int j = 0; j < nCols; j++) {
					if (wall[i][j] == 'O') { // available?
						if (findPathFrom(wall, i, j)) {
							foundPath = true; // found augmentation
						}
					}
				}
			}
			if (!foundPath) isDone = true;	// no augmentation possible
		}
	}


	/**
	 * Print wall for debugging.
	 */
	private static void basicPrintWall(char[][] wall, String message)
	{
		int nRows = wall.length;
		int nCols = wall[0].length;
		System.out.println(message + ":");
		for (int i = 0; i < nRows; i++) {
			System.out.print("  ");
			for (int j = 0; j < nCols; j++) {
				System.out.print(wall[i][j] + " ");
			}
			System.out.println();
		}
	}


	/**
	 * Attempts to perform a single augmentation starting from 
	 * wall[i][j] using DFS. Returns true if we were successful. As a
	 * side effect, the augmentation is performed.
	 */
	private static boolean findPathFrom(char[][] wall, int i, int j)
	{
		wall[i][j] = 'Z';					// mark starting square
		boolean success = DFSvisit(wall, i, j); // start a new DFS here
		DFScleanup(wall);					// clean up after DFS
		return success;						// return status
	}

	/**
	 * Returns true if wall[i][j] is a valid square of the grid and
	 * it contains a valid value for DFS to visit it (namely not forbidden).
	 */
	private static boolean isValidAndUnvisited(char[][] wall, int i, int j)
	{
		String valid = "O<>^v";			// valid neighbor values
											// check i and j in bounds
		if (i < 0 || i >= wall.length || j < 0 || j >= wall[0].length) {
			return false;
		}
											// forbidden or visited?
		else if (valid.indexOf(wall[i][j]) == -1) {
			return false;
		}
		return true;
	}

	/**
	 * Performs recursive DFS at wall[i][j]. Returns true if an
	 * augmenting path is found to an available square.
	 * 
	 * We generate all neighbors [ii][jj] of entry [i][j] that have
	 * not already been visited. Nodes are in one of three possible
	 * states. If unvisited, then the value is just its original value.
	 * If active (visited but not finished), we store a predecessor 
	 * direction (NESW) indicating the direction of the immediate 
	 * predecessor in the DFS (so we can trace back the augmenting 
	 * path). Finally, if finished (we have returned from an
	 * unsuccessful call), we store a variant of its original value.
	 * This is done to avoid revisiting this node in the future.
	 * 
	 * To implement the alternation, we perform two steps with each
	 * iteration of the DFS. First, we move from the current square to
	 * any valid neighbor. If that neighbor is available ('O') we
	 * return with success. If not, it must be a brick. We then move to
	 * the other square associated with this brick. For example, if we
	 * arrive at a square labeled '<', we know that it is the left of
	 * a left-right brick, and we move to the right by incrementing the
	 * j value and storing an indication that the predecessor is to
	 * to the west ('W').
	 */
	private static boolean DFSvisit(char[][] wall, int i, int j)
	{
		char from[] = {'S', 'W', 'N', 'E'};	// direction you came from
		int di[] = {-1,  0, +1,  0};		// index increment
		int dj[] = { 0, +1,  0, -1};
		
		for (int m = 0; m < 4; m++) {
			int ii = i + di[m];
			int jj = j + dj[m];
			if (isValidAndUnvisited(wall, ii, jj)) {
				char save = wall[ii][jj];	// save current entry
				wall[ii][jj] = from[m];		// direction you came from
				if (save == 'O') {			// found an available square?
					augmentPath(wall, ii, jj); // augment along this path
					return true;			// we're done!
				}
				else {						// otherwise we hit a brick
					switch(save) {			// go to other end of brick
						case '<': 			// ...and save back direction
							jj++; wall[ii][jj] = 'W'; break;
						case '>': 
							jj--; wall[ii][jj] = 'E'; break;
						case '^': 
							ii++; wall[ii][jj] = 'N'; break;
						case 'v': 
							ii--; wall[ii][jj] = 'S'; break;
					}
											// continue the search
					boolean success = DFSvisit(wall, ii, jj);
					if (success) {			// found a path?
						return true;		// ...terminate the search
					}
					else {					// dead end - must backtrack
						switch(save) {		// use modified values
							case '<': 		// ...to avoid future revisits
								wall[ii][jj] = ']'; jj--; wall[ii][jj] = '['; break;
							case '>': 
								wall[ii][jj] = '['; jj++; wall[ii][jj] = ']'; break;
							case '^': 
								wall[ii][jj] = '-'; ii--; wall[ii][jj] = '+'; break;
							case 'v': 
								wall[ii][jj] = '+'; ii++; wall[ii][jj] = '-'; break;
						}
					}
				}
			}
		}
		return false;						// failed
	}

	/**
	 * This performs the augmentation when an alternating path
	 * ending at wall[i][j] is found. We follow the predecessor
	 * directions back until reaching the source ('Z') and adjust
	 * the brick values accordingly.
	 */
	private static void augmentPath(char[][] wall, int i, int j)
	{
		int ii = i;
		int jj = j;
		char save = wall[ii][jj];
		while (save != 'Z') {		// repeat until source
			switch (wall[ii][jj]) {
				case 'W': 
					wall[ii][jj] = '>'; jj--; save = wall[ii][jj]; wall[ii][jj] = '<'; break;
				case 'E': 
					wall[ii][jj] = '<'; jj++; save = wall[ii][jj]; wall[ii][jj] = '>'; break;
				case 'N': 
					wall[ii][jj] = 'v'; ii--; save = wall[ii][jj]; wall[ii][jj] = '^'; break;
				case 'S': 
					wall[ii][jj] = '^'; ii++; save = wall[ii][jj]; wall[ii][jj] = 'v'; break;
			}
			switch (save) {
				case 'W': jj--; break; 
				case 'E': jj++; break;
				case 'N': ii--; break;
				case 'S': ii++; break;
			}
		}
	}


	/**
	 * Cleans up array after running an unsuccessful DFS by replacing
	 * variant values (which were used to indicate previously visited
	 * squares) with their original values.
	 */
	private static void DFScleanup(char[][] wall)
	{
		int nRows = wall.length;
		int nCols = wall[0].length;
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				switch(wall[i][j]) {
					case '[': wall[i][j] = '<'; break;
					case ']': wall[i][j] = '>'; break;
					case '+': wall[i][j] = '^'; break;
					case '-': wall[i][j] = 'v'; break;
					case 'Z': wall[i][j] = 'O'; break;
				}
			}
		}
	}

	/** 
	 *  Check solution for validity. This is just my own checker for
	 *  debugging.
	 */
	private static boolean checkBasicValidity(char[][] wall, String message)
	{
		basicPrintWall(wall, message);		// print array
		int nRows = wall.length;
		int nCols = wall[0].length;
											// bricks are legal?
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				switch (wall[i][j]) {
					case '<':
						if (j+1 >= nCols || wall[i][j+1] != '>') {
							System.out.println("'<' at wall[" + i + "][" + j + "] not matched with '>'");
							return false;
						}
						break;
					case '>':
						if (j-1 < 0 || wall[i][j-1] != '<') {
							System.out.println("'>' at wall[" + i + "][" + j + "] not matched with '<'");
							return false;
						}
						break;
					case '^':
						if (i+1 >= nRows || wall[i+1][j] != 'v') {
							System.out.println("'^' at wall[" + i + "][" + j + "] not matched with 'v'");
							return false;
						}
						break;
					case 'v':
						if (i-1 < 0 || wall[i-1][j] != '^') {
							System.out.println("'v' at wall[" + i + "][" + j + "] not matched with '^'");
							return false;
						}
						break;
					case 'X':
					case 'O':
						break;
					default:
						System.out.println("invalid character '" + wall[i][j] + "' at wall[" + i + "][" + j + "]");
						break;
						
				}
			}
		}
		return true;					// otherwise, everything is okay

		/* --------------------- END OF INSERTION ---------------------*/
	}

	/** 
	 *  Pretty print the wall
	 */
	private static void prettyPrintWall(char[][] wall)
	{
		int nRows = wall.length;
		int nCols = wall[0].length;

		for (int j = 0; j < nCols; j++) {	// print top boundary
			System.out.print("+---");
		}
		System.out.println("+");

		for (int i = 0; i < nRows; i++) {
											// print row contents
			for (int j = 0; j < nCols; j++) {
				switch (wall[i][j]) {
					case 'X':
						System.out.print("| X "); break;
					case 'O':
						System.out.print("| O "); break;
					case '<':
					case '^':
					case 'v':
						System.out.print("|   "); break;
					case '>':
						System.out.print("    "); break;
				}
			}
			System.out.println("|");
											// print row separator
			for (int j = 0; j < nCols; j++) {
				switch (wall[i][j]) {
					case 'X':
					case 'O':
					case '<':
					case '>':
					case 'v':
						System.out.print("+---"); break;
					case '^':
						System.out.print("+   "); break;
				}
			}
			System.out.println("+");
		}
	}

	/** 
	 *  Check solution for validity
	 */
	private static boolean checkValidity(char[][] wall, char[][] orig)
	{
		//basicPrintWall(wall, "In checkValidity");
											// check that sizes match
		int nRows = orig.length;
		int nCols = orig[0].length;

		if (wall.length != nRows) {
			System.out.println("Different number of rows");
			return false;
		}
		for (int i = 0; i < nRows; i++) {
			if (wall[i].length != nCols) {
				System.out.println("Different number of columns");
				return false;
			}
		}
											// forbiddens preserved?
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				if (orig[i][j] == 'X' && wall[i][j] != 'X') {
					System.out.println("Forbidden square at wall[" + i + "][" + j + "] removed");
					return false;
				}
				if (orig[i][j] != 'X' && wall[i][j] == 'X') {
					System.out.println("Forbidden square at wall[" + i + "][" + j + "] added");
					return false;
				}
			}
		}
											// bricks are legal?
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				switch (wall[i][j]) {
					case '<':
						if (j+1 >= nCols || wall[i][j+1] != '>') {
							System.out.println("'<' at wall[" + i + "][" + j + "] not matched with '>'");
							return false;
						}
						break;
					case '>':
						if (j-1 < 0 || wall[i][j-1] != '<') {
							System.out.println("'>' at wall[" + i + "][" + j + "] not matched with '<'");
							return false;
						}
						break;
					case '^':
						if (i+1 >= nRows || wall[i+1][j] != 'v') {
							System.out.println("'^' at wall[" + i + "][" + j + "] not matched with 'v'");
							return false;
						}
						break;
					case 'v':
						if (i-1 < 0 || wall[i-1][j] != '^') {
							System.out.println("'v' at wall[" + i + "][" + j + "] not matched with '^'");
							return false;
						}
						break;
					case 'X':
					case 'O':
						break;
					default:
						System.out.println("invalid character '" + wall[i][j] + "' at wall[" + i + "][" + j + "]");
						break;
						
				}
			}
		}
		return true;					// otherwise, everything is okay
	}

	/** 
	 *  Main Program
	 */
	public static void main(String[] args) throws Exception {
											// read input
		// System.setIn(new FileInputStream("01.in"));
		Scanner scanner = new Scanner(System.in);
		int numCases = scanner.nextInt();

		for(int t = 1; t <= numCases; t++) {
			int nRows = scanner.nextInt();	// number of rows in wall
			int nCols = scanner.nextInt();	// number of columns in wall
			scanner.nextLine();				// skip to eol
			char[][] orig = new char[nRows][]; // original wall
			char[][] wall = new char[nRows][]; // the wall
			for (int i = 0; i < nRows; i++) {
				orig[i] = new char[nCols];
				wall[i] = new char[nCols];
				String line = scanner.nextLine();
				for (int j = 0; j < nCols; j++) {
					wall[i][j] = orig[i][j] = line.charAt(j);
				}
			}

			System.out.println("Case: " + t);// echo input
			prettyPrintWall(orig);			// print the original wall

			layBricks(wall);				// lay the bricks
											// check for valid solution
			boolean valid = checkValidity(wall, orig);
			if (!valid) return;

			System.out.println("\nSolution:");// pretty print the solution
			prettyPrintWall(wall);

			int availCt = 0;				// number of available
			for (int i = 0; i < nRows; i++) {
				for (int j = 0; j < nCols; j++) {
					if (wall[i][j] == 'O') availCt++;
				}
			}
			System.out.println("\nUncovered squares remaining = " + availCt);
			System.out.println();
		}
		scanner.close();					// close input stream
	}
}
