package src;

public class Determinant {
    public static void determinantGauss(){
        Matrix fileMatrix = Main.masukMatrix();
        double determinantResult = MainOperation.determinantGaussMatriks(fileMatrix);
        System.out.println("====== HASIL ======");
        System.out.printf("Hasil Determinan (Gauss): %f\n",determinantResult);
    }

    public static void determinantKofaktor(){
        Matrix fileMatrix = Main.masukMatrix();
        double determinantResult = MainOperation.getDeterminantCofactor(fileMatrix);
        System.out.println("====== HASIL ======");
        System.out.printf("Hasil Determinan (Gauss): %f\n",determinantResult);
    }
}
