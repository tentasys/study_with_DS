class Solution {
    public String solution(String[] table, String[] languages, int[] preference) {
        String map[][] = new String[5][6];

        for(int i=0; i<5; i++)
        {
            String temp[] = table[i].split(" ");
            map[i][0] = temp[0];
            for(int j=0; j<5; j++)
                map[i][5-j] = temp[1+j];
        }
        /////////계산 시작////////

        int MAX = Integer.MIN_VALUE;
        String answer = "";
        //각 직군별로 돌면서 찾기
        for(int i=0; i<5; i++)
        {
            int temp_score = 0;
            //각 언어별로 돌면서 찾기
            for(int j=0; j<languages.length; j++)
            {
                //해당 언어가 직군별 선호언어 리스트에 있는지 찾기
                for(int k=1; k<=5; k++)
                {
                    if(languages[j].equals(map[i][k]) == true)
                    {
                        temp_score += k*preference[j];
                        break;
                    }
                }
            }

            //////결과 입력//////
            if(temp_score > MAX)
            {
                MAX = temp_score;
                answer = map[i][0];
            }
            else if(temp_score == MAX)
            {
                if(answer.compareTo(map[i][0]) > 0)
                    answer = map[i][0];
            }
        }

        return answer;
    }
}