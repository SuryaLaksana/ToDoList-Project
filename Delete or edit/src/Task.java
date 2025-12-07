class Task {
    int id;
    String title;
    String status;

    Task(int id, String title) {
        this.id = id;
        this.title = title;
        this.status = "Pending";
    }

    void display() {
        System.out.println(title);
    }
}
