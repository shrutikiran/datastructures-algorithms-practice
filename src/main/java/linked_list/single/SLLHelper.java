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

        // have two references -- fast and slow -- initialized to the beginning of the list
        SLLNode fastRef = head, slowRef = head;

        // move the slow reference one node, and the fast reference 2 nodes at a time...
        while (slowRef != null && fastRef != null && fastRef.next != null) {
            slowRef = slowRef.next;
            fastRef = fastRef.next.next;
        }

        // ... until the fast reference reaches the end. at that point, the slow reference
        // would be pointing to the middle node, if odd; otherwise to the last node of the first half

        return slowRef;
    }

    public static SLLNode nthNodeFromLast_Iterative(SLLNode head, int position) {
        if (head == null) {
            return null;
        }

        // initialize two pointers to the start of the list
        SLLNode node1 = head, node2 = head;

        while (node1 != null) {
            // move the first node ...
            node1 = node1.next;

            // ... but, move the second one only after n number of nodes has passed..
            if (--position >= 0) {
                continue;
            }

            // ... this creates a lag of n nodes between node1 and node2....
            node2 = node2.next;

            // ... therefore, when the first node reaches the end, the other node
            // is n nodes behind, pointing to the required node ...
        }

        // ... so, return it
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
        // no list, nothing to reverse
        if (head == null) {
            return head;
        }

        // maintain two additional pointers -- one pointing to previous, other to next..
        SLLNode prevNode = null, nextNode = null;

        do {
            // save the next node...
            nextNode = head.next;

            // update the next node to previous
            head.next = prevNode;

            // move the previous node to current...
            prevNode = head;

            // ... and, current to next..
            head = nextNode;

            // ... until we have reached the end
        } while (head != null);

        // previous node is the one that points to the reversed list starting node now..
        return prevNode;
    }

    public static SLLNode reverse_Recursive(SLLNode head, SLLNode prevNode) {
        // base case: no list, return the previous node reference
        if (head == null) {
            return prevNode;
        }

        // head is not null, so save the next node of head...
        SLLNode nextNode = head.next;

        // ... and, make the head' next point to previous node
        head.next = prevNode;

        // do the same thing to next node, with head becoming the previous one now..
        return reverse_Recursive(nextNode, head);
    }

    public static boolean hasLoop(SLLNode head) {
        if (head == null) {
            return false;
        }

        // first, initialize both the fast and slow references at the beginning..
        SLLNode slowRef = head, fastRef = head;

        // and, move the slow reference by one, and fast reference by two steps, until they reach the end.
        while (null != slowRef && null != fastRef && null != fastRef.next) {
            slowRef = slowRef.next;
            fastRef = fastRef.next.next;

            // if they meet, then there is a cycle...
            if (slowRef == fastRef) {
                return true;
            }
        }

        // no cycle
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

    public static SLLNode findIntersectingNode(SLLNode list1, SLLNode list2) {
        // first, get the size of both the lists
        int c1 = count(list1), c2 = count(list2);

        // then, figure out the bigger list..
        int     delta = (c1 > c2) ? (c1 - c2) : (c2 - c1);

        // ... and, set it to ptr2...
        SLLNode ptr1 = (c1 > c2) ? list1 : list2;

        // ... and, the other list to ptr2
        SLLNode ptr2 = (ptr1 == list1) ? list2 : list1;

        // next, move the bigger list pointer by delta nodes so that both lists become same size from now on..
        while (delta-- > 0) {
            ptr1 = ptr1.next;
        }

        // then, move both the list pointers one step at a time, until they intersect
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return (ptr1 == ptr2) ? ptr1 : null;
    }

    public static int count(SLLNode head) {
        int c = 0;

        while (head != null) {
            head = head.next;
            c++;
        }

        return c;
    }
}
