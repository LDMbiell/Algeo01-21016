import java.util.*;
class Menu {
  //memberikan print kepada pengguna untuk memilih pilihan operasi matriks
 public static int menu() throws NoSuchElementException {
        Scanner s = new Scanner(System.in);
        int result = 0;

        System.out.println("\n\n---Selamat Datang di AntiTuru Kalkulator Matriks---");
        System.out.println("---Berikut pilihan operasi yang dapat kamu pilih---");
        System.out.println("============================");
        System.out.println("             Menu");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Matriks Kofaktor");
        System.out.println("5. Adjoin");
        System.out.println("6. Interpolasi Polinom");
        System.out.println("7. Keluar\n============================");

        System.out.print("\nSilakan Masukkan Pilihan: ");

        result = s.nextInt();

        return result;
    }

    public static int menuInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n\n============================");
        System.out.println("             Menu");
        System.out.println("1. Input dari Keyboard");
        System.out.println("2. Input dari File\n============================");
        System.out.print("\nSilakan Masukkan Pilihan: ");

        int result = scan.nextInt();

        return result;
    }

    public static String inputFile() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\n\n============================\nMasukkan nama file(.txt): \n============================");

        String result = scan.nextLine();

        return result;
    }

    public static int spl() {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("\n\n============================");
        System.out.println("             Menu");
        System.out.println("1. Metode Eliminasi Gauss");
        System.out.println("2. Metode Eliminasi Gauss-Jordan");
        System.out.println("3. Metode Matriks Balikan");
        System.out.println("4. Kaidah Cramer\n============================");
        System.out.print("\nSilakan Masukkan Pilihan: ");

        int result = scan.nextInt();

        return result;
    }
  
  public static int determinan() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n\n============================");
        System.out.println("             Menu");
        System.out.println("1. Metode Operasi Baris Elementer(OBE)");
        System.out.println("2. Metode Matriks Kofaktor\n============================");
        System.out.print("\nSilakan Masukkan Pilihan: ");

        int result = scan.nextInt();

        return result;
    }

    public static int invers() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n\n============================");
        System.out.println("             Menu");
        System.out.println("1. Metode Adjoin");
        System.out.println("2. Metode Eliminasi Gauss-Jordan\n============================");
        System.out.print("\nSilakan Masukkan Pilihan: ");

        int result = scan.nextInt();

        return result;
    }
}
