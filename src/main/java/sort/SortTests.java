package sort;

import java.util.Collections;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by kirn on 4/12/18.
 */
public class SortTests {
    public static Integer[] createRandomArray(int nrElements, int maxValue) {
        Random r = new Random(System.currentTimeMillis());
        List<Integer> elements = new ArrayList(nrElements);

        for (int i = 0; i < nrElements; i++) {
            elements.add(r.nextInt(maxValue));
        }

        return elements.toArray(new Integer[nrElements]);
    }

    public static void main(String[] args) {
        Integer[] input = createRandomArray(100000, 100);

        System.out.print("Input: ");SortHelper.printArray(input);

        {
            long delta;

            System.out.print("Selection Sort: ");
            delta = System.currentTimeMillis();
            SortHelper.printArray(SortHelper.selectionSort(input));
            delta = System.currentTimeMillis() - delta;
            System.out.println("delta = " + delta);
        }

        {
            long delta;

            System.out.print("Binary Sort: ");
            delta = System.currentTimeMillis();
            SortHelper.printArray(SortHelper.binarySort(input));
            delta = System.currentTimeMillis() - delta;
            System.out.println("delta = " + delta);
        }
    }
}
