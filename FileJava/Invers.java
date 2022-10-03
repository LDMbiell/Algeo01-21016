class Invers {
    //inverse menggunakaan GJordan
    public static void InversGaussJordan() {
        Matriks M1 = Input.InputMatriks(true);//cek matriks m=n
        Matriks M2 = new Matriks(M1.Mat);
        Matriks.InversGaussJordan(M1, M2);

        M2.TulisMat();
    }
    //mencari adjoin
    public static void InversAdjoin() {
        Matriks M1 = Input.InputMatriks(true);//cek matriks m=n
        Matriks M2 = new Matriks(M1.Mat);
        M1.InversAdjoin(M1, M2);

        M2.TulisMat();
    }
}
