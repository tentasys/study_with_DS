import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		boolean map[][] = new boolean[N+1][N+1];
		
		for(int i=0; i<K; i++) {
			String[] str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			
			map[a][b] = true;
			map[b][a] = true;
		}
		
		br.close();

		boolean visit[] = new boolean[N+1];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		visit[1] = true;
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i=2; i<=N; i++) {
				if(visit[i] == true)	continue;
				if(map[cur][i] == false)	continue;
				
				q.add(i);
				visit[i] = true;
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
