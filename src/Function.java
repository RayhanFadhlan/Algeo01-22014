package src;

import java.util.Scanner;

public class Function {
    public static void spl() {
        // deklarasi
        String inp;
        Matrix m = new Matrix();
        Scanner sc = new Scanner(System.in);

        // algo
        m = Matrix.chooseNGetMatrix(false);
        System.out.println(Menu.mspl);
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
                s = m.printInverseSPLSol(); // Matriks "m"-nya kudu berubah
                break;
            case "4":
                s =  m.printCramerSol(); // Matriks "m"-nya kudu berubah
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
        System.out.println(Menu.mdet);
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
        System.out.println(Menu.mmatbal);
        inp = sc.nextLine();
        switch (inp) {
            case "1":
                // Identity (Rafly)
                m = m.printInverseCofactor();
                System.out.println();
                m.chooseWriteMatrix();
                break;
            case "2":
                m = m.printInverseAdjoin();
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

        twrite = Matrix.isToFile();
        /* membuat matriks X dan inversnya */
        xBic = xBic.matrixBicubicSpline();
        invxBic = xBic.inverseMatrix(xBic);

        //membuat matriks a
        yBic = Matrix.chooseNGetMatrix(true);
        aBic = Matrix.perkalianMatrix(invxBic, yBic);

        // hitung hasil
        hasil = yBic.bicMeasure(aBic);
        sAns = String.format("%.3f",hasil)+"\n";
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

        //pilih keluaran
        twrite = Matrix.isToFile();

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

