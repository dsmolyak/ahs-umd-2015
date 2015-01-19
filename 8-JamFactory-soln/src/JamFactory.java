import java.util.*;
import java.io.*;
import java.math.*;


public class JamFactory{
	public static void main(String args[]){
		Scanner cin = new Scanner(System.in);
		while (cin.hasNextInt()){
			int n = cin.nextInt();
			if (n == 0)
				break;
			int m = cin.nextInt();
			int a = cin.nextInt();
			int b = cin.nextInt();
			int c = cin.nextInt();
			int distance[][] = new int[n+1][n+1];
			for (int i=1;i<=n;i++)
				for (int j=1;j<=n;j++)
					if (i == j)
						distance[i][j] = 0;
					else
						distance[i][j] = 1<<25;
			for (int i=1;i<=m;i++){
				int x = cin.nextInt();
				int y = cin.nextInt();
				int z = cin.nextInt();
				distance[x][y] = distance[y][x] = Math.min(distance[x][y],z);
			}
			for (int i=1;i<=n;i++)
				for (int j=1;j<=n;j++)
					for (int k=1;k<=n;k++)
						distance[j][k] = Math.min(distance[j][k],distance[j][i] + distance[i][k]);
			int ans = 1<<25;
			for (int i=1;i<=n;i++)
				ans = Math.min(ans,distance[a][i]+distance[b][i]+distance[i][c]);
			if (ans == 1<<25)
				System.out.format("Cannot connect %d and %d to %d %n",a,b,c);
			else
				System.out.format("Cost of connecting %d and %d to %d is %d %n",a,b,c,ans);
		}

	}
}
