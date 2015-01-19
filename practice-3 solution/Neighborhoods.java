import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;

class Edge {
    String node1;
    String node2;
    Edge(String n1, String n2) {
        node1 = n1;
        node2 = n2;
    }
}

public class Neighborhoods {

	private static int solveNeighborhoods(int numNodes, ArrayList<Edge> edges, String n)
    {
		/* ------------------- INSERT CODE HERE ---------------------*/

        HashSet<String> neighbors = new HashSet<String>();

        for(Edge e : edges) {
            if(e.node1.equals(n)) 
                neighbors.add(e.node2);
            if(e.node2.equals(n)) 
                neighbors.add(e.node1);
        }

        HashSet<String> neighborsOfNeighbors = new HashSet<String>();

        for(Edge e : edges) {
            if(neighbors.contains(e.node1)) 
                neighborsOfNeighbors.add(e.node2);
            if(neighbors.contains(e.node2)) 
                neighborsOfNeighbors.add(e.node1);
        }

        neighborsOfNeighbors.remove(n);

        for(String s : neighbors) 
            neighborsOfNeighbors.add(s);

        return neighborsOfNeighbors.size();
        
		/* -------------------- END OF INSERTION --------------------*/
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numTests = sc.nextInt();		// number of test cases

		for (int t = 0; t < numTests; t++) {
            int numNodes = sc.nextInt();
            int numEdges = sc.nextInt();
            ArrayList<Edge> edges = new ArrayList<Edge>();

            for(int i = 0; i < numEdges; i++) 
                edges.add(new Edge(sc.next(), sc.next()));

            String n = sc.next();
            int res = solveNeighborhoods(numNodes, edges, n);

            System.out.println("The number of supervillains in 2-hop neighborhood of " + n + " is " + res);
		}
	}
}
