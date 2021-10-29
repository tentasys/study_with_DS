import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int K = Integer.parseInt(str[2]);
		int X = Integer.parseInt(str[3]);
		
		HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<Integer, ArrayList<Integer>>();
		
		for(int i=0; i<M; i++) {
			str = br.readLine().split(" ");
			int src = Integer.parseInt(str[0]);
			int dest = Integer.parseInt(str[1]);
			
			if(adjList.get(src) == null) {
				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(dest);
				adjList.put(src, list);
			}
			else {
				ArrayList<Integer> list = adjList.get(src);
				list.add(dest);
			}
		}
		
		br.close();
		
		boolean visit[] = new boolean[N+1];
		int depth[] = new int[N+1];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(X);
		visit[X] = true;
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			ArrayList<Integer> inner_list = adjList.get(cur);
			if(inner_list == null)		continue;
			
			for(int a : inner_list) {
				if(visit[a] == true)	continue;
				
				depth[a] = depth[cur] + 1;
				visit[a] = true;
				q.add(a);
				if(depth[a] == K)		list.add(a);
			}	
		}
		
		Collections.sort(list);
		
		if(list.size() == 0)
			System.out.println(-1);
		else
			for(int a : list)
				System.out.println(a);
	}

}
