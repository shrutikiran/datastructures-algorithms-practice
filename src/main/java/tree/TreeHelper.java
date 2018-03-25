package tree;

import java.util.*;

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

    public static void levelOrder_Iterative(BinaryTreeNode root, INodeOperation nodeOperation) {
        if (root == null) {
            return;
        }

        Queue<BinaryTreeNode> nodes = new LinkedList<>();

        nodes.add(root);
        while (false == nodes.isEmpty()) {
            BinaryTreeNode node = nodes.remove();

            nodeOperation.run(node);

            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
    }

    public static void levelWiseTraversal(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        Queue<BinaryTreeNode> nodes = new LinkedList<>();

        nodes.add(root);
        do {
            Queue<BinaryTreeNode> childNodes = new LinkedList<>();

            while (false == nodes.isEmpty()) {
                BinaryTreeNode node = nodes.remove();
                System.out.print(node.value + " ");

                if (node.left != null) {
                    childNodes.add(node.left);
                }
                if (node.right != null) {
                    childNodes.add(node.right);
                }
            }

            if (childNodes.isEmpty()) {
                break;
            }

            nodes.addAll(childNodes);
            childNodes.clear();
            System.out.println();
        } while (true);
    }

    public static void levelWiseTraversal_SingleQueue(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        Queue<BinaryTreeNode> nodes = new LinkedList<>();

        nodes.add(root);
        nodes.add(null);
        while (false == nodes.isEmpty()) {
            BinaryTreeNode node = nodes.remove();
            if (node == null) {
                if (false == nodes.isEmpty()) {
                    nodes.add(null);
                    System.out.println();
                }
                continue;
            }

            System.out.print(node.value + " ");

            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
    }

    public static int maxHeight_Recursive(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = maxHeight_Recursive(root.left);
        int rightHeight = maxHeight_Recursive(root.right);
        return (1 + ((leftHeight > rightHeight) ? leftHeight : rightHeight));
    }

    public static int maxHeight_Iterative(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<BinaryTreeNode> nodes = new LinkedList<>();

        int height = 1;
        int maxHeight = -1;

        nodes.add(root);
        nodes.add(null);

        while (false == nodes.isEmpty()) {
            BinaryTreeNode node = nodes.remove();
            if (node == null) {
                if (false == nodes.isEmpty()) {
                    nodes.add(null);
                    height++;

                    if (maxHeight < 0 || maxHeight < height) {
                        maxHeight = height;
                    }
                }
                continue;
            }

            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }

        return maxHeight;
    }

    public static int minHeight_Recursive(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = maxHeight_Recursive(root.left);
        int rightHeight = maxHeight_Recursive(root.right);
        return (1 + ((leftHeight > rightHeight) ? rightHeight : leftHeight));
    }

    public static int minHeight_Iterative(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<BinaryTreeNode> nodes = new LinkedList<>();

        int height = 1;
        int minHeight = -1;

        nodes.add(root);
        nodes.add(null);

        while (false == nodes.isEmpty()) {
            BinaryTreeNode node = nodes.remove();
            if (node == null) {
                if (false == nodes.isEmpty()) {
                    nodes.add(null);
                    height++;
                }
                continue;
            }

            if (isLeafNode(node)) {
                minHeight = height;
                return minHeight;
            }

            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }

        return height;
    }

    public static boolean isFoldableTree(BinaryTreeNode root) {
        if (root == null) {
            return false;
        }

        return isFoldableTreeInternal(root.left, root.right);
    }

    private static boolean isFoldableTreeInternal(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        if (root1.left == null && root2.right == null) {
            if (root1.right == null && root2.left == null) {
                return true;
            }

            if (root1.right == null || root2.left == null) {
                return false;
            }

            return isFoldableTreeInternal(root1.left, root2.right);
        }

        if (root1.left == null || root2.right == null) {
            return false;
        }

        return isFoldableTreeInternal(root1.left, root2.right);
    }
}
