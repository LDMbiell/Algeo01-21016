import java.util.*;
class Menu {
  //memberikan print kepada pengguna untuk memilih pilihan operasi matriks
 public static int menu() throws NoSuchElementException {
        Scanner scan = new Scanner(System.in);
        int result = 0;
   
   
        System.out.println("\n\n---Selamat Datang di AntiTuru Kalkulator Matriks---");
        System.out.println("---Berikut pilihan operasi yang dapat kamu pilih---\n============================");
        System.out.println("           Menu");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Matriks Kofaktor");
        System.out.println("5. Adjoin");
        System.out.println("6. Interpolasi Polinom");
        System.out.println("7. Keluar\n============================");

        System.out.print("\nSilakan Masukkan Pilihan: ");

        result = scan.nextInt();

        return result;
    } 
}
