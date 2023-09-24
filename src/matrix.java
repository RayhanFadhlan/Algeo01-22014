package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;
import javax.swing.plaf.BorderUIResource.MatteBorderUIResource;
public class matrix{
    double [][] matrix;
    int row;
    int col;
    double tx,ty;

    // Constructor
    public matrix(){
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

    public void setMatrix(int row, int col) {
        // Memberikan matriks nilai baris dan kolom
        setRow(row);
        setCol(col);
        initializeMatrix(this.row, this.col);
    }

    public void setMatrixValue(int row, int col, double value){
        // Memberikan matriks nilai untuk baris dan kolom
        this.matrix[row][col] = value;
    }

    // Methods

    // OPERASI MATRIKS
    public matrix add(matrix m){
        // Menjumlahkan matrik dengan elemennya sendiri 
        matrix result = new matrix(); 
        result.setMatrix(row, col);
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                result.setMatrixValue(i, j, matrix[i][j] + m.matrix[i][j]);
            }
        }
        return result;
    }

    public matrix perkalianMatrix(matrix a, matrix b){
        // Perkalian matriks5

        matrix hasil = new matrix();
        int sum=0;

        hasil.setMatrix(a.getRow(), b.getCol());
        for(int i=0;i<a.getRow();i++){
            for(int j=0;j<b.getCol();j++){
                sum=0;
                for(int k=0;k<b.getRow();k++){
                    sum+=(a.matrix[i][k]*b.matrix[k][j]);
                }
                hasil.matrix[i][j]=sum;
            }
        }
        //hasil.printMatriks();
        return hasil;
    }

    public void swaprow(matrix m, int i, int j){
        // Menukar baris matriks
        double temp[] = m.matrix[i];
        m.matrix [i] = m.matrix[j];
        m.matrix[j] = temp;
    }

    public matrix copyMatrix(matrix m){
        // Memberi salinan dari matriks m
        matrix result = new matrix(); 
        result.setMatrix(row, col);
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                result.setMatrixValue(i, j, matrix[i][j]);
            }
        }
        return result;
    }


    public matrix transpose(matrix m){
        // Transpose matriks
        matrix result = new matrix(); 
        result.setMatrix(row, col);        
        for(int i = 0; i < col; i++){
            for(int j = 0; j < row; j++){
                result.setMatrixValue(i, j, matrix[j][i]);
            }
        }
        return result;
    }

    public int makeLeftNonZero(matrix m,int row){
        // Menukar baris dengan nilai nol paling banyak ke bawah.
        for(int j=row;j<m.col;j++){
            for(int i = row;i<m.row;i++){
                if(m.matrix[i][j]!=0){
                    swaprow(m,row,i);
                    return j;
                }
            }
        }
       return 0;
    }
  
    public void forwardOBE(matrix m,int row,int col){
        // OBE dengan langkah maju
        double scale;
        for(int i = row+1;i<m.row;i++){
            scale = m.matrix[i][col];
            for(int j = 0; j<m.col;j++){
                m.matrix[i][j]-=((m.matrix[row][j])*scale);
            }
        }
    }


    public matrix identity() {
        // Mengembalikan nilai matriks identitas NxN
        matrix identitas = new matrix();
        identitas.setMatrix(this.row, this.col);
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

    public boolean isRow0(matrix m,int row){
        // Mengecek apakah nilai dari suatu baris bernilai 0
        for(int i = 0;i<m.col-1;i++){
            if(m.matrix[row][i]!=0){
                return false;
            }
        }
        return true;
    }
    public matrix Gauss(matrix m){
        matrix result = copyMatrix(m);
        for(int i = 0;i<result.row;i++){
            int pivotCol = makeLeftNonZero(result,i);
            double pivot = result.matrix[i][pivotCol];
            if(!(isRow0(result, i))){
                for(int j = pivotCol;j<result.col;j++){
                    result.matrix[i][j]/=pivot;
            }
                forwardOBE(result, i, pivotCol);
            }
        }
            return result;
    }
    
    public void reverseOBE(matrix m,int row,int col){
        // Melakukan OBE dengan langkah mundur
        for(int i= 0;i<row;i++){
            if(m.matrix[i][col]!=0){
                double scale = m.matrix[i][col];
                for(int j = 0;j<m.col;j++){
                    m.matrix[i][j]-=scale*m.matrix[row][j];
                }
            }
        }
    }

    public int findLeading1(matrix m,int row){
        // Mencari nilai leading one
        for(int i = 0;i<m.col;i++){
            if(m.matrix[row][i]==1){
                return i;
            }
        }
        return 0;
    }
    public matrix GaussJordan(matrix m){
        matrix result = copyMatrix(m);
        result = Gauss(result);
        for(int i = result.row-1;i>=0;i--){
            if(!isRow0(result, i)){
                int leading1 = findLeading1(result, i);
                reverseOBE(result, i, leading1);
            }
            }
            return result;
        }
    
    public matrix inverseMatrix(matrix par) {
        matrix m=new matrix();
        m.setMatrix(par.row, par.col);
        m=par.copyMatrix(par);
        matrix identMatrix;
        identMatrix = new matrix();
        identMatrix.setMatrix(m.row, m.col);
        identMatrix = m.identity();
        for (int n =0;n<this.row;n++) {
            //identMatrix.printMatriks();
            //System.out.println();
            double pivot = m.matrix[n][n];
            for (int i = n + 1; i<this.row;i++) {
                int newN = i;
                if (pivot == 0) { // Cek dan tuker bila nilai pivotnya nol
                    while ((newN < row)) {
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
    public boolean isSPLUnique(matrix m){
        return (m.determinantMatriks()!=0);
    }
    // mengganti satu kolom matriks dengan matriks lain
    
    
    // make cramer to get linear equation
    public matrix getCramerSol(matrix m){
        matrix square = new matrix();
        square.setMatrix(m.row, m.col-1);
        for(int i = 0; i < m.row; i++){
            for(int j = 0; j < m.col-1; j++){
                square.matrix[i][j] = m.matrix[i][j];
            }
        }

        matrix result = new matrix();
        result.setMatrix(square.row, 1);
        double det = square.determinantMatriks();

        // replace column dgn solusi dan cari determinan
        for(int i = 0; i < square.row; i++){
            matrix temp = new matrix();
            temp.setMatrix(square.row, square.col);
            temp = copyMatrix(square);
            for(int j = 0; j < m.row; j++){
                temp.matrix[j][i] = m.matrix[j][m.col-1];
            }
            result.matrix[i][0] = temp.determinantMatriks()/det;
        }
        return result;
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
        int row = sc.nextInt();
        int col = sc.nextInt();
        setMatrix(row, col);
        System.out.println("Enter matrix: ");
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                setMatrixValue(i, j, sc.nextDouble());
            }
            dump=sc.nextLine();
        }
        sc.close();
    }

    public matrix matrixBicubicSpline(){
        matrix aBic = new matrix();
        aBic.setMatrix (16, 16);
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

    public void isiMatrixBicubic(int x,int y,int row){
        int cnt=0;
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                this.setMatrixValue(row, cnt, pangkat(x,i)*pangkat(y,j));
                cnt++;
            }
        }
        cnt=0;
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                this.setMatrixValue(row+4, cnt,i*pangkat(x,i-1)*pangkat(y,j));
                cnt++;
            }
        }
        cnt=0;
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                this.setMatrixValue(row+8, cnt,j*pangkat(x,i)*pangkat(y,j-1));
                cnt++;
            }
        }
        cnt=0;
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                this.setMatrixValue(row+12, cnt,i*j*pangkat(x,i-1)*pangkat(y,j-1));
                cnt++;
            }
        }
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


    public double determinantMatriks() {
        // Mencari deteriminan menggunakan metode upper triangle
        double determinant = 1;
        matrix result = new matrix();
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
                //result.printMatriks();
                //System.out.printf("\n");
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
        if(bic) setMatrix(16, 1);
        else setMatrix(row, col);
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

    public double bicMeasure(matrix a){
        double hasil;
        int cnt;

        hasil=0;
        cnt=0;
        for(int j=0;j<=3;j++){
            for(int i=0;i<=3;i++){
                hasil+=(a.matrix[cnt][0]*pangkat(this.tx, i)*pangkat(ty, j));
            }
        }
        return hasil;
    }
}

