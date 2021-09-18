# 시행착오 TIP
# 틀렸던 이유 : 순서를 잘못잡음
# 순서를 다음과 같이 나열
# 1. 1초 지나며 이동할 곳에 대가리 생성
# 2. 대가리 생성한곳이 멀쩡한곳인지 확인
# (2-ok)3. 대가리 몸통에 붙여주기
# (2-ok)4. 대가리가 사과 먹었는지 확인하고 먹었으면 꼬리자르기
# (2-ok)5. 움직일 방향 바꿔주기(n초 후 방향 바꿔주므로 마지막에 방향전환)

# INIT CODE USING FILE I/O
# max_length, apple_length, apples = None, None, []
# turn_length, turn = None, []
# with open("test_case.txt", "r") as f:
#     max_length = int(f.readline())
#     apple_length = int(f.readline())
#     apples = []
#     for _ in range(apple_length):
#         tmp = f.readline().split(" ")
#         apples.append([int(tmp[0]), int(tmp[1])])
#     turn_length = int(f.readline())
#     turn = []
#     for _ in range(turn_length):
#         tmp = f.readline().split(" ")
#         if(tmp[1] == "L"):
#             tmp[0] = int(tmp[0]) * -1
#         turn.append(int(tmp[0]))
#     f.close()

# INIT CODE USING INPUT() <-- baekjoon :(
max_length = int(input())
apple_length = int(input())
apples = []
for _ in range(apple_length):
    tmp = input().split(" ")
    apples.append([int(tmp[0]), int(tmp[1])])
turn_length = int(input())
turn = []
for _ in range(turn_length):
    tmp = input().split(" ")
    if(tmp[1] == "L"):
        tmp[0] = int(tmp[0]) * -1
    turn.append(int(tmp[0]))

#input 확인
#print(max_length, apples, turn)
snake = [[1,1]]
cnt = 0
dir = ["R", "D", "L", "U"]
dir_idx = 0
stop_sign = False
while(True):
    # print(snake, cnt)
    cnt += 1    
    now = snake[len(snake)-1][:]
    if(dir[dir_idx] == "R"):
        now[1] += 1
    elif(dir[dir_idx] == "D"):
        now[0] += 1
    elif(dir[dir_idx] == "L"):
        now[1] -= 1
    elif(dir[dir_idx] == "U"):
        now[0] -= 1

    if(now[0] > max_length or now[1] > max_length
     or now[0] < 1 or now[1] < 1):
        stop_sign = True
    for idx in range(0, len(snake)-1):
        if(snake[idx] == now):
            stop_sign = True
            break
    if stop_sign:
        break

    snake.append(now)

    ate = False
    for i, apple in enumerate(apples):
        if(apple == now):
            # print("ate!!", now)
            ate = True
            apples.pop(i)
            break

    if not ate:
        snake.pop(0)
 
    if(len(turn) > 0 and abs(turn[0]) == cnt):
        next = turn.pop(0)
        if(next > 0):
            dir_idx += 1
            if(dir_idx > len(dir)-1):
                dir_idx = 0
        else:
            dir_idx -= 1
            if(dir_idx < 0):
                dir_idx = len(dir)-1

print(cnt)