length = None
stairs_score = []
# INIT CODE USING FILE I/O
# with open("test_case.txt", "r") as f:
#     length = int(f.readline())
#     stairs_score.append(0)
#     for _ in range(length):
#         stairs_score.append(int(f.readline()))

# INIT CODE USING BAEK
length = int(input())
stairs_score.append(0)
for _ in range(length):
    stairs_score.append(int(input()))

max_score = []
max_score.append(0)
max_score.append(stairs_score[1])
if length > 1:
    max_score.append(stairs_score[1]+stairs_score[2])
for i in range(3, length+1):
    max_score.append(max(max_score[i-3]+stairs_score[i-1]+stairs_score[i], max_score[i-2]+stairs_score[i]))
print(max_score[length])