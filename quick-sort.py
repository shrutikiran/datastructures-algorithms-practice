import sys

def partition(arr, startIx, endIx):
    pivotIx = endIx - 1

    for i in range(startIx, endIx):
        if (arr[i] > arr[pivotIx]):
            temp = arr[i]
            arr[i] = arr[pivotIx]
            arr[pivotIx] = temp

    print('partitioned array = ' + str(arr))

    return pivotIx;

def quickSort(arr, startIx, endIx):
    print('arr = ' + str(arr) + ', startIx = ' + str(startIx) + ', endIx = ' + str(endIx))

    if (endIx - startIx <= 1):
        return

    pivotIx = partition(arr, startIx, endIx)

    quickSort(arr, startIx, pivotIx)
    quickSort(arr, pivotIx, endIx)

if __name__ == '__main__':
    arr = [10, 5, 2, 18, 92, 12, -1, 48]

    quickSort(arr, 0, len(arr))

    print(str(arr))
