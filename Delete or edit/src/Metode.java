public class Metode {
    private Node front;  
    private Node rear;  
    private Node top;  

    private class Node {
        Task data;
        Node next;

        Node(Task data) {
            this.data = data;
            this.next = null;
        }
    } 

    public Metode() {
        front = rear = top = null;
    }

    boolean isEmptyQueue() {
        return front == null;
    }

    boolean isEmptyStack() {
        return top == null;
    }

    void enqueue(Task task) {
        Node newNode = new Node(task);

        if (isEmptyQueue()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    Task dequeue() {
        if (isEmptyQueue()) return null;

        Task task = front.data;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        return task;
    }

    void display() {
        int index = 0;
        if (isEmptyQueue()) {
            System.out.println("Tidak ada tugas.");
            return;
        }

        Node temp = front;
        while (temp != null) {
            System.out.print(index + temp.data.id + ". ");
            temp.data.display();
            temp = temp.next;
        }
    }

    Task getbyIndex(int index) {
        Node temp = front;
        while (temp != null) {
            if (temp.data.id == index) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    Task removebyIndex(int index) {
        if (isEmptyQueue()) return null;

        Node temp = front;
        Node prev = null;

        while (temp != null) {
            if (temp.data.id == index) {
                if (prev == null) {
                    front = temp.next;
                    if (front == null) {
                        rear = null;
                    }
                } else {
                    prev.next = temp.next;
                    if (temp == rear) {
                        rear = prev;
                    }
                }
                return temp.data;
            }
            prev = temp;
            temp = temp.next;
        }
        return null;
    }

    void push(Task task) {
        Node newNode = new Node(task);
        newNode.next = top;
        top = newNode;
    }

    Task pop() {
        if (isEmptyStack()) return null;

        Task task = top.data;
        top = top.next;
        return task;
    }
}