package src;

import java.time.format.SignStyle;
import java.util.Scanner;
import src.Matrix;
public class Main {

    public static void spl(){
        return;
    }
    public static void determinant(){
        return;
    }
    public static void matBal(){
        String newline = System.lineSeparator();
        System.out.println("Pilih cara input Matrix");`
        
    }

    public static void intPol(){
        Matrix.interpolasiPolinomial();
        return;
    }

    public static void intBic()
    {
        //deklarasi
        Matrix invxBic=new Matrix();
        Matrix xBic=new Matrix();
        Matrix aBic=new Matrix();
        Matrix yBic=new Matrix();
        double hasil;

        //algo
        xBic=xBic.matrixBicubicSpline();
        invxBic=xBic.inverseMatrix(xBic);
        yBic = Matrix.chooseNGetMatrix(true);
        aBic = Matrix.perkalianMatrix(invxBic, yBic);
        hasil = yBic.bicMeasure(aBic);
        System.out.println(hasil);

        return;
    }

    public static void regLin(){
            Matrix mInp = new Matrix();
            Matrix mReg = new Matrix();
            Matrix mGReg = new Matrix();
            Matrix result = new Matrix();

            mInp.bacaFileMatrix("", false);
            mReg.formReglin(mInp);
            mGReg = Matrix.gauss(mReg);
            result = Matrix.getSPLGauss(mGReg);
            //result.printMatriks();
            //invxs.printMatriks();
            //ys.printMatriks();

        return;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String newline = System.lineSeparator();
        int inpmain;
        System.out.println("MENU"+newline+
        "1.Sistem Persamaaan Linier"+newline+
        "2.Determinan"+newline+
        "3.Matriks balikan"+newline+
        "4.Interpolasi Polinom"+newline+
        "5.Interpolasi Bicubic Spline"+newline+
        "6.Regresi linier berganda"+newline+
        "7.Keluar");
        inpmain =sc.nextInt();
        while(true){
            switch (inpmain){
                case 1:
                    spl();
                    break;
                case 2:
                    determinant();
                    break;
                case 3:
                    matBal();
                    break;
                case 4:
                    intPol();
                    break;
                case 5:
                    intBic();
                    break;
                case 6:
                    regLin();
                    break;
                case 7:
                    sc.close();
                    System.exit(0);
            }
            inpmain =sc.nextInt();
        }
    }
}
