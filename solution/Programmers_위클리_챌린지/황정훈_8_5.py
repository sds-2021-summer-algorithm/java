def solution(word):
    dic = {'A':1, 'E':2, 'I':3, 'O':4, 'U':5}
    return sum([efc*(dic[c]-1)+dic[c] for efc, c in zip([780, 155, 30, 5, 0], word)])
