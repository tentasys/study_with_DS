import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int A[] = new int[N];
		String str[] = br.readLine().split(" ");
		
		
		for(int i=0; i<N; i++)
			A[i] = Integer.parseInt(str[i]);
		
		str = br.readLine().split(" ");
		int B = Integer.parseInt(str[0]); //총감독 감시 가능 인원 
		int C = Integer.parseInt(str[1]); //부감독 감시 가능 인원 
		
		long sum = 0;
		
		for(int i=0; i<N; i++)
		{
			//감시 가능 인원은 총 인원에서 총감독 감시 인원 제외 A[i]-B 
			//필요 부감독 수 : 남은 감시인원 / 감시가능인원 +1
			int watch = A[i]-B;
			
			//감독관의 능력은 출중한데 학생이 적을때는 뺀 값이 음수가 나올 수 있기 때문 
			if(watch < 0)	watch = 0;
			
			if(watch != 0)
			{
				int sub_watch = watch/C;
				int sub_watch_div = watch%C;
				
				if(sub_watch_div == 0)
					sum += sub_watch + 1;
				else
					sum += sub_watch + 2;
			}
			else
				sum += 1;
		}
		
		System.out.println(sum);
	}

}
