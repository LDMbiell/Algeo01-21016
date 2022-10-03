import java.util.*;

public class SPL{
static Scanner scanner = new Scanner(System.in);

    public static void SPLGauss(String suffix) {
        System.out.print("Masukan m : ");//input barisan dari matriks
        int m = scanner.nextInt();
        System.out.print("Masukan n : ");//input kolom dari matriks
        int n = scanner.nextInt();
        Matriks M = new Matriks(m, n + 1);//memanggil fungsi Matriks(int baris,int kolom) dari Matriks.java

        M.BacaMat();//memanggil fungsi BacaMat dari Matriks.java

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
                if (M.Mat[i][j] != 0) {
                    if (j != n) {
                        if (hasil[j].IsEmpty()) { // Belum ada nilai
                            if (idxPertama == -1) { // Variabel baru pertama
                                idxPertama = j;
                            } else { // Buat variabel parametrik
                                value.SetVariable(hasil[j].GenerateNewVar(), -M.Mat[i][j]);
                            }
                        } else { // Sudah ada nilai
                            for (int k = 0; k < Nilai.varList.length(); k++) {
                                value.SetVariable(k,
                                        value.GetVariable(k).val - (hasil[j].GetVariable(k).val * M.Mat[i][j]));
                            }
                        }
                    } else {
                        value.SetConst(value.GetConst() + M.Mat[i][j]);
                    }
                }
            }
            if (idxPertama == -1) {
                if (M.Mat[i][n] != 0) {
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

        M.BacaMat();

        SPLGaussJordan(M, suffix);
    }
  
}
