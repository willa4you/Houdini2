from numpy import empty
import pandas as pd
import collections

def populate_arr(index, original, target):
    splitted = original[index].split(",")
    for a in splitted:
        a_ = a.lstrip().rstrip().lstrip("\"").rstrip("\"").lstrip().rstrip()
        if "\"" in a_ or " " in a_:
            raise
        target.append(a_)

_ours = []
_delores = []

path_delores = "conclusions_delores.csv"
path_ours = "conclusions_ours.csv"

with open(path_delores, "r") as f_d:
    with open(path_ours, "r") as f_o:
        _delores = [x.lstrip().rstrip().lstrip("[").rstrip("]") for x in f_d.readlines()]
        _ours = [x.lstrip().rstrip().lstrip("[").rstrip("]") for x in f_o.readlines()]
#Save to file
data = pd.DataFrame({'delores': _delores, 'ours': _ours})
data.to_csv("./all_conclusions.csv")

n = len(_delores)
counter = 0

#print("ciao"[1:-1])
for i in range(0,n):
    delo = []
    our = []
    
    populate_arr(i, _delores, delo)
    populate_arr(i, _ours, our)
    delo_set = set(delo)
    our_set = set(our)
    difference_left = delo_set - our_set
    difference_right = our_set - delo_set
    if not(len(difference_right)==0 and len(difference_left)==0):
        counter += 1
        print(f">>>>>> Difference in theory number {i+1}")
        #print(f"D - O = {difference_left}")
        #print(f"O - D = {difference_right}")
    cc = 0
    """
    for l in [5, 9, 3, 17, 24]:
        ournew = []
        populate_arr(l, _ours, ournew)
        if collections.Counter(delo) != collections.Counter(ournew):
            cc += 1
    print(cc/5)
    """
    

print(f"{counter} / {n}")