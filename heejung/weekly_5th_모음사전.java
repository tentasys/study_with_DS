import java.util.*;

class Solution {
    
    static public ArrayList<String> list;
    
    public int solution(String word) {
        
        list = new ArrayList<String>();
        
        list.add("A");
        setDictionary(1, new StringBuilder("A"));
        list.add("E");
        setDictionary(1, new StringBuilder("E"));
        list.add("I");
        setDictionary(1, new StringBuilder("I"));
        list.add("O");
        setDictionary(1, new StringBuilder("O"));
        list.add("U");
        setDictionary(1, new StringBuilder("U"));
        
        Collections.sort(list);
        
        int answer = 0;
        for(String s : list)
        {
            answer++;
            if(s.compareTo(word) == 0)
                break;
        }
            
        return answer;
    }
    
    static public void setDictionary(int len, StringBuilder cur)
    {
        //System.out.println(cur);
        if(len == 5)
            return;
        
        list.add(cur.append("A").toString());
        setDictionary(len+1, cur);
        cur.deleteCharAt(len);
        list.add(cur.append("E").toString());
        setDictionary(len+1, cur);
        cur.deleteCharAt(len);
        list.add(cur.append("I").toString());
        setDictionary(len+1, cur);
        cur.deleteCharAt(len);
        list.add(cur.append("O").toString());
        setDictionary(len+1, cur);
        cur.deleteCharAt(len);
        list.add(cur.append("U").toString());
        setDictionary(len+1, cur);
        cur.deleteCharAt(len);
    }
}