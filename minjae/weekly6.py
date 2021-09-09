def solution(weights, head2head):
    answer = []
    res = []
    for i in range(len(head2head)):
        round = head2head[i]
        info = []
        rate_tmp = 0
        w_tmp = 0
        div = 0
        for j in range(len(round)):
            game = round[j]
            # rate count
            if game == 'W':
                rate_tmp += 1
                # weight count
                if weights[i] < weights[j]:
                    w_tmp += 1
            if game != 'N':
                div += 1
        if div == 0:
            div = 1
        rate = rate_tmp / div

        info.append(rate)
        info.append(w_tmp)
        info.append(weights[i])
        info.append(i + 1)
        res.append(info)
    # print(res)
    res.sort(key=lambda x: (-x[0], -x[1], -x[2], x[3]))
    # print(res)
    for ele in res:
        num = ele[-1]
        answer.append(num)
    return answer