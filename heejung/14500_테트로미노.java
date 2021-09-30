import java.io.*;
public class Main {
	//2시간 34분 
	static int N, M;
	static final int dr[] = {-1, 1, 0, 0};
	static final int dc[] = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		int arr[][] = new int[N][M];
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<M; j++)
				arr[i][j] = Integer.parseInt(str[j]);
		}
		
		int max = Integer.MIN_VALUE;
		//각 칸별로 돌면서 모든 경우의 수 생각하기 
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				Block rotate[] = null;
				
				for(int k=0; k<8; k++) {	
					//ㄴ모양 블록 회전 
					rotate = null;
					rotate = func4(i, j, k);
					if(rotate != null)
						max = Math.max(max, cal(arr, rotate));
					
					//번개모양 블록 회전 
					rotate = null;
					rotate = func5(i, j, k);
					if(rotate != null)
						max = Math.max(max, cal(arr, rotate));
					
					if(k >= 4)	continue;
					
					//일자모양 블록 회전 
					rotate = null;
					rotate = func1(i, j, k);
					if(rotate != null)
						max = Math.max(max, cal(arr, rotate));
					
					//ㅏ모양 블록 회전 
					rotate = null;
					rotate = func2(i, j, k);
					if(rotate != null)
						max = Math.max(max, cal(arr, rotate));
					
					//ㅁ모양 블록 회전
					rotate = null;
					rotate = func3(i, j, k);
					if(rotate != null)
						max = Math.max(max, cal(arr, rotate));
				}
			}
		}
		
		System.out.println(max);
	}
	
	//일자모양 블록. 방향은 총 4개 
	static Block[] func1(int r, int c, int dir) {
		Block result[] = new Block[4];
		
		for(int i=0; i<4; i++) {
			int nr = r+(i*dr[dir]);
			int nc = c+(i*dc[dir]);
			
			if(nr < 0 || nc < 0 || nr >= N || nc >= M)	return null;
			
			result[i] = new Block(nr, nc);
		}
		return result;
	}
	
	//ㅏ 모양 블록, 방향은 총 4개 
	static Block[] func2(int r, int c, int dir){
		Block result[] = new Block[4];
			
		//상, 하
		if(dir == 0 || dir == 1) {
			int left = c-1;
			int right = c+1;
			int updown = r+dr[dir];
			
			if(left < 0 || right >= M || updown < 0 || updown >= N) {
				return null;
			}
			
			result[0] = new Block(r, c);
			result[1] = new Block(r, left);
			result[2] = new Block(r, right);
			result[3] = new Block(updown, c);
		}
		else {
			int up = r-1;
			int down = r+1;
			int leftright = c+dc[dir];
			
			if(up < 0 || down >= N || leftright < 0 || leftright >= M) {
				return null;
			}
			
			result[0] = new Block(r, c);
			result[1] = new Block(up, c);
			result[2] = new Block(down, c);
			result[3] = new Block(r, leftright);
		}
		
		return result;
	}
	
	//ㅁ자모양 블록, 방향은 총 4개 
	static Block[] func3(int r, int c, int dir) {
		Block result[] = new Block[4];
		
		//블록기준 위 
		if(dir == 0 || dir == 1) {
			int up = r-1;
			int minus = dir == 0 ? -1 : 1;
			int side = c+minus;
			
			if(up < 0 || side < 0 || side >= M)	 return null;
			
			result[0] = new Block(r, c);
			result[1] = new Block(up, c);
			result[2] = new Block(r, side);
			result[3] = new Block(up, side);
		}
		//블록기준 아래 
		else {
			int down = r+1;
			int minus = dir == 2 ? -1 : 1;
			int side = c+minus;
			
			if(down >= N || side < 0 || side >= M)	 return null;
			
			result[0] = new Block(r, c);
			result[1] = new Block(down, c);
			result[2] = new Block(r, side);
			result[3] = new Block(down, side);
		}
		
		return result;
	}
	
	//ㄴ자모양 블록, 방향은 총 8개 
	static Block[] func4(int r, int c, int dir) {
		Block result[] = new Block[4];
		int nr = r;
		int nc = c;
		
		//상하좌우 4개로 나누기 
		if(dir < 4) {
			//기본블록 기준 좌측으로 세팅 
			if(dir < 2)	nc = c-1;
			//기본블록 기준 위로 세팅 
			else		nr = r-1;		
		}else {
			dir %= 4;
			
			//기본블록 기준 우측으로 세팅 
			if(dir < 2)	nc = c+1;
			//기본블록 기준 아래로 세팅 
			else		nr = r+1;
		}

		result[0] = new Block(r, c);
		for(int i=1; i<4; i++) {
			if(i != 1) {
				if(dir%4 < 2)
					nr += dr[dir];
				else
					nc += dc[dir];
			}	
			
			if(nr < 0 || nc < 0 || nr >= N || nc >= M)	return null;
			
			result[i] = new Block(nr, nc);
		}	
			
		return result;
	}
	
	//번개모양 블록, 방향은 총 8개 
	static Block[] func5(int r, int c, int dir) {
		Block result[] = new Block[4];
		result[0] = new Block(r, c);
		
		//0, 1, 4, 5 
		if(dir%4 == 0 || dir%4 == 1) {
			int nc = dir >= 4 ? c-1 : c+1;
			if(nc < 0 || nc >= M)	return null;
			
			int nr = r;
			for(int i=1; i<4; i++) {
				if(i != 2)	nr += dr[dir%4];
				
				if(nr < 0 || nr >=  N)		return null;
				if(i == 1) 		result[i] = new Block(nr, c);
				else		result[i] = new Block(nr, nc);
			}
		}
		else {
			int nr = dir >= 4 ? r-1 : r+1;
			if(nr < 0 || nr >= N)	return null;
			
			int nc = c;
			for(int i=1; i<4; i++) {
				if(i != 2)	nc += dc[dir%4];
				
				if(nc < 0 || nc >= M)		return null;
				if(i == 1) result[i] = new Block(r, nc);
				else		result[i] = new Block(nr, nc);
			}
		}
		
		return result;
	}
	
	static int cal(int[][] arr, Block[] blocks) {
		int sum = 0;
		
		for(int i=0; i<4; i++)
			sum += arr[blocks[i].r][blocks[i].c];
		
		return sum;
	}
}

class Block{
	int r; int c;
	Block(int r, int c){
		this.r = r;
		this.c = c;
	}
}
