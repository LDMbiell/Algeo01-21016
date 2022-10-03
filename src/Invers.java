class Invers {
    //inverse menggunakaan GJordan
    public static void InversGaussJordan() {
        Matriks M1 = Input.InputMatriks(true);//cek matriks m=n
        Matriks M2 = new Matriks(M1.Matrix);
        Matriks.InversGaussJordan(M1, M2);

        M2.TulisMatriks();
    }
    //mencari adjoin
    public static void InversAdjoin() {
        Matriks M1 = Input.InputMatriks(true);//cek matriks m=n
        Matriks M2 = new Matriks(M1.Matrix);
        M1.InversAdjoin(M1, M2);

        M2.TulisMatriks();
    }
}
