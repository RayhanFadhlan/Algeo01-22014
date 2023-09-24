package src;

import java.util.Scanner;
import src.matrix;
public class Main {

    public static void SPL(){
        return;
    }
    public static void Determinan(){
        return;
    }
    public static void MatBal(){
        Scanner sc = new Scanner(System.in);
        matrix coba=new matrix();
        coba.bacaFileMatrix("",false);
        coba.printMatriks();
        
    }

    public static void IntPol(){
        return;
    }

    public static void IntBic()
    {
        //deklarasi
        matrix xBic=new matrix();
        matrix invxBic=new matrix();
        matrix aBic=new matrix();
        matrix yBic=new matrix();
        double hasil;

        /*membuat matriks X dan inversnya */
        xBic=xBic.matrixBicubicSpline();
        xBic.printMatriks();
        invxBic=xBic.inverseMatrix(invxBic);
        
        /*ambil data f, fx, fy, dan fxy*/
        yBic.bacaFileMatrix("",true);
        
        //membuat matriks a
        aBic=aBic.perkalianMatrix(invxBic, yBic);
        aBic.printMatriks();
        //hitung hasil
        //hasil = aBic.bicMeasure(yBic.tx, yBic.ty);
        //System.out.println(hasil);

        return;
    }

    public static void RegLin(){
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
                    SPL();
                    break;
                case 2:
                    Determinan();
                    break;
                case 3:
                    MatBal();
                    break;
                case 4:
                    IntPol();
                    break;
                case 5:
                    IntBic();
                    break;
                case 6:
                    RegLin();
                    break;
                case 7:
                    sc.close();
                    System.exit(0);
            }
            inpmain =sc.nextInt();
        }
    }
}
