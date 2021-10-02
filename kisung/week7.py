#context = "55+50+40"
context = input()
# INIT CODE USING FILE I/O
# print(context)
def add_number(input):
    sum = 0
    for num in input.split("+"):
        sum += int(num)
    return sum

ret_val = 0
# 1) - 를 기준으로 나눠준다. ex 15+30-20 -> 15+30, 20
parsed  = context.split("-")
# 2) 첫 - 전 +가 나오는지 확인하고. ex 15+30-20 -> 15 found
# 해당 값을 두번 더해준다.(logic에서 첫 번째가 +인경우 예외발생하므로)
if context.find("-") != 0:
    ret_val += add_number(parsed[0])
    ret_val += add_number(parsed[0])
# 3) 1)에서 나눈 각 식의 숫자합을 빼준다.
# logic:각 마이너스 앞부터 다음 마이너스까지 괄호를 치는것이 항상 최소값
# ex 15+30-20 
# => 2번조건) 15 + 15 
# => 3번조건) -(15+30) -(20)
# => 결과) 15 + 15 -(15+30) - (20)
# =>       15 + (15 - 15) -(30) -(20)
# 단 이 로직에서 모두 + 만 사용되는 경우는 고려하지 않았다 -> 백준에서는 합격임
for item in parsed:
    if item == '':
        continue
    ret_val -= add_number(item)
print(ret_val)
