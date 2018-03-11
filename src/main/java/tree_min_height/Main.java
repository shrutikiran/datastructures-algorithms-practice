package tree_min_height;

import java.util.*;

public class Main {

    private static class Node {
        private int value;
        private Node left, right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private static Node createTree() {

        Node root = new Node(1,
                new Node(2,
                        new Node(4,
                                new Node(8,
                                        new Node(12, null, null),
                                        new Node(13, null, null)),
                                null),
                        new Node(5,
                                new Node(9, null, null),
                                null)),
                new Node(3,
                        new Node(6,
                                null,
                                new Node(10,
                                        null,
                                        new Node(14, null, null))),
                        new Node(7,
                                new Node(11, null, null),
                                null)));

        return root;
    }

    public static void main(String[] args) {
        Node root = createTree();

        System.out.println("min height = " + getMinHeight_IterativeDFS(root));
    }

    private static boolean isLeafNode(Node node) {
        if (node == null) {
            return false;
        }

        return (node.left == null && node.right == null);
    }

    private static void printStack(Stack<Node> stack) {
        System.out.print("\nStack = [ ");
        for (Node n : stack) {
            System.out.print(n.value + " ");
        }
        System.out.println("]");
    }

    private static Node getTopNode(Stack<Node> stack) {
        if (stack == null || stack.isEmpty()) {
            return null;
        }

        return stack.peek();
    }

    private static boolean isLastChild(Node parent, Node child) {
        if (parent == null) {
            return false;
        }

        return (parent.right == null || parent.right == child);
    }

    private static int getMinHeight_IterativeDFS(Node root) {
        if (root == null) {
            return 0;
        }

        int minHeight = -1;
        int maxHeight = -1;
        Stack<Node> nodeStack = new Stack<>();

        nodeStack.push(root);

        while (!nodeStack.isEmpty()) {
            Node curNode = getTopNode(nodeStack);
            while (curNode != null && curNode.left != null) {
                nodeStack.push(curNode.left);
                curNode = curNode.left;
            }

            curNode = getTopNode(nodeStack);

            if (isLeafNode(curNode)) {
                if (maxHeight < 0 || maxHeight < nodeStack.size()) {
                    maxHeight = nodeStack.size();
                }
                if (minHeight < 0 || minHeight > nodeStack.size()) {
                    minHeight = nodeStack.size();
                }

                printStack(nodeStack);

                Node topNode;
                do {
                    curNode = nodeStack.pop();
                    printStack(nodeStack);

                    topNode = getTopNode(nodeStack);
                    if (topNode == null) {
                        break;
                    }
                    if (!isLastChild(topNode, curNode)) {
                        nodeStack.push(topNode.right);
                        printStack(nodeStack);
                        break;
                    }
                } while (true);
            } else {
                nodeStack.push(curNode.right);
                printStack(nodeStack);
            }
        }

        System.out.println("Min Height = " + minHeight);
        System.out.println("Max Height = " + maxHeight);

        return minHeight;
    }

    private static int getMinHeight_IterativeBFS(Node root) {
        if (root == null) {
            return 0;
        }

        int minHeight = 0;
        int height = 0;
        List<Node> levelNodes = new ArrayList<>();
        List<Node> childNodes = new ArrayList<>();

        levelNodes.add(root);
        height = 0;

        while (!levelNodes.isEmpty()) {
            Node curNode = levelNodes.remove(0);

            if (isLeafNode(curNode)) {
                if (minHeight <= 0 || height < minHeight) {
                    minHeight = height + 1;
                }
            }

            if (curNode.left != null) {
                childNodes.add(curNode.left);
            }
            if (curNode.right != null) {
                childNodes.add(curNode.right);
            }

            if (levelNodes.isEmpty()) {
                height++;
                levelNodes.addAll(childNodes);
                childNodes.clear();
            }
        }

        System.out.println("height = " + height);

        return minHeight;
    }
}
