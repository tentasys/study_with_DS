import sys
sys.stdin = open('7576.txt','r')

from collections import deque

def isSafe(y,x):
    if y>=0 and y<N and x>=0 and x<M:
        return True

def go(Q):
    global grid

    max_time = 0

    dy = [-1,0,1,0]
    dx = [0,1,0,-1]

    while Q:
        info = Q.popleft() #1칸의 정보
        y = info[0]
        x = info[1]
        time = info[2]

        for dir in range(4):
            n_y = y + dy[dir]
            n_x = x+ dx[dir]
            n_time =time+1

            if isSafe(n_y,n_x) and grid[n_y][n_x] == 0:
                n_info = (n_y,n_x,n_time)
                grid[n_y][n_x] = 1
                max_time = n_time
                Q.append(n_info)

    return max_time

M,N = map(int, input().split())

grid = []
for rows in range(N):
    row = list(map(int, input().split()))
    grid.append(row)
# print(grid)

#이미 다 익었는지?
ripe = True
for y in range(N):
    for x in range(M):
        if grid[y][x] == 0:
            ripe = False

if ripe:
    #다 익었으면 0
    res = 0
else:
    #안익은 토마토가 있다면 BFS
    Q = deque() #deque => 성능차이 매우 극명

    for y in range(N):
        for x in range(M):
            if grid[y][x] == 1:
                Q.append((y,x,0)) #y좌표,x좌표,시간0초 세팅
    #BFS 입구
    res = go(Q)

    #마지막에도 안익은게 있다?
    for y in range(N):
        for x in range(M):
            if grid[y][x] == 0:
                # -1 출력
                res = -1

print(res)