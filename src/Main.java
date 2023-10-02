package src;

import java.time.format.SignStyle;
import java.util.Scanner;
import src.Matrix;
public class Main {

    // public static void spl(){
    //     return;
    // }
    // public static void determinant(){
    //     return;
    // }
    // public static void matBal(){
    //     Scanner sc = new Scanner(System.in);
    //     Matrix coba=new Matrix();
    //     coba.bacaFileMatrix("",false);
    //     coba.printMatriks();
        
    // }

    // public static void intPol(){
    //     return;
    // }

    // public static void intBic()
    // {
    //     //deklarasi
    //     Matrix invxBic=new Matrix();
    //     Matrix xBic=new Matrix();
    //     Matrix aBic=new Matrix();
    //     Matrix yBic=new Matrix();
    //     double hasil;

    //     /*membuat matriks X dan inversnya */
    //     xBic=xBic.matrixBicubicSpline();
    //     //xBic.printMatriks();
    //     //System.out.println();

    //     invxBic=xBic.inverseMatrix(xBic);
    //     //invxBic.printMatriks();
    //     //System.out.println();
        
    //     /*ambil data f, fx, fy, dan fxy*/
    //     yBic.bacaFileMatrix("",true);

    //     //membuat matriks a
    //     aBic=aBic.perkalianMatrix(invxBic, yBic);
    //     //aBic.printMatriks();

    //     //System.out.println(hasil);
    //     //hitung hasil
    //     hasil = yBic.bicMeasure(aBic);
    //     System.out.println(hasil);

    //     return;
    // }

    // public static void regLin(){
    //         Matrix xy = new Matrix();
    //         Matrix xs = new Matrix();
    //         Matrix bs = new Matrix();
    //         Matrix invxs = new Matrix();

    //         xy.bacaFileMatrix("", false);
    //         xs.formReglin(xy);
    //         invxs = xs.inverseMatrix(xs);
    //         //invxs.printMatriks();
    //         xs.printMatriks();
    //         //ys.printMatriks();

    //     return;
    // }

    public static void clearScreen() {
        System.out.print("\033\143");
    }

    public static boolean continueApp() {
        Scanner sc = new Scanner(System.in);
        String newline = System.lineSeparator();
        int input;
        boolean isAnswerValid = false;
        while(isAnswerValid == false){
            System.out.println("\nContinue? (1 = Yes / 2 = No)");
            input = sc.nextInt();
            if (input == 1) {
                return true;
            } else if (input == 2) {
                clearScreen();
                System.out.println("   .+------+     +------+     +------+     +------+     +------+.\r\n" + //
                        " .' |    .'|    /|     /|     |      |     |\\     |\\    |`.    | `.\r\n" + //
                        "+---+--+'  |   +-+----+ |     +------+     | +----+-+   |  `+--+---+\r\n" + //
                        "|   |  |   |   | |    | |     |      |     | |    | |   |   |  |   |\r\n" + //
                        "|  ,+--+---+   | +----+-+     +------+     +-+----+ |   +---+--+   |\r\n" + //
                        "|.'    | .'    |/     |/      |      |      \\|     \\|    `. |   `. |\r\n" + //
                        "+------+'      +------+       +------+       +------+      `+------+");
                System.out.println("THANK YOU :'D\n");
                return false;
            }
        }
        return true;
    }

    public static Matrix masukMatrix() {
        Scanner sc = new Scanner(System.in);
        String newLine = System.lineSeparator();
        Matrix file = new Matrix();
        System.out.println("-> Pilih Metode Input File:"+newLine+
        "1. Keyboard" + newLine +
        "2. File");
        int inp = sc.nextInt();
        sc.nextLine();
        Main.clearScreen();
        switch (inp) {
            case 1:
                file.bacaMatriks();
                return file;
            case 2:
                String lokasi,lokasiFolderTest;
                System.out.println("== Alamat File =="+newLine+"- Pastikan file berbentuk .txt dan berada didalam folder test -");
                lokasi = sc.nextLine();
                lokasiFolderTest = "C:\\Users\\Asus\\Documents\\Heking\\Semester 3\\Algeo\\Tugas Besar 1\\Algeo01-22014\\test\\" + lokasi;
                Main.clearScreen();
                file.bacaFileMatrix(lokasiFolderTest, false);
                return file;
        }
        return file;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String newline = System.lineSeparator();
        int inpmain;
        boolean app = true;
        
        while(app){
            clearScreen();
            int inpmini;
            System.out.println("====== MENU ======"+newline+
            "1.Sistem Persamaaan Linier"+newline+
            "2.Determinan"+newline+
            "3.Matriks balikan"+newline+
            "4.Interpolasi Polinom"+newline+
            "5.Interpolasi Bicubic Spline"+newline+
            "6.Regresi linier berganda"+newline+
            "7.Keluar");
            inpmain =sc.nextInt();
            switch (inpmain){
                case 1:
                    clearScreen();
                    System.out.println("===> Pilih Metode:"+newline+"1. Gauss"+newline+
                        "2. Gauss - Jordan"+newline+
                        "3. Matriks Balikan" +newline+
                        "4.Balikan Cramer");
                    inpmini = sc.nextInt();
                    clearScreen();
                    switch (inpmini) {
                        case 1:
                            SistemPersamaanLinear.splGauss();
                            break;
                        case 2:
                            SistemPersamaanLinear.splGaussJordan();
                            break;
                        case 3:
                            SistemPersamaanLinear.splInverse();
                            break;
                        case 4:
                            SistemPersamaanLinear.splCramer();
                            break;
                    }
                    app = continueApp();
                    break;
                case 2:
                    clearScreen();
                    System.out.println("===> Pilih Metode:"+newline+"1. Gauss"+newline+
                    "2. Kofaktor");
                    inpmini = sc.nextInt();
                    clearScreen();
                    switch (inpmini) {
                        case 1:
                        Determinant.determinantGauss();
                        break;
                        case 2:
                        Determinant.determinantKofaktor();
                        break;
                    }
                    app = continueApp();
                    break;
                case 3:
                    clearScreen();
                    System.out.println("===> Pilih Metode:"+newline+"1. Identitas"+newline+
                    "2. Kofaktor - Adjoin");
                    inpmini = sc.nextInt();
                    clearScreen();
                    switch (inpmini) { // KASUS BUKAN NXN BELOM DI HITUNG
                        case 1:
                            Inverse.inverseIdentity();
                        break;
                        case 2:
                            Inverse.inverseKofaktorAdjoin();
                        break;
                    }
                    app = continueApp();
                    break;
                case 4:
                    System.out.println("======= INTERPOLASI POLINOM =======");
                    InterpolasiPolinom.interpolasiPolinom();
                    app = continueApp();
                    break;
                case 5:
                    System.out.println("======= Under Construction =======");
                    app = continueApp();
                    break;
                case 6:
                    System.out.println("======= Under Construction =======");
                    app = continueApp();
                    break;
                case 7:
                    sc.close();
                    System.exit(0);
            }
            // inpmain =sc.nextInt();
        }
    }
}
