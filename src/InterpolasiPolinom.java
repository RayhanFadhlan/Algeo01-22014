package src;
import java.util.Scanner;


public class InterpolasiPolinom {
    public static void interpolasiPolinom () {
        Scanner sc = new Scanner(System.in);
        int n,banyakTitik;
        System.out.println("Masukkan Banyak Titik:");
        banyakTitik = sc.nextInt();
        n = banyakTitik - 1;

        Matrix tabelMatrix = new Matrix();
        tabelMatrix.setMatrix(banyakTitik,banyakTitik+1);
        tabelMatrix.printMatriks();
        for(int i=0;i<banyakTitik;i++){
            System.out.println("Titik X:");
            float x = sc.nextFloat();
            System.out.println("Titik Y:");
            float y = sc.nextFloat();
            for(int j=0;j<tabelMatrix.col;j++){
                if (j != tabelMatrix.col - 1){
                    tabelMatrix.matrix[i][j] = Math.pow(x,j);
                } else {
                    tabelMatrix.matrix[i][j] = y;   
                }
            }
        }
        Matrix resultMatrix = new Matrix();
        resultMatrix = Matrix.copyMatrix(tabelMatrix);
        resultMatrix = Matrix.gaussJordan(tabelMatrix);
        resultMatrix.printMatriks(); 
        System.out.printf("f(x) = ");
        for (int i = 0; i < resultMatrix.row; i++) {
            for (int j = 0; j < resultMatrix.col - 1;j++) {
                if (resultMatrix.matrix[i][j] != 0) {
                    System.out.printf("(%.2f)x^%d",resultMatrix.matrix[i][resultMatrix.col-1],j);
                    if (i != resultMatrix.row -1) {
                        System.out.printf(" + ");
                    }
                }
            }
        }
        System.out.println();

        System.out.printf("Masukkan nilai yang ingin ditaksir: ");
        float inputX = sc.nextFloat();
        float result;
        result = 0;
        for (int i=0;i<banyakTitik;i++){
            result += resultMatrix.matrix[i][banyakTitik] * Math.pow(inputX,i);
        }
        System.out.printf("Result: %.2f",result);    
    }
}
