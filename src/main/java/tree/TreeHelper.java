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

    public static void postOrder_Iterative(BinaryTreeNode root, INodeOperation nodeOperation) {
        if (root == null) {
            return;
        }

        Stack<BinaryTreeNode> nodeStack = new Stack<>();

        while (true) {
            while (root != null) {
                nodeStack.push(root);
                root = root.left;
            }

            if (nodeStack.isEmpty()) {
                break;
            }

            BinaryTreeNode topNode = getTopNode(nodeStack);
            if (false == isLeafNode(topNode)) {
                root = topNode.right;
                continue;
            }

            BinaryTreeNode node = null;

            for (topNode = getTopNode(nodeStack); isLastChild(topNode, node); topNode = getTopNode(nodeStack)) {
                node = nodeStack.pop();
                nodeOperation.run(node);
            }

            if (nodeStack.isEmpty()) {
                root = null;
            } else {
                root = topNode.right;
            }
        }
    }

    public static void inOrder_Iterative(BinaryTreeNode root, INodeOperation nodeOperation) {
        if (root == null) {
            return;
        }

        Stack<BinaryTreeNode> nodeStack = new Stack<>();

        while (true) {
            while (root != null) {
                nodeStack.push(root);
                root = root.left;
            }

            if (nodeStack.isEmpty()) {
                break;
            }

            root = nodeStack.pop();
            nodeOperation.run(root);
            root = root.right;
        }
    }

    public static void preOrder_Iterative(BinaryTreeNode root, INodeOperation nodeOperation) {
        if (root == null) {
            return;
        }

        Stack<BinaryTreeNode> nodeStack = new Stack<>();

        while (true) {
            while (root != null) {
                nodeOperation.run(root);
                nodeStack.push(root);
                root = root.left;
            }

            if (nodeStack.isEmpty()) {
                break;
            }

            root = nodeStack.pop();
            root = root.right;
        }
    }

    public static BinaryTreeNode getTopNode(Stack<BinaryTreeNode> stack) {
        if (stack == null || stack.isEmpty()) {
            return null;
        }

        return stack.peek();
    }
}
