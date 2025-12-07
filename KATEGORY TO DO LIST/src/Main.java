public class Main {
    
    public static void main(String[] args) {

        TreeCategory category = new TreeCategory("Root");

        category.addCategory("Root", "Belajar");
        category.addCategory("Belajar", "Algoritma");
        category.addCategory("Belajar", "Jaringan");
        category.addCategory("Algoritma", "Tugas Modul 3");

        System.out.println("Daftar Kategori:");
        category.display(category.getRoot(), "");
    }
}
