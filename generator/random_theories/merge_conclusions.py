import pandas as pd
import collections


ours = []
delores = []

for i in range(1,1001):
    path_delores = f"conclusions_delores/theory{i}_conclusions.dt"
    path_ours = f"conclusions_ours/theory{i}_our_conclusions.dt"
    with open(path_delores, "r") as f_d:
        with open(path_ours, "r") as f_o:
            delores.append(f_d.readlines()[0].lstrip().rstrip().lstrip("[").rstrip("]"))
            ours.append(f_o.readlines()[0].lstrip().rstrip().lstrip("[").rstrip("]"))
#Save to file
data = pd.DataFrame({'delores': delores, 'ours': ours})
#data.to_csv("./all_conclusions.csv")
#print(data)

n = 1000
counter = 0

#print("ciao"[1:-1])
for i in range(0,n):
    cc = 0
    delo = [a.lstrip().rstrip().lstrip("\"").rstrip("\"").lstrip().rstrip() for a in delores[i].split(",")]
    our = [a.lstrip().rstrip().lstrip("\"").rstrip("\"").lstrip().rstrip() for a in ours[i].split(",")]
    
    """
    #check random correctness
    for l in [78, 908, 268, 124, 834]:
        ournew = [a.lstrip().rstrip().lstrip("\"").rstrip("\"").lstrip().rstrip() for a in ours[l].split(",")]
        if collections.Counter(delo) != collections.Counter(ournew):
            cc += 1
    print(cc/5)
    """
    if collections.Counter(delo) != collections.Counter(our):
        counter += 1
print(counter/n)