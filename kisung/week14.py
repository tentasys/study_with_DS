num = int(input())
stick_length_one = 64
count = 0
stick_length_now = 0
stick_length_goal = num
while stick_length_one > 0:
    if stick_length_now == stick_length_goal:
        break
    left_length = stick_length_goal - stick_length_now
    if left_length == stick_length_one:
        count += 1
        break
    elif left_length < stick_length_one:
        stick_length_one /= 2
    else:
        stick_length_now += stick_length_one
        count += 1
print(count)