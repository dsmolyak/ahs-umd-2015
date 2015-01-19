import java.util.*;
import java.io.*;
import java.math.*;

public class CrossingRiver{
	private static long l,c,d[];
	private static int n;

	private static boolean can(long steps,long dis){
		d[n+1] = l;
		int polonger = 0;
		for (int i=1;i<=steps;i++){
			int nw = polonger;
			while (nw <= n && d[nw+1] - d[polonger] <= dis)
				nw++;
			polonger = nw;
		}
		return(polonger == n+1);
	}

	public static void main(String args[]){

		Scanner cin = new Scanner(System.in);
		long jumpRange = 0;
		long numJumps = 0;

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
			Arrays.sort(d);
			long ans = 1l<<60;
			for (int i=1;i<=n+1;i++){
				long a = 1, b = l;
				while (a < b){
					long mid = (a+b)/2;
					if (can(i,mid))
						b = mid;
					else
						a = mid+1;
				}
				// ans = Math.min(ans,i*c+a*a);
				long newCost = i*c+a*a;
				if (newCost < ans) {
					jumpRange = a;
					numJumps = i;
					ans = newCost;
				}
			}
			System.out.format("Minimum cost %d achieved with %d jumps of range %d %n",ans,numJumps,jumpRange);
		}
	}

}
