package tree.bst;

import apple.laf.JRSUIUtils;
import tree.BinaryTreeNode;
import tree.TreeHelper;

import java.util.List;
import java.util.ArrayList;

public class BSTTests {

    public static void main(String[] args) {
        {
            /* Make a binary search tree out of a sorted array */

            System.out.println("Binary Search Tree from a sorted array: ");

            int[] input = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

            BinaryTreeNode root = TreeHelper.makeBinarySearchTree(input);

            List<Integer> output = new ArrayList<>();

            TreeHelper.inOrder_Recursive(root, (node) -> output.add(node.value));

            System.out.println(output);

            for (int i = 0; i < output.size(); i++) {
                if (input[i] != output.get(i)) {
                    System.out.println("Not same");
                }
            }

            System.out.println();
        }
    }
}
