import java.util.*;
import java.io.*;

//44�� 54��
public class Main {

	static int op[];
	static int arr[];
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//N �Է�
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		//���� �Է�
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		//������ �Է�
		op = new int[4];	//0: ����, 1: ����, 2: ����, 3: ������
		st= new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++)
			op[i] = Integer.parseInt(st.nextToken());
		
		br.close();
	
		f(arr[0], 1);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	static void f(int n, int count) {
		if(op[0] > 0)
		{
			op[0]--;
			f(n+arr[count], count+1);
			op[0]++;
		}
		if(op[1] > 0)
		{
			op[1]--;
			f(n-arr[count], count+1);
			op[1]++;			
		}
		if(op[2] > 0)
		{
			op[2]--;
			f(n*arr[count], count+1);
			op[2]++;			
		}
		if(op[3] > 0)
		{
			op[3]--;
			f(n/arr[count], count+1);
			op[3]++;			
		}
		
		if(count == N)
		{
			max = Math.max(max, n);
			min = Math.min(min, n);
		}
	}

}
