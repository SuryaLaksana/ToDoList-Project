public class CalendarNode {
    int day;               // tanggal
    int month;             // bulan
    int year;              // tahun
    CalendarNode next;     // pointer ke node berikutnya

    public CalendarNode(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.next = null;
    }
}
