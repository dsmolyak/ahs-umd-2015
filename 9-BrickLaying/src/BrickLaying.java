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
