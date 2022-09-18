import seaborn as sns
import matplotlib.pyplot as plt
import pandas as pd

pal = "RdYlGn"
#pal = "rocket"
sns.set_palette([sns.color_palette(pal)[0], sns.color_palette(pal)[3], sns.color_palette(pal)[5]])

data1 = pd.read_csv("times_delores.csv")
data2 = pd.read_csv("times_ours_old.csv")
data3 = pd.read_csv("times_ours.csv")
data = pd.concat([data1, data2, data3], axis=1)
print(data)
#data = pd.melt(data, var_name=['Delores', 'Ours'])

fig, axes = plt.subplots(nrows=1, ncols=1)

fig.set_size_inches(7,5.5)

sns.boxplot(data=data)

plt.savefig("./boxplot.pdf", papertype='A4', bbox_inches='tight')

#################
fig, axes = plt.subplots(nrows=1, ncols=1)

fig.set_size_inches(7,5.5)

sns.violinplot(data=data)

plt.savefig("./violin.pdf", papertype='A4', bbox_inches='tight')