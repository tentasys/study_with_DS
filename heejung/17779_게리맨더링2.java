//2시간 38분 53초 

import java.io.*;
import java.util.*;

public class Main {

	static int map[][];
	static int MIN = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// ------ 초기화 끝 ------ 
		
		//find(3, 5, 2, 1, N);
		
		go(0, N, new int[4]);
		
		System.out.println(MIN);
	}
	
	//중복을 허용하는 순열 -> 넣을 때 조건에 맞지 않으면 넣지 않는다. 
	static public void go(int idx, int N, int arr[]) {
		if(idx == 4) {
			find(arr[0], arr[1], arr[2], arr[3], N);
			return;
		}
		
		for(int i=1; i<=N; i++) {
			//idx 0(x) : 1 <= x <= N-2
			if(idx == 0) {
				if(i > N-2) continue;
			}
			//idx 1(y) : 2 <= y <= N-1
			else if(idx == 1) {
				if(i < 2)	continue;
				if(i > N-1)	continue;
			}
			//idx 2(d1) : 1 <= d1 <= N-2
			//				   d1 <= y-1
			else if(idx == 2) {
				if(i > N-2)			continue;
				if(i > arr[1]-1)	continue;
			}
			//idx 3(d2) : x + d1 + d2 <= N
			//			  y + d2 <= N
			else{
				if(arr[0] + arr[2] + i > N)	continue;
				if(arr[1] + i > N)			continue;
			}
			arr[idx] = i;
			go(idx+1, N, arr);
		}
	}
	
	//x, y, d1, d2로 구역 나누기 
	static void find(int x, int y, int d1, int d2, int N) {
		try {
			int find_map[][] = new int[N+1][N+1];
			//경계선 구하기 
			for(int i=0; i<=d1; i++) {
				find_map[x+i][y-i] = 5;			//1번 조건 
				find_map[x+d2+i][y+d2-i] = 5;	//4번 조건 
			}

			for(int i=0; i<=d2; i++) {
				find_map[x+i][y+i] = 5;			//2번 조건 
				find_map[x+d1+i][y-d1+i] = 5;	//3번 조건 	
			}
			
			int districts[] = new int[6];
				
			//1번 구역 
			for(int i=1; i<x+d1; i++) {
				for(int j=1; j<=y; j++) {
					if(find_map[i][j] == 5)	break;
					find_map[i][j] = 1;
					districts[1] += map[i][j];
				}
			}
			
			//2번 구역 
			for(int j=N; j>y; j--)  {
				for(int i=1; i<=x+d2; i++){
					if(find_map[i][j] == 5)	break;
					find_map[i][j] = 2;
					//districts[2]++;
					districts[2] += map[i][j];
				}
			}
			
			//3번 구역 
			for(int i=x+d1; i<=N; i++) {
				for(int j=1; j<y-d1+d2; j++) {
					if(find_map[i][j] == 5)	break;
					find_map[i][j] = 3;
					//districts[3]++;
					districts[3] += map[i][j];
				}
			}
			
			//4번 구역 
			for(int j=y-d1+d2; j<=N; j++){
				for(int i=N; i>x+d2; i--) {
					if(find_map[i][j] == 5)	break;
					find_map[i][j] = 4;
					//districts[4]++;
					districts[4] += map[i][j];
				}
			}
			
			//5번 구역 
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(find_map[i][j] == 5 || find_map[i][j] == 0) {
						districts[5] += map[i][j];
					}
				}
			}
			
			//최대, 최소 
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for(int i=1; i<=5; i++) {
				min = Math.min(min, districts[i]);
				max = Math.max(max, districts[i]);
			}
			
			MIN = Math.min((max-min), MIN);
		}catch (Exception e){
			//케이스 잘못 잡아서 예외 발생할 수 있음 -> 어차피 계산 안 해도 됨 
		}
	}
}
