import java.util.*;
import java.io.*;

public class Interpolation {

    public static void Input(String file_name, Matriks Min) throws FileNotFoundException { // Membaca Matriks dari
                                                                                           // sebuah file
        ArrayList<ArrayList<Double>> Mat = new ArrayList<ArrayList<Double>>();
        File file = new File(file_name);
        Scanner input = new Scanner(file);
        int Baris = -1;
        int Kolom = -1;
        while (input.hasNextLine()) {
            Baris++;
            Mat.add(new ArrayList<Double>());
            String baris = input.nextLine();
            Scanner scanBaris = new Scanner(baris);
            while (scanBaris.hasNextDouble()) {
                Double element = scanBaris.nextDouble();
                Mat.get(Baris).add(element);
            }
        }

        if (Baris == 0) {
            System.out.println("Tidak dapat membaca file");
        } else {
            Kolom = Mat.get(0).size();
            Min.Matrix = new double[Mat.size()][Mat.get(0).size()];
            for (int i = 0; i <= Baris; i++) {
                for (int j = 0; j < Kolom; j++) {
                    Min.Matrix[i][j] = Mat.get(i).get(j);
                }
            }
            Min.Baris = Baris + 1;
            Min.Kolom = Kolom;
        }
    }

    public static void Cari() {
        int inputspl = Menu.menuInput();
        Matriks M = new Matriks(1, 1);
        if (inputspl == 1) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Masukan n : ");
            int n = scanner.nextInt();
            M = new Matriks(n, 2);

            M.BacaMatriks();
        } else if (inputspl == 2) {
            String filename = Menu.inputFile();
            try {
                Input(filename, M);
                M.TulisMatriks();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        int n = M.Baris;

        // SPLIT INPUT
        Matriks A = new Matriks(n, n + 1);
        for (int i = A.GetFirstIdxBrs(A); i <= A.GetLastIdxBrs(A); i++) {
            for (int j = A.GetFirstIdxKol(A); j <= A.GetLastIdxKol(A) - 1; j++) {
                A.Matrix[i][j] = Math.pow(M.Matrix[i][0], j);
            }
        }
        for (int i = A.GetFirstIdxBrs(A); i <= A.GetLastIdxBrs(A); i++) {
            A.Matrix[i][n] = M.Matrix[i][1];
        }

        SPL.SPLGauss(A, "a");

    }
}
