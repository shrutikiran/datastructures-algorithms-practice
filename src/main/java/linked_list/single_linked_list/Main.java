package linked_list.single_linked_list;

import linked_list.SLLNode;

public class Main {

    private static SLLNode createList() {
        SLLNode head;

        head = new SLLNode(0);

        SLLNode ptr;
        int v = 1;

        for (ptr = head; ptr.next == null; ptr = ptr.next) {
            if (v > 10) {
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
            System.out.println();
            return;
        }

        printList_Reverse_Recursive(head.next);
        System.out.print(head.value + " ");
    }

    public static void main(String[] args) {
        SLLNode head = createList();

        printList(head);

        printList_Reverse_Recursive(head);
    }
}
