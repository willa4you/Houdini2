from numpy import empty
import pandas as pd
import collections
import random

def populate_arr(index, original, target):
    splitted = original[index].split(",")
    for a in splitted:
        a_ = a.lstrip().rstrip().lstrip("\"").rstrip("\"").lstrip().rstrip()
        if "\"" in a_ or " " in a_:
            raise
        target.append(a_)

def check_arr_sameness(arr1, arr2):
    arr1_set = set(arr1)
    arr2_set = set(arr2)
    difference_left = arr1_set - arr2_set
    difference_right = arr2_set - arr1_set
    return (len(arr1)==len(arr2) and len(difference_left)==0)

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
n_others = 100
counter = 0
total_same = 0
#print("ciao"[1:-1])
for i in range(0,n):
    delo = []
    our = []
    
    populate_arr(i, _delores, delo)
    populate_arr(i, _ours, our)
    
    if not check_arr_sameness(delo, our):
        counter += 1
        print(f">>>>>> Difference in theory number {i+1}")
        #print(f"D - O = {difference_left}")
        #print(f"O - D = {difference_right}")
    """
    for j in [random.randint(0, 10000-1) for x in range(0,n_others)]:
        if j == i:
            continue
        ournew = []
        populate_arr(j, _ours, ournew)
        if check_arr_sameness(ournew, our):
            total_same += 1
    """


#print(f"Same: {total_same/(n*n_others)}")

print(f"{counter} / {n}")