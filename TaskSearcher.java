public class TaskSearcher {

    public void searchByKeyword(LinkedListTaskStruktur list, String keyword) {
        TaskNode current = list.getHead();
        boolean found = false;

        while (current != null) {
            if (current.getTask().getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(current.getTask());
                found = true;
            }
            current = current.getNext();
        }

        if (!found) {
            System.out.println("Tidak ada tugas yang cocok dengan keyword: " + keyword);
        }
    }

    public void searchByCategory(LinkedListTaskStruktur list, String category) {
        TaskNode current = list.getHead();
        boolean found = false;

        while (current != null) {
            if (current.getTask().getCategory().equalsIgnoreCase(category)) {
                System.out.println(current.getTask());
                found = true;
            }
            current = current.getNext();
        }

        if (!found) {
            System.out.println("Tidak ada tugas dengan kategori: " + category);
        }
    }

    public void searchByPriority(LinkedListTaskStruktur list, int priority) {
        TaskNode current = list.getHead();
        boolean found = false;

        while (current != null) {
            if (current.getTask().getPriority() == priority) {
                System.out.println(current.getTask());
                found = true;
            }
            current = current.getNext();
        }

        if (!found) {
            System.out.println("Tidak ada tugas dengan prioritas: " + priority);
        }
    }
}
