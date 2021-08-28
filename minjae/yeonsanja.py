import sys
sys.stdin = open('yeonsanja.txt','r')

def go(deep, now):
    global max_num, min_num
    if deep == N:
        if now > max_num:
            max_num = now
        if now < min_num:
            min_num = now
        return

    for i in range(4):
        if calcs[i] > 0:
            calcs[i] -= 1
            if i == 0:
                go(deep+1, now + numbers[deep])
            elif i == 1:
                go(deep+1, now - numbers[deep])
            elif i == 2:
                go(deep+1, now * numbers[deep])
            else:
                if now < 0:
                    res = abs(now) //numbers[deep]
                    go(deep+1, -res)
                else:
                    go(deep+1, now // numbers[deep])
            calcs[i] += 1

N = int(input())
numbers = list(map(int, input().split()))
calcs = list(map(int, input().split()))

max_num = -9999999999999999999
min_num = 999999999999999999

go(1,numbers[0])

print(max_num)
print(min_num)