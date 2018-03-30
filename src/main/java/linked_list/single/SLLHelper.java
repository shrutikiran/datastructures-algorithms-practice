package linked_list.single;

import linked_list.SLLNode;

/**
 * Created by kirn on 3/30/18.
 */
public class SLLHelper {

    public static SLLNode findMiddleNode(SLLNode head) {
        if (head == null) {
            return null;
        }

        SLLNode fastRef = head, slowRef = head;

        while (slowRef != null && fastRef != null && fastRef.next != null) {
            slowRef = slowRef.next;
            fastRef = fastRef.next.next;
        }

        return slowRef;
    }

    public static SLLNode nthNodeFromLast_Iterative(SLLNode head, int position) {
        if (head == null) {
            return null;
        }

        SLLNode node1 = head, node2 = head;

        while (node1 != null) {
            node1 = node1.next;
            if (--position >= 0) {
                continue;
            }
            node2 = node2.next;
        }

        return node2;
    }

    public static interface INodeVisitor {
        void visitedNode(SLLNode node);
    }

    public static void traverse_Iterative(SLLNode head, INodeVisitor nodeVisitor) {
        if (head == null || nodeVisitor == null) {
            return;
        }

        do {
            nodeVisitor.visitedNode(head);
            head = head.next;
        } while (head != null);
    }

    public static void traverse_Recursive(SLLNode head, INodeVisitor nodeVisitor) {
        if (head == null || nodeVisitor == null) {
            return;
        }

        nodeVisitor.visitedNode(head);
        traverse_Recursive(head.next, nodeVisitor);
    }

    public static SLLNode reverse_Iterative(SLLNode head) {
        if (head == null) {
            return head;
        }

        SLLNode prevNode = null, nextNode = null;

        do {
            nextNode = head.next;
            head.next = prevNode;
            prevNode = head;
            head = nextNode;
        } while (head != null);

        return prevNode;
    }

    public static SLLNode reverse_Recursive(SLLNode head, SLLNode prevNode) {
        if (head == null) {
            return prevNode;
        }

        SLLNode nextNode = head.next;
        head.next = prevNode;
        return reverse_Recursive(nextNode, head);
    }

    public static boolean hasLoop(SLLNode head) {
        if (head == null) {
            return false;
        }

        SLLNode slowRef = head, fastRef = head;
        while (null != slowRef && null != fastRef && null != fastRef.next) {
            slowRef = slowRef.next;
            fastRef = fastRef.next.next;

            if (slowRef == fastRef) {
                return true;
            }
        }

        return false;
    }

    public static SLLNode findLoopStartNode(SLLNode head) {
        if (head == null) {
            return null;
        }

        SLLNode slowRef = head, fastRef = head;
        while (null != slowRef && null != fastRef && null != fastRef.next) {
            slowRef = slowRef.next;
            fastRef = fastRef.next.next;

            if (slowRef == fastRef) {
                break;
            }
        }

        if (slowRef == fastRef) {
            SLLNode ref1 = head, ref2 = slowRef;

            while (ref1 != ref2) {
                ref1 = ref1.next;
                ref2 = ref2.next;
            }

            return ref1;
        }

        return null;
    }

}
