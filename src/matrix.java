package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;

import javax.swing.plaf.BorderUIResource.MatteBorderUIResource;

public class Matrix{
    double [][] matrix;
    int row;
    int col;
    double tx,ty;

    // Constructor
    public Matrix(){
        this.row = 0;
        this.col = 0;
        this.matrix = new double[this.row][this.col];
    }

    // Getters
    public int getRow() {
        // Mengembalikan nilai row
        return this.row;
    }

    public int getCol() {
        // Mengembalikan nilai col
        return this.col;
    }

    // Setters
    public void setRow(int value) {
        // Memberi nilai row
        this.row = value;
    }

    public void setCol(int value) {
        // Memberi nilai col
        this.col = value;
    }

    public void initializeMatrix(int row, int col){
        // Inisialisasi matriks
        this.matrix = new double[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                this.matrix[i][j]=0;
            }
        }
    }


    public void createMatrix(int row, int col) {
        // Memberikan matriks nilai baris dan kolom
        setRow(nrow);
        setCol(ncol);
        initializeMatrix(this.row, this.col);
    }

    public void setMatrixValue(int nrow, int ncol, double value){
        // Memberikan matriks nilai untuk baris dan kolom
        this.matrix[nrow][ncol] = value;
    }

    // Methods

    /*OPERASI MATRIKS*/
    public Matrix add(Matrix m){
        // Menjumlahkan matrik dengan elemennya sendiri 
        Matrix result = new Matrix(); 
        result.createMatrix(row, col);
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                result.setMatrixValue(i, j, matrix[i][j] + m.matrix[i][j]);
            }
        }
        return result;
    }

    public static Matrix perkalianMatrix(Matrix a, Matrix b){
        // Perkalian matriks

        Matrix hasil = new Matrix();
        double sum=0;
        hasil.createMatrix(a.getRow(), b.getCol());
        for(int i=0;i<a.getRow();i++){
            for(int j=0;j<b.getCol();j++){
                sum=0;
                for(int k=0;k<b.getRow();k++){
                    sum+=(a.matrix[i][k]*b.matrix[k][j]);
                }
                hasil.matrix[i][j]=sum;
            }
        }
        return hasil;
    }

    public static void swaprow(Matrix m, int i, int j){
        // Menukar baris matriks
        double temp[] = m.matrix[i];
        m.matrix [i] = m.matrix[j];
        m.matrix[j] = temp;
    }

    public static Matrix copyMatrix(Matrix m){
        // Memberi salinan dari matriks m
        Matrix result = new Matrix(); 
        result.createMatrix(row, col);
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                result.setMatrixValue(i, j, matrix[i][j]);
            }
        }
        return result;
    }


    public static Matrix transpose(Matrix m){
        // Transpose matriks
        Matrix result = new Matrix(); 
        result.createMatrix(row, col);        
        for(int i = 0; i < col; i++){
            for(int j = 0; j < row; j++){
                result.setMatrixValue(i, j, matrix[j][i]);
            }
        }
        return result;
    }

    public int swapZeroToBottom (int nrow){
        // Menukar baris dengan nilai nol paling banyak ke bawah.
        for(int j=nrow;j<this.col;j++){
            for(int i = nrow;i<this.row;i++){
                if(this.matrix[i][j]!=0){
                    swaprow(this,nrow,i);
                    return j;
                }
            }
        }
       return 0;
    }
  
    public void forwardOBE(int nrow,int ncol){
        // OBE dengan langkah maju
        double scale;
        for(int i = nrow+1;i<row;i++){
            scale = matrix[i][ncol];
            for(int j = 0; j<col;j++){
                matrix[i][j]-=((matrix[nrow][j])*scale);
            }
        }
    }


    public Matrix identity() {
        // Mengembalikan nilai matriks identitas NxN
        Matrix identitas = new Matrix();
        identitas.createMatrix(this.row, this.col);
        for(int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                if (i == j) {
                    identitas.setMatrixValue(i, j, 1);
                } else {
                    identitas.setMatrixValue(i, j, 0);
                }
            }
        }
        return identitas;
    }

    public static boolean isRow0(Matrix m,int nrow){
        // Mengecek apakah nilai dari suatu baris bernilai 0
        for(int i = 0;i<m.col-1;i++){
            if(m.matrix[nrow][i]!=0){
                return false;
            }
        }
        return true;
    }

    public static Matrix gauss(Matrix m){

        Matrix result = copyMatrix(m);
        for(int i = 0;i<result.row;i++){
            int pivotCol = result.swapZeroToBottom(i);
            double pivot = result.matrix[i][pivotCol];
            if(!(isRow0(result, i))){
                for(int j = pivotCol;j<result.col;j++){
                    result.matrix[i][j]/=pivot;
            }
                result.forwardOBE(i, pivotCol);
            }
        }
            return result;
    }
    
    public void reverseOBE(int nrow,int ncol){
        // Melakukan OBE dengan langkah mundur
        for(int i= 0;i<nrow;i++){
            if(this.matrix[i][ncol]!=0){
                double scale = this.matrix[i][ncol];
                for(int j = 0;j<this.col;j++){
                    this.matrix[i][j]-=scale*this.matrix[nrow][j];
                }
            }
        }
    }

    public static int findLeading1(Matrix m,int nrow){
        // Mencari nilai leading one
        for(int i = 0;i<m.col;i++){
            if(m.matrix[nrow][i]==1){
                return i;
            }
        }
        return 0;
    }
    public static Matrix gaussJordan(Matrix m){
        Matrix result = copyMatrix(m);
        result = gauss(result);
        for(int i = result.row-1;i>=0;i--){
            if(!isRow0(result, i)){
                int leading1 = findLeading1(result, i);
                result.reverseOBE(i, leading1);
            }
        }
        return result;
    }

    public double pangkat(double a,double b){
        if(a==0){
            if(b==0)return 1;
            else return 0;
        }
        else{
            return(Math.pow(a,b));
        }
    }

    /* CHECK STATE */
    public boolean isRow0(Matrix m,int row){
        // Mengecek apakah nilai dari suatu baris bernilai 0
        for(int i = 0;i<m.col-1;i++){
            if(m.matrix[row][i]!=0){
                return false;
            }
        }
        return true;
    }

    /* BACA ATAU TULIS MATRIKS */
    public void printMatriks(){
        // Read or Write Matrix
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                System.out.printf("%.2f ",matrix[i][j]); // Print dua angka dibelakang desimal
            }
            System.out.println();
        }
    }

    public void bacaMatriks () {
        Scanner sc = new Scanner(System.in);
        String dump;
        System.out.println("Enter row and col: ");
        int row = sc.nextInt();
        int col = sc.nextInt();
        createMatrix(row, col);
        System.out.println("Enter matrix: ");
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                setMatrixValue(i, j, sc.nextDouble());
            }
            dump=sc.nextLine();
        }
    }
    
    public void bacaFileMatrix(String parlokasi,boolean parbic){
        int row=0,col=0;
        double dumpd;
        String lokasi,dumps;
        boolean bic;
        Scanner sc= new Scanner(System.in);

        lokasi=parlokasi;
        bic=parbic;
        if(lokasi==""){
            System.out.printf("Masukkan nama file: ");
            lokasi=sc.nextLine();
        }
        if(bic) row--;
        try{
            /*mengambil file */
            File inp=new File(lokasi);
            Scanner scf = new Scanner(inp);

            /*mengambil 1 baris dan menghitung kolom */
            Scanner cntcol = new Scanner(scf.nextLine());
            row++;
            while(cntcol.hasNextDouble()){
                col++;
                dumpd=cntcol.nextDouble();
            }

            /*menghitung baris */
            while(scf.hasNextLine()){
                dumps=scf.nextLine();
                row++;
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        if(bic) createMatrix(16, 1);
        else createMatrix(row, col);
        //System.out.println(row+" "+col);
        try{
            File inp = new File(lokasi);
            Scanner scf= new Scanner(inp);
            int cnt=0;
            for(int i=0;i<row;i++){
                Scanner srow = new Scanner (scf.nextLine());
                for(int j=0;j<col;j++){
                    if(bic) this.matrix[cnt][0] = srow.nextDouble();
                    else this.matrix[i][j] = srow.nextDouble();
                }
            }
            if(bic){
                Scanner srow = new Scanner (scf.nextLine());
                this.tx = srow.nextDouble();
                this.ty = srow.nextDouble();
            }
        }
            
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

     
    /* MAIN OPERATION */
    public Matrix inverseMatrix(Matrix par) {
        Matrix m = new Matrix();
        m.setMatrix(par.row, par.col);
        m = copyMatrix(par);
        Matrix identMatrix;
        identMatrix = new Matrix();
        identMatrix.createMatrix(m.row, m.col);
        identMatrix = m.identity();
        for (int n =0;n<this.row;n++) {
            double pivot = m.matrix[n][n];
            for (int i = n + 1; i<this.row;i++) {
                int newN = i;
                if (pivot == 0) { // Cek dan tuker bila nilai pivotnya nol
                    while ((newN < this.row)) {
                        swaprow(m, newN, n);
                        swaprow(identMatrix, newN, n);
                        pivot = m.matrix[n][n];
                        if (pivot != 0) {
                            break;
                        }
                        newN += 1;
                    }
                }
            }
            for (int j = 0; j < m.col; j++){
                m.matrix[n][j]/=pivot;
                identMatrix.matrix[n][j]/=pivot;
            }
            double scale;
            for(int i = n+1;i<m.row;i++){
                scale = m.matrix[i][n];
                for(int j = 0; j<m.col;j++){
                    m.matrix[i][j]-=((m.matrix[n][j])*scale);
                    identMatrix.matrix[i][j]-=((identMatrix.matrix[n][j])*scale);
                }

            }
        }              
         for (int n=this.row-1;n>=0;n--){
            for(int i= 0;i<n;i++){
                if(m.matrix[i][n]!=0){
                   double scale = m.matrix[i][n];
                    for(int j = 0;j<m.col;j++){
                        m.matrix[i][j]-=scale*m.matrix[n][j];
                        identMatrix.matrix[i][j]-=scale*identMatrix.matrix[n][j];
                    }
                }
           }

        }
        return identMatrix; 
    }
    

    // Read or Write Matrix
    public void printMatriks(){
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                System.out.printf("%.2f ",matrix[i][j]); // Print dua angka dibelakang desimal
            }
            System.out.println();
        }
    }

    public void bacaMatriks () {
        Scanner sc = new Scanner(System.in);
        String dump;
        System.out.println("Enter row and col: ");
        int nrow = sc.nextInt();
        int ncol = sc.nextInt();
        setMatrix(nrow, ncol);
        System.out.println("Enter matrix: ");
        for(int i = 0; i < nrow; i++){
            for(int j = 0; j < ncol; j++){
                setMatrixValue(i, j, sc.nextDouble());
            }
            dump=sc.nextLine();
        }
        // sc.close();
    }


    public Matrix matrixBicubicSpline(){
        Matrix aBic = new Matrix();
        aBic.createMatrix (16, 16);
        int cnt;
        cnt=0;
        for(int i=0;i<=1;i++){
            for(int j=0;j<=1;j++){
                aBic.isiMatrixBicubic(j, i, cnt);
                cnt++;
            }
        }
        return aBic;
    }

    public void isiMatrixBicubic(int x,int y,int nrow){
        int cnt=0;
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                this.setMatrixValue(nrow, cnt, pangkat(x,i)*pangkat(y,j));
                cnt++;
            }
        }
        cnt=0;
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                this.setMatrixValue(nrow+4, cnt,i*pangkat(x,i-1)*pangkat(y,j));
                cnt++;
            }
        }
        cnt=0;
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                this.setMatrixValue(nrow+8, cnt,j*pangkat(x,i)*pangkat(y,j-1));
                cnt++;

            }
        }
        cnt=0;
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                this.setMatrixValue(nrow+12, cnt,i*j*pangkat(x,i-1)*pangkat(y,j-1));
                cnt++;
            }
        }
    }
    


    public double determinantGaussMatriks() {
        // Mencari deteriminan menggunakan metode upper triangle
        double determinant = 1;
        Matrix result = new Matrix();
        result = copyMatrix(this);
        for (int n=0;n<this.row;n++) {
            for (int i = n + 1; i<this.row;i++) {
                double pivot = result.matrix[n][n];
                int newN = i;

                if (pivot == 0) { // Cek dan tuker bila nilai pivotnya nol
                    while ((newN < this.row)) {
                        swaprow(result, newN, n);
                        pivot = result.matrix[n][n];
                        System.out.printf("Pivot Modified: %f\n",pivot);
                        if (pivot != 0) {
                            break;
            double determinant = 1;
            Matrix result = new Matrix();
            result = copyMatrix(this);
            for (int n=0;n<this.row;n++) {
                for (int i = n + 1; i<this.row;i++) {
                    double pivot = result.matrix[n][n];
                    int newN = i;
    
                    if (pivot == 0) { // Cek dan tuker bila nilai pivotnya nol
                        while ((newN < row)) {
                            swaprow(result, newN, n);
                            pivot = result.matrix[n][n];
                            if (pivot != 0) {
                                break;
                            }
                            newN += 1;

                        }
                    }
                    double scale = result.matrix[i][n] / pivot; 
                    for (int j=0;j<this.col;j++) {
                        result.matrix[i][j] -= result.matrix[n][j] * scale;
                    }
                }
            } 
    
            for (int i=0;i<this.row;i++) { // Mengalikan determinan secara diagonal
                determinant *= result.matrix[i][i];
            }
            if (determinant == 0) {
                determinant = 0;
            }
            return determinant;
        } 
        catch(ArrayIndexOutOfBoundsException n) {
            System.out.println("error");
            return 0;
        }
    } 

    public double determinantCofacMatrix(){
        // Mencari determinant matriks menggunakan kofaktor
        
        return 0.0;


    public void bacaFileMatrix(String parlokasi,boolean parbic){
        int nrow=0,ncol=0;
        double dumpd;
        String lokasi,dumps;
        boolean bic;
        Scanner sc= new Scanner(System.in);

        lokasi=parlokasi;
        bic=parbic;
        if(lokasi==""){
            System.out.printf("Masukkan nama file: ");
            lokasi=sc.nextLine();
        }
        if(bic) nrow--;
        try{
            /*mengambil file */
            File inp = new File(lokasi);
            Scanner scf = new Scanner(inp);

            /*mengambil 1 baris dan menghitung kolom */
            Scanner cntcol = new Scanner(scf.nextLine());
            nrow++;
            while(cntcol.hasNextDouble()){
                ncol++;
                dumpd=cntcol.nextDouble();
            }

            /*menghitung baris */
            while(scf.hasNextLine()){
                dumps=scf.nextLine();
                nrow++;
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        if(bic) setMatrix(16, 1);
        else setMatrix(nrow, ncol);
        //System.out.println(this.row+" "+this.col);
        try{
            File inp = new File(lokasi);
            Scanner scf= new Scanner(inp);
            int cnt=0;
            for(int i=0;i<nrow;i++){
                Scanner srow = new Scanner (scf.nextLine());
                for(int j=0;j<ncol;j++){
                    if(bic) this.matrix[cnt][0] = srow.nextDouble();
                    else this.matrix[i][j] = srow.nextDouble();
                    cnt++;
                }
            }
            if(bic){
                Scanner srow = new Scanner (scf.nextLine());
                this.tx = srow.nextDouble();
                this.ty = srow.nextDouble();
            }
        }
            
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    public double bicMeasure(Matrix a){
        double hasil;
        int cnt;

        hasil=0;
        cnt=0;
        for(int j=0;j<=3;j++){
            for(int i=0;i<=3;i++){
                hasil+=(a.matrix[cnt][0]*pangkat(this.tx, i)*pangkat(ty, j));
                cnt++;
            }
        }
        return hasil;
    }

    
    public void interpolasiPolinomial() {
        Scanner sc = new Scanner(System.in);
        int n,banyakTitik;
        System.out.println("Masukkan Banyak Titik:");
        banyakTitik = sc.nextInt();
        n = banyakTitik - 1;

        Matrix tabelMatrix = new Matrix();
        tabelMatrix.createMatrix(banyakTitik,banyakTitik+1);
        tabelMatrix.printMatriks();
        for(int i=0;i<banyakTitik;i++){
            System.out.println("Titik X:");
            float x = sc.nextFloat();
            System.out.println("Titik Y:");
            float y = sc.nextFloat();
            for(int j=0;j<tabelMatrix.col;j++){
                if (j != tabelMatrix.col - 1){
                    tabelMatrix.matrix[i][j] = Math.pow(x,j);
                } else {
                    tabelMatrix.matrix[i][j] = y;   
                }
            }
        }
        tabelMatrix.printMatriks();
        Matrix resultMatrix = new Matrix();
        resultMatrix = copyMatrix(tabelMatrix);
        resultMatrix = gaussJordan(tabelMatrix);
        resultMatrix.printMatriks(); 

        float inputX = sc.nextFloat();
        float result;
        result = 0;
        for (int i=0;i<banyakTitik;i++){
            result += resultMatrix.matrix[i][banyakTitik] * Math.pow(inputX,i);
        }
        System.out.printf("Result: %f",result);
    }


    public void inverseSPL(){
        Matrix a,b;
        a = new Matrix();
        b = new Matrix();
        a.bacaMatriks();
        b.createMatrix(a.row, 1);
        Scanner sc = new Scanner(System.in);
        for (int i=0;i<a.row;i++){
            b.matrix[i][0] = sc.nextDouble();
        }

        Matrix inverseA = a.inverseMatrix(a);
        System.out.println("Inverse A:");
        inverseA.printMatriks();
        System.out.println("Matriks b:");
        b.printMatriks();

        Matrix result = perkalianMatrix(inverseA, b);    
        System.out.println("Result Matriks:");    
        result.printMatriks();
    }


    public void formReglin(Matrix parxy){
        Matrix xy= new Matrix();
        int c,r;
        double sum;
        xy.setMatrix(parxy.row, parxy.col);
        xy = copyMatrix(parxy);
        //k jadi c
        //n jadi r
        c = xy.col;
        r = xy.row;
        setMatrix(c, c+1);
        setMatrixValue(0,0,r);
        for(int i=1;i<c;i++){
            sum=0;
            for(int k=0;k<r;k++){
                sum+=xy.matrix[k][i-1];
            }
            setMatrixValue(i, 0, sum);
            setMatrixValue(0, i, sum);
        }
        for(int i=1;i<c;i++){
            for(int j=1;j<c;j++){
                sum=0;
                    for(int k=0;k<r;k++){
                        for(int l=0;l<r;l++){
                            sum+=(xy.matrix[k][i-1]*xy.matrix[l][j-1]);
                        }
                    }
                this.matrix[i][j]=sum;
            }
        }
        for(int i=0;i<c;i++){
            sum=0;
            for(int j=0;j<r;j++){
                if(i==0){
                    sum+=xy.matrix[j][c-1];
                }
                else{
                    for(int k=0;k<r;k++){
                        sum+=(xy.matrix[j][c-1]*xy.matrix[k][i-1]);
                    }
                }
            }
            this.matrix[i][c]=sum;
        }
    }
    
}

