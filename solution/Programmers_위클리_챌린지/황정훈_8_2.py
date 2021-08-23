def solution(scores):
    n = len(scores)
    answer = ''
    for i, mine in enumerate(zip(*scores)):
        mine=list(mine)
        if mine[i] in [min(mine), max(mine)]:
            my = mine.pop(i)
            if my in [min(mine), max(mine)]: answer+=calc((sum(mine)+my)/n)
            else: answer+=calc(sum(mine)/(n-1))
        else: answer+=calc(sum(mine)/n)
    return answer

def calc(score):
    if 90<=score: return 'A'
    elif 80<=score<90: return 'B'
    elif 70<=score<80: return 'C'
    elif 50<=score<70: return 'D'
    else: return 'F'
