import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) { 
        int time = 0;
        int idx = 0;
        ArrayList<Integer> res = new ArrayList<Integer>();
        while(time < 100){
            if(idx >= progresses.length)    break;
            
            for(int i=idx; i<progresses.length; i++){
                progresses[i] += speeds[i];
            }
            
            int cnt = 0;
            while(progresses[idx] >= 100){
                cnt++;
                idx++;
                if(idx >= progresses.length)    break;
            }
            
            if(cnt != 0)    res.add(cnt);
            time++;
        }
        
        int[] answer = new int[res.size()];
        for(int i=0; i<res.size(); i++)
            answer[i] = res.get(i);
        return answer;
    }
}