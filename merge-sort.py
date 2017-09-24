import sys

def merge(arr, startIx, endIx, mid):
    mergeIx = startIx
    lIx = 0
    rIx = 0

    lArr = arr[startIx:mid]
    rArr = arr[mid:endIx]

    while (lIx < len(lArr) and rIx < len(rArr)):
        if (lArr[lIx] <= rArr[rIx]):
            arr[mergeIx] = lArr[lIx]
            lIx += 1
        else:
            arr[mergeIx] = rArr[rIx]
            rIx += 1
        mergeIx += 1

    for x in range(lIx, len(lArr)):
        arr[mergeIx] = lArr[x]
        mergeIx += 1

    for x in range(rIx, len(rArr)):
        arr[mergeIx] = rArr[x]
        mergeIx += 1

    print('merged array = ' + str(arr))

def mergeSort(arr, startIx, endIx):
    print('arr = ' + str(arr) + ', startIx = ' + str(startIx) + ', endIx = ' + str(endIx))

    if (endIx - startIx <= 1):
        return

    mid = (startIx + endIx) / 2

    mergeSort(arr, startIx, mid)
    mergeSort(arr, mid, endIx)
    merge(arr, startIx, endIx, mid)

if __name__ == '__main__':
    arr = [10, 5, 2, 18, 92, 12, -1, 48]

    mergeSort(arr, 0, len(arr))

    print(str(arr))
