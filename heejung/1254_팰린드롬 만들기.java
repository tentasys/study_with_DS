import java.io.*;
 
public class Main {
 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringBuilder sb = new StringBuilder();
        StringBuilder result = new StringBuilder();	
		for(int i=0; i<str.length(); i++)
		{
            result = new StringBuilder(str);
            result.append(sb);
            if(checkPalindrome(result))
                break;
            sb.insert(0, str.charAt(i));
		}
 
        System.out.println(result.length());
 
	}
 
 
	static boolean checkPalindrome(StringBuilder sb) {
		int mid = sb.length()/2;
		StringBuilder begin = new StringBuilder(sb.substring(0, mid));
		begin.reverse();
		StringBuilder end = new StringBuilder(sb.substring(mid+sb.length()%2));
 
		if(begin.toString().equals(end.toString()))
			return true;
		else
			return false;
	}
}