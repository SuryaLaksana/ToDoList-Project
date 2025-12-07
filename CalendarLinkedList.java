public class CalendarLinkedList {
    CalendarNode head;

    public CalendarLinkedList() {
        head = null;
    }

    public void addDate(int day, int month, int year) {
        CalendarNode newNode = new CalendarNode(day, month, year);

        if (head == null) {
            head = newNode;
            return;
        }

        CalendarNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void displayCalendar() {
        CalendarNode temp = head;
        while (temp != null) {
            System.out.println(temp.day + "-" + temp.month + "-" + temp.year);
            temp = temp.next;
        }
    }
}
