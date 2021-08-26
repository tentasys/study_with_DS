import sys
sys.stdin = open('gamdok.txt','r')

N = int(input())
candi = list(map(int, input().split()))
chong, bu = map(int, input().split())

cnt = 0

# chong count
for i in range(N):
    candi[i] -= chong
    cnt+=1

# bu count
for i in range(N):
    left_candi = candi[i]
    if left_candi >0:
        calc = left_candi % bu
        how_many = left_candi // bu
        if calc == 0:
            cnt += how_many
        else:
            cnt += how_many +1

print(cnt)
