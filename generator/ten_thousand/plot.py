import seaborn as sns
import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

withold = False

n_dataframes = 2 if not withold else 3
pal = "RdYlGn"
palette = [sns.color_palette(pal)[0], sns.color_palette(pal)[5]] if not withold else [sns.color_palette(pal)[0], sns.color_palette(pal)[4], sns.color_palette(pal)[5]]
sns.set_palette(palette)

frompoint = 50
data1 = pd.read_csv("times_delores.csv")[frompoint:]
data2 = pd.read_csv("times_ours.csv")[frompoint:]
dataold = pd.read_csv("times_ours_old.csv")[frompoint:]
#dataoldold = pd.read_csv("times_ours_old_old.csv")[frompoint:]
#datawide = pd.concat([dataold, data2], axis=1)
#datawide['Experiment'] = [x for x in range(0, datawide.shape[0])]
data = pd.DataFrame({"Reasoner": ["Spindle" for x in range(0, data1.shape[0])], "Time(s)": data1['Delores']})
#data = pd.DataFrame()
#data = data.append(pd.DataFrame({"Algorithm": ["Ours_old_old" for x in range(0, dataold.shape[0])], "Time(s)": dataoldold['Ours_old']}))
if withold:
    data = data.append(pd.DataFrame({"Reasoner": ["Houdini old" for x in range(0, dataold.shape[0])], "Time(s)": dataold['Ours']}))
data = data.append(pd.DataFrame({"Reasoner": ["Houdini" for x in range(0, data2.shape[0])], "Time(s)": data2['Ours']}))

data['Experiment'] = [int(x%(data.shape[0]/n_dataframes)) for x in range(0, data.shape[0])]

print(f"~~~~~~~~~~~~~~~~~~\nDelores: {round(data1.median()[0],5)} s")
print(f"Ours: {round(data2.median()[0],5)} s\n~~~~~~~~~~~~~~~~~~")
print(f"{round(data1.median()[0]/data2.median()[0], 3)} volte meglio di Delores (in mediana)\n")
fig, axes = plt.subplots(nrows=1, ncols=1)

fig.set_size_inches(8,5.33)

"""
sns.scatterplot(data=data[data['Reasoner']=='Delores'], x="index", y="Time(s)", hue="Algorithm", ax=axes[0])
sns.scatterplot(data=data[data['Reasoner']=='Ours'], x="index", y="Time(s)",
    hue="Reasoner", ax=axes[1], palette=[sns.color_palette(pal)[5]])

axes[0].set_ylim(0, 0.225)
axes[1].set_ylim(0, 0.225)
"""
sns.scatterplot(data=data, x='Experiment', y='Time(s)', hue="Reasoner")
dot1 = plt.plot(-75, data2.median()[0],marker="x", markersize=7, markeredgecolor="black", markerfacecolor="black")[0]
dot2 = plt.plot(-75, data1.median()[0], marker="x", markersize=7, markeredgecolor="black", markerfacecolor="black")[0]
dot1.set_clip_on(False)
dot2.set_clip_on(False)
axes.set_xlim(-75,10015)
plt.savefig("./scatterplot.pdf", papertype='A4', bbox_inches='tight')

# Box

fig, axes = plt.subplots(nrows=1, ncols=1)

fig.set_size_inches(4,6)

sns.boxplot(data=data, y="Time(s)", x="Reasoner", showmeans=True, linewidth=0.75,
    meanprops={"marker":"x", "markerfacecolor":"white", "markeredgecolor":"white", "markersize":"4"},
     )

plt.ylabel("Time (s)")
plt.savefig("./boxplot.pdf", papertype='A4', bbox_inches='tight')



# KDEplot
fig, axes = plt.subplots(nrows=1, ncols=1)

fig.set_size_inches(8,5.33)

sns.displot(data=data, stat="density", bins=np.linspace(0, 0.16, 160), x="Time(s)", hue="Reasoner",
     multiple="stack")

plt.ylabel("Density")
dot1 = plt.plot(data2.median()[0], 0, marker="x", markersize=7, markeredgecolor="black", markerfacecolor="black")[0]
dot2 = plt.plot(data1.median()[0], 0, marker="x", markersize=7, markeredgecolor="black", markerfacecolor="black")[0]
dot1.set_clip_on(False)
dot2.set_clip_on(False)

plt.savefig("./distribution.pdf", papertype='A4', bbox_inches='tight')