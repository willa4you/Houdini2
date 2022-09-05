import seaborn as sns
import matplotlib.pyplot as plt
import pandas as pd

sns.set_palette("Paired")

data1 = pd.read_csv("times.csv")
data2 = pd.read_csv("times2.csv")
data = pd.concat([data1, data2], axis=1)
print(data)
#data = pd.melt(data, var_name=['Delores', 'Ours'])

#RNNdata = pd.read_csv("CV_RNN," + t + ".csv")

#data=pd.concat([MLPdata, RNNdata])

fig, axes = plt.subplots(nrows=1, ncols=1)

fig.set_size_inches(11,9.5)

sns.boxplot(data=data)


plt.savefig("./results.pdf", papertype='A4', bbox_inches='tight')