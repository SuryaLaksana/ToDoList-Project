import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Metode list = new Metode();

        int choice;
        int taskId = 1;

        do {
            System.out.println("\n=== ToDoList ===");
            System.out.println("1. Tambah Tugas");
            System.out.println("2. Edit Tugas");
            System.out.println("3. Hapus Tugas");
            System.out.println("4. Tampilkan Semua");
            System.out.println("5. Undo");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan judul tugas: ");
                    String title = input.nextLine();
                    list.enqueue(new Task(taskId++, title));
                    System.out.println("Tugas ditambahkan.");
                    break;

                case 2:
                    System.out.print("Masukkan ID tugas yang ingin diedit: ");
                    int editId = input.nextInt();
                    input.nextLine();

                    Task editTask = list.getById(editId);
                    if (editTask != null) {
                        System.out.print("Judul baru: ");
                        editTask.title = input.nextLine();
                        System.out.println("Tugas berhasil diubah.");
                    } else {
                        System.out.println("Tugas tidak ditemukan.");
                    }
                    break;

                case 3:
                    System.out.print("Masukkan ID tugas yang ingin dihapus: ");
                    int delId = input.nextInt();

                    Task deleted = list.removeById(delId);
                    if (deleted != null) {
                        list.push(deleted);
                        System.out.println("Tugas dihapus (masuk Stack Undo).");
                    } else {
                        System.out.println("Tugas tidak ditemukan.");
                    }
                    break;

                case 4:
                    System.out.println("\n=== DAFTAR TUGAS ===");
                    list.display();
                    break;

                case 5:
                    Task undoTask = list.pop();
                    if (undoTask != null) {
                        list.enqueue(undoTask);
                        System.out.println("Tugas berhasil dikembalikan.");
                    } else {
                        System.out.println("Tidak ada tugas untuk di-undo.");
                    }
                    break;
            }

        } while (choice != 0);

        input.close();
    }
}