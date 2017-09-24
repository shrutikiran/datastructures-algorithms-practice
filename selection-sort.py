import sys

def selectionSort(arr):
    l = len(arr)
    for i in range(0, l):
        minIx = i
        for j in range(i + 1, l):
            if (arr[minIx] > arr[j]):
                minIx = j
        print('swapping indicies [' + str(i) + ', ' + str(minIx) + ']')
        temp = arr[i]
        arr[i] = arr[minIx]
        arr[minIx] = temp
        print(str(arr))

if __name__ == '__main__':
    arr = [10, 5, 2, 18, 92, 12, -1, 48]

    selectionSort(arr)

    print(str(arr))
