import java.io.*;
import java.util.*;



public class B{
	static class Pair<X, Y>{
		public final X n;
		public final Y d;
		public Pair(X n, Y d) { 
		    this.n = n; 
		    this.d = d; 
		  } 
	}

	public static  void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        int f = sc.nextInt();
        int s = sc.nextInt();
        int t = sc.nextInt();
        
        ArrayList<Pair<Integer, Integer>>[] adj;
        adj = new ArrayList[n];
        //initialize adjacency lists for all the vertices
        for (int i = 0; i < n ; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            adj[a].add(new Pair(b, c));
            adj[b].add(new Pair(a,c));
        }
        
        int[][] fss = new int[f][2];
        for(int i = 0; i < f; i++) {
            fss[i][0] = sc.nextInt();
            fss[i][1] = sc.nextInt();
        }
        
        
        
        int[] first = djik(s, adj, n);
        int[] second = djik(t, adj, n);

        int min = first[t];
        //System.out.println(min);
        for(int i = 0; i < f; i++) {
            int d = first[fss[i][0]];
            int e = second[fss[i][1]];
            if( (d != Integer.MAX_VALUE && e != Integer.MAX_VALUE ) && (d + e < min)) {
                min = d+e;
            }
        }
        
        System.out.println(min);
        sc.close();
    
    }


	public static int[] djik(int s, ArrayList<Pair<Integer, Integer>>[] adj, int n) {
        
		// visited and distance sets
        int[] visited = new int[n]; 
        int[] dist = new int[n];
        for(int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;        
        int ind = s;      
        
        for(Pair<Integer, Integer> q : adj[s]){
        	dist[q.n] = q.d;
        }
        
        // while not all visited
        int sum = 0;
        while(sum !=n) {
            int min = Integer.MAX_VALUE;
            
            // choosing next vertex to visit
            for(int i = 0; i < n; i++) {
                if(visited[i] == 0 && dist[i] < min) {
                    ind = i;
                    min = dist[i];
                }
            }
            
            
            if(min == Integer.MAX_VALUE) {
                return dist;
            }
            
            for(Pair<Integer, Integer> q : adj[ind]){
            	if(dist[q.n] > dist[ind] + q.d){
            		dist[q.n] = dist[ind] + q.d;
            	}
            }
            visited[ind] = 1;
            sum++;
        }
        
        
        
        
        return dist;
    }
    
}

