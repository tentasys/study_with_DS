# dequeue - rotate 함수를 모르고 짜다가 깊은 빡침을 느낌
# 언젠가는 로직수정할게여
# 아래는 두번째 예제 테스트중

# 접속하기 귀찮으니 문제 적어놓기
# step 1 : 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다
# step 2 : 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
#              1. 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
# step 3 : 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
# step 4 : 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
# 2 ≤ N ≤ 100

def move(belt, robot, idx):
    last = belt.pop()
    belt.insert(0,last)
    last = robot.pop()
    robot.insert(0,last)

    next = idx+1
    if(next >= len(belt)):
        return 0
    else:
        return next

def move_ind(now, next, belt, robot):
    if(robot[next] == 0 and belt[next] > 0):
        robot[next] = robot[now]
        belt[next] -= 1
        robot[now] = 0

def finished(belt, K):
    return belt.count(0) >= K 

def get_next_idx(ary, start, end):
    next_idx = -1
    for i in range(start, end-1, -1):
        if(ary[i] == 1):
            next_idx = i
            break
    return next_idx

# init 2번째 문제 예시(하드코딩)
K=5
N=4
belt = [ 10, 1, 10, 6, 3, 4, 8, 2 ]
robot = [ 0, 0, 0, 0, 0, 0, 0, 0 ]
print("0 belt:", belt)
print("0 robot:", robot)
stage = 0

# step 1
last = belt.pop()
belt.insert(0,last)
# step 2 생략
# step 3
head_idx = 0
robot[head_idx] = 1
belt[head_idx] -= 1
# step 4 생략
stage = 1

# loop start
while not finished(belt, K):
    stage += 1
    print('---------------------------')
    print("시작전 belt:", belt)
    print("시작전 robot:", robot, "head_idx", head_idx)

    # step 1(전체이동)
    head_idx = move(belt, robot, head_idx)
    print("전체이동 belt:", belt)
    print("전체이동 robot:", robot, "head_idx", head_idx)
    # 가장 선두가 도착했나 확인
    if(head_idx == N-1):
        robot[head_idx] = 0
        head_idx = get_next_idx(robot, N-2, 0)   
        if(head_idx < 0):
            head_idx = get_next_idx(robot, 2*N-1, N)  
    
    print("전체이동-내리기 belt:", belt)
    print("전체이동-내리기 robot:", robot, "head_idx", head_idx)

    # step 2(개별이동)
    next = head_idx+1
    if(next == 2*N):
        next = 0
    move_ind(head_idx, next, belt, robot)         
    head_idx = next

    print("개별이동H belt:", belt)
    print("개별이동H robot:", robot, "head_idx", head_idx)

    # 가장 선두가 도착했나 확인
    if(head_idx == N-1):
        robot[head_idx] = 0
        head_idx = get_next_idx(robot, N-1, 0)   
        if(head_idx < 0):
            head_idx = get_next_idx(robot, 2*N-1, N+1)

    print("개별이동H-내리기 belt:", belt)
    print("개별이동H-내리기 robot:", robot, "head_idx", head_idx)

    for now in range(head_idx-1, -1, -1):
        if(robot[now]) > 0:
            if(next == 2*N):
                next = 0
            move_ind(now, next, belt, robot)

    for i in range(2*N-1, head_idx, -1):
        if(robot[now]) > 0:
            if(next == 2*N):
                next = 0
            move_ind(now, next, belt, robot)

    print("개별이동 belt:", belt)
    print("개별이동 robot:", robot, "head_idx", head_idx)

 
    # 가장 선두가 도착했나 확인
    if(head_idx == N-1):
        robot[head_idx] = 0
        head_idx = get_next_idx(robot, N-1, 0)   
        if(head_idx < 0):
            head_idx = get_next_idx(robot, 2*N-1, N+1)
    
    # step 3(올리기)
    if(belt[0] > 0 and robot[0] == 0):
        if(head_idx < 0):
            head_idx = 0
        robot[0] = 1
        belt[0] = belt[0] - 1

    print("올리기 belt:", belt)
    print("올리기 robot:", robot, "head_idx", head_idx)

print(stage)