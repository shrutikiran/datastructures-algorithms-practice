package tree.tree_height;

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

        System.out.println("min height = " + getMinHeight_IterativeDFS(root));
    }

    private static void printStack(Stack<BinaryTreeNode> stack) {
        System.out.print("\nStack = [ ");
        for (BinaryTreeNode n : stack) {
            System.out.print(n.value + " ");
        }
        System.out.println("]");
    }

    private static BinaryTreeNode getTopNode(Stack<BinaryTreeNode> stack) {
        if (stack == null || stack.isEmpty()) {
            return null;
        }

        return stack.peek();
    }

    private static int getMinHeight_IterativeDFS(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }

        int minHeight = -1;
        int maxHeight = -1;
        Stack<BinaryTreeNode> binaryTreeNodeStack = new Stack<>();

        binaryTreeNodeStack.push(root);

        while (!binaryTreeNodeStack.isEmpty()) {
            BinaryTreeNode curBinaryTreeNode = getTopNode(binaryTreeNodeStack);
            while (curBinaryTreeNode != null && curBinaryTreeNode.left != null) {
                binaryTreeNodeStack.push(curBinaryTreeNode.left);
                printStack(binaryTreeNodeStack);
                curBinaryTreeNode = curBinaryTreeNode.left;
            }

            curBinaryTreeNode = getTopNode(binaryTreeNodeStack);

            if (TreeHelper.isLeafNode(curBinaryTreeNode)) {
                if (maxHeight < 0 || maxHeight < binaryTreeNodeStack.size()) {
                    maxHeight = binaryTreeNodeStack.size();
                }
                if (minHeight < 0 || minHeight > binaryTreeNodeStack.size()) {
                    minHeight = binaryTreeNodeStack.size();
                }

                BinaryTreeNode topBinaryTreeNode;
                do {
                    curBinaryTreeNode = binaryTreeNodeStack.pop();
                    printStack(binaryTreeNodeStack);

                    topBinaryTreeNode = getTopNode(binaryTreeNodeStack);
                    if (topBinaryTreeNode == null) {
                        break;
                    }
                    if (!TreeHelper.isLastChild(topBinaryTreeNode, curBinaryTreeNode)) {
                        binaryTreeNodeStack.push(topBinaryTreeNode.right);
                        printStack(binaryTreeNodeStack);
                        break;
                    }
                } while (true);
            } else {
                binaryTreeNodeStack.push(curBinaryTreeNode.right);
                printStack(binaryTreeNodeStack);
            }
        }

        System.out.println("Min Height = " + minHeight);
        System.out.println("Max Height = " + maxHeight);

        return minHeight;
    }

    private static int getMinHeight_IterativeBFS(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }

        int minHeight = 0;
        int height = 0;
        List<BinaryTreeNode> levelBinaryTreeNodes = new ArrayList<>();
        List<BinaryTreeNode> childBinaryTreeNodes = new ArrayList<>();

        levelBinaryTreeNodes.add(root);
        height = 0;

        while (!levelBinaryTreeNodes.isEmpty()) {
            BinaryTreeNode curBinaryTreeNode = levelBinaryTreeNodes.remove(0);

            if (TreeHelper.isLeafNode(curBinaryTreeNode)) {
                if (minHeight <= 0 || height < minHeight) {
                    minHeight = height + 1;
                }
            }

            if (curBinaryTreeNode.left != null) {
                childBinaryTreeNodes.add(curBinaryTreeNode.left);
            }
            if (curBinaryTreeNode.right != null) {
                childBinaryTreeNodes.add(curBinaryTreeNode.right);
            }

            if (levelBinaryTreeNodes.isEmpty()) {
                height++;
                levelBinaryTreeNodes.addAll(childBinaryTreeNodes);
                childBinaryTreeNodes.clear();
            }
        }

        System.out.println("height = " + height);

        return minHeight;
    }
}
