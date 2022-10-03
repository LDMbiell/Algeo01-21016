import java.util.*;

public class SPL{
static Scanner scanner = new Scanner(System.in);

    public static void SPLGauss(String suffix) {
        System.out.print("Masukan m : ");//input barisan dari matriks
        int m = scanner.nextInt();
        System.out.print("Masukan n : ");//input kolom dari matriks
        int n = scanner.nextInt();
        Matriks M = new Matriks(m, n + 1);//memanggil fungsi Matriks(int baris,int kolom) dari Matriks.java

        M.BacaMatriks();//memanggil fungsi BacaMat dari Matriks.java

        SPLGauss(M, suffix);//memanggil fungsi di bawah SPLGauss(Matriks M, String suffix)

    }
  
  
  
  public static void SPLGauss(Matriks M, String suffix) {

        boolean tidakBernilai = false;//melakukan pengecekan bahwa matriks memiliki nilai

        int n = M.GetLastIdxKol(M);
        M = Matriks.EliminasiGauss(M);

        Expression[] hasil = new Expression[n];
        for (int i = 0; i < hasil.length; i++) {
            hasil[i] = new Expression();
        }

        for (int i = M.GetLastIdxBrs(M); i >= M.GetFirstIdxBrs(M); i--) {
            int idxPertama = -1;
            Expression value = new Expression();
            for (int j = M.GetFirstIdxKol(M); j <= M.GetLastIdxKol(M); j++) {
                if (M.Matrix[i][j] != 0) {
                    if (j != n) {
                        if (hasil[j].IsEmpty()) { // Belum ada nilai
                            if (idxPertama == -1) { // Variabel baru pertama
                                idxPertama = j;
                            } else { // Buat variabel parametrik
                                value.SetVariable(hasil[j].GenerateNewVar(), -M.Matrix[i][j]);
                            }
                        } else { // Sudah ada nilai
                            for (int k = 0; k < Nilai.varList.length(); k++) {
                                value.SetVariable(k,
                                        value.GetVariable(k).val - (hasil[j].GetVariable(k).val * M.Matrix[i][j]));
                            }
                        }
                    } else {
                        value.SetConst(value.GetConst() + M.Matrix[i][j]);
                    }
                }
            }
            if (idxPertama == -1) {
                if (M.Matrix[i][n] != 0) {
                    tidakBernilai = true;
                }
            } else {
                hasil[idxPertama] = value;
            }
        }

        /*
         * ************************************** OUTPUT
         * **************************************
         */

        // Cek bernilai atau tidak
        if (tidakBernilai) {
            System.out.println("SPL tersebut tidak memiliki solusi");
        } else {
            for (int i = 0; i < hasil.length; i++) {
                System.out.println(suffix + (i + 1) + " = " + hasil[i].ToString());
            }
        }

        Expression.ResetVars();

    }
  
  
  public static void SPLGaussJordan(String suffix) {
        System.out.print("Masukan m : ");
        int m = scanner.nextInt();
        System.out.print("Masukan n : ");
        int n = scanner.nextInt();
        Matriks M = new Matriks(m, n + 1);

        M.BacaMatriks();

        SPLGaussJordan(M, suffix);
    }
       public static void SPLGaussJordan(Matriks M, String suffix) {

        boolean tidakBernilai = false;

        int m = M.Baris;
        int n = M.Kolom - 1;

        M = Matriks.EliminasiGaussJordan(M);

        Expression[] hasil = new Expression[n];
        for (int i = 0; i < hasil.length; i++) {
            hasil[i] = new Expression();
        }

        for (int i = M.GetLastIdxBrs(M); i >= M.GetFirstIdxBrs(M); i--) {
            int idxPertama = -1;
            Expression value = new Expression();
            for (int j = M.GetFirstIdxKol(M); j <= M.GetLastIdxKol(M); j++) {
                if (M.Matrix[i][j] != 0) {
                    if (j != n) {
                        if (hasil[j].IsEmpty()) { // Belum ada nilai
                            if (idxPertama == -1) { // Variabel baru pertama
                                idxPertama = j;
                            } else { // Buat variabel parametrik
                                value.SetVariable(hasil[j].GenerateNewVar(), -M.Matrix[i][j]);
                            }
                        } else { // Sudah ada nilai
                            for (int k = 0; k < Nilai.varList.length(); k++) {
                                value.SetVariable(k,
                                        value.GetVariable(k).val - (hasil[j].GetVariable(k).val * M.Matrix[i][j]));
                            }
                        }
                    } else {
                        value.SetConst(value.GetConst() + M.Matrix[i][j]);
                    }
                }
            }
            if (idxPertama == -1) {
                if (M.Matrix[i][n] != 0) {
                    tidakBernilai = true;
                }
            } else {
                hasil[idxPertama] = value;
            }
        }

        /*
         * ************************************** OUTPUT
         * **************************************
         */

        // Cek bernilai atau tidak
        if (tidakBernilai) {
            System.out.println("SPL tersebut tidak memiliki solusi");
        } else {
            for (int i = 0; i < hasil.length; i++) {
                System.out.println(suffix + (i + 1) + " = " + hasil[i].ToString());
            }
        }

        Expression.ResetVars();

    }

    public static void SPLInvers(String suffix) {
        System.out.print("Masukan m : ");
        int n = scanner.nextInt();
        Matriks M = new Matriks(n, n + 1);

        M.BacaMatriks();

        SPLInvers(M, suffix);
    }

    public static void SPLInvers(Matriks M, String suffix) {
        int n = M.Baris;

        // SPLIT INPUT
        Matriks A = new Matriks(n, n);
        for (int i = A.GetFirstIdxBrs(A); i <= A.GetLastIdxBrs(A); i++) {
            for (int j = A.GetFirstIdxKol(A); j <= A.GetLastIdxKol(A); j++) {
                A.Matrix[i][j] = M.Matrix[i][j];
            }
        }

        Matriks B = new Matriks(n, 1);
        for (int i = B.GetFirstIdxBrs(B); i <= B.GetLastIdxBrs(B); i++) {
            B.Matrix[i][0] = M.Matrix[i][n];
        }

        // OUTPUT
        Matriks AInvers = new Matriks(n, n);
        if (Matriks.InversGaussJordan(A, AInvers)) {
            Matriks X = Matriks.Kali(AInvers, B); // PROSES SEBELUM OUTPUT

            for (int i = X.GetFirstIdxBrs(X); i <= X.GetLastIdxBrs(X); i++) {
                System.out.println(suffix + (i + 1) + " = " + X.Matrix[i][0]);
            }
        } else {
            System.out.println("SPL tersebut tidak memiliki solusi");
        }

    }

    public static void SPLCramer(String suffix) {
        System.out.print("Masukan m : ");
        int n = scanner.nextInt();
        Matriks M = new Matriks(n, n + 1);

        M.BacaMatriks();

        SPLCramer(M, suffix);
    }

    public static void SPLCramer(Matriks M, String suffix) {
        int n = M.Baris;

        // SPLIT INPUT
        Matriks A = new Matriks(n, n);
        for (int i = A.GetFirstIdxBrs(A); i <= A.GetLastIdxBrs(A); i++) {
            for (int j = A.GetFirstIdxKol(A); j <= A.GetLastIdxKol(A); j++) {
                A.Matrix[i][j] = M.Matrix[i][j];
            }
        }

        Matriks B = new Matriks(n, 1);
        for (int i = B.GetFirstIdxBrs(B); i <= B.GetLastIdxBrs(B); i++) {
            B.Matrix[i][0] = M.Matrix[i][n];
        }

        // PROSES & OUTPUT
        double detA = A.DeterminanCofaktor(A);
        if (detA != 0) {
            double[] x = new double[n];
            for (int j = A.GetFirstIdxKol(A); j <= A.GetLastIdxKol(A); j++) {
                Matriks Aj = new Matriks(n, n);
                Matriks.Copy(A, Aj);
                for (int i = A.GetFirstIdxBrs(M); i <= A.GetLastIdxBrs(A); i++) {
                    Aj.Matrix[i][j] = B.Matrix[i][0];
                }

                x[j] = Aj.DeterminanCofaktor(Aj) / detA;
            }

            // OUTPUT
            for (int i = 0; i < n; i++) {
                System.out.println(suffix + (i + 1) + " = " + x[i]);
            }
        } else {
            System.out.println("SPL tersebut tidak memiliki solusi");
        }
    }
}
