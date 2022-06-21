from asyncio.windows_events import NULL
import os

import matplotlib

matplotlib.use('TkAgg')
import matplotlib.pyplot as plt





def twoOptPlot():
    file = "test_two_opt.txt"
    fileDir = os.path.dirname(os.path.realpath('__file__'))
    path = os.path.join(fileDir, "../" + file)
    currFile = open(path)

    size = []

    timeKRand = []
    timeCN = []
    timeCNE = []

    prdKRand =[]
    prdCN =[]
    prdCNE = []

    lines = currFile.readlines()

    for line in lines:
            line = line.strip()
            line = line.split(";")
            size.append(int(line[0]))
            timeKRand.append(int(line[1]))
            timeCNE.append(int(line[2]))
            timeCN.append(int(line[3]))
            prdKRand.append(float(line[4]))
            prdCNE.append(float(line[5]))
            prdCN.append(float(line[6]))

    name = file.replace("test_", "").replace('.txt','_time')
    
    plt.title(name)
    plt.ylabel("time [nanoseconds]")
    plt.xlabel("size")
    plt.plot(size, timeKRand, label = "kRand")
    plt.plot(size, timeCN, label = "CN")
    plt.plot(size, timeCNE, label = "CNE")
    plt.legend()
        
    plt.savefig(name)
    plt.close()

    name = file.replace("test_", "").replace('.txt','_prd')
    
    plt.title(name)
    plt.ylabel("prd [%]")
    plt.xlabel("size")
    plt.plot(size, prdCNE, label = "CNE")
    plt.plot(size, prdCN, label = "CN")
    plt.plot(size, prdKRand, label = "kRand")
    plt.legend()
    plt.savefig(name)
    plt.close()
    currFile.close()

def kRandomPlot():

    files = ['bays29.txt', 'berlin52.txt', 'gr120.txt']
    for file in files:

        fileDir = os.path.dirname(os.path.realpath('__file__'))
        path = os.path.join(fileDir, "../" + file)
        currFile = open(path)


        k = []
        time = []
        prd =[]
        

        lines = currFile.readlines()


        for line in lines:
            line = line.strip()
            line = line.split(";")
            k.append(int(line[0]))
            time.append(float(line[1]))
            prd.append(float(line[2]))
           
        name = file.replace('.txt','_time')
        
        plt.title(name)
        plt.ylabel("time [nanoseconds]")
        plt.xlabel("k")
        plt.scatter(k, time, label= "Time", color= "green",
            marker= ".", s=10)
        
        plt.legend()
        
        plt.savefig(name)
        plt.close()

        name = file.replace('.txt','_prd')
        plt.scatter(k, prd, label= "prd", color= "green",
            marker= ".", s=10)
        plt.title(name)
        plt.ylabel("prd [%]")
        plt.xlabel("k")
       
        plt.savefig(name)
        plt.close()
        currFile.close()



def algorithmsBenchmark(): 
    fileDir = os.path.dirname(os.path.realpath('__file__'))
    files = ['test_asym_rand.txt', 'test_euc_rand.txt', 'test_sym_rand.txt', 'test_asym_TSPLIB.txt', 'test_sym_TSPLIB.txt', 'test_euc_TSPLIB.txt']

    for file in files:

        path = os.path.join(fileDir, "../" + file)
        currFile = open(path)


        size = []

        timeCNE = []
        time2opt = []
        timeCN = []

        prdCNE =[]
        prd2opt =[]
        prdCN = []

        lines = currFile.readlines()


        for line in lines:
            line = line.strip()
            line = line.split(";")
            size.append(int(line[0]))
            timeCNE.append(int(line[1]))
            time2opt.append(int(line[2]))
            timeCN.append(int(line[3]))
            prdCNE.append(float(line[4]))
            prd2opt.append(float(line[5]))
            prdCN.append(float(line[6]))


        name = file.replace("test_", "").replace('.txt','')
        name += '_time'
        plt.title(name)
        plt.ylabel("time [nanoseconds]")
        plt.xlabel("size")

        if name.__contains__('TSPLIB'):
            plt.scatter(size, timeCNE, label = "CNE",
                marker= ".", s=50)
            plt.scatter(size, timeCN, label = "CN",
                marker= ".", s=50)
            plt.scatter(size, time2opt, label = "2opt",
                marker= ".", s=50)
        else:
            plt.plot(size, timeCNE, label = "CNE")
            plt.plot(size, timeCN, label = "CN")
            plt.plot(size, time2opt, label = "2opt")
        plt.legend()
        
        plt.savefig(name)
      
        plt.close()

        name = file.replace("test_", "").replace('.txt','')
        name += '_prd'
        plt.title(name)
        plt.ylabel("prd [%]")
        plt.xlabel("size")



        if name.__contains__('TSPLIB'):
            plt.scatter(size, prdCNE, label = "CNE",
                marker= ".", s=50)
            plt.scatter(size, prdCN, label = "CN",
                marker= ".", s=50)
            plt.scatter(size, prd2opt, label = "2opt",
                marker= ".", s=50)
        else:
            plt.plot(size, prdCNE, label = "CNE")
            plt.plot(size, prdCN, label = "CN")
            plt.plot(size, prd2opt, label = "2opt")

      
        plt.legend()
        plt.savefig(name)
        plt.close()
        currFile.close()




twoOptPlot()
# kRandomPlot()
# algorithmsBenchmark()