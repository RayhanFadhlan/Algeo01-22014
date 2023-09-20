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
        
    }
    public static void IntPol(){
        return;
    }
    public static void IntBic(){
        /*ambil input
         * buat dan isi matrix
         * gunain matrix buat nentuin
         */
        matrix xBic=new matrix();
        xBic=xBic.matrixBicubicSpline();
        xBic.print();
        /*xBic diinvers 
         * xBic dikali matrix input jadi matrix a;
        */
        matrix aBic=new matrix();

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