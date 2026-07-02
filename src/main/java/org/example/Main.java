
package org.example;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    private static ArrayList<String> daftarNama = new ArrayList<>();
    private static ArrayList<LocalDate> daftarDeadline = new ArrayList<>();
    private static ArrayList<Integer> daftarPrioritas = new ArrayList<>();
    private static ArrayList<Boolean> daftarStatus = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== MENU TODOLIST ===");
            System.out.println("1. Tambah tugas");
            System.out.println("2. Lihat tugas");
            System.out.println("3. Tandai selesai");
            System.out.println("4. Hapus tugas");
            System.out.println("5. Keluar");
            System.out.print("Pilih: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            if (pilihan == 1) {
                ViewAddTodoList();
            } else if (pilihan == 2) {
                ViewTodoList();
            } else if (pilihan == 3) {
                ViewMarkAsDone();
            } else if (pilihan == 4) {
                ViewRemoveTodoList();
            } else if (pilihan == 5) {
                System.out.println("Terima kasih!");
                break;
            } else {
                System.out.println("❌ Pilihan tidak valid!");
            }
        }
        scanner.close();

    }

    public static String[][] showTodoList() {
        if (daftarNama.isEmpty()) {
            return new String[0][0];
        }

        String[][] data = new String[daftarNama.size()][4];
        for (int i = 0; i < daftarNama.size(); i++) {
            data[i][0] = daftarNama.get(i);
            data[i][1] = daftarDeadline.get(i).toString();
            data[i][2] = String.valueOf(daftarPrioritas.get(i));
            data[i][3] = daftarStatus.get(i) ? "[x]" : "[ ]";
        }
        return data;
    }

    public static void addTodoList(String nama, LocalDate deadline, int prioritas) {
        daftarNama.add(nama);
        daftarDeadline.add(deadline);
        daftarPrioritas.add(prioritas);
        daftarStatus.add(false);
    }

    public static void removeTodoList(int index) {
        daftarNama.remove(index);
        daftarDeadline.remove(index);
        daftarPrioritas.remove(index);
        daftarStatus.remove(index);
    }

    public static void markAsDone(int index) {
        daftarStatus.set(index, true);
    }

    public static void ViewTodoList(){
        String[][] data = showTodoList();  // Panggil logic

        if (data.length == 0) {
            System.out.println("Belum ada tugas!");
            return;
        }

        System.out.println("\n=== DAFTAR TUGAS ===");
        for (int i = 0; i < data.length; i++) {
            System.out.println((i + 1) + ". " + data[i][3] + " " + data[i][0] +
                    " (Deadline: " + data[i][1] + ", Prioritas: " + data[i][2] + ")");
        }
    }

    public static void ViewAddTodoList() {
        System.out.println("\n=== TAMBAH TUGAS BARU ===");

        System.out.print("Masukkan nama tugas: ");
        String nama = scanner.nextLine();

        LocalDate deadline = null;
        while (deadline == null) {
            try {
                System.out.print("Masukkan deadline (YYYY-MM-DD): ");
                String inputDeadline = scanner.nextLine();
                deadline = LocalDate.parse(inputDeadline);
            } catch (Exception e) {
                System.out.println("❌ Format deadline salah! Gunakan format YYYY-MM-DD (contoh: 2026-07-15)");
            }
        }

        int prioritas = 0;
        while (prioritas < 1 || prioritas > 5) {
            try {
                System.out.print("Masukkan prioritas (1-5): ");
                prioritas = scanner.nextInt();
                scanner.nextLine();
                if (prioritas < 1 || prioritas > 5) {
                    System.out.println("❌ Prioritas harus antara 1-5!");
                }
            } catch (Exception e) {
                System.out.println("❌ Prioritas harus berupa angka!");
                scanner.nextLine();  // Buang input yang salah
            }
        }

        addTodoList(nama, deadline, prioritas);
        System.out.println("✅ Tugas berhasil ditambahkan!");
    }

    public static void ViewRemoveTodoList() {
        System.out.println("\n=== HAPUS TUGAS ===");

        if (daftarNama.isEmpty()) {  // ← UBAH!
            System.out.println("Belum ada tugas!");
            return;
        }

        ViewTodoList();  // Tampilkan semua tugas dulu

        System.out.print("Masukkan nomor tugas yang akan dihapus: ");
        int nomor = scanner.nextInt();
        scanner.nextLine();

        if (nomor < 1 || nomor > daftarNama.size()) {  // ← UBAH!
            System.out.println("❌ Nomor tugas tidak valid!");
            return;
        }

        // Tampilkan konfirmasi
        System.out.print("Apakah Anda yakin ingin menghapus tugas '" + daftarNama.get(nomor - 1) + "'? (y/n): ");  // ← UBAH!
        char konfirmasi = scanner.next().charAt(0);
        scanner.nextLine();

        if (konfirmasi == 'y' || konfirmasi == 'Y') {
            removeTodoList(nomor - 1);  // Panggil logic
            System.out.println("✅ Tugas berhasil dihapus!");
        } else {
            System.out.println("❌ Penghapusan dibatalkan!");
        }
    }

    public static void ViewMarkAsDone() {
        System.out.println("\n=== TANDAI TUGAS SELESAI ===");

        if (daftarNama.isEmpty()) {  // ← UBAH!
            System.out.println("Belum ada tugas!");
            return;
        }

        ViewTodoList();  // Tampilkan semua tugas dulu

        System.out.print("Masukkan nomor tugas yang sudah selesai: ");
        int nomor = scanner.nextInt();
        scanner.nextLine();

        if (nomor < 1 || nomor > daftarNama.size()) {  // ← UBAH!
            System.out.println("❌ Nomor tugas tidak valid!");
            return;
        }

        int index = nomor - 1;

        // Cek apakah sudah selesai
        if (daftarStatus.get(index)) {  // ← UBAH!
            System.out.println("⚠️ Tugas '" + daftarNama.get(index) + "' sudah selesai!");  // ← UBAH!
            return;
        }

        // Panggil logic untuk tandai selesai
        markAsDone(index);

        System.out.println("✅ Tugas '" + daftarNama.get(index) + "' berhasil ditandai selesai!");  // ← UBAH!
    }

}
