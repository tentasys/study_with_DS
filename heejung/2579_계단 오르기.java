import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		//-------입력 끝 
		
		//사용 알고리즘 : 다이나믹 프로그래밍 
		int max[] = new int[N];
		
		max[0] = arr[0];		//처음 계단만 있을 때의 최대값 
		if(N >= 2)				//두번째 계단까지 있을 때의 최대값 
			max[1] = arr[0] + arr[1];
		if(N >= 3)				//세번째 계단까지 있을 때의 최대값 
			max[2] = Math.max(arr[0]+arr[2], arr[1]+arr[2]);
		//-> 이 부분에서 틀렸음!! 
		
		//두 케이스 비교
		//1. i-1 계단값 + i-3 최대값 
		//2. i-2 최대값 
		//둘 중 더 큰 값을 택한다. 
		for(int i=3; i<N; i++)
		{
			int pprev = arr[i-1] + max[i-3];
			int prev = max[i-2];
			
			if(pprev > prev)
				max[i] = pprev + arr[i];
			else
				max[i] = prev + arr[i];
		}
		
		System.out.println(max[N-1]);
	}

}
