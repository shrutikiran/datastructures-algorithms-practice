import sys

def bubbleSort(arr):
    l = len(arr)
    for i in range(0, l):
        for j in range(i + 1, l):
            if (arr[i] > arr[j]):
                print('swapping indicies [' + str(i) + ', ' + str(j) + ']')
                temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
                print(str(arr))

if __name__ == '__main__':
    arr = [10, 5, 2, 18, 92, 12, -1, 48]

    bubbleSort(arr)

    print(str(arr))
