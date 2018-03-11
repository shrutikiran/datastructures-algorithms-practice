package tree.traversals;

import java.util.Stack;

import tree.Node;
import tree.TreeHelper;

public class Main {

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
                                new Node(10,
                                        null,
                                        new Node(14, null, null)),
                                null),
                        new Node(7,
                                new Node(11, null, null),
                                null)));

        return root;
    }

    public static void main(String[] args) {

        Node root = createTree();

        System.out.print("Pre-order (Recursive) ");preOrder_Recursive(root);System.out.println();
        System.out.print("Pre-order (Iterative) ");preOrder_Iterative(root);System.out.println();

        System.out.print("In-order (Recursive) ");inOrder_Recursive(root);System.out.println();
        System.out.print("In-order (Iterative) ");inOrder_Iterative(root);System.out.println();

        System.out.print("Post-order (Recursive) ");postOrder_Recursive(root);System.out.println();
        System.out.print("Post-order (Iterative) ");postOrder_Iterative(root);System.out.println();
    }

    private static void preOrder_Recursive(Node root) {
        if (root == null) {
            return;
        }

        System.out.print(root.value + " ");
        preOrder_Recursive(root.left);
        preOrder_Recursive(root.right);
    }

    private static void inOrder_Recursive(Node root) {
        if (root == null) {
            return;
        }

        inOrder_Recursive(root.left);
        System.out.print(root.value + " ");
        inOrder_Recursive(root.right);
    }

    private static void postOrder_Recursive(Node root) {
        if (root == null) {
            return;
        }

        postOrder_Recursive(root.left);
        postOrder_Recursive(root.right);
        System.out.print(root.value + " ");
    }

    // TODO: Make the loop wrt node, not stack
    private static void postOrder_Iterative(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> nodeStack = new Stack<>();

        nodeStack.push(root);

        while (!nodeStack.isEmpty()) {
            Node curNode = getTopNode(nodeStack);
            while (curNode != null && curNode.left != null) {
                nodeStack.push(curNode.left);
                curNode = curNode.left;
            }

            curNode = getTopNode(nodeStack);

            if (TreeHelper.isLeafNode(curNode)) {
                Node topNode;
                do {
                    curNode = nodeStack.pop();
                    System.out.print(curNode.value + " ");

                    topNode = getTopNode(nodeStack);
                    if (topNode == null) {
                        break;
                    }
                    if (!TreeHelper.isLastChild(topNode, curNode)) {
                        nodeStack.push(topNode.right);
                        break;
                    }
                } while (true);
            } else {
                nodeStack.push(curNode.right);
            }
        }
    }

    // TODO: Get it to work
    private static void inOrder_Iterative(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> nodeStack = new Stack<>();

        nodeStack.push(root);

        while (!nodeStack.isEmpty()) {
            Node curNode = getTopNode(nodeStack);
            while (curNode != null && curNode.left != null) {
                nodeStack.push(curNode.left);
                curNode = curNode.left;
            }

            curNode = getTopNode(nodeStack);

            if (TreeHelper.isLeafNode(curNode)) {
                Node topNode;
                do {
                    curNode = nodeStack.pop();
                    System.out.print(curNode.value + " ");

                    topNode = getTopNode(nodeStack);
                    if (topNode == null) {
                        break;
                    }
                    System.out.print(topNode.value + " ");
                    if (!TreeHelper.isLastChild(topNode, curNode)) {
                        nodeStack.push(topNode.right);
                        break;
                    }
                } while (true);
            } else {
                nodeStack.push(curNode.right);
            }
        }
    }

    // TODO: Make the loop wrt node, not stack
    private static void preOrder_Iterative(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> nodeStack = new Stack<>();

        nodeStack.push(root);

        while (!nodeStack.isEmpty()) {
            Node curNode = getTopNode(nodeStack);
            while (curNode != null && curNode.left != null) {
                System.out.print(curNode.value + " ");
                nodeStack.push(curNode.left);
                curNode = curNode.left;
            }

            curNode = getTopNode(nodeStack);
            System.out.print(curNode.value + " ");

            if (TreeHelper.isLeafNode(curNode)) {
                Node topNode;
                do {
                    curNode = nodeStack.pop();

                    topNode = getTopNode(nodeStack);
                    if (topNode == null) {
                        break;
                    }
                    if (!TreeHelper.isLastChild(topNode, curNode)) {
                        nodeStack.push(topNode.right);
                        break;
                    }
                } while (true);
            } else {
                nodeStack.push(curNode.right);
            }
        }
    }

    private static Node getTopNode(Stack<Node> stack) {
        if (stack == null || stack.isEmpty()) {
            return null;
        }

        return stack.peek();
    }
}
