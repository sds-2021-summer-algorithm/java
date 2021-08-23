def solution(table, languages, preference):
    jobs = {"SI":0, "CONTENTS":0, "HARDWARE":0, "PORTAL":0, "GAME":0}
    for column in table:
        column = column.split()
        job = column[0]
        for lang, score in zip(languages, preference):
            if lang in column: jobs[job] -= score * (6-column.index(lang))
    return sorted(jobs.items(), key=lambda x:(x[1], x[0]))[0][0]
