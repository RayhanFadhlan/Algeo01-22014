package src;

public class Inverse {
    public static void inverseIdentity(){
        Matrix fileMatrix = Main.masukMatrix();
        System.out.println("====== HASIL ======");
        Matrix result = MainOperation.inverseMatrixIdentity(fileMatrix);
        result.printMatriks();
    } 

    public static void inverseKofaktorAdjoin(){
        Matrix fileMatrix = Main.masukMatrix();
        System.out.println("====== HASIL ======");
        Matrix result = MainOperation.getInverseADJ(fileMatrix);
        result.printMatriks();
    }
}
