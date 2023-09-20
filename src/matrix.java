package src;

import java.util.Scanner;
public class matrix{
    /*kelas matrix
     * berisikan value matrix,row(baris),col(kolom)
     */
    double [][] matrix;
    int row;
    int col;
    public matrix(int row, int col){
        this.row = row;
        this.col = col;
        matrix = new double[row][col];
    }


    public void set(int row, int col, double value){
        /*memasukkan value kedalam matrix[row][col] */
        matrix[row][col] = value;
    }


    public matrix add(matrix m){/
        /*mengembalikan matrix yang 
        setiap elemennya sudah ditambahkan oleh matrix m
        pada indeks yang sama*/
        matrix result = new matrix(row, col);
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                result.set(i, j, matrix[i][j] + m.matrix[i][j]);
            }
        }
        return result;
    }

    public void swaprow(matrix m, int i, int j){
        /*menukar baris i dan j*/
        double temp[] = m.matrix[i];
        m.matrix [i] = m.matrix[j];
        m.matrix[j] = temp;

    }

    public matrix copyMatrix(matrix m){
        /*mencopy matrix*/
        matrix result = new matrix(row, col);
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                result.set(i, j, matrix[i][j]);
            }
        }
        return result;
    }


    public matrix transpose(matrix m){
        matrix result = new matrix(col, row);
        for(int i = 0; i < col; i++){
            for(int j = 0; j < row; j++){
                result.set(i, j, matrix[j][i]);
            }
        }
        return result;
    }

    public int makeLeftNonZero(matrix m,int row){
        // check if leftmost col not all 0
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
    public boolean isRow0(matrix m,int row){
        for(int i = 0;i<m.col-1;i++){
            if(m.matrix[row][i]!=0){
                return false;
            }
        }
        return true;
    }
    public matrix OBE(matrix m){
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
        for(int i = 0;i<m.col;i++){
            if(m.matrix[row][i]==1){
                return i;
            }
        }
        return 0;
    }
    public matrix GaussJordan(matrix m){
        matrix result = copyMatrix(m);
        result = OBE(result);
        for(int i = result.row-1;i>=0;i--){
            if(!isRow0(result, i)){
                int leading1 = findLeading1(result, i);
                eraseNon0(result, i, leading1);
            }
            }
            return result;
        }
    
    
    //print matrix
    public void print(){
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    //input matrix
    public static matrix input(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter row and col: ");
        int row = sc.nextInt();
        int col = sc.nextInt();
        matrix m = new matrix(row, col);
        System.out.println("Enter matrix: ");
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                m.set(i, j, sc.nextDouble());
            }
        }
        return m;
    }
    // test obe and print
    public static void main(String[] args){
        matrix m = input();
        m.print();
        System.out.println("OBE: ");
        matrix result = m.GaussJordan(m);
        result.print();
    }
    
}
// test OBE and print

