from asyncio.windows_events import NULL
import os

import matplotlib

matplotlib.use('TkAgg')
import matplotlib.pyplot as plt

filenames = ['maxTime', 'maxIteration', 'tabuSize', 'withoutImprove', 'envSize']
TSP = '_TSPLIB_'
formats = ['EUC', 'FULL_MATRIX', 'LOWER_DIAG_ROW']
txt = ".txt"

for filename in filenames:
    for format in formats:

        file = filename + TSP + format + txt
        fileDir = os.path.dirname(os.path.realpath('__file__'))
        path = os.path.join(fileDir, "../" + file)
        currFile = open(path)

        size = []
        parameter = []
        prdData = []

        lines = currFile.readlines()

        for line in lines:
            line = line.strip()
            line = line.split(";")
            size.append(int(line[0]))
            parameter.append(int(line[1]))
            prdData.append(float(line[2]))

        
        file.replace(".txt", "")
        plt.title(filename + " for instance size " + str(size[0]) + " format " + format)
        plt.ylabel("PRD")
        plt.xlabel(filename)
        plt.plot(parameter, prdData)
        
        
        
        plt.savefig(file.replace(".txt", "") + ".jpg")
        plt.close()
        currFile.close()