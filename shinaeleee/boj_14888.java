import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList <Integer> num;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		num = new ArrayList<Integer>();
		ArrayList <Character> ep = new ArrayList<Character>();
		ArrayList <Character> picked = new ArrayList<Character>();
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n; i++){
			num.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine(), " ");
		
		int p = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int mp = Integer.parseInt(st.nextToken());
		int dv = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < p; i++){
			ep.add('+');
		}
		for(int i = 0; i < m; i++){
			ep.add('-');
		}
		for(int i = 0; i < mp; i++){
			ep.add('*');
		}
		for(int i = 0; i < dv; i++){
			ep.add('/');
		}
		boolean[] visited = new boolean[n-1];
		pick(n-1, picked, visited, ep);
		System.out.println(max);
		System.out.println(min);
	}
	public static void pick(int n, ArrayList<Character>picked, boolean[] visited, ArrayList<Character>ep){
		if(picked.size() == n){
			int answer = calc(picked);
			min = Math.min(min, answer);
			max = Math.max(max, answer);
			return;
		}
		for(int i = 0; i < ep.size(); i++){
			if(visited[i]) continue;
			visited[i] = true;
			picked.add(ep.get(i));
			pick(n, picked, visited, ep);
			picked.remove(picked.size()-1);
			visited[i] = false;
		}
	}
	public static int calc(ArrayList<Character> picked){	
		int val = num.get(0);
		for(int i = 0; i < picked.size(); i++){
			switch(picked.get(i)){
			case '+':
				val += num.get(i+1);
				break;
 			case '-':
				val -= num.get(i+1);
				break;
			case '*':
				val *= num.get(i+1);
				break;
			case '/':
				val /= num.get(i+1);
				break;
			}
		}
		return val;
	}
}

