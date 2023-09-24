package src;
// import src.matrix;
// import java.util.Scanner;

public class udin {
    public static void main(String[] args){
        matrix m = new matrix();
        m.bacaMatriks();
        System.out.println("Original: ");
        m.printMatriks();
        System.out.println("Cramer: ");
        matrix result = m.getCramerSol(m);
        result.printMatriks();
    }
}
