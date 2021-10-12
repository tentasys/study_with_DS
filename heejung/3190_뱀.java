//54분 37초 

import java.io.*;
import java.util.*;

public class Main {

	//상(0) 하(1) 좌(2) 우(3) 
	static final int dr[] = {-1, 1, 0, 0};
	static final int dc[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		int map[][] = new int[N+1][N+1];	//0행0열이 아닌 1행1열부터 시작하므로(문제 조건) 
		
		//사과 세팅 : 사과는 2로 표현
		for(int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			map[r][c] = 2;
		}
		
		//X초가 끝난 뒤의 행동 
		char timeline[] = new char[100001];
		int L = Integer.parseInt(br.readLine());
		for(int i=0; i<L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			timeline[idx] = st.nextToken().charAt(0);
		}
		
		// ------- 입력 끝 -------
		
		// ------- 뱀 초기화 ------- 
		Deque<Node> snake = new LinkedList<Node>();		//덱의 처음은 머리, 끝은 꼬리 
		snake.add(new Node(1, 1));
		int time = 1;		//시간 정보 
		map[1][1] = 1;		//뱀이 있는 곳은 1로 표현 
		int dir = 3;		//방향 정보 - 초기 방향 : 오른쪽 
		
		while(time <= 10000) {
			
			Node cur = snake.peekFirst(); 	//머리 정보

			//다음 좌표 정보 
			int nr = cur.r + dr[dir];
			int nc = cur.c + dc[dir];
			
			//다음 좌표가 인덱스를 벗어남 
			if(nr < 1 || nc < 1 || nr > N || nc > N)
				break;
			
			//다음 좌표에 몸이 있음 
			if(map[nr][nc] == 1)
				break;
			
			//이동한 좌표에 사과가 없으면 꼬리 삭제
			if(map[nr][nc] != 2) {
				Node last = snake.pollLast();
				map[last.r][last.c] = 0;
			}
			
			//머리 이동 
			map[nr][nc] = 1;
			snake.addFirst(new Node(nr, nc));
			
			//다음 방향 정보가 있으면 세팅 
			if(timeline[time] != 0) {
				dir = rotate(dir, timeline[time]);
			}
			
			//시간 흐름 
			time++;
		}
		
		System.out.println(time);
	}
	
	//방향 전환 함수 	
	static int rotate(int cur, char dir) {
		int result = cur;
		
		//현재 : 상 
		if(cur == 0) {
			//왼쪽 회전 : 좌 (2)
			if(dir == 'L')		result = 2;
			//오른쪽 회전 : 우 (3)
			else if(dir == 'D')	result = 3;
		}
		//현재 : 하  
		else if(cur == 1) {
			//왼쪽 회전 : 우(3) 
			if(dir == 'L')		result = 3;
			//오른쪽 회전 : 좌(2)
			else if(dir == 'D')	result = 2;
		}
		//현재 : 좌 
		else if(cur == 2) {
			//왼쪽 회전 : 하(1)
			if(dir == 'L')		result = 1;
			//오른쪽 회전 : 상(0)
			else if(dir == 'D')	result = 0;
		}
		//현재 : 우 
		else if(cur == 3) {
			//왼쪽 회전 : 상(0)
			if(dir == 'L')		result = 0;
			//오른쪽 회전 : 하(1)
			else if(dir == 'D')	result = 1;
		}
		
		return result;
	}
}

class Node{
	int r;	int c;
	
	Node(int r, int c){
		this.r = r;
		this.c = c;
	}
}
