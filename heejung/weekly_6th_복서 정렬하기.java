import java.util.*;

class Solution {
    public int[] solution(int[] weights, String[] head2head) {
        
        ArrayList<Player> list = new ArrayList<Player>(); //복서 리스트
        for(int i=0; i<weights.length; i++)               //몸무게 세팅
            list.add(new Player(i, weights[i]));
        
        //승패 정보 세팅
        for(int i=0; i<head2head.length; i++){
            char arr[] = head2head[i].toCharArray();  //i번째 복서의 승패 정보
            int games = 0;          //게임 수
            int wins = 0;           //이긴 게임 수
            int over_wins = 0;      //몸무게 많은 사람한테 이긴 수 
            for(int j=0; j<arr.length; j++){
                if(arr[j] == 'N')
                    continue;
                games++;
                if(arr[j] == 'L')
                    continue;
                wins++;
                if(list.get(i).weight < list.get(j).weight)
                    over_wins++;
            }
            
            Player cur = list.get(i);
            cur.games = games;
            cur.wins = wins;
            cur.over_wins = over_wins;
        }
        
        //정렬 : Comparable order에 따라 정렬 
        Collections.sort(list);

        //결과 리턴
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i).no+1;
        }
        
        return answer;
    }
}

//Comparable 사용
class Player implements Comparable<Player>{
    int weight;     //몸무게
    int no;         //번호
    int games;      //경기 수
    int wins;       //이긴 수
    int over_wins;  //몸무게 많이 나가는 사람 이긴 수
    
    Player(int no, int weight){
        this.no = no;
        this.weight = weight;
    }
    
    @Override
    public int compareTo(Player player){
        //1. 전체 승률이 높으면
        double cur_ratio = 0;
        double comp_ratio = 0;
        if(this.games != 0)
            cur_ratio = (double)this.wins / (double)this.games;
        
        if(player.games != 0)
            comp_ratio = (double)player.wins / (double)player.games;
        
        if(cur_ratio < comp_ratio)
            return 1;
        else if(cur_ratio > comp_ratio)
            return -1;
        
        //2. 무거운 복서 이긴 횟수 많으면
        if(this.over_wins < player.over_wins)
            return 1;
        else if(this.over_wins > player.over_wins)
            return -1;
        
        //3. 몸무게가 많으면
        System.out.println("조건 3");
        if(this.weight < player.weight)
            return 1;
        else if(this.weight > player.weight)
            return -1;
        
        //4. 번호가 작으면
        if(this.no > player.no)
            return 1;    

        //번호가 같은 경우는 없기에 위 경우 해당 안하면 무조건 우선순위에서 밀린다.
        return -1;
    }
}