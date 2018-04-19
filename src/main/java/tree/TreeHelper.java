package tree;

import java.util.*;

public class TreeHelper {
    public static boolean isLastChild(BinaryTreeNode parent, BinaryTreeNode child) {
        if (parent == null) {
            return false;
        }

        // if parent node' right subtree is null or if this node is the right subtree, then its the last child in a binary tree
        return (parent.right == null || parent.right == child);
    }

    public static boolean isLeafNode(BinaryTreeNode binaryTreeNode) {
        if (binaryTreeNode == null) {
            return false;
        }

        // true, if both the right and left subtrees are null
        return (binaryTreeNode.left == null && binaryTreeNode.right == null);
    }

    // Time Complexity: O(n) since it visits all n nodes. Space Complexity: O(n) since it requires that many recursive calls.
    public static void preOrder_Recursive(BinaryTreeNode root, INodeOperation nodeOperation) {
        if (root == null) {
            return;
        }

        // first, operate on the current node ...
        nodeOperation.run(root);

        // ... then the left subtree...
        preOrder_Recursive(root.left, nodeOperation);

        // ... and, then the right subtree.
        preOrder_Recursive(root.right, nodeOperation);
    }

    // Time Complexity: O(n) since it visits all n nodes. Space Complexity: O(n) since it requires that many recursive calls.
    public static void inOrder_Recursive(BinaryTreeNode root, INodeOperation nodeOperation) {
        if (root == null) {
            return;
        }

        // traverse the left subtree ..
        inOrder_Recursive(root.left, nodeOperation);

        // ... then, operate on the current node..
        nodeOperation.run(root);

        // ... and then the right subtree...
        inOrder_Recursive(root.right, nodeOperation);
    }

    // Time Complexity: O(n) since it visits all n nodes. Space Complexity: O(n) since it requires that many recursive calls.
    public static void postOrder_Recursive(BinaryTreeNode root, INodeOperation nodeOperation) {
        if (root == null) {
            return;
        }

        // first, traverse the left subtree ..
        postOrder_Recursive(root.left, nodeOperation);

        // ... and, then the right subtree ..
        postOrder_Recursive(root.right, nodeOperation);

        // ... and, then operate upon the current node
        nodeOperation.run(root);
    }

    // Time Complexity: O(n) since it visits all n nodes. Space Complexity: O(n) since it requires that many entries in the stack
    public static void postOrder_Iterative(BinaryTreeNode root, INodeOperation nodeOperation) {
        if (root == null) {
            return;
        }

        // create a stack explicitly, since we do not use recursion..
        Stack<BinaryTreeNode> nodeStack = new Stack<>();

        while (true) {
            // drill down the left subtree, collecting the nodes into the stack..,
            while (root != null) {
                nodeStack.push(root);
                root = root.left;
            }

            // break out if the stack is empty => nothing to process.
            if (nodeStack.isEmpty()) {
                break;
            }

            // we have hit past the left most node... so, peek into the topmost node in the stack.
            BinaryTreeNode topNode = getTopNode(nodeStack);

            // ... if that node is not a leaf node, then we need to take care of the right subtree...
            if (false == isLeafNode(topNode)) {
                root = topNode.right;
                continue;
            }

            // now, we have hit the leaf node, we need to unwind now..
            BinaryTreeNode node = null;

            // unwind each of the nodes on the stack, as long as they are the last child ...
            for (topNode = getTopNode(nodeStack); isLastChild(topNode, node); topNode = getTopNode(nodeStack)) {
                node = nodeStack.pop();

                // ... and, operate upon the node that is being popped
                nodeOperation.run(node);
            }

            // if no more nodes exist to process, set root to null; otherwise, set it to root...
            if (nodeStack.isEmpty()) {
                root = null;
            } else {
                root = topNode.right;
            }
        }
    }

    // Time Complexity: O(n) since it visits all n nodes. Space Complexity: O(n) since it requires that many entries in the stack
    public static void inOrder_Iterative(BinaryTreeNode root, INodeOperation nodeOperation) {
        if (root == null) {
            return;
        }

        // create a stack explicitly to be used..
        Stack<BinaryTreeNode> nodeStack = new Stack<>();

        while (true) {
            // traverse the left subtree and collect the nodes into the stack...
            while (root != null) {
                nodeStack.push(root);
                root = root.left;
            }

            // check if the stack is empty, nothing to process, break out
            if (nodeStack.isEmpty()) {
                break;
            }

            // we have hit past the left most node. so, pop it out and operate upon
            root = nodeStack.pop();
            nodeOperation.run(root);

            // ... and, remember to traverse its right subtree
            root = root.right;
        }
    }

    // Time Complexity: O(n) since it visits all n nodes. Space Complexity: O(n) since it requires that many entries in the stack
    public static void preOrder_Iterative(BinaryTreeNode root, INodeOperation nodeOperation) {
        if (root == null) {
            return;
        }

        // create a stack explicitly
        Stack<BinaryTreeNode> nodeStack = new Stack<>();

        while (true) {
            // traverse the left subtree ...
            while (root != null) {
                // ... and, operate upon the node ...
                nodeOperation.run(root);

                // ... and push them onto the stack
                nodeStack.push(root);
                root = root.left;
            }

            // nothing to process, if stack is empty; break out
            if (nodeStack.isEmpty()) {
                break;
            }

            // we have hit past the last left subtree. so, pop out the top most node
            root = nodeStack.pop();

            // and, remember to visit its right subtree
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

        int leftHeight = minHeight_Recursive(root.left);
        int rightHeight = minHeight_Recursive(root.right);
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

        return isFoldableTreeInternal(root1.left, root2.right) && isFoldableTreeInternal(root1.right, root2.left);
    }

    public static BinaryTreeNode findLeastCommonAncestor(BinaryTreeNode root, BinaryTreeNode node1, BinaryTreeNode node2) {
        if (root == null) {
            return null;
        }

        if (root == node1 || root == node2) {
            return root;
        }

        BinaryTreeNode temp1, temp2;

        temp1 = findLeastCommonAncestor(root.left, node1, node2);
        temp2 = findLeastCommonAncestor(root.right, node1, node2);
        if (temp1 != null && temp2 != null) {
            return root;
        }

        return (temp1 != null) ? temp1 : temp2;
    }

    public static BinaryTreeNode findNode_Recursive(BinaryTreeNode root, BinaryTreeNode node) {
        if (root == null || node == null) {
            return null;
        }

        if (root == node) {
            return root;
        }

        BinaryTreeNode temp = findNode_Recursive(root.left, node);
        return (temp != null) ? temp : findNode_Recursive(root.right, node);
    }

    public static BinaryTreeNode findNode_Iterative(BinaryTreeNode root, BinaryTreeNode node) {
        if (root == null || node == null) {
            return null;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (false == queue.isEmpty()) {
            BinaryTreeNode temp = queue.remove();
            if (temp == node) {
                return root;
            }

            if (temp.left != null) {
                queue.add(temp.left);
            }

            if (temp.right != null) {
                queue.add(temp.right);
            }
        }

        return null;
    }

    public static boolean isBalancedTree_Recursive(BinaryTreeNode root) {
        if (root == null) {
            return true;
        }

        int lHeight = maxHeight_Recursive(root.left);
        int rHeight = maxHeight_Recursive(root.right);
        int delta = (lHeight > rHeight) ? lHeight - rHeight : rHeight - lHeight;

        if (delta > 1) {
            System.out.print("(imbalance detected at node " + root.value + ")");
            return false;
        }

        return isBalancedTree_Recursive(root.left) && isBalancedTree_Recursive(root.right);
    }

    public static boolean isBalancedTree_Iterative(BinaryTreeNode root) {
        if (root == null) {
            return true;
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
            int lHeight = maxHeight_Iterative(root.left);
            int rHeight = maxHeight_Iterative(root.right);
            int delta = (lHeight > rHeight) ? lHeight - rHeight : rHeight - lHeight;
            if (delta > 1) {
                System.out.print("(imbalance detected at node " + root.value + ")");
                return false;
            }

            root = root.right;
        }

        return true;
    }

    public static BinaryTreeNode makeBinarySearchTree(int[] sortedInput) {
        if (sortedInput == null || sortedInput.length <= 0) {
            return null;
        }

        return makeBinarySearchTree(sortedInput, 0, sortedInput.length);
    }

    private static BinaryTreeNode makeBinarySearchTree(int[] sortedInput, int startIx, int endIx) {
        if (startIx >= endIx) {
            return null;
        }

        int midIx = startIx + (endIx - startIx) / 2;

        BinaryTreeNode node = new BinaryTreeNode(sortedInput[midIx], null, null);
        node.left = makeBinarySearchTree(sortedInput, startIx, midIx);
        node.right = makeBinarySearchTree(sortedInput, midIx + 1, endIx);

        return node;
    }

    /**
     * Created by kirn on 3/25/18.
     */
    public static interface INodeOperation {
        void run(BinaryTreeNode node);
    }
}
