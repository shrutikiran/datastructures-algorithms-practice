import sys

def nrZeros(num):
    nrZero = 0
    for c in str(num):
        if c == '0':
            nrZero += 1
    return nrZero

def maxZeros(nums):
    maxZeros = 0
    maxZeroNum = -1
    for i in range(0, len(nums)):
        val = nrZeros(nums[i])
        if (val > maxZeros):
            maxZeros = val
            maxZeroNum = nums[i]
    return maxZeroNum

if __name__ == '__main__':
    nrTests = sys.stdin.readline()
    nrTests = int(nrTests)

    arr = []

    for i in range(0, nrTests):
        nums = sys.stdin.readline()
        nums = nums.strip().split(' ')
        print('number with max zeros = ' + str(maxZeros(nums)))