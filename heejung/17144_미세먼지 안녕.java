import java.io.*;
import java.util.*;

//3시간 12분 53초 ㅜ
public class Main {
	
	//상(0), 하(1), 좌(2), 우(3) 
	static final int dr[] = {-1, 1, 0, 0};
	static final int dc[] = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken()); //행 
		int C = Integer.parseInt(st.nextToken()); //열 
		int T = Integer.parseInt(st.nextToken()); //소요시간 
		
		int map[][] = new int[R][C];			//지도(먼지 현황, 공청기 위치) 
		Queue<Node> q = new LinkedList<Node>();	//미세먼지를 관리할 큐 
		Node machine[] = new Node[2];			//공청기 위치. 0:상부, 1:하부 
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == -1) {
					if(machine[0] == null)	machine[0] = new Node(i, j);
					else					machine[1] = new Node(i, j);
				}
				else if(map[i][j] != 0) {
					q.add(new Node(i, j));
				}
			}
		}
		
		//========입력 끝 ========
		
		//======== 시작 ========
		while(T > 0) {
			//-------- 1. 미세먼지 확산 --------
			int temp_map[][] = new int[R][C];		//변동된 미세먼지의 수 
			
			while(!q.isEmpty()) {
				Node cur = q.poll();
				int share = map[cur.r][cur.c] / 5;	//나눠줄 미세먼지 양 
				 
				//나눠줄 미세먼지의 양이 0이면 의미 없음
				if(share == 0)
					continue;
				
				for(int i=0; i<4; i++) {			//네방향 돌면서 뿌림
					int nr = cur.r + dr[i];
					int nc = cur.c + dc[i];
					
					if(nr < 0 || nc < 0 || nr >= R || nc >= C)		continue;	//인덱스 범위 확인 
					if(map[nr][nc] == -1)		continue;		//공기청정기 확인 
										
					temp_map[nr][nc] += share;			//확산되는 위치에 먼지 뿌림 
					temp_map[cur.r][cur.c] -= share;		//원래 위치 자리에서 확산된만큼 감소 
				}
			}
			
			//변동치만큼 미세먼지 뿌리기 
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					map[i][j] += temp_map[i][j];
				}
			}
			
			//-------- 2. 공기청정기 작동 --------
			//공기청정기에서 나오는 바람은 깨끗하니까 일단 0로 세팅 
			map[machine[0].r][machine[0].c] = 0;
			map[machine[1].r][machine[1].c] = 0;
			
			int dir_upper[] = {0, 3, 1, 2};	//공기청정기 상부 : 하-좌-상-우 방향으로 회전 
			int r = machine[0].r;
			int c = machine[0].c;
			
			
			for(int i=0; i<4; i++) {
				while(true) {
					int nr = r + dr[dir_upper[i]];
					int nc = c + dc[dir_upper[i]];
					
					if(nr < 0 || nr > machine[0].r || nc < 0 || nc >= C)	break;				
					map[r][c] = map[nr][nc];
					
					//이부분이 없어서 틀렸음 
					if(r == machine[0].r && c == machine[0].c)
						map[r][c] = 0;
					r = nr;
					c = nc;
				}
				
			}
			
			int dir_below[] = {1, 3, 0, 2};	//공기청정기 하부 : 상-좌-하-우 방향으로 회전 
			r = machine[1].r;
			c = machine[1].c;
			
			for(int i=0; i<4; i++) {
				while(true) {
					int nr = r + dr[dir_below[i]];
					int nc = c + dc[dir_below[i]];
					
					if(nr < machine[1].r || nr >= R || nc < 0 || nc >= C)	break;
					
					map[r][c] = map[nr][nc];
					
					//이부분이 없어서 틀렸음 
					if(r == machine[1].r && c == machine[1].c)
						map[r][c] = 0;
					r = nr;
					c = nc;
				}
			}
			
			//공기청정기값 다시 세팅 
			map[machine[0].r][machine[0].c] = -1;
			map[machine[1].r][machine[1].c] = -1;
			
			//-------- 3. 정리 --------		
			//다음 큐 대상 선정 
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++){
					if(map[i][j] > 0)	q.add(new Node(i, j));
				}
			}
			T--;			//시간 흐름 
		}
		
		//큐에 남아있는 미세먼지의 양을 센다 
		int result = 0;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			result += map[cur.r][cur.c];
		}
		
		System.out.println(result);
	}

}

class Node{
	int r;	int c;
	Node(int r, int c){
		this.r = r;
		this.c = c;
	}
}