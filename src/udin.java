package src;
// import src.matrix;
// import java.util.Scanner;

public class udin {
    public static void main(String[] args){
        Matrix n;
        Matrix m = new Matrix();
        m.bacaMatriks();
        System.out.println("Original: ");
        m.printMatriks();
        System.out.println("Tes:");
        n = Matrix.getSPLGaussJordan(m);
        // n.printMatriks();
        // print splsol
    }
}
