package sort;

/**
 * Created by kirn on 4/12/18.
 */
public class SortHelper {
    public static <T> void printArray(T[] array) {
        if (array == null || array.length <= 0) {
            return;
        }

        System.out.print("[ ");
        for (T a : array) {
            System.out.print(a.toString() + " ");
        }
        System.out.println("]");
    }

    public static <T extends Comparable<T>> T[] selectionSort(T[] input) {
        if (input == null || input.length <= 0) {
            return null;
        }

        for (int i = 0; i < input.length; i++) {
            int minIx = i;
            for (int j = i + 1; j < input.length; j++) {
                if (input[j].compareTo(input[minIx]) < 0) {
                    minIx = j;
                }
            }
            if (minIx != i) {
                T temp = input[i];
                input[i] = input[minIx];
                input[minIx] = temp;
            }
        }

        return input;
    }

    public static <T extends Comparable<T>> T[] binarySort(T[] input) {
        if (input == null || input.length <= 0) {
            return null;
        }

        for (int startIx = 0, endIx = input.length - 1; startIx < endIx; startIx++, endIx--) {
            int minIx = startIx, maxIx = endIx;

            for (int i = startIx + 1; i <= endIx; i++) {
                if (input[i].compareTo(input[minIx]) < 0) {
                    minIx = i;
                }
                if (input[i].compareTo(input[maxIx]) > 0) {
                    maxIx = i;
                }
            }
            if (minIx != startIx) {
                T temp = input[startIx];
                input[startIx] = input[minIx];
                input[minIx] = temp;
            }
            if (maxIx != startIx) {
                T temp = input[endIx];
                input[endIx] = input[maxIx];
                input[maxIx] = temp;
            }
        }

        return input;
    }
}
