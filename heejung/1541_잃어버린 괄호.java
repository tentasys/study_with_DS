import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char arr[] = br.readLine().toCharArray();
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<arr.length; i++) {
			char ch = arr[i];
			
			if(ch == '+' || ch == '-') {
				list.add(Integer.parseInt(sb.toString()));
				sb = new StringBuilder();
				sb.append(ch);
			}
			else
				sb.append(ch);
		}
		list.add(Integer.parseInt(sb.toString()));
		
		br.close();
		
		int result = 0;
		int sum = 0;
		

		for(int a : list) {
			if(a >= 0) {
				if(sum < 0) {
					sum -= a;
				}
				else
					result += a;
			}
			else {
				if(sum < 0) {
					result += sum;
					sum = a;
				}
				else {
					sum += a;
				}
			}
		}
		
		result += sum;
		
		System.out.println(result);
	}

}
