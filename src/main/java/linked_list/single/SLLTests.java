package linked_list.single;

import linked_list.SLLNode;

public class SLLTests {

    private static SLLNode createList() {
        SLLNode head;

        head = new SLLNode(0);

        SLLNode ptr;
        int v = 1;

        for (ptr = head; ptr.next == null; ptr = ptr.next) {
            if (v >= 20) {
                break;
            }
            ptr.next = new SLLNode(v++);
        }

        return head;
    }

    private static SLLNode createListWithLoop() {
        SLLNode head;

        head = new SLLNode(0);

        SLLNode ptr, tempNode = null;
        int v = 1;

        for (ptr = head; ptr.next == null; ptr = ptr.next) {
            if (v >= 20) {
                break;
            }
            if (v == 12) {
                tempNode = ptr;
            }
            ptr.next = new SLLNode(v++);
        }

        ptr.next = tempNode;

        return head;
    }

    private static SLLNode createIntersectingList(SLLNode list1) {
        SLLNode head;

        head = new SLLNode(0);

        SLLNode ptr;
        int v = -1;

        for (ptr = head; ptr.next == null; ptr = ptr.next) {
            if (v >= 0) {
                break;
            }
            ptr.next = new SLLNode(v++);
        }

        ptr.next = SLLHelper.findMiddleNode(list1);

        return head;
    }

    public static void main(String[] args) {
        SLLNode head, ptr;

        head = createList();

        {
            System.out.println();
            System.out.println("Iterative Traversal");
            SLLHelper.traverse_Iterative(head, (node) -> System.out.print(node.value + " "));
            System.out.println();
            System.out.println();
        }

        {
            System.out.println();
            System.out.println("Recursive Traversal");
            SLLHelper.traverse_Recursive(head, (node) -> System.out.print(node.value + " "));
            System.out.println();
            System.out.println();
        }

        {
            System.out.println("Reverse (Recursive)");
            SLLNode temp = createList();
            temp = SLLHelper.reverse_Recursive(temp, null);
            SLLHelper.traverse_Iterative(temp, (node) -> System.out.print(node.value + " "));
            System.out.println();
            System.out.println();
        }

        {
            System.out.println();
            System.out.println("Reverse (Iterative)");
            SLLNode temp = createList();
            temp = SLLHelper.reverse_Iterative(temp);
            SLLHelper.traverse_Iterative(temp, (node) -> System.out.print(node.value + " "));
            System.out.println();
            System.out.println();
        }

        {
            System.out.println();
            head = createList();
            System.out.print("Middle node (Even Number): ");
            ptr = SLLHelper.findMiddleNode(head);
            System.out.println(ptr == null ? "null" : ptr.value);

            System.out.print("Middle node (Odd Number): ");
            ptr = SLLHelper.findMiddleNode(head.next.next.next);
            System.out.println(ptr == null ? "null" : ptr.value);
            System.out.println();
        }

        {
            System.out.println();
            int position = 2;
            head = createList();
            System.out.print("nth (" + position + ") node from end: ");
            SLLNode nthNode = SLLHelper.nthNodeFromLast_Iterative(head, position);
            System.out.println((nthNode != null) ? nthNode.value : "null");
            System.out.println();
        }

        {
            System.out.println();
            head = createList();
            System.out.println("Linear list; has loop = " + SLLHelper.hasLoop(head));

            head = createListWithLoop();
            System.out.println("Circular list; has loop = " + SLLHelper.hasLoop(head));
            SLLNode loopStartNode = SLLHelper.findLoopStartNode(head);
            System.out.println("Loop start node: " + ((loopStartNode != null) ? loopStartNode.value : "null"));
            System.out.println();
        }

        {
            System.out.println();
            SLLNode list1 = createList();
            SLLNode list2 = createIntersectingList(list1);

            System.out.println("Finding the intersecting Node");
            SLLNode intersectingNode = SLLHelper.findIntersectingNode(list1, list2);
            System.out.println("Intersecting node: " + ((intersectingNode != null) ? intersectingNode.value : "null"));
            System.out.println();
        }
    }
}
