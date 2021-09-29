import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		String str[] = br.readLine().split(" ");
		
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(0, str.length-1, 1));
		StringBuilder sb = new StringBuilder();
		int prev_level = 1;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int mid = (cur.left + cur.right)/2;
			
			int cur_level = cur.level;
			if(cur_level != prev_level) {
				sb.append("\n");
				prev_level = cur_level;
			}	
			
			sb.append(str[mid]+" ");
			
			if(cur.left <= mid-1)
				q.add(new Node(cur.left, mid-1, cur_level+1));
			if(mid+1 <= cur.right)
				q.add(new Node(mid+1, cur.right, cur_level+1));
			
		}
		
		System.out.println(sb.toString());
	}

}

class Node{
	int left;
	int right;
	int level;
	Node(int left,int right, int level){
		this.left = left;
		this.right = right;
		this.level = level;
	}
}