package src;
import java.util.Scanner;
import src.Matrix;

public class Main {
    /* 
    ***README***
    
    SETIAP FUNGSI YANG DIMASUKKAN JANGAN LUPA DIKASIH NAMA ORANG YANG MASUKINNYA.
    */ 

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
                // Gauss (Rafly)
                m = Matrix.gauss(m);
                Matrix.printSPLSol(m);
                break;
            case "2":
                // Gauss Jordan (Rafly)
                m = Matrix.gauss(m);
                Matrix.printSPLSol(m);
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
        Double detResult;
        Matrix m = new Matrix();
        Scanner sc = new Scanner(System.in);

        //algo
        m = Matrix.chooseNGetMatrix(false);
        System.out.println(menu.mdet);
        inp=sc.nextLine();
        switch(inp){
            case "1":
                // Deteriminan Gauss (Rafly)
                detResult = m.determinantGaussMatriks();
                System.out.printf("Determinan - Gauss: %f",detResult);
                break;
            case "2":
                // Determinan Kofaktor (Rafly)
                detResult = m.getDeterminantCofactor();
                System.out.printf("Determinan - Gauss: %f",detResult);
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
                // Identity (Rafly)
                m = m.inverseMatrix(m);
                System.out.println("Matriks Sesudah Invers:");
                m.printMatriks();
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

        // Interpolasi Polinomial (Rafly)
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

        /*membuat matriks X dan inversnya */
        xBic=xBic.matrixBicubicSpline();
        //xBic.printMatriks();
        //System.out.println();

        invxBic = xBic.inverseMatrix(xBic);
        //invxBic.printMatriks();
        //System.out.println();
        
        /*ambil data f, fx, fy, dan fxy*/
        yBic.bacaFileMatrix("",true);

        //membuat matriks a
        aBic = Matrix.perkalianMatrix(invxBic, yBic);
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