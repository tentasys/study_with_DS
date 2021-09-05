def solution(word):
    global answer, tmp
    answer = 0
    tmp = 0

    def go(now, deep):
        global answer, tmp

        if now == word:
            answer = tmp
            return
        tmp +=1
        if deep == 5:
            return



        alpha = ["A", "E", "I", "O", "U"]

        for i in range(5):
            ele = alpha[i]
            go(now + ele, deep + 1)

        return

    go('', 0)
    return answer

print(solution("AAAAE"))