import java.util.*;
//https://www.tutorialspoint.com/What-does-import-Java-util-in-Java-do
import java.io.*;
//https://www.geeksforgeeks.org/java-io-input-output-in-java-with-examples/


public class Matriks{
  public static final double decPoint = 10000000000d;//membuat konstanta berjenis double dan bersifat dapat dirubah di dalam class
  
  /* ********** PENDEFINISIAN ********** */
  
  Scanner scanner = new Scanner(System.in); // membuat input 
    public int MinimalKolom = 0; //mendefinisikan konstanta minimal kolom
    public int MinimalBaris = 0; //mendefinisikan konstanta minimal baris
    public int MaximalKolom = 100; //mendefinisikan konstanta maximal baris
    public int MaximalBaris = 100; //mendefinisikan konstanta maximal baris
    public int Baris; // mendefinisikan Baris matriks dengan Baris
    public int Kolom; // mendefinisikan Kolom matriks dengan Kolom
    public double[][] Matrix; // mendefinisikan isi array, jenisnya double agar threadsafe
    // cara untuk memanggil isi matriks-nya yaitu matriks.Matrix[Baris][Kolom]
    // Indeks Baris & Kolom mulai dari 0 sesuai MinimalBaris dan MinimalKolom
  
  /* ********** KONSTRUKTOR ********** */
  //akan berisi beberapa fungsi dari class Matriks.
  public Matriks(int baris, int kolom) {
    this.Baris = baris;
    this.Kolom = kolom;
    this.Matrix = new double[baris][kolom];
 }

  public Matriks(double[][] Matrix) {
    // Konstruktor dari tabel matrix i:baris, j:kolom
    this.Baris = Matrix.length;
    this.Kolom = Matrix[0].length;
    this.Matrix = new double[Matrix.length][Matrix[0].length];
    for (int i = 0; i < Matrix.length; i++)
        for (int j = 0; j < Matrix[0].length; j++)
            this.Matrix[i][j] = Matrix[i][j];
}
 
  public Matriks(String file_name) throws FileNotFoundException {
    // Membaca Matriks dari sebuah file
    ArrayList<ArrayList<Double>> Matrix = new ArrayList<ArrayList<Double>>();
    File file = new File(file_name);
    Scanner input = new Scanner(file);
    int Baris = -1;
    int Kolom = -1;
    while (input.hasNextLine()) {
        Baris++;
        Matrix.add(new ArrayList<Double>());
        String baris = input.nextLine();
        Scanner scanBaris = new Scanner(baris);
        while (scanBaris.hasNextDouble()) {
            Double element = scanBaris.nextDouble();
            Matrix.get(Baris).add(element);
        }
    }

    if (Baris == 0) {
        System.out.println("Tidak dapat membaca file yang dipilih");
    } else {
        Kolom = Matrix.get(0).size();
        this.Matrix = new double[Matrix.size()][Matrix.get(0).size()];
        for (int i = MinimalBaris; i <= Baris; i++) {
            for (int j = MinimalKolom; j < Kolom; j++) {
                this.Matrix[i][j] = Matrix.get(i).get(j);
            }
        }
        this.Baris = Baris + 1;
        this.Kolom = Kolom;
    }
}
  /* ********** SELEKTOR ********** */
    public int GetFirstIdxBrs(Matriks M) {
        return MinimalBaris;//MinimalBaris sudah didefinisikan di atas
    }

    public int GetFirstIdxKol(Matriks M) {
        return MinimalKolom;//MinimalKolom sudah didefinisikan di atas
    }

    public int GetLastIdxBrs(Matriks M) {
        return M.Baris - 1;//Mengambil index -1 dari Baris
    }

    public int GetLastIdxKol(Matriks M) {
        return M.Kolom - 1;//Mengambil index -1 dari Baris
    }

    public int NbElmt(Matriks M) {
        return (M.Baris * M.Kolom);//Mengambil total/jumlah element matriks
    }
  
  /* ********** BENTUK MATRIKS ********** */
    public static Matriks Identitas(int N) {// N adalah jumlah baris atau jumlah kolom 
        Matriks I = new Matriks(N, N);//membuat matriks N x N dengan element 0. karena matriks identitas harus berbentuk persegi alias m=n
        for (int i = 0; i < N; i++) //menyeleksi matriks [1][1], [2][2], [3][3], [n][n]
            I.Matrix[i][i] = 1; //mengganti element dengan angka 1
        return I;//return matriks identitas
    }
  /* ********** INPUT/OUTPUT MATRIKS ********** */
    // Baca Matriks
    public void BacaMat() {
        System.out.println("Silahkan inputkan Matriks : ");
        for (int i = 0; i < this.Baris; i++) {
            for (int j = 0; j < this.Kolom; j++) {
                this.Matrix[i][j] = scanner.nextDouble();
            }
        }
        System.out.print("\n");
    }

    // Menulis Matriks
    public void TulisMat() {
        for (int i = 0; i < this.Baris; i++) {
            for (int j = 0; j < this.Kolom; j++) {
                this.Matrix[i][j] += 0; // Agar tidak ada (-0)
                System.out.printf("%.2f ", this.Matrix[i][j]);
            }
            System.out.print("\n");
        }
    }

    /* ********** OPERASI MATRIKS DASAR ********** */
    public static Matriks Kali(Matriks M, double k) {
        Matriks out = new Matriks(M.Baris, M.Kolom);
        for (int i = 0; i < M.Baris; i++) {
            for (int j = 0; j < M.Kolom; j++) {
                out.Matrix[i][j] = M.Matrix[i][j] * k;
            }
        }
        return out;
    }

    public void Kali(double k) {
        this.Matrix = Kali(this, k).Matrix;
    }

    public static Matriks Kali(Matriks M, Matriks N) {
        Matriks out = new Matriks(M.Baris, N.Kolom);

        for (int i = 0; i < out.Baris; i++) {
            for (int j = 0; j < out.Kolom; j++) {
                out.Matrix[i][j] = 0;
                for (int k = 0; k < M.Kolom; k++) {
                    out.Matrix[i][j] += M.Matrix[i][k] * N.Matrix[k][j];
                }
            }
        }

        return out;
    }

    /* ********** OPERASI BARIS ELEMENTER ********** */
    // Nuker Baris
    public void Swap(int BrsA, int BrsB) {
        double[] temp = Matrix[BrsA];
        Matrix[BrsA] = Matrix[BrsB];
        Matrix[BrsB] = temp;
    }

    public void KaliBaris(int Baris, double x) {
        // Perkalian Baris dengan sebuah bilangan real
        for (int i = 0; i < Kolom; i++) {
            Matrix[Baris][i] *= x;
        }
    }

    public void PlusBaris(int BarisA, int BarisB, double k) {
        // Baris ke-a ditambah dengan bilangan di baris ke-b
        for (int i = 0; i < Kolom; i++) {
            Matrix[BarisA][i] += Matrix[BarisB][i] * k;
        }
    }

    public void PlusBaris(int BarisA, int BarisB) {
        // Baris ke-a ditambah dengan bilangan di baris ke-b
        PlusBaris(BarisA, BarisB, 1);
    }

    public void MinusBaris(int a, int b, double k) {
        // Baris ke-a dikurangi dengan bilangan di baris ke-b * k
        PlusBaris(a, b, -k);
    }

    public void MinusBaris(int a, int b) {
        // Baris ke-a dikurangi dengan bilangan di baris ke-b
        MinusBaris(a, b, 1);
    }

    /* ********** SIFAT MATRIKS ********** */
    public static boolean IsIdentitas(Matriks M) {
        boolean out = true;
        for (int i = 0; i < M.Baris; i++) {
            for (int j = 0; j < M.Kolom; j++) {
                if (!(((i == j) && M.Matrix[i][j] == 1) || ((i != j) && M.Matrix[i][j] == 0))) {
                    out = false;
                }
            }
        }
        return out;
    }

   /* ********** FUNGSI SKALAR ********** */
   // Determinan
    public double DeterminanOBE(Matriks M) {
        Matriks N = Copy(M);

        // Proses mengurutkan baris
        int[] zeroCount = new int[N.Baris];
        int swapCount = 0;
        for (int i = 0; i < N.Baris; i++) { // Kalkulasi jumlah 0
            zeroCount[i] = 0;
            int j = 0;
            while (j < N.Kolom && N.Matrix[i][j] == 0) {
                zeroCount[i]++;
                j++;
            }
        }
        for (int i = 0; i < N.Baris; i++) { // Algoritma Pengurutan
            for (int j = 0; j < N.Baris - 1; j++) {
                if (zeroCount[j] > zeroCount[j + 1]) {
                    int temp;
                    N.Swap(j, j + 1);
                    swapCount++;
                    temp = zeroCount[j];
                    zeroCount[j] = zeroCount[j + 1];
                    zeroCount[j + 1] = temp;
                }
            }
        }
        // Proses mereduksi baris
        int r = 0;

        for (int i = 0; i < N.Baris; i++) {
            // Mencari sel bernilai
            while (i + r < N.Kolom && N.Matrix[i][i + r] == 0) {
                r++;
            }

            if (i + r < N.Kolom) {
                // Pengurangan baris dibawahnya
                for (int j = i + 1; j < N.Baris; j++) {
                    N.MinusBaris(j, i, N.Matrix[j][i + r] / N.Matrix[i][i + r]);

                }
            }
        }

        // Proses menghitung jumlah diagonal
        double det = N.Matrix[0][0];
        for (int i = 1; i < N.Baris; i++) {
            det *= N.Matrix[i][i];
        }
        det *= ((swapCount & 2) == 0) ? 1 : -1;
        return det;
    }

    public double DeterminanCofaktor(Matriks M) {
        /* Prekondisi: M bujur sangkar */
        /* Menghitung nilai determinan M */
        double det;

        if ((M.Baris == 1) && (M.Kolom == 1)) // Basis 1x1
            det = M.Matrix[0][0];
        else { // Rekurens nxn
            det = 0;
            for (int i = GetFirstIdxBrs(M); i <= GetLastIdxBrs(M); i++)
                det += M.Matrix[i][GetFirstIdxKol(M)] * Cofaktor(M, i, GetFirstIdxKol(M));
        }

        return det;
    }

    private double Cofaktor(Matriks M, int i, int j) {
        return DeterminanCofaktor(Minor(M, i, j)) * (((i + j) % 2 == 0) ? 1 : -1);
    }

    /* ********** MANIPULASI MATRIKS ********** */
    private Matriks Minor(Matriks M, int i, int j) {
        // Minor M(i,j) dari matriks M
        Matriks Minor = new Matriks(M.Baris - 1, M.Kolom - 1);
        int iMi, jMi, iM, jM;
        iMi = GetFirstIdxBrs(Minor);
        for (iM = GetFirstIdxBrs(M); iM <= GetLastIdxBrs(M); iM++)
            if (iM != i) {
                jMi = GetFirstIdxKol(Minor);
                for (jM = GetFirstIdxKol(M); jM <= GetLastIdxKol(M); jM++)
                    if (jM != j) {
                        Minor.Matrix[iMi][jMi] = M.Matrix[iM][jM];
                        jMi++;
                    }
                iMi++;
            }
        return Minor;
    }

    public void Transpose() {
        /* I.S. M terdefinisi dan IsBujursangkar(M) */
        /*
         * F.S. M "di-transpose", yaitu setiap elemen M(i,j) ditukar nilainya dengan
         * elemen M(j,i)
         */
        Matriks M1 = new Matriks(this.Kolom, this.Baris);

        for (int i = GetFirstIdxBrs(M1); i <= GetLastIdxBrs(M1); i++) {
            for (int j = GetFirstIdxKol(M1); j <= GetLastIdxKol(M1); j++) {
                M1.Matrix[i][j] = this.Matrix[j][i];
            }
        }

        this.Baris = M1.Baris;
        this.Kolom = M1.Kolom;
        this.Matrix = M1.Matrix;
    }

    public void MatCofaktor() {
        Matriks M = new Matriks(this.Kolom, this.Baris);
        for (int i = GetFirstIdxBrs(this); i <= GetLastIdxBrs(this); i++)
            for (int j = GetFirstIdxKol(this); j <= GetLastIdxKol(this); j++) {
                M.Matrix[i][j] = Cofaktor(this, i, j);
            }
        this.Matrix = M.Matrix;
    }

    public void Adjoin() {
        if (this.NbElmt(this) != 1) {
            this.MatCofaktor();
            this.Transpose();
        } else {
            this.Matrix[0][0] = 1;
        }
    }

    public static boolean InversGaussJordan(Matriks in, Matriks out) {
        // in terdefinisi dan IsBujurSangkar(in), Program menghasilkan invers dari in
        // dengan Eliminasi Gauss-Jordan
        // Jika gagal maka out = in
        Matriks M = Copy(in);

        M = ConcatHorizontally(M, Identitas(M.Baris));
        M = EliminasiGaussJordan(M);

        Matriks N = new Matriks(in.Baris, in.Kolom);
        for (int i = 0; i < N.Baris; i++) {
            for (int j = 0; j < N.Kolom; j++) {
                N.Matrix[i][j] = M.Matrix[i][j];
            }
        }

        if (IsIdentitas(N)) {
            Copy(in, out);

            for (int i = 0; i < out.Baris; i++) {
                for (int j = 0; j < out.Kolom; j++) {
                    out.Matrix[i][j] = M.Matrix[i][j + out.Kolom];
                }
            }
            return true;
        } else {
            Copy(in, out);
            return false;
        }
    }

    public boolean InversAdjoin(Matriks in, Matriks out) {
        Copy(in, out);
        double det = DeterminanCofaktor(out);
        if (det != 0) {
            out.Adjoin();
            out.Kali(1 / det);
            return true;
        } else {
            return false;
        }
    }

    public static Matriks EliminasiGauss(Matriks in) {
        // I.S. M terdefinisi
        // F.S. M diubah menjadi matriks eselonnya
        // Proses: Eliminasi Gauss

        // Inisialisasi
        Matriks M = new Matriks(1, 1);
        M = Copy(in);

        // Proses mengurutkan baris
        int[] zeroCount = new int[M.Baris];
        for (int i = 0; i < M.Baris; i++) { // Kalkulasi jumlah 0
            zeroCount[i] = 0;
            int j = 0;
            while (j < M.Kolom && M.Matrix[i][j] == 0) {
                zeroCount[i]++;
                j++;
            }
        }
        for (int i = 0; i < M.Baris; i++) { // Algoritma Pengurutan
            for (int j = 0; j < M.Baris - 1; j++) {
                if (zeroCount[j] > zeroCount[j + 1]) {
                    int temp;
                    M.Swap(j, j + 1);
                    temp = zeroCount[j];
                    zeroCount[j] = zeroCount[j + 1];
                    zeroCount[j + 1] = temp;
                }
            }
        }

        // Proses mereduksi baris
        int r = 0;

        for (int i = 0; i < M.Baris; i++) {
            // Mencari sel bernilai
            while (i + r < M.Kolom && M.Matrix[i][i + r] == 0) {
                r++;
            }

            if (i + r < M.Kolom) {
                // Ubah angka depan jadi 1
                M.KaliBaris(i, 1 / M.Matrix[i][i + r]);

                // Pengurangan baris dibawahnya
                for (int j = i + 1; j < M.Baris; j++) {
                    if (M.Matrix[j][i + r] != 0) {
                        M.KaliBaris(j, 1 / M.Matrix[j][i + r]);
                        M.MinusBaris(j, i);
                    }
                }
            }
        }
        M.Approximate();

        return M;
    }

    public static Matriks EliminasiGaussJordan(Matriks in) {
        // I.S. M terdefinisi
        // F.S. M diubah menjadi matriks eselon-terreduksinya
        // Proses: Eliminasi Gauss Jordan

        // Proses
        Matriks M = EliminasiGauss(in);
        int r = 0;

        for (int i = 0; i < M.Baris; i++) {
            // Pencarian sel tidak nol
            while (i + r < M.Kolom && M.Matrix[i][i + r] == 0) {
                r++;
            }

            if (i + r < M.Kolom) {

                // Pengurangan baris diatasnya
                for (int j = i - 1; j >= 0; j--) {
                    if (M.Matrix[j][i + r] != 0) {
                        M.MinusBaris(j, i, M.Matrix[j][i + r]);
                    }
                }
            }
        }

        M.Approximate();
        return M;
    }

    public static void Copy(Matriks dari, Matriks ke) {
        // I.S. dari dan ke terdefinisi
        // F.S. ke berisi sama dengan dari
        ke.Baris = dari.Baris;
        ke.Kolom = dari.Kolom;
        ke.Matrix = new double[dari.Baris][dari.Kolom];

        for (int i = 0; i < ke.Baris; i++) {
            for (int j = 0; j < ke.Kolom; j++) {
                ke.Matrix[i][j] = dari.Matrix[i][j];
            }
        }
    }

    // Varian fungsi dari Copy Matriks diatas
    public static Matriks Copy(Matriks dari) {
        Matriks ke = new Matriks(1, 1);
        Copy(dari, ke);
        return ke;
    }

    // Menyambungkan matriks M dan N
    public static Matriks ConcatHorizontally(Matriks M, Matriks N) {
        // M dan N terdefinisi dan jumlah baris sama, fungsi mengoutput hasil gabungan M
        // dan N
        Matriks out = new Matriks(M.Baris, M.Kolom + N.Kolom);

        for (int i = 0; i < out.Baris; i++) {
            for (int j = 0; j < out.Kolom; j++) {
                if (j < M.Kolom) {
                    out.Matrix[i][j] = M.Matrix[i][j];
                } else {
                    out.Matrix[i][j] = N.Matrix[i][j - M.Kolom];
                }
            }
        }

        return out;
    }

    // Menyambungkan matriks M dan N
    public static Matriks ConcatVertically(Matriks M, Matriks N) {
        // M dan N terdefinisi dan jumlah kolom sama, fungsi mengoutput hasil gabungan M
        // dan N
        Matriks out = new Matriks(M.Baris + N.Baris, M.Kolom);

        for (int i = 0; i < out.Baris; i++) {
            for (int j = 0; j < out.Kolom; j++) {
                if (i < M.Baris) {
                    out.Matrix[i][j] = M.Matrix[i][j];
                } else {
                    out.Matrix[i][j] = N.Matrix[i - M.Baris][j];
                }
            }
        }

        return out;
    }

    private static double Approximate(double x) {
        return (Math.round(x * decPoint) / decPoint);
    }

    private void Approximate() {
        for (int i = 0; i < Baris; i++) {
            for (int j = 0; j < Kolom; j++) {
                Matrix[i][j] = Approximate(Matrix[i][j]);
            }
        }
    }

    public static void TulisFile(String file, Matriks M) {
        try {
            File F = new File(file);
            if (!(F.exists())) {
                F.createNewFile();
            }

            FileWriter FF = new FileWriter(file);
            PrintWriter print = new PrintWriter(FF);

            for (int i = 0; i < M.Baris; i++) {
                for (int j = 0; j < M.Kolom; j++) {
                    M.Matrix[i][j] += 0;
                    print.printf("%.2f ", M.Matrix[i][j]);
                }
                print.printf("\n");
            }

            print.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void h() {
        this.Matrix[MinimalBaris][GetLastIdxKol(this)] = 1;
    }
}
