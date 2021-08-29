import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_13458 {
	static int n, b, c;
	static int a[];
	static int ob;
	public static void main(String[] args) throws NumberFormatException, IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		n = Integer.parseInt(br.readLine());
		a = new int[n];
		
		String[] str = br.readLine().split(" ");
		
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(str[i]);
		}
		
		str = br.readLine().split(" ");
		b = Integer.parseInt(str[0]);
		c = Integer.parseInt(str[1]);
		
		
		for(int i = 0; i < n; i++) {
			//총감독관은 한명 
			ob++;
			//총감독관 케어 인원 
			a[i]-=b;
			
			//남은 인원 부감독관 
			if(a[i] > 0) {
				if(a[i]%c == 0) {
					ob += a[i]/c;
				}else {
					ob += a[i]/c+1;					
				}
			}
		}
		
		System.out.println(ob);
		
	}

}
