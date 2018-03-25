package tree;

import java.util.Stack;

public class TreeHelper {
    public static boolean isLastChild(BinaryTreeNode parent, BinaryTreeNode child) {
        if (parent == null) {
            return false;
        }

        return (parent.right == null || parent.right == child);
    }

    public static boolean isLeafNode(BinaryTreeNode binaryTreeNode) {
        if (binaryTreeNode == null) {
            return false;
        }

        return (binaryTreeNode.left == null && binaryTreeNode.right == null);
    }

    public static void preOrder_Recursive(BinaryTreeNode root, INodeOperation nodeOperation) {
        if (root == null) {
            return;
        }

        nodeOperation.run(root);

        preOrder_Recursive(root.left, nodeOperation);

        preOrder_Recursive(root.right, nodeOperation);
    }

    public static void inOrder_Recursive(BinaryTreeNode root, INodeOperation nodeOperation) {
        if (root == null) {
            return;
        }

        inOrder_Recursive(root.left, nodeOperation);

        nodeOperation.run(root);

        inOrder_Recursive(root.right, nodeOperation);
    }

    public static void postOrder_Recursive(BinaryTreeNode root, INodeOperation nodeOperation) {
        if (root == null) {
            return;
        }

        postOrder_Recursive(root.left, nodeOperation);

        postOrder_Recursive(root.right, nodeOperation);

        nodeOperation.run(root);
    }

    // TODO: Make the loop wrt node, not stack
    public static void postOrder_Iterative(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        Stack<BinaryTreeNode> binaryTreeNodeStack = new Stack<>();

        binaryTreeNodeStack.push(root);

        while (!binaryTreeNodeStack.isEmpty()) {
            BinaryTreeNode curBinaryTreeNode = getTopNode(binaryTreeNodeStack);
            while (curBinaryTreeNode != null && curBinaryTreeNode.left != null) {
                binaryTreeNodeStack.push(curBinaryTreeNode.left);
                curBinaryTreeNode = curBinaryTreeNode.left;
            }

            curBinaryTreeNode = getTopNode(binaryTreeNodeStack);

            if (isLeafNode(curBinaryTreeNode)) {
                BinaryTreeNode topBinaryTreeNode;
                do {
                    curBinaryTreeNode = binaryTreeNodeStack.pop();
                    System.out.print(curBinaryTreeNode.value + " ");

                    topBinaryTreeNode = getTopNode(binaryTreeNodeStack);
                    if (topBinaryTreeNode == null) {
                        break;
                    }
                    if (!isLastChild(topBinaryTreeNode, curBinaryTreeNode)) {
                        binaryTreeNodeStack.push(topBinaryTreeNode.right);
                        break;
                    }
                } while (true);
            } else {
                binaryTreeNodeStack.push(curBinaryTreeNode.right);
            }
        }
    }

    // TODO: Get it to work
    public static void inOrder_Iterative(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        Stack<BinaryTreeNode> binaryTreeNodeStack = new Stack<>();

        binaryTreeNodeStack.push(root);

        while (!binaryTreeNodeStack.isEmpty()) {
            BinaryTreeNode curBinaryTreeNode = getTopNode(binaryTreeNodeStack);
            while (curBinaryTreeNode != null && curBinaryTreeNode.left != null) {
                binaryTreeNodeStack.push(curBinaryTreeNode.left);
                curBinaryTreeNode = curBinaryTreeNode.left;
            }

            curBinaryTreeNode = getTopNode(binaryTreeNodeStack);

            if (isLeafNode(curBinaryTreeNode)) {
                BinaryTreeNode topBinaryTreeNode;
                do {
                    curBinaryTreeNode = binaryTreeNodeStack.pop();
                    System.out.print(curBinaryTreeNode.value + " ");

                    topBinaryTreeNode = getTopNode(binaryTreeNodeStack);
                    if (topBinaryTreeNode == null) {
                        break;
                    }
                    System.out.print(topBinaryTreeNode.value + " ");
                    if (!isLastChild(topBinaryTreeNode, curBinaryTreeNode)) {
                        binaryTreeNodeStack.push(topBinaryTreeNode.right);
                        break;
                    }
                } while (true);
            } else {
                binaryTreeNodeStack.push(curBinaryTreeNode.right);
            }
        }
    }

    // TODO: Make the loop wrt node, not stack
    public static void preOrder_Iterative(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        Stack<BinaryTreeNode> binaryTreeNodeStack = new Stack<>();

        binaryTreeNodeStack.push(root);

        while (!binaryTreeNodeStack.isEmpty()) {
            BinaryTreeNode curBinaryTreeNode = getTopNode(binaryTreeNodeStack);
            while (curBinaryTreeNode != null && curBinaryTreeNode.left != null) {
                System.out.print(curBinaryTreeNode.value + " ");
                binaryTreeNodeStack.push(curBinaryTreeNode.left);
                curBinaryTreeNode = curBinaryTreeNode.left;
            }

            curBinaryTreeNode = getTopNode(binaryTreeNodeStack);
            System.out.print(curBinaryTreeNode.value + " ");

            if (isLeafNode(curBinaryTreeNode)) {
                BinaryTreeNode topBinaryTreeNode;
                do {
                    curBinaryTreeNode = binaryTreeNodeStack.pop();

                    topBinaryTreeNode = getTopNode(binaryTreeNodeStack);
                    if (topBinaryTreeNode == null) {
                        break;
                    }
                    if (!isLastChild(topBinaryTreeNode, curBinaryTreeNode)) {
                        binaryTreeNodeStack.push(topBinaryTreeNode.right);
                        break;
                    }
                } while (true);
            } else {
                binaryTreeNodeStack.push(curBinaryTreeNode.right);
            }
        }
    }

    public static BinaryTreeNode getTopNode(Stack<BinaryTreeNode> stack) {
        if (stack == null || stack.isEmpty()) {
            return null;
        }

        return stack.peek();
    }
}
