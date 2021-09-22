max_length, wants, seats = None, [], None
# INIT CODE USING FILE I/O
# with open("test_case.txt", "r") as f:
#     max_length = int(f.readline())
#     classmates = max_length * max_length
#     seats = [[0 for _ in range(max_length)] for _ in range(max_length)]
#     for _ in range(classmates):
#         tmp = f.readline().split(" ")
#         wants.append([int(data) for data in tmp])
#     f.close()
# print(wants, seats)

# INIT CODE USING INPUT() <-- baekjoon :(
max_length = int(input())
classmates = max_length * max_length
seats = [[0 for _ in range(max_length)] for _ in range(max_length)]
for _ in range(classmates):
    tmp = input().split(" ")
    wants.append([int(data) for data in tmp])

def calculate_seat_score(r, c, max_length, friend_score, empty_score, _seats, _wants):
    if(r > -1 and r < max_length and c > -1 and c < max_length):
            if(_seats[r][c] > 0):
                for want in _wants:
                    if(_seats[r][c] == want):
                        friend_score += 1
                        break
            else:
                empty_score += 1
    return friend_score, empty_score

def step1(seats, now_student, length):
    wanting_seats = []
    wanting_scores = -1
    for i, row in enumerate(seats):
        for j, col in enumerate(row):
            if(col > 0):
                continue
            now_score = 0
            tmp_friend, tmp_empty = 0, 0
            tmp_friend, tmp_empty = calculate_seat_score(i, j+1, length, tmp_friend, tmp_empty, seats, now_student)
            tmp_friend, tmp_empty = calculate_seat_score(i, j-1, length, tmp_friend, tmp_empty, seats, now_student)
            tmp_friend, tmp_empty = calculate_seat_score(i-1, j, length, tmp_friend, tmp_empty, seats, now_student)
            tmp_friend, tmp_empty = calculate_seat_score(i+1, j, length, tmp_friend, tmp_empty, seats, now_student)
            now_score = round(pow(10, tmp_friend-1))
            if(now_score > wanting_scores):
                wanting_scores = now_score
                wanting_seats = [[tmp_empty, i, j]]
            elif(now_score == wanting_scores):
                wanting_seats.append([tmp_empty, i, j])
    return wanting_seats

for want in wants:
    wanting_seats = step1(seats, want[1:], max_length)
# if wanting_seats length == 1, then mark and continue loop
# else continue to next step
    if(len(wanting_seats) > 1):
        # step 2
        wanting_seats2 = []
        empty_scores = -1
        for seat in wanting_seats:
            if(seat[0] > empty_scores):
                empty_scores = seat[0]
                wanting_seats2 = [seat[:]]
            elif(seat[0] == empty_scores):
                wanting_seats2.append(seat[:])
        # step 3
        if(len(wanting_seats2) > 1):
            wanting_seats2.sort(key=lambda x:(x[1], x[2]))
        seats[wanting_seats2[0][1]][wanting_seats2[0][2]] = want[0]
    else:
        seats[wanting_seats[0][1]][wanting_seats[0][2]] = want[0]

def calculate_final_scores(r, c, length, _seat, _wants, friend_score):
    if(r > -1 and r < length and c > -1 and c < length):
        for want in _wants:
            if(_seat[r][c] == want):
                friend_score += 1
                break
    return friend_score

final_scores = 0
for i, seat in enumerate(seats):
    for j, student in enumerate(seat):
        now_want = None
        for want in wants:
            if(want[0] == student):
                now_want = want[1:]
                break
        now_score = 0
        now_score = calculate_final_scores(i, j+1, max_length, seats, now_want, now_score)
        now_score = calculate_final_scores(i, j-1, max_length, seats, now_want, now_score)
        now_score = calculate_final_scores(i-1, j, max_length, seats, now_want, now_score)
        now_score = calculate_final_scores(i+1, j, max_length, seats, now_want, now_score)
        final_scores += round(pow(10, now_score-1))

# print(wants, seats, final_scores)
print(final_scores)