package tree;

public class TreeHelper {
    public static boolean isLastChild(Node parent, Node child) {
        if (parent == null) {
            return false;
        }

        return (parent.right == null || parent.right == child);
    }

    public static boolean isLeafNode(Node node) {
        if (node == null) {
            return false;
        }

        return (node.left == null && node.right == null);
    }
}
