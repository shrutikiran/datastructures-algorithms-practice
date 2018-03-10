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

        System.out.println("min height = " + getMinHeight_IterativeBFS(root));
    }

    private static int getMinHeight_IterativeDFS(Node root) {
        if (root == null) {
            return 0;
        }

        int minHeight = -1;
        Node curNode = root;
        Stack<Node> nodeStack = new Stack<>();

        while (curNode != null || !nodeStack.isEmpty()) {
            while (curNode != null) {
                nodeStack.push(curNode);
                curNode = curNode.left;
            }

            curNode = nodeStack.peek();
            if (curNode.left == null && curNode.right == null) {
                int height = nodeStack.size();
                if (minHeight < 0 || height < minHeight) {
                    minHeight = height;
                }
            }

            curNode = nodeStack.pop();
            System.out.print(curNode.value + "(" + minHeight + ") ");

            curNode = curNode.right;
        }

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

            if (curNode.left == null && curNode.right == null) {
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
