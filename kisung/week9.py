steals_with_layser = input()
# INIT CODE USING FILE I/O
# with open("test_case.txt", "r") as f:
#     steals_with_layser = f.readline()

before = None
steal_stack = []
sliced_steal = 0
for item in steals_with_layser:
    if item == "(":
        steal_stack.append(item)
    elif item == ")":
        steal_stack.pop()
        if before == "(":
            sliced_steal += len(steal_stack)
        elif before == ")":
            sliced_steal += 1
    before = item
print(sliced_steal)