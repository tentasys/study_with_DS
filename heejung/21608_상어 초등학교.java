import java.io.*;
import java.util.*;

public class Main {
	static final int dr[] = {-1, 1, 0, 0};
	static final int dc[] = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Student student[] = new Student[N*N+1];				//index번 학생이 어디 앉고 어느 학생을 좋아하는지 
		int map[][] = new int[N+1][N+1];					//교실 지도 
		
		for(int i=1; i<=N*N; i++) {
			String str[] = br.readLine().split(" ");		//input
			int n = Integer.parseInt(str[0]);				//학생 번호 
			int visit[][] = new int[N+1][N+1];		
			
			
			//1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다. 
			Queue<Student> q = new LinkedList<Student>();
			Queue<Student> temp_q;
			
			int like[] = new int[4];
			for(int j=0; j<4; j++)
			{
				like[j] = Integer.parseInt(str[j+1]);
				//좋아하는 학생의 인접자리를 큐에 넣기 
				if(student[like[j]] != null) {
					for(int k=0; k<4; k++) {
						int nr = student[like[j]].r + dr[k];
						int nc = student[like[j]].c + dc[k];
						
						if(nr < 1 || nc < 1 || nr > N || nc > N)
							continue;
						if(map[nr][nc] != 0)
							continue;
						visit[nr][nc]++;
						if(visit[nr][nc] > 1)
							continue;
						q.add(new Student(nr, nc));
					}
				}	
			}
			student[n] = new Student(like);
			
			//좋아하는 학생 근처의 인접 자리가 많으면 -> 좋아하는 학생이 많은 지점 고르기
			if(!q.isEmpty() && q.size() > 1) {
				temp_q = new LinkedList<Student>();
				int max = -1;
				
				while(!q.isEmpty()) {
					Student cur = q.poll();
					if(max < visit[cur.r][cur.c]) {
						max = visit[cur.r][cur.c];
						temp_q = new LinkedList<Student>();
						temp_q.add(cur);
					}
					else if(max == visit[cur.r][cur.c]) {
						temp_q.add(cur);
					}
				}
				q = temp_q;
			}
			//좋아하는 학생 근처의 자리가 없으면 -> 빈칸 다 큐에 넣기 
			else if(q.isEmpty()) {
				for(int j=1; j<=N; j++) {
					for(int k=1; k<=N; k++) {
						if(map[j][k] == 0)
							q.add(new Student(j, k));
					}
				}
			}
				
			//남은 인접 자리가 1이면 자리에 앉힌다
			if(q.size() == 1) {
				Student cur = q.poll();
				student[n].r = cur.r;
				student[n].c = cur.c;
				map[cur.r][cur.c] = n;
				continue;
			}
			
			//2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
			temp_q = new LinkedList<Student>();
			int temp_max = -1;
			while(!q.isEmpty()) {
				Student cur = q.poll();
				int cnt = 0;
				for(int j=0; j<4; j++) {
					int nr = cur.r + dr[j];
					int nc = cur.c + dc[j];
					
					if(nr < 1 || nc < 1 || nr > N || nc > N)
						continue;
					if(map[nr][nc] != 0)
						continue;
					cnt++;
				}
				
				if(temp_max < cnt) {
					temp_max = cnt;
					temp_q = new LinkedList<Student>();
					temp_q.add(cur);
				}
				else if(temp_max == cnt) {
					temp_q.add(cur);
				}
			}
			q = temp_q;
			
			//남은 인접 자리가 1이면 자리에 앉힌다
			if(q.size() == 1) {
				Student cur = q.poll();
				student[n].r = cur.r;
				student[n].c = cur.c;
				map[cur.r][cur.c] = n;
				continue;
			}
			
			//3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
			Student min = new Student(N, N);
			while(!q.isEmpty()) {
				Student cur = q.poll();
				if(min.r > cur.r)
					min = cur;
				else if(min.r == cur.r) {
					if(min.c > cur.c)
						min = cur;
				}
			}
			
			student[n].r = min.r;
			student[n].c = min.c;	
			map[min.r][min.c] = n;
		}
		br.close();
		
		//만족도 구하기 
		int result = 0;
		for(int i=1; i<=N*N; i++) {
			int sub_result = 0;
			for(int j=0; j<4; j++) {
				int nr = student[i].r + dr[j];
				int nc = student[i].c + dc[j];
				
				if(nr < 1 || nc < 1 || nr > N || nc > N)
					continue;
				
				boolean check = false;
				for(int k=0; k<4; k++)
					if(student[i].likes[k] == map[nr][nc])	check = true;
				if(check == true) {
					if(sub_result == 0)
						sub_result = 1;
					else
						sub_result *=10;
				}
			}
			result += sub_result;
		}
		
		System.out.println(result);
	}

}

class Student{
	int r;	int c;
	int likes[];
	Student(int r, int c){
		this.r = r;
		this.c = c;
		likes = null;
	}
	Student(int[] arr){
		likes = new int[4];
		for(int i=0; i<4; i++)
			likes[i] = arr[i];
	}
}