package src;
import java.util.Scanner;


import java.time.format.SignStyle;
import java.util.Scanner;
import src.matrix;
public class Main {

    public static void spl(){
        //deklarasi
        System.out.println(menu.mspl);
        String inp;
        Scanner sc = new Scanner(System.in);
        inp=sc.nextLine();
        Matrix m = new Matrix();
        m = Matrix.chooseNGetMatrix(false);

        
        
        
        System.out.println();


        
        switch(inp){
            case "1":
                m.getSPLGauss();
                break;
            case "2":
                m.getSPLGaussJordan();
                break;
            case "3":
                m.printInverseSPLSol();
                break;
            case "4":
                m.printCramerSol();
                break;
            default:
                System.out.println("Masukkan tidak valid, kembali ke main menu.");
        }
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
        System.out.println();

        invxBic=xBic.inverseMatrix(xBic);
        invxBic.printMatriks();
        System.out.println();
        
        /*ambil data f, fx, fy, dan fxy*/
        yBic.bacaFileMatrix("",true);
        yBic.printMatriks();
        System.out.println();

        //membuat matriks a
        aBic=aBic.perkalianMatrix(invxBic, yBic);
        //aBic.printMatriks();

        //System.out.println(hasil);
        //hitung hasil
        //hasil=xBic.bicMeasure(aBic);
        //System.out.println(hasil);

        return;
    }

    public static void RegLin(){
        return;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inpmain,Menu;
    
        while(true){
            System.out.println(menu.MAIN);
            inpmain = sc.nextLine();
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
            // System.out.println(menu.MAIN);
            // inpmain =sc.nextLine();
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
    "Pilih cara mencari sistem persamaan linier matriks"+newline+
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
