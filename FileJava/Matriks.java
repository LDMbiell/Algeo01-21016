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
}
//fungsi definisi matriks
