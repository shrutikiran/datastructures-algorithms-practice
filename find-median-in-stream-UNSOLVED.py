import sys

def calculateMedian(arr):

    if (len(arr) % 2 == 0):
        ix1 = (len(arr) / 2) - 1
        ix2 = ix1 + 1
        return (arr[ix1] + arr[ix2]) / 2
    else:
        ix = len(arr) / 2
        return arr[ix]

if __name__ == '__main__':
    nrNumbers = sys.stdin.readline()
    nrNumbers = int(nrNumbers)

    arr = []

    for i in range(0, nrNumbers):
        num = sys.stdin.readline()
        num = int(num)
        arr.append(num)
        print('median of ' + str(arr) + ' = ' + str(calculateMedian(arr)))
