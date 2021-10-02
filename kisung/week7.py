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
parsed  = context.split("-")
if context.find("-") != 0:
    ret_val += add_number(parsed[0])
    ret_val += add_number(parsed[0])
for item in parsed:
    if item == '':
        continue
    ret_val -= add_number(item)
print(ret_val)
