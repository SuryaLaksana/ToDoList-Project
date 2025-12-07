public class TaskAlgorithms {

    // ------------------ MERGE SORT BY PRIORITY ------------------
    public static DoublyLinkedList mergeSortByPriority(DoublyLinkedList list) {
        if (list.head == null || list.head.next == null) return list;

        DoublyLinkedList left = new DoublyLinkedList();
        DoublyLinkedList right = new DoublyLinkedList();

        split(list, left, right);

        left = mergeSortByPriority(left);
        right = mergeSortByPriority(right);

        return merge(left, right);
    }

    private static void split(DoublyLinkedList source, DoublyLinkedList left, DoublyLinkedList right) {
        Node slow = source.head;
        Node fast = source.head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            if (fast != null) slow = slow.next;
        }

        left.head = source.head;
        right.head = slow.next;

        slow.next = null; // pisahkan list
        if (right.head != null) right.head.prev = null;
    }

    private static DoublyLinkedList merge(DoublyLinkedList L1, DoublyLinkedList L2) {
        DoublyLinkedList result = new DoublyLinkedList();

        Node p1 = L1.head, p2 = L2.head;

        while (p1 != null && p2 != null) {
            if (p1.task.getPriority() < p2.task.getPriority()) { // HIGH = 1 jadi paling depan
                result.addLast(p1.task);
                p1 = p1.next;
            } else {
                result.addLast(p2.task);
                p2 = p2.next;
            }
        }

        while (p1 != null) { result.addLast(p1.task); p1 = p1.next; }
        while (p2 != null) { result.addLast(p2.task); p2 = p2.next; }

        return result;
    }
}