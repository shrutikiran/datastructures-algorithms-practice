package tree.traversals;

import tree.BinaryTreeNode;
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

        {
            BinaryTreeNode node = new BinaryTreeNode(1,
                    new BinaryTreeNode(2,
                            null,
                            new BinaryTreeNode(4, null, null)),
                    new BinaryTreeNode(3,
                            new BinaryTreeNode(12, null, null),
                            new BinaryTreeNode(5, null, null)));
            System.out.println("Is Tree foldable? " + TreeHelper.isFoldableTree(node));
            System.out.println();
        }

        {
            BinaryTreeNode node = new BinaryTreeNode(1,
                    new BinaryTreeNode(2,
                            null,
                            new BinaryTreeNode(4, null, null)),
                    new BinaryTreeNode(3,
                            new BinaryTreeNode(12, null, null),
                            new BinaryTreeNode(5, null, null)));
            BinaryTreeNode node1 = node.right.left;
            BinaryTreeNode node2 = node.right.right;

            BinaryTreeNode lca = TreeHelper.findLeastCommonAncestor(node, node1, node2);
            System.out.println("Least Common Ancestor of " + node1.value + " and " + node2.value + ": " + ((lca != null) ? lca.value : "null"));
            System.out.println();
        }
    }
}
