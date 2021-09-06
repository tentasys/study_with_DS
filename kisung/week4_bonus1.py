import math
max_level = int(input())
input = input().split(" ")
ary = []
ary.append(input)
cnt = 0
level = 1
string = ""
while(len(ary) > 0):
    cnt += 1
    now = ary.pop(0)
    mid = int(len(now)/2)
    string += now[mid]
    if(math.pow(2, level)-1 == cnt):
        level += 1
        if(level > max_level):
            break
        string += "\n"
    else:
        string += " "
    if(len(now) > 1):
        ary.append(now[:mid])
        ary.append(now[mid+1:])

print(string)
