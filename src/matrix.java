package src;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;
import javax.swing.plaf.BorderUIResource.MatteBorderUIResource;
import java.text.DecimalFormat;

public class Matrix {
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

    /* GETTERS */
    public int getRow() {
        // Mengembalikan nilai row
        return this.row;
    }

    public int getCol() {
        // Mengembalikan nilai col
        return this.col;
    }

    /* SETTERS */
    public void setRow(int value) {
        // Memberi nilai row
        this.row = value;
    }
    
    public void setCol(int value) {
        // Memberi nilai col
        this.col = value;
    }
    
    public void setMatrixValue(int nrow, int ncol, double value){
        // Memberikan matriks nilai untuk baris dan kolom
        this.matrix[nrow][ncol] = value;
    }

    /* INISIALISASI MATRIKS */
    public void initializeMatrix(int row, int col){
        // Inisialisasi matriks
        this.matrix = new double[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                this.matrix[i][j]=0;
            }
        }
    }

    public void setMatrix(int nrow, int ncol) {
        // Memberikan matriks nilai baris dan kolom
        setRow(nrow);
        setCol(ncol);
        initializeMatrix(this.row, this.col);
    }




    /*OPERASI TERHADAP MATRIKS*/
    public Matrix add(Matrix m){
        // Menjumlahkan matrik dengan elemennya sendiri 
        Matrix result = new Matrix(); 
        result.setMatrix(row, col);
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

        result.setMatrix(m.row, m.col);
        for(int i = 0; i < m.row; i++){
            for(int j = 0; j < m.col; j++){
                result.setMatrixValue(i, j, m.matrix[i][j]);
            }
        }
        return result;
    }


    public static Matrix transpose(Matrix m){
        // Transpose matriks
        Matrix result = new Matrix(); 

        result.setMatrix(m.row, m.col);        
        for(int i = 0; i < m.col; i++){
            for(int j = 0; j < m.row; j++){
                result.setMatrixValue(i, j, m.matrix[j][i]);
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


    public static Matrix identity(Matrix m) {
        // Mengembalikan nilai matriks identitas NxN
        Matrix identitas = new Matrix();
        identitas.setMatrix(m.row, m.col);
        for(int i = 0; i < m.row; i++) {
            for (int j = 0; j < m.col; j++) {
                if (i == j) {
                    identitas.setMatrixValue(i, j, 1);
                } else {
                    identitas.setMatrixValue(i, j, 0);
                }
            }
        }
        return identitas;
    }

    public static boolean isRow0(Matrix m,int nrow,boolean includeLastCol){
        // Mengecek apakah nilai dari suatu baris bernilai 0
        if (includeLastCol == false) {
            for(int i = 0;i<m.col-1;i++){
                if(m.matrix[nrow][i]!=0){
                    return false;
                }
            }
            return true;
        } else {
            for(int i = 0;i<m.col;i++){
                if(m.matrix[nrow][i]!=0){
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean isSPLInverseable(Matrix m){
        return (MainOperation.determinantGaussMatriks(m)!=0 && m.row==m.col-1);
    }

    public static Matrix removeCol(int col,Matrix m){
        Matrix result = new Matrix();
        result.setMatrix(m.row, m.col-1);
        for (int i = 0; i < m.row; i++) {
            for (int j = 0; j < col; j++) {
                result.matrix[i][j] = m.matrix[i][j];
            }
            for (int j = col+1; j < m.col; j++) {
                result.matrix[i][j-1] = m.matrix[i][j];
            }
        }
        return result;
    }

    public static Matrix formMatrixfrom1Col(Matrix m,int col){
        Matrix result = new Matrix();
        result.setMatrix(m.row, 1);
        for (int i = 0; i < m.row; i++) {
            result.matrix[i][0] = m.matrix[i][col];
        }
        return result;
    }

    
    public static Matrix gauss(Matrix m){
        Matrix result = copyMatrix(m);
        for(int i = 0;i<result.row;i++){
            int pivotCol = result.swapZeroToBottom(i);
            double pivot = result.matrix[i][pivotCol];
            if(!(isRow0(result, i,false))){
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
            if(!isRow0(result, i,false)){
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

    public int getSumOfLeading1(){
        int sum = 0;
        for (int i = 0; i < this.row; i++){
            for (int j = 0; j < this.col-1; j++){
                if (this.matrix[i][j] == 1){
                    sum++;
                    break;
                }
                else if (this.matrix[i][j] != 0){
                    break;
                }
            }
        }
        return sum;
    }

    public static int countNonZeroRow(Matrix m){
        int count = 0;
        for (int i=0;i<m.row;i++){
            if (!isRow0(m, i,true)) {
                count += 1;
            }
        }
        return count;
    }

    public static boolean isSPLUnique(Matrix m){
        return (m.getSumOfLeading1() >= m.col -1);
    }

    public static boolean isRowInvalidSol(Matrix m,int row){
        for(int i = 0;i<m.col-1;i++){
            if(m.matrix[row][i]!=0){
                return false;
            }
        }
        if(m.matrix[row][m.col-1]!=0){
            return true;
        }
        return false;
    }
    public static boolean isSPLInfiniteSol(Matrix m){
        for(int i = 0;i<m.row;i++){
            if(isRow0(m,i,true) == true){
                return true;
            }
        }
        return false;
    }

    public static boolean isSPLInvalidValue(Matrix m){
        for(int i = 0;i<m.row;i++){
            if(isRow0(m,i,false) == true){
                return true;
            }
        }
        return false;   
    }

    public static boolean isMatrixSquare(Matrix m) {
        return (m.col == m.row);
    }

    public static void printParametricValue(Matrix m, int nRow){
        if (!isRow0(m, nRow,true)){
            int variable = 115;
            int leadingOneIdx = findLeading1(m, nRow);
            System.out.printf("x%d = ",leadingOneIdx + 1);
            for (int i=leadingOneIdx + 1;i<m.col - 1;i++){
                if (m.matrix[nRow][i] != 0 && i != leadingOneIdx) {
                    System.out.printf("(%.2f)%c",m.matrix[nRow][i]*-1,(char)variable);
                    variable += 1;
                    if (m.matrix[nRow][i+1] != 0) {
                        System.out.printf(" + ");
                    }
                }
            }
            if (m.matrix[nRow][m.col-1] != 0) {
                System.out.printf("(%.2f)",m.matrix[nRow][m.col-1]);
            }
            System.out.println();
        }
    }

    // make cramer to get linear equation
    public static Matrix getCramerSol(Matrix m){
        Matrix square = new Matrix();
        square.setMatrix(m.row, m.col-1);
        for(int i = 0; i < m.row; i++){
            for(int j = 0; j < m.col-1; j++){
                square.matrix[i][j] = m.matrix[i][j];
            }
        }

        Matrix result = new Matrix();
        result.setMatrix(square.row, 1);
        double det = MainOperation.determinantGaussMatriks(square);

        // replace column dgn solusi dan cari determinan
        for(int i = 0; i < square.row; i++){
            Matrix temp = new Matrix();
            temp.setMatrix(square.row, square.col);
            temp = Matrix.copyMatrix(square);
            for(int j = 0; j < m.row; j++){
                temp.matrix[j][i] = m.matrix[j][m.col-1];
            }
            result.matrix[i][0] = MainOperation.determinantGaussMatriks(temp)/det;
        }
        return result;
    }

    public static boolean isMatrixCramerable(Matrix m){
        return ((m.row == m.col - 1) && MainOperation.determinantGaussMatriks(m) != 0);
    }

    public static Matrix getMinor(Matrix m,int row, int col) {
        // Mencari minor dari matriks
        Matrix result = new Matrix();
        result.setMatrix(m.row - 1, m.col - 1);
        int cnt = 0;
        for (int i = 0; i < m.row; i++) {
            if (i != row) {
                for (int j = 0; j < m.col; j++) {
                    if (j != col) {
                        result.matrix[cnt / (m.col - 1)][cnt % (m.col - 1)] = m.matrix[i][j];
                        cnt++;
                    }
                }
            }
        }
        // transpose result using transpose functions already made

        return result;
    }

    public static Matrix getAdjoin(Matrix m) {
        // Mencari adjoin dari matriks
        Matrix result = new Matrix();
        result.setMatrix(m.row, m.col);

        if (m.row == 1 && m.col == 1) {
            result.setMatrixValue(0, 0, 1);
            return result;
        } else {
            Matrix temp = new Matrix();
            temp.setMatrix(m.row - 1, m.col - 1);
            int sign = 1;
            for (int i = 0; i < m.row; i++) {
                for (int j = 0; j < m.col; j++) {
                    sign = ((i + j) % 2 == 0) ? 1 : -1;
                    temp = getMinor(m,i, j);
                    result.matrix[i][j] = sign * MainOperation.determinantGaussMatriks(temp);
                }
            }

        }
        result = transpose(result);
        return result;
        // tes
    }

    public static Matrix perkalianWithSkalar(Matrix m,double num){
        Matrix result = new Matrix();
        result =copyMatrix(m);
        for(int i = 0;i<m.row;i++){
            for(int j = 0;j<m.col;j++){
                //multiply each element of result with num
                result.matrix[i][j] *= num;
            }
        }
        return result;
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

    

    /* BACA ATAU TULIS MATRIKS */

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


    public void printMatriks(){
        // Read or Write Matrix
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                System.out.printf("%.2f\t",matrix[i][j]); // Print dua angka dibelakang desimal
            }
            System.out.println();
        }
    }

    public void bacaMatriks() {
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
    }


    public void buatFile(String namaFile) {
        //try {
            File objekFile = new File(namaFile);
            if (!(objekFile.exists() && !objekFile.isDirectory())) {
                System.out.println("File " + objekFile.getName() + " dibuat.");
            } else {
                System.out.println("File sudah ada. Silahkan ganti nama file");
            }
        //} catch (IOException e) {
        //    System.out.println("Ada error");
        //    e.printStackTrace();
        // }
    }

    public void writeMatrixToFile() {
        try {
            String namaFile;
            DecimalFormat df = new DecimalFormat("0.00");
            System.out.println("Masukan nama file");
            Scanner sc = new Scanner(System.in);
            // sc.nextLine();
            namaFile = sc.nextLine();
            // namaFile += namaFile + ".txt";
            // System.out.println(namafile);
            buatFile(namaFile);
            FileWriter penulis = new FileWriter(namaFile + ".txt");
            
            for (int i=0; i<this.row;i++){
                for (int j=0 ; j<this.col;j++){
                    String elemen = df.format(this.matrix[i][j]);
                    penulis.write(elemen);
                    penulis.write(" ");
                }
                penulis.write("\n");
            }
            penulis.close();
            sc.close();
        } catch (IOException e) {
            System.out.println("Input ada yang tidak jelas.");
            e.printStackTrace();
        }
    }

}



