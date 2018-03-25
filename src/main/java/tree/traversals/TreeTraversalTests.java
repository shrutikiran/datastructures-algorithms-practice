package tree.traversals;

import tree.BinaryTreeNode;
import tree.INodeOperation;
import tree.TreeHelper;

public class TreeTraversalTests {

    private static BinaryTreeNode createTree() {
        BinaryTreeNode root = new BinaryTreeNode(1,
                new BinaryTreeNode(2,
                        new BinaryTreeNode(4,
                                new BinaryTreeNode(8,
                                        new BinaryTreeNode(12, null, null),
                                        new BinaryTreeNode(13, null, null)),
                                null),
                        new BinaryTreeNode(5,
                                new BinaryTreeNode(9, null, null),
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

    public static void main(String[] args) {

        BinaryTreeNode root = createTree();

        System.out.print("Pre-order (Recursive) ");
        TreeHelper.preOrder_Recursive(root, node -> System.out.print(node.value + " "));
        System.out.println();

        System.out.print("Pre-order (Iterative) ");
        TreeHelper.preOrder_Iterative(root, node -> System.out.print(node.value + " "));
        System.out.println();

        System.out.print("In-order (Recursive) ");
        TreeHelper.inOrder_Recursive(root, node -> System.out.print(node.value + " "));
        System.out.println();

        System.out.print("In-order (Iterative) ");
        TreeHelper.inOrder_Iterative(root, node -> System.out.print(node.value + " "));
        System.out.println();

        System.out.print("Post-order (Recursive) ");
        TreeHelper.postOrder_Recursive(root, node -> System.out.print(node.value + " "));
        System.out.println();

        System.out.print("Post-order (Iterative) ");
        TreeHelper.postOrder_Iterative(root, node -> System.out.print(node.value + " "));
        System.out.println();

        System.out.print("Level-order (Iterative) ");
        TreeHelper.levelOrder_Iterative(root, node -> System.out.print(node.value + " "));
        System.out.println();

        System.out.print("Level-wise Traversal (Iterative) ");
        TreeHelper.levelWiseTraversal(root);
        System.out.println();

        System.out.print("Level-wise Traversal (Iterative, with single queue) ");
        TreeHelper.levelWiseTraversal_SingleQueue(root);
        System.out.println();
    }
}
