public class DoublyLinkedList {
    public Task head;
    public Task tail;

    public void addTask(Task newTask) {
        if (head == null) {
            head = tail = newTask;
        } else {
            tail.next = newTask;
            newTask.prev = tail;
            tail = newTask;
        }
    }

    public Task findTaskById(String id) {
        Task current = head;
        while (current != null) {
            if (current.id.equals(id)) return current;
            current = current.next;
        }
        return null;
    }

    public void deleteTask(String id) {
        Task current = findTaskById(id);
        if (current == null) return;
        if (current == head) {
            head = current.next;
            if (head != null) head.prev = null;
        } else if (current == tail) {
            tail = current.prev;
            tail.next = null;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
    }

    public void displayRowsOnly(String categoryName) {
        Task current = head;
        while (current != null) {
            printRow(current, categoryName);
            current = current.next;
        }
    }

    public void displaySearch(String keyword, String categoryName) {
        Task current = head;
        while (current != null) {
            // Cek jika judul mengandung keyword (ignore case sederhana)
            if (current.title.toLowerCase().indexOf(keyword.toLowerCase()) != -1) {
                printRow(current, categoryName);
            }
            current = current.next;
        }
    }

    // UPDATE: Method Manual Filter Priority
    private void printRow(Task t, String cat) {
        String status = t.isCompleted ? "selesai" : "aktif";
        System.out.printf("| %-4s | %-30s | %-12s | %-12s | %-10s |\n", 
            t.id, padRight(t.title, 30), cat, t.deadline, status);
    }

    private String padRight(String s, int n) {
        if (s.length() > n) return s.substring(0, n-3) + "...";
        return String.format("%-" + n + "s", s);
    }
    
    public int count() {
        int c = 0;
        Task cur = head;
        while(cur != null) { c++; cur = cur.next; }
        return c;
    }

    public int getSize() {
        return count();
    }
}