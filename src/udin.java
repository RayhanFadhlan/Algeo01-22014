package src;
// import src.matrix;
// import java.util.Scanner;

public class udin {
    public static void main(String[] args){
        Matrix n;
        // Matrix.buatFile("file1.txt");
        Matrix m = new Matrix();
        m.bacaMatriks();
        System.out.println("Tes:");
        m.printMatriks();
        m.writeMatrixToFile();
    }
}
