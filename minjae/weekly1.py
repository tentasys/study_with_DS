def solution(table, languages, preference):
    answer = ''

    grid = []
    dic = {}

    for ele in table:
        ele = ele.split()
        grid.append(ele)

        dic[ele[0]] = 0

    for i in range(len(languages)):
        lang = languages[i]
        score = preference[i]

        for ele in grid:
            for idx in range(1, len(ele)):
                if ele[idx] == lang:
                    dic[ele[0]] += score * (6 - idx)

    res = list(dic.items())
    # print(res)

    res.sort(key=lambda x: (-x[1], x[0]))
    # print(res)

    answer = res[0][0]
    return answer