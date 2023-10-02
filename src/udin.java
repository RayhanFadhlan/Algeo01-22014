package src;
// import src.matrix;
// import java.util.Scanner;

public class udin {
    public static void main(String[] args){
        Matrix n;
        Matrix m = new Matrix();
        m.bacaMatriks(false);
        System.out.println("Original: ");
        m.printMatriks();
        System.out.println("Tes:");
        n = m.getAdjoin();
        n.printMatriks();
        // n.printMatriks();
        // print splsol
    }
}
