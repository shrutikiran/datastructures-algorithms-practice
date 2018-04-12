package sort;

/**
 * Created by kirn on 4/12/18.
 */
public class SortTests {
    public static void main(String[] args) {
        Integer[] input = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };

        System.out.print("Input: ");SortHelper.printArray(input);

        System.out.print("Selection Sort: ");
        SortHelper.printArray(SortHelper.selectionSort(input));

        System.out.print("Binary Sort: ");
        SortHelper.printArray(SortHelper.binarySort(input));
    }
}
