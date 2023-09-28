package src;
import java.util.Scanner;
import src.Matrix;

public class Main {

    public static void spl(){
        //deklarasi
        String inp;
        Matrix m = new Matrix();
        Scanner sc = new Scanner(System.in);

        //algo
        m = Matrix.chooseNGetMatrix(false);
        System.out.println();
        inp=sc.nextLine();
        switch(inp){
            case "1":
                //masukin caranya, matriksnya dah ada
                break;
            case "2":
                //masukin caranya, matriksnya dah ada
                break;
            case "3":
                //masukin caranya, matriksnya dah ada
                break;
            case "4":
                //masukin caranya, matriksnya dah ada
                break;
            default:
                System.out.println("Masukkan tidak valid, kembali ke main menu.");
        }
        return;
    }
    public static void determinant(){
        //deklarasi
        String inp;
        Matrix m = new Matrix();
        Scanner sc = new Scanner(System.in);

        //algo
        m = Matrix.chooseNGetMatrix(false);
        System.out.println(menu.mdet);
        inp=sc.nextLine();
        switch(inp){
            case "1":
                //masukin caranya, matriksnya dah ada
                break;
            case "2":
                //masukin caranya, matriksnya dah ada
                break;
            default:
                System.out.println("Masukkan tidak valid, kembali ke main menu.");
        }
        return;
    }
    public static void matBal(){
        //deklarasi
        String inp;
        Matrix m = new Matrix();
        Scanner sc = new Scanner(System.in);

        //algo
        m = Matrix.chooseNGetMatrix(false);
        System.out.println(menu.mmatbal);
        inp=sc.nextLine();
        switch(inp){
            case "1":
                //masukin caranya, matriksnya dah ada
                break;
            case "2":
                //masukin caranya, matriksnya dah ada
                break;
            default:
                System.out.println("Masukkan tidak valid, kembali ke main menu.");
        }
        return;
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
        String inpmain,Menu;
    
        System.out.println(menu.MAIN);
        inpmain = sc.nextLine();
        while(true){
            switch (inpmain){
                case "1":
                    spl();
                    break;
                case "2":
                    determinant();
                    break;
                case "3":
                    matBal();
                    break;
                case "4":
                    intPol();
                    break;
                case "5":
                    intBic();
                    break;
                case "6":
                    regLin();
                    break;
                case "7":
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Masukkan tidak valid"+menu.newline+"Kembali ke main menu"+menu.newline);
            }
            System.out.println(menu.MAIN);
            inpmain =sc.nextLine();
        }
    }
}


class menu{
    public static final String newline = System.lineSeparator();
    public static final String MAIN =
    "MENU"+newline+
    "1.Sistem Persamaaan Linier"+newline+
    "2.Determinan"+newline+
    "3.Matriks balikan"+newline+
    "4.Interpolasi Polinom"+newline+
    "5.Interpolasi Bicubic Spline"+newline+
    "6.Regresi linier berganda"+newline+
    "7.Keluar";
    public static final String mspl =
    "Pilih cara mencari determinan matriks"+newline+
    "1.Metode eliminasi Gauss"+newline+
    "2.Metode eliminasi Gauss-Jordan"+newline+
    "3.Metode matriks balikan"+newline+
    "4.Metode Cramer";
    public static final String mdet =
    "Pilih cara mencari determinan matriks"+newline+
    "1.Upper triangle"+newline+
    "2.Ekspansi kofaktor";
    public static final String mmatbal =
    "Pilih cara inverse matriks"+newline+
    "1.Matriks identitas"+newline+
    "2.Matriks adjoin";
}