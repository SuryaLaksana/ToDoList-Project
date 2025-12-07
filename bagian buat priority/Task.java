public class Task {
    private String title;
    private String deadline;
    private boolean isCompleted;
    private int priority; 
    // 1 = High, 2 = Medium, 3 = Low

    public Task(String title, String deadline, int priority) {
        this.title = title;
        this.deadline = deadline;
        this.priority = priority;
        this.isCompleted = false;
    }

    // --- GETTER & SETTER ---
    public String getTitle() { return title; }
    public String getDeadline() { return deadline; }
    public boolean isCompleted() { return isCompleted; }
    public int getPriority() { return priority; }

    public void setTitle(String title) { this.title = title; }
    public void setDeadline(String deadline) { this.deadline = deadline; }
    public void setCompleted(boolean completed) { this.isCompleted = completed; }
    public void setPriority(int priority) { this.priority = priority; }

    @Override
    public String toString() {
        String p = (priority == 1 ? "HIGH" : priority == 2 ? "MEDIUM" : "LOW");
        return "[ " + title + " | Deadline: " + deadline + " | Priority: " + p + " ]";
    }
}