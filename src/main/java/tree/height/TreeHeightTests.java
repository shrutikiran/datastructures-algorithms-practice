package tree.height;

import java.util.*;

import tree.BinaryTreeNode;
import tree.TreeHelper;

public class TreeHeightTests {
    private static BinaryTreeNode createTree() {
        BinaryTreeNode root = new BinaryTreeNode(1,
                new BinaryTreeNode(2,
                        new BinaryTreeNode(4,
                                new BinaryTreeNode(8,
                                        new BinaryTreeNode(12, null, null),
                                        new BinaryTreeNode(13, null, null)),
                                null),
                        new BinaryTreeNode(5,
                                null,
                                null)),
                new BinaryTreeNode(3,
                        new BinaryTreeNode(6,
                                new BinaryTreeNode(10,
                                        null,
                                        new BinaryTreeNode(14, null, null)),
                                null),
                        new BinaryTreeNode(7,
                                new BinaryTreeNode(11, null, null),
                                null)));

        return root;
    }

    private static BinaryTreeNode createBalancedTree() {
        BinaryTreeNode root = new BinaryTreeNode(1,
                new BinaryTreeNode(2,
                        new BinaryTreeNode(4,
                                new BinaryTreeNode(6, null, null),
                                null),
                        null),
                new BinaryTreeNode(3,
                        null,
                        new BinaryTreeNode(5,null,null)));

        return root;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = createTree();

        System.out.println("Max Height (Recursive) = " + TreeHelper.maxHeight_Recursive(root));
        System.out.println("Max Height (Iterative) = " + TreeHelper.maxHeight_Iterative(root));

        System.out.println("Min Height (Recursive) = " + TreeHelper.minHeight_Recursive(root));
        System.out.println("Min Height (Iterative) = " + TreeHelper.minHeight_Iterative(root));

        {
            root = createBalancedTree();
            System.out.println("Is Balanced (Recursive) = " + TreeHelper.isBalancedTree_Recursive(root));
            System.out.println("Is Balanced (Iterative) = " + TreeHelper.isBalancedTree_Iterative(root));
        }
    }
}
