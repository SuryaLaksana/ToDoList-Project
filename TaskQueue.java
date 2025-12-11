public class TaskQueue {
    private Task front;
    private Task rear;
    private int size;

    public TaskQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }
    
    private String getSortableDeadline(Task t) {
        String d = t.deadline; 
        
        if (d != null && d.length() == 10 && d.charAt(2) == '-' && d.charAt(5) == '-') {
            String day = d.substring(0, 2);    
            String month = d.substring(3, 5);  
            String year = d.substring(6, 10);  

            return year + "-" + month + "-" + day; 
        }
        return d; 
    }

    public int getSize() {
        return size;
    }

    public void addTask(Task task) {
        if (rear == null) {
            front = rear = task;
        } else {
            rear.next = task;
            rear = task;
        }
        size++;
    }
    
    // Metode Hapus Tugas
    public void deleteTask(String id) {
        if (front == null) return;

        if (front.id.equals(id)) {
            front = front.next;
            if (front == null) rear = null;
            size--;
            return;
        }

        Task current = front;
        while (current.next != null) {
            if (current.next.id.equals(id)) {
                if (current.next == rear) {
                    rear = current;
                }
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
    }

    // Metode Cari Tugas berdasarkan ID
    public Task findTaskById(String id) {
        Task current = front;
        while (current != null) {
            if (current.id.equals(id)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Metode untuk FITUR 1 & 2: Menampilkan Baris Tugas Saja
    public void displayRowsOnly(String category) {
        Task current = front;
        while (current != null) {
            String statusStr = current.isCompleted ? "selesai" : "aktif";
            System.out.printf("| %-4s | %-30s | %-12s | %-12s | %-10s |\n", 
                current.id, Main.padRight(current.title, 30), category, current.deadline, statusStr);
            current = current.next;
        }
    }
    
    // Metode untuk FITUR 3: Searching
    public void displaySearch(String keyword, String category) {
        String lowerKeyword = keyword.toLowerCase();
        Task current = front;
        while (current != null) {
            if (current.title.toLowerCase().contains(lowerKeyword)) {
                 String statusStr = current.isCompleted ? "selesai" : "aktif";
                 System.out.printf("| %-4s | %-30s | %-12s | %-12s | %-10s |\n", 
                    current.id, Main.padRight(current.title, 30), category, current.deadline, statusStr);
            }
            current = current.next;
        }
    }
    
    // Metode untuk FITUR 4: Enqueue
    public void enqueue(Task t) {
        Task newNode = new Task(t.id, t.title, t.deadline, t.category, t.isCompleted);
        
        String newNodeSortableDeadline = getSortableDeadline(newNode);
        
        // Kasus 1: List kosong
        if (front == null) {
            front = rear = newNode;
        } 
        // Kasus 2: Sisip di depan
        else if (newNodeSortableDeadline.compareTo(getSortableDeadline(front)) < 0) {
            newNode.next = front;
            front = newNode;
        } 
        // Kasus 3: Sisip di tengah atau di belakang
        else {
            Task current = front;
            while (current.next != null && newNodeSortableDeadline.compareTo(getSortableDeadline(current.next)) >= 0) {
                current = current.next;
            }
            
            newNode.next = current.next;
            current.next = newNode;
            
            if (newNode.next == null) {
                rear = newNode;
            }
        }
        size++;
    }

    // Metode untuk FITUR 4: Display
    public void display() {
        Task cur = front;
        while(cur != null) {
            String statusStr = cur.isCompleted ? "selesai" : "aktif";
            System.out.printf("| %-4s | %-30s | %-12s | %-12s | %-10s |\n", 
                cur.id, 
                Main.padRight(cur.title, 30), 
                cur.category, 
                cur.deadline, 
                statusStr);
            cur = cur.next;
        }
    }
}