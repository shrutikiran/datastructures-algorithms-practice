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

    public static void main(String[] args) {
        SLLNode head;
        SLLNode reverse;

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
    }
}
