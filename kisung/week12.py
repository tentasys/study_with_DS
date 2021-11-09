import math
def solution(progresses, speeds):
    time_takes = []
    for idx, progress in enumerate(progresses):
        time_takes.append(math.ceil((100-progress)/speeds[idx]))
    answer = []
    main_progress_takes = 0
    publishing_idx = -1
    for now_time_takes in time_takes:
        if(now_time_takes > main_progress_takes):
            main_progress_takes = now_time_takes
            answer.append(1)
            publishing_idx += 1
        else:
            answer[publishing_idx] += 1
    return answer