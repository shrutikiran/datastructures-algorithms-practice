package linked_list.single_linked_list;

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
            if (v == 10) {
                tempNode = ptr;
            }
            ptr.next = new SLLNode(v++);
        }

        ptr.next = tempNode;

        return head;
    }

    private static void printList(SLLNode head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    private static void printList_Reverse_Recursive(SLLNode head) {
        if (head == null) {
            return;
        }

        printList_Reverse_Recursive(head.next);
        System.out.print(head.value + " ");
    }

    private static SLLNode reverseList_Recursive(SLLNode head, SLLNode previous) {
        if (head == null) {
            return previous;
        }

        SLLNode next = head.next;
        head.next = previous;
        return reverseList_Recursive(next, head);
    }

    private static SLLNode reverseList_Iterative(SLLNode head) {
        if (head == null) {
            return null;
        }

        SLLNode previous, current, next;

        previous = null; current = head; next = head.next;

        while (current != null) {
            current.next = previous;
            previous = current;
            current = next;
            next = (next != null) ? next.next : null;
        }

        return previous;
    }

    private static SLLNode nthNodeFromLast_Iterative(SLLNode head, int pos) {
        if (head == null) {
            return null;
        }

        SLLNode nthNode = head;
        while (head != null) {
            head = head.next;
            if (--pos >= 0) {
                continue;
            }
            nthNode = nthNode.next;
        }

        return nthNode;
    }

    private static SLLNode midNode(SLLNode head) {
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

    private static boolean hasLoop(SLLNode head) {
        if (head == null) {
            return false;
        }

        if (head.next == head) {
            return true;
        }

        SLLNode fastRef = head, slowRef = head;

        while (slowRef != null && fastRef != null && fastRef.next != null) {
            slowRef = slowRef.next;
            fastRef = fastRef.next.next;

            if (slowRef == fastRef) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        SLLNode head, reverse, ptr;

        head = createList();
        printList(head);

        System.out.print("Print Reverse (Recursive) = ");
        printList_Reverse_Recursive(head);
        System.out.println();

        System.out.print("Reverse (Recursive) = ");
        reverse = reverseList_Recursive(head, null);
        printList(reverse);
        System.out.println();

        head = createList();
        System.out.print("Reverse (Iterative) = ");
        reverse = reverseList_Iterative(head);
        printList(reverse);
        System.out.println();

        head = createList();
        System.out.print("5th node from last (Iterative) = ");
        ptr = nthNodeFromLast_Iterative(head, 5);
        System.out.println(ptr == null ? "null" : ptr.value);

        head = createList();
        System.out.print("Middle node = ");
        ptr = midNode(head);
        System.out.println(ptr == null ? "null" : ptr.value);

        head = createListWithLoop();
        System.out.println("has loop = " + hasLoop(head));
    }
}
