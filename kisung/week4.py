# 아 시간초과... 백준의 불편한 인터페이스에 익숙해가는중..

string1 = input().split(" ")
col = int(string1[0]) # m
row = int(string1[1]) # n
ary = []
for _ in range(row):
    temp = input().split(" ")
    for item in temp:
        ary.append(int(item))

def finished(ary):
    flag = True
    for data in ary:
        if data == 0:
            flag = False
    return flag

def affected(origin, ary, m, n):
    for i, data in enumerate(origin):
        if data == 1:
            if(i%m > 0 and ary[i-1] != -1):
                ary[i-1] = 1
            if(int(i/m) > 0 and ary[i-m] != -1):
                ary[i-m] = 1
            if((i+1)%m > 0 and ary[i+1] != -1):
                ary[i+1] = 1
            if(int(i/m)+1 < n and ary[i+m] != -1):
                ary[i+m] = 1

def test_print(ary, m):
    string = ""
    row = 0
    for i, data in enumerate(ary):
        if(row != int(i/m)):
            print(string)
            row = int(i/m)
            string = str(data)+" "
        else:
            string += str(data)+" "
    print(string)

need_stage = 0
changed = 0
while not finished(ary):
    need_stage+=1
    origin = ary.copy()
    affected(origin, ary, col, row)
    if(origin == ary):
        break

if not finished(ary):
    need_stage = -1

print(need_stage)


