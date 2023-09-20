package src;

import java.util.Scanner;

import javax.swing.plaf.BorderUIResource.MatteBorderUIResource;
public class matrix{
    double [][] matrix;
    int row;
    int col;

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
        // Memberika matriks nilai
        setRow(row);
        setCol(col);
        initializeMatrix(this.row, this.col);
    }


    // Methods
    public void setMatrixValue(int row, int col, double value){
        this.matrix[row][col] = value;
    }


    public matrix add(matrix m){
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
        /*matrix a . matrix b */
        matrix hasil = new matrix();
        int sum=0;
        hasil.setMatrix(a.getRow(), b.getCol());
        for(int i=0;i<a.getRow();i++){
            for(int j=0;j<b.getCol();j++){
                sum=0;
                for(int k=0;k<b.getRow();k++) sum+=(a.matrix[i][k]*b.matrix[k][j]);
                hasil.matrix[i][j]=sum;
            }
        }
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
  
    public void minusOBE(matrix m,int row,int col){
        double scale;
        for(int i = row+1;i<m.row;i++){
            scale = m.matrix[i][col];
            for(int j = 0; j<m.col;j++){
                m.matrix[i][j]-=((m.matrix[row][j])*scale);
            }
        }
    }

    public void minusOBESExt(matrix m,int row,int col,matrix ident){
        double scale;
        for(int i = row+1;i<m.row;i++){
            scale = m.matrix[i][col];
            for(int j = 0; j<m.col;j++){
                m.matrix[i][j]-=((m.matrix[row][j])*scale);
                ident.matrix[i][j]-=((m.matrix[row][j])*scale);
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
            // print pivot col = pivot col
            // System.out.println("pivot col = " + pivotCol);

            double pivot = result.matrix[i][pivotCol];
            //print pivot
            // System.out.println("pivot = " + pivot);
            // //print i
            // System.out.println("i = " + i);
            if(!(isRow0(result, i))){
                for(int j = pivotCol;j<result.col;j++){
                    result.matrix[i][j]/=pivot;
            }
                minusOBE(result, i, pivotCol);
            }
        }
            return result;
    }
    
    public void eraseNon0(matrix m,int row,int col){
        // Mengubah elemen diatas leading one menjadi 0
        for(int i= 0;i<row;i++){
            if(m.matrix[i][col]!=0){
                double scale = m.matrix[i][col];
                for(int j = 0;j<m.col;j++){
                    m.matrix[i][j]-=scale*m.matrix[row][j];
                }
            }
        }
    }

    public void eraseNon0Ext(matrix m,int row,int col,matrix ident){
        // Mengubah elemen diatas leading one menjadi 0
        for(int i= 0;i<row;i++){
            if(m.matrix[i][col]!=0){
                double scale = m.matrix[i][col];
                for(int j = 0;j<m.col;j++){
                    m.matrix[i][j]-=scale*m.matrix[row][j];
                    ident.matrix[i][j]-=scale*m.matrix[row][j];
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
                eraseNon0(result, i, leading1);
            }
            }
            return result;
        }
    
    public matrix inverseMatrix(matrix m) {
        // Meng-invers matriks
        matrix identMatrix;
        identMatrix = new matrix();
        identMatrix.setMatrix(m.row, m.col);
        // matrix copyMatrix;
        // identMatrix = this.copy(this.matrix);
        // identMatrix.setMatrix(this.row, this.col);
        identMatrix = m.identity();
        for (int n =0;n<this.row;n++) {
            identMatrix.print();
            System.out.println();
            double pivot = m.matrix[n][n];
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


        // Print matriks
                // for(int tesi = 0; tesi < m.row; tesi++){
                //     for(int tesj = 0; tesj < m.col; tesj++){
                //             System.out.printf("%.2f ",m.matrix[tesi][tesj]); // Print dua angka dibelakang desimal
                // }
                // System.out.println(); }
                // identMatrix.print(m);
        return identMatrix; 
    }
    
        
        

    /*
     * 1 2 3
     * 5 2 3
     * 7 2 3
     */

    // Read or Write Matrix
    public void print(){
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
        }
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
                this.setMatrixValue(row, cnt, pownolsatu(x,i)*pownolsatu(y,j));
                cnt++;
            }
        }
        cnt=0;
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                this.setMatrixValue(row+4, cnt,i*pownolsatu(x,i-1)*pownolsatu(y,j));
                cnt++;
            }
        }
        cnt=0;
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                this.setMatrixValue(row+8, cnt,j*pownolsatu(x,i)*pownolsatu(y,j-1));
                cnt++;
            }
        }
        cnt=0;
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                this.setMatrixValue(row+12, cnt,i*j*pownolsatu(x,i-1)*pownolsatu(y,j-1));
                cnt++;
            }
        }
    }
    public int pownolsatu(int a,int b){
        if(a==1){
            return 1;
        }
        else{
            if(b==0)return 1;
            else return 0;
        }
    }
}

