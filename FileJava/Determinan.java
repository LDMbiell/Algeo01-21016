class Determinan {
    public static void DeterminanOBE() {
        Matriks M = Input.InputMatriks(true);
        System.out.println("Hasil operasi Determinannya menggunakan cara OBE adalah " + M.DeterminanOBE(M));
    }

    public static void DeterminanCofaktor() {
        Matriks M = Input.InputMatriks(true);
        System.out.println("Hasil operasi Determinannya menggunakan cara kofaktor adalah " + M.DeterminanCofaktor(M));
    }
}
