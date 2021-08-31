import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int belt[] = new int[2*N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<2*N; i++)
		{
			belt[i] = Integer.parseInt(st.nextToken());
		}
				 
		int count = 0;		//count가 k이상이면 종료
		int step = 1;		//몇 번째 단계인지
		ArrayList<Integer> list = new ArrayList<Integer>(); 	//로봇 인덱스 리스트
		
		while(true)
		{
			//list 연산 시 한 list에서 연산하면 꼬일 것 같다 
			ArrayList<Integer> new_list = new ArrayList<Integer>();
			
			/////////////////////1번 동작 
			//벨트 회전 
			int temp = belt[2*N-1];
			for(int i=2*N-1; i>0; i--)
				belt[i] = belt[i-1];
			belt[0] = temp;
			
			//로봇 이동 
			for(int a : list)
			{
				temp = (a+1)%(2*N);
				if(temp != N-1)			//내리는 위치에 도달하면 내리기 
					new_list.add(temp);
			}
			list = new_list;	
			
			////////////////////2번 동작 : 로봇 이동하기 
			temp = 0;
			new_list = new ArrayList<Integer>();
			boolean vacant[] = new boolean[2*N];
			for(int a : list)
			{
				temp = a;
				int next_idx = (a+1)%(2*N);
				//이동하려는 칸에 로봇이 없는지 
				if(list.contains(next_idx) == false || vacant[next_idx] == true)
				{
					//내구도가 1 이상인지 
					if(belt[next_idx] >= 1)
					{
						temp = next_idx;	//로봇 이동
						belt[next_idx]--;	//내구도 감소 
						if(belt[next_idx] == 0)		count++;
						vacant[a] = true;
					}
				}
				
				if(temp != N-1)
					new_list.add(temp); 
			}
			list = new_list;
			
			
			////////////////////3번 동작 : 로봇 올리기 
			if(belt[0] != 0 && !list.contains(0))
			{
				list.add(0);
				belt[0]--;
				if(belt[0] == 0)	count++;
			}
			
			////////////////////4번 동작 
			if(count >= K)
				break;
			
			step++;	
		}
		
		System.out.println(step); 
	}

}
