import java.io.*;
import java.util.*;

public class Main {
	//상하좌우 
	static final int dr[] = {1, -1, 0, 0};
	static final int dc[] = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");

		int M = Integer.parseInt(str[0]);
		int N = Integer.parseInt(str[1]);
		
		int map[][] = new int[N][M];
		int zero_cnt = 0;		//0의 갯수 
		Queue<Node> q = new LinkedList<Node>();	//1을 queue로 관리
		boolean visit[][] = new boolean[N][M];	//방문 여부
		int answer = 0;			//반복 횟수 
		
		for(int i=0; i<N; i++)
		{
			str = br.readLine().split(" ");
			for(int j=0; j<M; j++)
			{
				map[i][j] = Integer.parseInt(str[j]);
				if(map[i][j] == 0)	zero_cnt++;
				else if(map[i][j] == 1)
				{
					q.add(new Node(i, j));
					visit[i][j] = true;
				}
			}
		}
		
		while(zero_cnt != 0)
		{
			Queue<Node> next_q = new LinkedList<Node>();	//임시 큐 
			
			while(!q.isEmpty())
			{
				Node cur = q.poll();
				
				for(int i=0; i<4; i++)
				{
					int nr = cur.r + dr[i];
					int nc = cur.c + dc[i];
					
					if(nr >= N || nc >= M || nr < 0 || nc < 0)
						continue;
					if(map[nr][nc] != 0)
						continue;
					if(visit[nr][nc] == true)
						continue;
					
					visit[nr][nc] = true;
					next_q.add(new Node(nr, nc));
					map[nr][nc] = 1;
					zero_cnt--;
				}
			}
			
			if(zero_cnt != 0 && next_q.isEmpty())
				break;
			
			q = next_q;
			answer++;
		}
		
		if(zero_cnt != 0)	System.out.println(-1);
		else				System.out.println(answer);
	}

}

class Node{
	int r;	int c;
	Node(int r, int c){
		this.r = r;	this.c = c;
	}
}