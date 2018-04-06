package misc;

/**
 * Created by kirn on 4/4/18.
 */
public class MiscellaneousTests {
    private static int maxValueContiguousSubsequence(int[] values) {
        int maxSum = 0;
        int maxI = -1, maxJ = -1;

        for (int i = 0; i < values.length; i++) {
            int currentSum = 0;
            for (int j = i; j < values.length; j++) {
                currentSum += values[j];
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    maxI = i;
                    maxJ = j;
                }
            }
        }

        System.out.println("maxI = " + maxI + "; maxJ = " + maxJ);

        return maxSum;
    }

    public static void main(String[] args) {
        int[] values = { -2, 11, -4, 13, -5, 2 };

        System.out.println(maxValueContiguousSubsequence(values));
    }
}
