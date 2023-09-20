package src;
// import src.matrix;
// import java.util.Scanner;

public class udin {
    public static void main(String[] args){
        matrix m = new matrix();
        m.bacaMatriks();
        System.out.println("Original: ");
        m.print();
        System.out.println("OBE: ");
        matrix result = m.identity();
        result.print();
    }
}
