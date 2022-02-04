N = int(input())
TS = []
for _ in range(N):
    TS.append(list(map(int,input().split()))) #걸리는 시간 #끝내야하는 시간
TS.sort(key=lambda x:x[1])

total_time = 0
min_rest = float('inf')
for time, limit in TS:
    total_time += time
    min_rest = min(min_rest, limit-total_time)
    if limit < total_time:
        print(-1)
        exit()
print(min_rest)
