import java.util.*;
//https://www.tutorialspoint.com/What-does-import-Java-util-in-Java-do
import java.io.*;
//https://www.geeksforgeeks.org/java-io-input-output-in-java-with-examples/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Matriks{
  public static final double decPoint = 10000000000d;//membuat konstanta berjenis double dan bersifat dapat dirubah di dalam class
  
  /* ********** PENDEFINISIAN ********** */
  
  
  InputStreamReader isr = new InputStreamReader(System.in);
  BufferedReader scanner = new BufferedReader(isr);
  Scanner scanner = new Scanner(System.in); // membuat input 
    public int MinimalKolom = 0; //mendefinisikan konstanta minimal kolom
    public int MinimalBaris = 0; //mendefinisikan konstanta minimal baris
    public int MaximalKolom = 100; //mendefinisikan konstanta maximal baris
    public int MaximalBaris = 100; //mendefinisikan konstanta maximal baris
    public int Baris; // mendefinisikan Baris matriks dengan Baris
    public int Kolom; // mendefinisikan Kolom matriks dengan Kolom
    public double[][] Matrix; // mendefinisikan isi array, jenisnya double agar threadsafe
    // cara untuk memanggil isi matriks-nya matriks.Matrix[Baris][Kolom]
    // Indeks Baris & Kolom mulai dari 0 sesuai MinimalBaris dan MinimalKolom
  
  /* ********** KONSTRUKTOR ********** */
  //akan berisi beberapa fungsi dari class Matriks.
  
}
//fungsi definisi matriks
