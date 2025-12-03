import java.util.Scanner;

public class SistemManajemenParkir {
    class Kendaraan {
        String platNomor;
        String jenisKendaraan;
        String tipeKendaraan;
        String slotParkir;
        String waktuMasuk;
        int biayaParkir;
    }
    class SlotParkir{
        int slotNumber;
        boolean isOccupied;
    }
    static void Menu(){
        System.out.println("+===== Sistem Manajemen Parkir =====+");
        System.out.println("| 1. Masuk Parkir                   |");
        System.out.println("| 2. Keluar Parkir                  |");
        System.out.println("| 3. Tampilkan Status Parkir        |");
        System.out.println("| 4. Keluar Aplikasi                |");
        System.out.println("+===================================+");
        System.out.print("Pilih menu: ");
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Kendaraan[] kendaraan = new Kendaraan[100];

        int pilihan;
        do {
            Menu();
            pilihan = input.nextInt();
            switch (pilihan) {
                case 1:
                    System.out.println("Fitur Masuk Parkir belum diimplementasikan.");
                    break;
                case 2:
                    System.out.println("Fitur Keluar Parkir belum diimplementasikan.");
                    break;
                case 3:
                    System.out.println("Fitur Tampilkan Status Parkir belum diimplementasikan.");
                    break;
                case 4:
                    System.out.println("Keluar dari aplikasi. Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 4);
    }
    class jawa{
        boolean hitam = false;
    }
}




