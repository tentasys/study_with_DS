//21분 15초 

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char str[] = br.readLine().toCharArray();

		Stack<Character> st = new Stack<Character>();
		boolean beam = true;
		int result = 0;
		
		for(int i=0; i<str.length; i++) {
			char cur = str[i];
			
			if(cur == '(') {
				st.push(cur);
				beam = true;
			}
			else {
				st.pop();
				if(beam == true) {		//레이저를 만나면 
					result += st.size(); //현재 쌓여있는 막대 수 만큼의 조각이 생김 
					beam = false;
				}else {					//레이저가 아닌 막대의 끝이면 
					result++;			//이 막대의 끄트머리 조각만 생김 
				}
			}
		}
		
		System.out.println(result);
	}

}
