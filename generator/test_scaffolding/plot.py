import seaborn as sns
import matplotlib.pyplot as plt
import pandas as pd

pal = "RdYlGn"
sns.set_palette([sns.color_palette(pal)[0], sns.color_palette(pal)[5]])

"""
data1 = pd.read_csv("times_delores.csv")
data2 = pd.read_csv("times_ours.csv")
dataold = pd.read_csv("times_ours_old.csv")
data = pd.concat([data1[10:], dataold[10:], data2[10:]], axis=1)
print(data2.mean()[0]/dataold.mean()[0])
#data = pd.melt(data, var_name=['Delores', 'Ours'])

fig, axes = plt.subplots(nrows=1, ncols=1)

fig.set_size_inches(7,5.5)

sns.violinplot(data=data)

plt.savefig("./violin.pdf", papertype='A4', bbox_inches='tight')
"""
data1 = pd.read_csv("times_delores.csv")[10:]
data2 = pd.read_csv("times_ours.csv")[10:]
dataold = pd.read_csv("times_ours_old.csv")[10:]
#dataold = pd.read_csv("times_ours_old.csv")
datawide = pd.concat([data1, data2, dataold], axis=1)

datawide['Experiment'] = [x for x in range(0, datawide.shape[0])]
data = pd.DataFrame({"Algorithm": ["Delores" for x in range(0, data1.shape[0])], "Time(s)": data1['Delores']})
data = data.append(pd.DataFrame({"Algorithm": ["Ours_old" for x in range(0, dataold.shape[0])], "Time(s)": dataold['Ours_old']}))
data = data.append(pd.DataFrame({"Algorithm": ["Ours" for x in range(0, data2.shape[0])], "Time(s)": data2['Ours']}))
data['Experiment'] = [x%data1.shape[0] for x in range(0, data.shape[0])]
print(data)
print(f"~~~~~~~~~~~~~~~~~~\nDelores: {round(data1.mean()[0],3)} s")
print(f"Ours: {round(data2.mean()[0],3)} s\n~~~~~~~~~~~~~~~~~~")

fig, axes = plt.subplots(nrows=1, ncols=1)

fig.set_size_inches(7,4)

"""
sns.scatterplot(data=data[data['Algorithm']=='Delores'], x="index", y="Time(s)", hue="Algorithm", ax=axes[0])
sns.scatterplot(data=data[data['Algorithm']=='Ours'], x="index", y="Time(s)",
    hue="Algorithm", ax=axes[1], palette=[sns.color_palette(pal)[5]])

axes[0].set_ylim(0, 0.225)
axes[1].set_ylim(0, 0.225)
"""
sns.scatterplot(data=data, x='Experiment', y='Time(s)', hue="Algorithm")
plt.savefig("./violin.pdf", papertype='A4', bbox_inches='tight')


# Box

fig, axes = plt.subplots(nrows=1, ncols=1)

fig.set_size_inches(7,5.5)

sns.boxplot(data=data, y="Time(s)", x="Algorithm", showmeans=True, 
    meanprops={"marker":"x", "markerfacecolor":"white", "markeredgecolor":"white", "markersize":"5"} )

plt.savefig("./boxplot.pdf", papertype='A4', bbox_inches='tight')