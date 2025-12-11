public class Task {
    public String id;
    public String title;
    public String deadline;
    public boolean isCompleted;
    public String category;
    public Task next;
    public Task prev;

    public Task(String id, String title, String deadline, String category, boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.deadline = deadline;
        this.category = category; 
        this.isCompleted = isCompleted;
        this.next = null;
        this.prev = null;
}
}