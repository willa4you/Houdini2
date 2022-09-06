import pandas as pd
import collections


ours = []
delores = []

path_delores = "conclusions_delores.csv"
path_ours = f"conclusions_ours.csv"

with open(path_delores, "r") as f_d:
    with open(path_ours, "r") as f_o:
        delores = [x.lstrip().rstrip().lstrip("[").rstrip("]") for x in f_d.readlines()]
        ours = [x.lstrip().rstrip().lstrip("[").rstrip("]") for x in f_o.readlines()]
#Save to file
data = pd.DataFrame({'delores': delores, 'ours': ours})
data.to_csv("./all_conclusions.csv")

n = 1000
counter = 0

#print("ciao"[1:-1])
for i in range(0,len(delores)):
    delo = [a.lstrip().rstrip().lstrip("\"").rstrip("\"").lstrip().rstrip() for a in delores[i].split(",")]
    our = [a.lstrip().rstrip().lstrip("\"").rstrip("\"").lstrip().rstrip() for a in ours[i].split(",")]
    if collections.Counter(delo) != collections.Counter(our):
        counter += 1
print(counter/n)