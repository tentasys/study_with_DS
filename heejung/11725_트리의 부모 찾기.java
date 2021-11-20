//약 30분.. MLE TLE 잡느라 시간 씀.. 
//BFS, HashMap 

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		HashMap<Integer, HashMap<Integer, Boolean>> map = new HashMap<Integer, HashMap<Integer, Boolean>>();
		
		for(int i=1; i<N; i++) {
			String str[] = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			
			if(map.get(a) == null) {
				map.put(a, new HashMap<Integer, Boolean>());
			}
			map.get(a).put(b, true);
			
			if(map.get(b) == null) {
				map.put(b, new HashMap<Integer, Boolean>());
			}
			map.get(b).put(a, true);
		}
		
		br.close();
	
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		
		int parents[] = new int[N+1];
		boolean visit[] = new boolean[N+1];
		visit[1] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			HashMap<Integer, Boolean> inner_map = map.get(cur);
			
			for(int key : inner_map.keySet()) {
				if(visit[key] == true)	continue;
				visit[key] = true;
				parents[key] = cur;
				q.add(key);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=2; i<=N; i++) {
			sb.append(parents[i]+"\n");
		}
		System.out.println(sb.toString());
	}

}