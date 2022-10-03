

public class Nilai {
    public static final String varList = "abcdefghijklmnopqrstuvwxyz";//definisikan semua abjad

    public double val;
    public int var;
    public String varName;

    public Nilai (double val, int var) {
        this.val = val;
        this.var = var;
        this.varName = GetNamaVar(var);
    }

    public static String GetNamaVar (int var) {//definisikan fungsi ambil nama variabel agar bisa dipanggil di expression.
        if (var == 0) {
            return "";
        } else {
            return varList.substring(var - 1, var);
        }
    }

    public static int GetIndeksVar (String var) {
        return varList.indexOf(var) + 1;
    }
}
