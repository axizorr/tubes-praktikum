/*
Tugas Besar Praktikum
Kelompok Tukang Parkir - Aplikasi Sistem Manajemen Parkir
- Muhammad Adlan Al-Ghifaari
- Muhammad Riyadh
- Ahmad Zaki Alfaruki
*/
import java.util.Scanner;

// Class utama sistem manajemen parkir
public class SistemManajemenParkir {

    // Class Kendaraan untuk menyimpan data tiap kendaraan
    public static class Kendaraan {
        int slotParkir; // Nomor slot parkir
        String jenisKendaraan; // Jenis kendaraan (mobil/motor)
        String merekKendaraan; // Merek Kendaraan
        String platNomor; // Plat nomor kendaraan
        int durasiJam; // Durasi parkir dalam jam

        // Konstruktor Kendaraan
        public Kendaraan(int slotParkir, String jenisKendaraan, String merekKendaraan, String platNomor,
                int durasiJam) {
            this.slotParkir = slotParkir;
            this.jenisKendaraan = jenisKendaraan;
            this.merekKendaraan = merekKendaraan;
            this.platNomor = platNomor;
            this.durasiJam = durasiJam;
        }
    }

    // Method untuk menampilkan menu utama
    static void menu() {
        System.out.println("+===== Sistem Manajemen Parkir =====+");
        System.out.println("| 1. Masuk Parkir                   |");
        System.out.println("| 2. Edit Status Parkir             |");
        System.out.println("| 3. Keluar Parkir                  |");
        System.out.println("| 4. Cari Kendaraan                 |");
        System.out.println("| 5. Tampilkan Slot Parkir          |");
        System.out.println("| 6. Keluar Aplikasi                |");
        System.out.println("+===================================+");
        System.out.print("Pilih menu: ");
    }

    // Method untuk kendaraan masuk parkir
    static void masukParkir(Kendaraan[] data) {
        Scanner input = new Scanner(System.in);

        // Masukkan Data Kendaraan
        System.out.print("Masukkan Plat Nomor (DXXXXABC): ");
        String plat = input.nextLine();

        System.out.print("Masukkan Jenis Kendaraan (motor/mobil): ");
        String jenis = input.nextLine().toLowerCase();

        while (!jenis.equals("motor") && !jenis.equals("mobil")) {
            System.out.print("Jenis tidak valid! Masukkan (motor/mobil): ");
            jenis = input.nextLine().toLowerCase();
        }

        System.out.print("Masukkan Merek Kendaraan: ");
        String merek = input.nextLine();

        int start, end;

        // Menentukan batas slot parkir untuk motor / mobil
        if (jenis.equals("motor")) {
            start = 0;
            end = 9;
        } else {
            start = 10;
            end = 19;
        }

        int slotKosong = -1;

        // Mencari slot kosong pertama untuk jenis kendaraan yang dipilih
        for (int i = start; i <= end; i++) {
            if (data[i] == null) {
                slotKosong = i;
                break;
            }
        }

        // Mengeluarkan error jika slot parkir untuk jenis kendaraan tersebut sudah penuh
        if (slotKosong == -1) {
            System.out.println("Maaf, slot parkir untuk " + jenis + " sudah penuh.");
            return;
        }

        // Membuat data kendaraan baru jika ketemu slot kosong
        data[slotKosong] = new Kendaraan(slotKosong + 1, jenis, merek, plat, 0);

        System.out.println("Kendaraan berhasil masuk!");
        System.out.println("Slot Parkir : " + (slotKosong + 1));
    }

    // Method untuk mengedit data kendaraan di slot tertentu
    public static void editParkir(Kendaraan[] data) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukan slot yang mau di edit (0 untuk kembali): ");
        int slot = input.nextInt();
        input.nextLine();

        // Logika untuk kembali ke menu utama / menangani jika user menginput slot parkir diluar batas
        if (slot == 0) {
            System.out.println("Kembali ke menu utama.");
            return;
        }
        if (slot > data.length || slot < 0) {
            System.out.println("Slot parkir tidak valid. (Slot = 1-" + data.length + ")");
            return;
        }

        int index = slot - 1;

        // Jika slot kosong, tidak bisa diedit
        if (data[index] == null) {
            System.out.println("Slot ini masih kosong. Pengeditan dibatalkan");
            return;
        }

        // Menyimpan data lama ke variable untuk kemudian diedit
        String plat = data[index].platNomor;
        String jenis = data[index].jenisKendaraan;
        jenis = jenis.substring(0, 1).toUpperCase() + jenis.substring(1);
        String merek = data[index].merekKendaraan;
        int durasi = data[index].durasiJam;

        // Menampilkan data sebelumnya
        System.out.println("\n=== Data Sebelumnya ===");
        System.out.println("Plat Nomor      : " + plat);
        System.out.println("Jenis Kendaraan : " + jenis);
        System.out.println("Merek Kendaraan : " + merek);
        System.out.println("Durasi (jam)    : " + durasi);

        int pilihan = -1;
        do {
            // Menu edit
            System.out.println("\n=== Pilih Data yang mau diedit ===");
            System.out.println("1. Plat Nomor");
            System.out.println("2. Jenis Kendaraan");
            System.out.println("3. Merek Kendaraan");
            System.out.println("4. Durasi (jam)");
            System.out.println("5. Keluar Pengeditan");
            System.out.print("Pilihan: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Plat Nomor baru: ");
                    plat = input.nextLine();
                    break;
                case 2:
                    System.out.print("Jenis kendaraan baru (mobil/motor): ");
                    jenis = input.nextLine();
                    while (!jenis.equals("mobil") && !jenis.equals("motor")) {
                        System.out.println("Jenis tidak valid!");
                        System.out.print("Masukkan lagi (mobil/motor): ");
                        jenis = input.nextLine();
                    }
                    break;
                case 3:
                    System.out.print("Plat Nomor baru: ");
                    merek = input.nextLine();
                    break;
                case 4:
                    System.out.print("Durasi (jam) baru: ");
                    durasi = input.nextInt();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);

        // Update data kendaraan
        data[index].platNomor = plat;
        data[index].jenisKendaraan = jenis;
        data[index].merekKendaraan = merek;
        data[index].durasiJam = durasi;

        System.out.println("\nData kendaraan berhasil diperbarui!");
    }

    // Method kendaraan keluar parkir
    public static void keluarParkir(Kendaraan[] data, int slot) {
        int index = slot - 1;

        // Validasi slot jika keluar batas
        if (index < 0 || index >= data.length) {
            System.out.println("Slot tidak valid.");
            return;
        }

        if (data[index] == null) {
            System.out.println("Slot " + slot + " tidak terisi.");
            return;
        }

        // Menampilkan info keluar parkir
        System.out.println("Kendaraan dengan plat " + data[index].platNomor + " telah keluar dari slot " + slot);
        System.out.println("Total biaya parkir: Rp " +
                hargaParkir(data[index].durasiJam, data[index].jenisKendaraan));

        // Mengosongkan slot
        data[index] = null;
    }

    // Method untuk mencari kendaraan berdasarkan plat nomor
    static void cariKendaraan(Kendaraan[] data, String plat) {
        for (int i = 0; i < data.length; i++) {

            if (data[i] != null && data[i].platNomor.equalsIgnoreCase(plat)) {

                String jenis = data[i].jenisKendaraan;
                // Mengubah huruf pertama dari jenis (motor/mobil) menjadi huruf kapital (Motor/Mobil)
                jenis = jenis.substring(0, 1).toUpperCase() + jenis.substring(1);

                // Menampilkan data yang ditemukan
                System.out.println("Kendaraan dengan plat \"" + plat + "\" ditemukan!");
                System.out.println("Slot Parkir   : " + (i + 1));
                System.out.println("Jenis         : " + jenis);
                System.out.println("Merek         : " + data[i].merekKendaraan);
                System.out.println("Durasi (jam)  : " + data[i].durasiJam);
                return;
            }
        }

        // Menampilkan pesan jika kendaraan dengan plat yang dicari tidak ditemukan
        System.out.println("Kendaraan dengan plat \"" + plat + "\" tidak ditemukan.");
    }

    // Method untuk menghitung harga parkir berdasarkan jenis dan durasi
    static int hargaParkir(int durasiJam, String jenis) {
        // Harga per jam setelah satu jam pertama
        int HargaMobil = 3000;
        int HargaMotor = 2000;

        // Harga satu jam pertama
        int Harga1Mobil = 5000;
        int Harga1Motor = 3000;

        // Mereturn total harga parkir berdasarkan jenis kendaraan
        if (jenis.equals("motor")) {
            if(durasiJam == 0){
                return Harga1Motor;
            } else{
                return Harga1Motor + (HargaMotor * (durasiJam - 1));
            }
        } else {
            if(durasiJam == 0){
                return Harga1Mobil;
            } else{
                return Harga1Mobil + (HargaMobil * (durasiJam - 1));
            }
        }
    }

    // Method untuk menampilkan semua slot parkir
    static void tampilkanSlotParkir(Kendaraan[] data) {
        Scanner input = new Scanner(System.in);

        System.out.println("============================== Status Semua Slot Parkir ==============================");
        for (int i = 0; i < data.length; i++) {
            System.out.printf("Slot %-2d: ", i + 1);

            if (data[i] == null) {
                System.out.printf("Kosong | Plat: %-9s | Jenis: %-7s | Merek: %-10s | Durasi: %-6s\n",
                        "-", "-", "-", "-");
            } else {
                String jenis = data[i].jenisKendaraan;
                jenis = jenis.substring(0, 1).toUpperCase() + jenis.substring(1);

                System.out.printf("Terisi | Plat: %-9s | Jenis: %-7s | Merek: %-10s | Durasi: %-6s\n",
                        data[i].platNomor, jenis, data[i].merekKendaraan, data[i].durasiJam + " jam");
            }
        }

        int pilihan;
        // Pilihan untuk memfilter tampilan data parkir
        do {
            System.out.println("\n1. Filter Berdasarkan Status Slot");
            System.out.println("2. Filter Berdasarkan Jenis Kendaraan");
            System.out.println("3. Keluar");
            System.out.print("> ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan status slot (terisi/kosong): ");
                    String status = input.nextLine();
                    filterStatusSlot(data, status);
                    break;

                case 2:
                    System.out.print("Masukkan jenis kendaraan (motor/mobil): ");
                    String jenis = input.nextLine();
                    filterJenisKendaraan(data, jenis);
                    break;

                case 3:
                    System.out.println("Kembali ke menu utama...");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 3);
    }

    // Method untuk memfilter data parkir berdasarkan status slot
    static void filterStatusSlot(Kendaraan[] data, String status) {
        System.out.println("============= Hasil Filter Status Slot =============");

        for (int i = 0; i < data.length; i++) {
            boolean terisi = data[i] != null;

            if ((status.equalsIgnoreCase("terisi") && terisi) ||
                    (status.equalsIgnoreCase("kosong") && !terisi)) {

                System.out.printf("Slot %-2d: ", i + 1);

                if (!terisi) {
                    System.out.printf("Kosong | Plat: %-9s | Jenis: %-7s | Merek: %-10s | Durasi: %-6s\n",
                            "-", "-", "-", "-");
                } else {
                    String jenis = data[i].jenisKendaraan;
                    jenis = jenis.substring(0, 1).toUpperCase() + jenis.substring(1);

                    System.out.printf("Terisi | Plat: %-9s | Jenis: %-7s | Merek: %-10s | Durasi: %-6s\n",
                            data[i].platNomor, jenis, data[i].merekKendaraan, data[i].durasiJam + " jam");
                }
            }
        }
    }

    // Method untuk memfilter data parkir berdasarkan jenis kendaraan
    static void filterJenisKendaraan(Kendaraan[] data, String jenisFilter) {
        System.out.println("=========== Hasil Filter Jenis Kendaraan ===========");

        for (int i = 0; i < data.length; i++) {
            if (data[i] != null && data[i].jenisKendaraan.equalsIgnoreCase(jenisFilter)) {

                String jenis = data[i].jenisKendaraan;
                jenis = jenis.substring(0, 1).toUpperCase() + jenis.substring(1);

                System.out.printf("Slot %-2d: Terisi | Plat: %-9s | Jenis: %-7s | Merek: %-10s | Durasi: %-6s\n",
                        i + 1, data[i].platNomor, jenis,
                        data[i].merekKendaraan, data[i].durasiJam + " jam");
            }
        }
    }

    // Method main (program utama)
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Array kendaraan dengan kapasitas 20 slot
        Kendaraan[] kendaraan = new Kendaraan[20];

        // Data awal kendaraan
        kendaraan[11] = new Kendaraan(1, "mobil", "Avanza", "B1234CD", 2);
        kendaraan[2] = new Kendaraan(3, "motor", "RX King", "D5678EF", 3);
        kendaraan[14] = new Kendaraan(5, "mobil", "Ferrari", "F9012GH", 1);
        kendaraan[7] = new Kendaraan(8, "motor", "Scoopy", "H3456IJ", 4);
        kendaraan[18] = new Kendaraan(11, "mobil", "BMW", "J7890KL", 2);
        kendaraan[3] = new Kendaraan(16, "motor", "Honda Beat", "L2345MN", 5);

        int pilihan;
        // Pengulangan tampilan menu selama user belum keluar dari aplikasi
        do {
            menu();
            pilihan = input.nextInt();
            input.nextLine();

            // Pilihan menu
            switch (pilihan) {
                case 1:
                    masukParkir(kendaraan);
                    break;
                case 2:
                    editParkir(kendaraan);
                    break;
                case 3:
                    System.out.print("Masukkan slot parkir yang akan keluar (0 untuk kembali): ");
                    int slotKeluar = input.nextInt();
                    if(slotKeluar == 0) break;
                    keluarParkir(kendaraan, slotKeluar);
                    break;
                case 4:
                    System.out.print("Masukkan plat nomor kendaraan yang ingin dicari (DXXXXABC): ");
                    String plat = input.next();
                    cariKendaraan(kendaraan, plat);
                    break;
                case 5:
                    tampilkanSlotParkir(kendaraan);
                    break;
                case 6:
                    System.out.println("Keluar dari aplikasi. Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
            System.out.println();
        } while (pilihan != 6);

        // Menutup input
        input.close();
    }
}
