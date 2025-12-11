public class Main {
    static CategoryTree tree = new CategoryTree();
    static TaskQueue queue = new TaskQueue();
    private static Task globalSortedHead = null;

    private static int parseTaskId(String id) {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE; 
        }
    }
    
    private static void insertTaskSorted(Task newTask) {
        newTask.next = null; 
        if (globalSortedHead == null || parseTaskId(newTask.id) < parseTaskId(globalSortedHead.id)) {
            newTask.next = globalSortedHead;
            globalSortedHead = newTask;
            return;
        }

        Task current = globalSortedHead;
        int newId = parseTaskId(newTask.id);
        while (current.next != null && newId >= parseTaskId(current.next.id)) {
            current = current.next;
        }
        newTask.next = current.next;
        current.next = newTask;
    }

    private static void collectAndSortTasksRecursive(CategoryNode node) {
        if (node == null) return;

        Task currentTask = node.tasks.head; 
        while (currentTask != null) {
            Task taskCopy = new Task(
                currentTask.id, 
                currentTask.title, 
                currentTask.deadline, 
                currentTask.category,
                currentTask.isCompleted
            );
            insertTaskSorted(taskCopy);
            currentTask = currentTask.next;
        }

        collectAndSortTasksRecursive(node.firstChild);
        collectAndSortTasksRecursive(node.nextSibling);
    }

    private static void displayAllTasksSorted() {
        globalSortedHead = null; 
        collectAndSortTasksRecursive(tree.root);

        Task current = globalSortedHead;
        while (current != null) {
            String status = current.isCompleted ? "selesai" : "aktif";
            System.out.printf("| %-4s | %-30s | %-12s | %-12s | %-10s |\n", 
                current.id, padRight(current.title, 30), current.category, current.deadline, status);
            current = current.next;
        }
    }
    static int calculateTotalTasks() {
        return countTasksRecursive(tree.root); 
    }
    
    private static int countTasksRecursive(CategoryNode node) {
        if (node == null) {
            return 0;
        }

        int total = node.tasks.getSize(); 

        total += countTasksRecursive(node.firstChild);
        total += countTasksRecursive(node.nextSibling);

        return total;
    }

    static void printHeader(String title) {
    if (title.equals("ToDoList")) {
        // --- Header KHUSUS ToDoList (Lebar 84 karakter) ---
        String line = "====================================================================================";
        
        // Baris Atas
        System.out.println(line);
        
        // Baris Teks ToDoList yang dibuat besar manual
        System.out.println("||            _______       _____           _      ___   ____   _____             ||");
        System.out.println("||           |__   __|     |  __ \\         | |    |_ _| / ___| |_   _|            ||");
        System.out.println("||              | | ___    | |  | | ___    | |     | | |  |__    | |              ||");
        System.out.println("||              | |/ _ \\   | |  | |/ _ \\   | |     | |  \\___  \\  | |              ||");
        System.out.println("||              | | (_) |  | |__| | (_) |  | |___  | |   ___) |  | |              ||");
        System.out.println("||              |_|\\___/   |_____/ \\___/   |_____||___| |____/   |_|              ||");
        System.out.println("||                                                                                ||");
        System.out.println("||                                   ToDoList                                     ||");
        // Baris Bawah
        System.out.println(line);
        } else {
            // Header standar untuk fitur-fitur lainnya
            System.out.println("====================================================================================");
            System.out.println("                                     " + title);
            System.out.println("====================================================================================");
        }
    }

    static void printTableHeader() {
        System.out.printf("| %-4s | %-30s | %-12s | %-12s | %-10s |\n",
            "ID", "JUDUL TUGAS", "KATEGORI", "DEADLINE", "STATUS");
        System.out.println("|======|================================|==============|==============|============|");
    }

    static void printLine() {
        System.out.println("====================================================================================");
    }

    public static String padRight(String s, int n) {
        if (s == null) return String.format("%-" + n + "s", ""); 
        if (s.length() > n) return s.substring(0, n - 3) + "...";
        return String.format("%-" + n + "s", s);
    }


    public static void main(String[] args) {

        printHeader("ToDoList");
        // SETUP DATA
        tree.addSubCategory("All Tasks", "Kuliah");
        tree.addSubCategory("All Tasks", "Pribadi");
        tree.addSubCategory("All Tasks", "Volunteer");
        tree.addSubCategory("All Tasks", "Organisasi");
        tree.addSubCategory("All Tasks", "Kerja");

        // Pencarian node
        CategoryNode catKuliah = tree.findCategory(tree.root, "Kuliah");
        CategoryNode catPribadi = tree.findCategory(tree.root, "Pribadi");
        CategoryNode catVolunteer = tree.findCategory(tree.root, "Volunteer");
        CategoryNode catOrganisasi = tree.findCategory(tree.root, "Organisasi");
        CategoryNode catKerja = tree.findCategory(tree.root, "Kerja");
        
        catKuliah.tasks.addTask(new Task("01", "Buat Laporan Praktikum", "10-12-2025", "Kuliah", false));
        catKuliah.tasks.addTask(new Task("02", "Edit Video Edukasi Final", "13-12-2025", "Kuliah", false));
        catPribadi.tasks.addTask(new Task("03", "Belanja Perlengkapan", "08-12-2025", "Pribadi", false));
        catKuliah.tasks.addTask(new Task("04", "Riset Topik Jurnal", "20-12-2025", "Kuliah", false));
        catOrganisasi.tasks.addTask(new Task("05", "Rapat Umum Keakraban", "10-12-2025", "Organisasi", false));
        catVolunteer.tasks.addTask(new Task("06", "Update Database Peserta", "15-12-2025", "Volunteer", false));
        catOrganisasi.tasks.addTask(new Task("07", "Desain Banner Musyawarah Umum", "09-12-2025", "Organisasi", false));
        catKerja.tasks.addTask(new Task("08", "Supervisi", "20-12-2025", "Kerja", false));
        catOrganisasi.tasks.addTask(new Task("09", "Edit Video After Movie Prisma", "25-12-2025", "Organisasi", false));
        catKuliah.tasks.addTask(new Task("10", "Buat Laporan Project", "10-12-2025", "Kuliah", false));

        // FITUR 1: DAFTAR TUGAS (Tampilan Gabungan)
        printHeader("DAFTAR TUGAS");
        printTableHeader();

        displayAllTasksSorted(); 
        printLine();
        System.out.println("Total tugas : " + calculateTotalTasks());

        // FITUR 2: KATEGORI TUGAS
        System.out.println("\n");
        printHeader("KATEGORI TUGAS");

        System.out.println("[Kategori: KULIAH]");
        printLine();
        printTableHeader();
        catKuliah.tasks.displayRowsOnly("Kuliah");
        printLine();

        System.out.println("\n[Kategori: PRIBADI]");
        printLine();
        printTableHeader();
        catPribadi.tasks.displayRowsOnly("Pribadi");
        printLine();

        System.out.println("\n[Kategori: VOLUNTEER]");
        printLine();
        printTableHeader();
        catVolunteer.tasks.displayRowsOnly("Volunteer");
        printLine();

        System.out.println("\n[Kategori: ORGANISASI]");
        printLine();
        printTableHeader();
        catOrganisasi.tasks.displayRowsOnly("Organisasi");
        printLine();

        System.out.println("\n[Kategori: KERJA]");
        printLine();
        printTableHeader();
        catKerja.tasks.displayRowsOnly("Kerja");
        printLine();

        // FITUR 3: SEARCHING & FILTER
        System.out.println("\n");
        printHeader("FILTER / SEARCH");
        String keyword = "Video";
        System.out.println("Kata kunci : \"" + keyword + "\"");
        printLine();
        printTableHeader();

        int searchCount = 0;
        CategoryNode currentCat = tree.root.firstChild; 

        while (currentCat != null) {
            Task currentTask = currentCat.tasks.head; 
            while (currentTask != null) {
                if (currentTask.title.toLowerCase().contains(keyword.toLowerCase())) {
                    String status = currentTask.isCompleted ? "selesai" : "aktif";
                    System.out.printf("| %-4s | %-30s | %-12s | %-12s | %-10s |\n", 
                    currentTask.id, padRight(currentTask.title, 30), currentCat.name, currentTask.deadline, status);
                    searchCount++;
                }
                currentTask = currentTask.next;
            }
            currentCat = currentCat.nextSibling;
        }

        printLine();
        System.out.println(searchCount + " tugas ditemukan.");

        // FITUR 4: KALENDER DEADLINE
        System.out.println("\n");
        printHeader("KALENDER DEADLINE");

        queue.enqueue(new Task("01", "Buat Laporan Praktikum", "10-12-2025", "Kuliah", false));
        queue.enqueue(new Task("02", "Edit Video Edukasi Final", "13-12-2025", "Kuliah", false));
        queue.enqueue(new Task("03", "Belanja Perlengkapan", "08-12-2025", "Pribadi", false));
        queue.enqueue(new Task("04", "Riset Topik Jurnal", "20-12-2025", "Kuliah", false));
        queue.enqueue(new Task("05", "Rapat Umum Keakraban", "10-12-2025", "Organisasi", false));
        queue.enqueue(new Task("06", "Update Database Peserta", "15-12-2025", "Volunteer", false));
        queue.enqueue(new Task("07", "Desain Banner Musyawarah Umum", "09-12-2025", "Organisasi", false));
        queue.enqueue(new Task("08", "Supervisi", "20-12-2025", "Kerja", false));
        queue.enqueue(new Task("09", "Edit Video After Movie Prisma", "25-12-2025", "Organisasi", false));
        queue.enqueue(new Task("10", "Buat Laporan Project", "10-12-2025", "Kuliah", false));

        printTableHeader();
        queue.display(); 
        printLine();

        // FITUR 5: EDIT / DELETE & UNDO
        System.out.println("\n");
        printHeader("DELETE / UNDO / EDIT");

        System.out.println("Skenario: User menghapus tugas '02' (Edit Video)...");
        Task originalTask02 = catKuliah.tasks.findTaskById("02");
        Task deletedTask = new Task(originalTask02.id, originalTask02.title, originalTask02.deadline, originalTask02.category, originalTask02.isCompleted);

        catKuliah.tasks.deleteTask("02");
        System.out.println("-> Tugas dihapus dari list.");
        printLine();
        printTableHeader();
        
        Task checkDeleted = catKuliah.tasks.findTaskById("02");
        if (checkDeleted == null) {
            System.out.println("| ID 02 tidak ditemukan di list Kuliah. Berhasil dihapus.");
        } else {
            System.out.println("| Gagal menghapus ID 02.");
        }
        
        printLine();

        System.out.println("\n[TAMPILAN UNDO]");
        System.out.println("Aksi terakhir : Hapus tugas (ID 02)");
        printLine();
        printTableHeader();
        String deletedStatus = deletedTask.isCompleted ? "selesai" : "aktif";
        System.out.printf("| %-4s | %-30s | %-12s | %-12s | %-10s |\n",
            deletedTask.id, padRight(deletedTask.title, 30), deletedTask.category, deletedTask.deadline, deletedStatus);
        printLine();

        System.out.println("\n");
        System.out.println("Skenario: User menyelesaikan tugas '01' (Laporan Praktikum).");
        Task taskToEdit = catKuliah.tasks.findTaskById("01");

        System.out.println("[SEBELUM]");
        printLine();
        printTableHeader();
        String statusBefore = (taskToEdit != null && taskToEdit.isCompleted) ? "selesai" : "aktif"; 
        System.out.printf("| %-4s | %-30s | %-12s | %-12s | %-10s |\n",
            taskToEdit.id, padRight(taskToEdit.title, 30), taskToEdit.category, taskToEdit.deadline, statusBefore);
        printLine();

        if (taskToEdit != null) {
            taskToEdit.isCompleted = true;
        }

        System.out.println("\n[SESUDAH]");
        printLine();
        printTableHeader();
        String statusStr = (taskToEdit != null && taskToEdit.isCompleted) ? "selesai" : "aktif";

        System.out.printf("| %-4s | %-30s | %-12s | %-12s | %-10s |\n",
            taskToEdit.id, padRight(taskToEdit.title, 30), taskToEdit.category, taskToEdit.deadline, statusStr);
        printLine();
        System.out.println("-> Status tugas berhasil diperbarui.");
        System.out.println("\n");

        // DAFTAR TUGAS SETELAH DI EDIT
        printHeader("DAFTAR TUGAS");
        printTableHeader();
        displayAllTasksSorted(); 

        printLine();
        System.out.println("Total tugas : " + calculateTotalTasks());
    }

}