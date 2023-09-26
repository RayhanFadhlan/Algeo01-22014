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
        Scanner sc = new Scanner(System.in);
        Matrix coba=new Matrix();
        coba.bacaFileMatrix("",false);
        coba.printMatriks();
        
    }

    public static void intPol(){
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

        /*membuat matriks X dan inversnya */
        xBic=xBic.matrixBicubicSpline();
        //xBic.printMatriks();
        //System.out.println();

        invxBic=xBic.inverseMatrix(xBic);
        //invxBic.printMatriks();
        //System.out.println();
        
        /*ambil data f, fx, fy, dan fxy*/
        yBic.bacaFileMatrix("",true);

        //membuat matriks a
        aBic=aBic.perkalianMatrix(invxBic, yBic);
        //aBic.printMatriks();

        //System.out.println(hasil);
        //hitung hasil
        hasil = yBic.bicMeasure(aBic);
        System.out.println(hasil);

        return;
    }

    public static void regLin(){
            Matrix xy = new Matrix();
            Matrix xs = new Matrix();
            Matrix bs = new Matrix();
            Matrix invxs = new Matrix();

            xy.bacaFileMatrix("", false);
            xs.formReglin(xy);
            invxs = xs.inverseMatrix(xs);
            //invxs.printMatriks();
            xs.printMatriks();
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
