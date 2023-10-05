package src;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inpmain;

        System.out.println(menu.MAIN);
        inpmain = sc.nextLine();
        while (true) {
            switch (inpmain) {
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
                    Image_upscale.imageUpscale();
                    break;
                case "8":
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Masukkan tidak valid" + menu.newline + "Kembali ke main menu" + menu.newline);
            }
            System.out.println(menu.MAIN);
            inpmain = sc.nextLine();
        }
    }
    /* 
    ***README***
    
    SETIAP FUNGSI YANG DIMASUKKAN JANGAN LUPA DIKASIH NAMA ORANG YANG MASUKINNYA.
    */ 

    public static void spl() {
        // deklarasi
        String inp;
        Matrix m = new Matrix();
        Scanner sc = new Scanner(System.in);

        // algo
        m = Matrix.chooseNGetMatrix(false);
        System.out.println(menu.mspl);
        String s = "";
        inp = sc.nextLine();
        switch (inp) {
            case "1":
            s = m.getSPLGauss();
            break;
            case "2":
            s = m.getSPLGaussJordan();
                
            break;
            case "3":
            s = m.printInverseSPLSol(); 
            break;
            case "4":
            s =  m.printCramerSol();
            break;
            default:
                System.out.println("Masukkan tidak valid, kembali ke main menu.");
                return;
        }
        boolean twrite = Matrix.isToFile();
            if(twrite){
                Matrix.writeStringToFile(s);
            }
            else{
                System.out.println(s);
            }
        return;
    }

    public static void determinant() {
        // deklarasi
        String inp;
        Matrix m = new Matrix();
        Scanner sc = new Scanner(System.in);

        // algo
        m = Matrix.chooseNGetMatrix(false);
        System.out.println(menu.mdet);
        inp = sc.nextLine();
        switch (inp) {
            case "1":
                if(m.isSquare()){
                    System.out.println("Determinan: " + m.determinantGaussMatriks());
                    m.chooseWriteDeterminant(m.determinantGaussMatriks());
                }
                else{
                    System.out.println("Matriks tidak berukuran nxn. tidak dapat dicari determinan.");
                }
                break;
            case "2":
                // print getdeterminantcofactor
                if(m.isSquare()){
                    System.out.println(m.getDeterminantCofactor());
                    m.chooseWriteDeterminant(m.getDeterminantCofactor());
                }
                else{
                    System.out.println("Matriks tidak berukuran nxn. tidak dapat dicari determinan.");
                }
                break;
            default:
                System.out.println("Masukkan tidak valid, kembali ke main menu.");
        }
        return;
    }

    public static void matBal() {

        // deklarasi
        String inp;
        Matrix m = new Matrix();
        Scanner sc = new Scanner(System.in);

        // algo
        m = Matrix.chooseNGetMatrix(false);
        System.out.println(menu.mmatbal);
        inp = sc.nextLine();
        switch (inp) {
            case "1":
                // Identity (Rafly)
                m = m.inverseMatrixIdentity();
                m.printMatriks();
                System.out.println();
                m.chooseWriteMatrix();
                break;
            case "2":
                // Metode Ekspansi Kofaktor - Adjoin
                m = m.printInverseCofactor();
                m.printMatriks();
                System.out.println();
                m.chooseWriteMatrix();
                break;
            default:
                System.out.println("Masukkan tidak valid, kembali ke main menu.");
        }
        return;
    }

    public static void intPol(){
        // Interpolasi Polinomial (Rafly)
        Matrix m = new Matrix();
        m = Matrix.chooseNGetMatrix(false);
        m = m.interpolasiPolinomial();
        m.chooseWriteInterpolasi(); 
        return;
    }

    public static void intBic() {
        // deklarasi
        Matrix invxBic = new Matrix();
        Matrix xBic = new Matrix();
        Matrix aBic = new Matrix();
        Matrix yBic = new Matrix();
        boolean twrite;
        double hasil;
        String sAns;
        Scanner sc = new Scanner(System.in);

        /* membuat matriks X dan inversnya */
        xBic = xBic.matrixBicubicSpline();
        invxBic = xBic.inverseMatrixIdentity();

        //membuat matriks a
        yBic = Matrix.chooseNGetMatrix(true);
        aBic = Matrix.perkalianMatrix(invxBic, yBic);

        // hitung hasil
        hasil = yBic.bicMeasure(aBic);
        sAns = String.format("f(%f,%f) = ",yBic.tx,yBic.ty);
        sAns += String.format("%.3f",hasil)+"\n";

        twrite = Matrix.isToFile();
        if(twrite){
            Matrix.writeStringToFile(sAns);
        }
        else{
            System.out.printf(sAns);
        }
        return;
    }

    public static void regLin() {
        //deklarasi
        Matrix mInp = new Matrix();
        Matrix mMea = new Matrix();
        Matrix mxy = new Matrix();
        Matrix mAns = new Matrix();
        boolean twrite;
        String sAns="";

        //mengambil masukan matriks dan membentuk matriks untuk regresi
        mInp=Matrix.chooseNGetMatrix(false);
        mxy.formReglin(mInp);
        mAns = Matrix.gaussJordan(mxy);

        //cek tak ada solusi
        if(!mAns.isSPLUnique(mAns)){
            System.out.println("Didapat solusi parametrik atau tanpa solusi, kembali main menu");
            return;
        }
        else{
            //masukin
            sAns += "Persamaan regresi yang didapat adalah "+Matrix.regToString(mAns)+"\n";
        }

        //tanya apakah ada yg mau dimeasure
        mMea = Matrix.wantMeasure(mInp);

        //klo ada yg mau dimeasure
        if (!(mMea.row==0 && mMea.col==0)){
            sAns += "Nilai Aproksimasi yang didapat adalah "+Matrix.regMeasure(mMea,mAns)+"\n";
        }


        //pilih keluaran
        twrite = Matrix.isToFile();
        //output
        if(twrite){
            Matrix.writeStringToFile(sAns);
           // Matrix.stringToFile(sAns);
        }
        else{
            System.out.printf(sAns);
        }

        return;
    }

}

class menu {
    public static final String newline = System.lineSeparator();
    public static final String MAIN = "MENU" + newline +
            "1.Sistem Persamaaan Linier" + newline +
            "2.Determinan" + newline +
            "3.Matriks balikan" + newline +
            "4.Interpolasi Polinom" + newline +
            "5.Interpolasi Bicubic Spline" + newline +
            "6.Regresi linier berganda" + newline +
            "7.Perbesaran Gambar" + newline +
            "8.Keluar";
    public static final String mspl = "Pilih cara mencari determinan matriks" + newline +
            "1.Metode eliminasi Gauss" + newline +
            "2.Metode eliminasi Gauss-Jordan" + newline +
            "3.Metode matriks balikan" + newline +
            "4.Metode Cramer";
    public static final String mdet = "Pilih cara mencari determinan matriks" + newline +
            "1.Upper triangle" + newline +
            "2.Ekspansi kofaktor";
    public static final String mmatbal = "Pilih cara inverse matriks" + newline +
            "1.Matriks identitas" + newline +
            "2.Matriks adjoin";
}