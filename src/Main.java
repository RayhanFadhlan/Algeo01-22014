package src;
// import src.matrix;
// import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        matrix m = new matrix();
        m.bacaMatriks();
        System.out.println("Original: ");
        m.print();
        System.out.println("OBE: ");
        matrix result = m.GaussJordan(m);
        result.print();
    }
}
