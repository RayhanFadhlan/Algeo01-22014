package src;

import java.util.Scanner;

public class MainOperation {
    /* MAIN OPERATION */    
    public static Matrix inverseMatrixIdentity(Matrix par) {
        Matrix m = new Matrix();
        m.setMatrix(par.row, par.col);
        m = Matrix.copyMatrix(par);
        Matrix identMatrix;
        identMatrix = new Matrix();
        identMatrix.setMatrix(m.row, m.col);
        identMatrix = Matrix.identity(m);
        for (int n =0;n<par.row;n++) {
            double pivot = m.matrix[n][n];
            for (int i = n + 1; i<par.row;i++) {
                int newN = i;
                if (pivot == 0) { // Cek dan tuker bila nilai pivotnya nol
                    while ((newN < par.row)) {
                        Matrix.swaprow(m, newN, n);
                        Matrix.swaprow(identMatrix, newN, n);
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
         for (int n=par.row-1;n>=0;n--){
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
    

    public static Matrix matrixBicubicSpline(){
        Matrix aBic = new Matrix();
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


    

    public static double determinantGaussMatriks(Matrix m) {
        // Mencari deteriminan menggunakan metode upper triangle
        double determinant = 1;
        Matrix result = new Matrix();
        result = Matrix.copyMatrix(m);
        for (int n=0;n<m.row;n++) {
            for (int i = n + 1; i<m.row;i++) {
                double pivot = result.matrix[n][n];
                int newN = i;

                if (pivot == 0) { // Cek dan tuker bila nilai pivotnya nol
                    while ((newN < m.row)) {
                        Matrix.swaprow(result, newN, n);
                        pivot = result.matrix[n][n];
                        if (pivot != 0) {
                            break;
                        }
                    }
                }
                double scale = result.matrix[i][n] / pivot; 
                for (int j=0;j<m.col;j++) {
                    result.matrix[i][j] -= result.matrix[n][j] * scale;
                }
            }
        } 
        for (int i=0;i<m.row;i++) { // Mengalikan determinan secara diagonal
            determinant *= result.matrix[i][i];
        }
        if (determinant == 0) {
            determinant = 0;
        }
        return determinant;
    } 
    
    public static void getSPLCramer(Matrix m){
        if(!Matrix.isMatrixCramerable(m)){
            System.out.println("Matriks tidak dapat diselesaikan dengan metode Cramer, silakan menggunakan metode gauss atau gauss jordan.");
        }
        else{
            Matrix result = new Matrix();
            result.setMatrix(m.row, 1);
            result = Matrix.getCramerSol(m);
            for (int i = 0; i < result.row; i++) {
                System.out.printf("x%d = %.2f\n", i + 1, result.matrix[i][0]);
            }
        }
    }

            
                    
    public static double getDeterminantCofactor(Matrix m){
        if (m.row == 1){
            return m.matrix[0][0];
        } else {
            float det = 0;
            int i,j,k;
            for (i = 0; i < m.row; i++){
                Matrix mTemp = new Matrix();
                mTemp.setMatrix(m.row -1, m.col);
                for (j = 1; j < m.row; j++){
                    for (k = 0; k < m.col; k++){
                        if (k < i){
                            mTemp.matrix[j-1][k] = m.matrix[j][k];
                        } else if (k > i){
                            mTemp.matrix[j-1][k-1] = m.matrix[j][k];
                        }
                    }
                }
                det += m.matrix[0][i] * getDeterminantCofactor(mTemp) * (i % 2 == 0 ? 1 : -1);
            }
            return det;
        }
    }


    public static void getSPLGauss(Matrix m){
        m = Matrix.gauss(m);
        if(Matrix.isSPLUnique(m)){
            Matrix result = new Matrix();
            int sumOfSolution = m.getCol() -1;
            result.setMatrix(sumOfSolution, 1);
            for(int i = sumOfSolution -1 ; i>= 0;i--){
                result.matrix[i][0] = m.matrix[i][sumOfSolution];
                for(int j = i+1;j<m.col-1;j++){
                    result.matrix[i][0]-=m.matrix[i][j]*result.matrix[j][0];
                }
                result.matrix[i][0] /= m.matrix[i][i];
            }

            // TO PRINT RESULT
            for(int i=0;i<result.row;i++) {
                System.out.printf("x%d = %.2f\n",i+1,result.matrix[i][0]);
            }

        }
        else if (Matrix.isSPLInfiniteSol(m)){
            // Dijadiin ke gauss jordan
            m = Matrix.gaussJordan(m);
            System.out.println("Matriks Solusi Banyak");
            int variable = 115;


            Matrix freeVariables = new Matrix();
            freeVariables.setMatrix(1, m.col-1);

            for (int i=0;i<m.row;i++){
                int idxLeadingOne = Matrix.findLeading1(m, i);
                freeVariables.matrix[0][idxLeadingOne] += 1;
                Matrix.printParametricValue(m, i);
            }
            for (int j=1;j<m.col-1;j++){
                if (freeVariables.matrix[0][j] == 0) {
                    System.out.printf("x%d = %c\n",j+1,(char) variable);
                    variable += 1;
                }
            }
            variable = 115;
            for (int j=1;j<m.col-1;j++){
                if (freeVariables.matrix[0][j] == 0) {
                    System.out.printf("%c ",(char) variable);
                    variable += 1;
                }
            }
            System.out.printf("Memiliki nilai real yang bebas.");
        }
        else if (Matrix.isSPLInvalidValue(m)){
            System.out.println("SPL Tidak ada solusi.");
        } else {
        }
}

    public static void getSPLGaussJordan(Matrix m){
        m = Matrix.gaussJordan(m);
        if(Matrix.isSPLUnique(m)){
            Matrix result = new Matrix();
            int sumOfSolution = m.getCol() -1;
            result.setMatrix(sumOfSolution, 1);
            for(int i = sumOfSolution -1 ; i>= 0;i--){
                result.matrix[i][0] = m.matrix[i][sumOfSolution];
                for(int j = i+1;j<m.col-1;j++){
                    result.matrix[i][0]-=m.matrix[i][j]*result.matrix[j][0];
                }
                result.matrix[i][0] /= m.matrix[i][i];
            }

            // TO PRINT RESULT
            for(int i=0;i<result.row;i++) {
                System.out.printf("x%d = %.2f\n",i+1,result.matrix[i][0]);
            }

        }
        else if (Matrix.isSPLInfiniteSol(m)){
            // Dijadiin ke gauss jordan
            m = Matrix.gaussJordan(m);
            System.out.println("Matriks Solusi Banyak");
            int variable = 115;


            Matrix freeVariables = new Matrix();
            freeVariables.setMatrix(1, m.col-1);

            for (int i=0;i<m.row;i++){
                int idxLeadingOne = Matrix.findLeading1(m, i);
                freeVariables.matrix[0][idxLeadingOne] += 1;
                Matrix.printParametricValue(m, i);
            }
            for (int j=1;j<m.col-1;j++){
                if (freeVariables.matrix[0][j] == 0) {
                    System.out.printf("x%d = %c\n",j+1,(char) variable);
                    variable += 1;
                }
            }
            variable = 115;
            for (int j=1;j<m.col-1;j++){
                if (freeVariables.matrix[0][j] == 0) {
                    System.out.printf("%c ",(char) variable);
                    variable += 1;
                }
            }
            System.out.printf("Memiliki nilai real yang bebas.");
        }
        else if (Matrix.isSPLInvalidValue(m)){
            System.out.println("SPL Tidak ada solusi.");
        } else {
        }
}


    
    public void interpolasiPolinomial() {
        Scanner sc = new Scanner(System.in);
        int n,banyakTitik;
        System.out.println("Masukkan Banyak Titik:");
        banyakTitik = sc.nextInt();
        n = banyakTitik - 1;

        Matrix tabelMatrix = new Matrix();
        tabelMatrix.setMatrix(banyakTitik,banyakTitik+1);
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
        resultMatrix = Matrix.copyMatrix(tabelMatrix);
        resultMatrix = Matrix.gaussJordan(tabelMatrix);
        resultMatrix.printMatriks(); 

        float inputX = sc.nextFloat();
        float result;
        result = 0;
        for (int i=0;i<banyakTitik;i++){
            result += resultMatrix.matrix[i][banyakTitik] * Math.pow(inputX,i);
        }
        System.out.printf("Result: %f",result);
    }


    public static void inverseSPL(Matrix m){
        if(!Matrix.isSPLInverseable(m)){
            System.out.println("Tidak dapat dicari invers dari matriks, gunakan metode gauss atau gauss jordan");
        }
        else{
            Matrix inversed = new Matrix();
            inversed.setMatrix(m.row, m.col);
            inversed = Matrix.copyMatrix(m);
            inversed = Matrix.removeCol(inversed.col-1,inversed);
            inversed = inverseMatrixIdentity(inversed);
            Matrix matrixB = new Matrix();
            matrixB.setMatrix(m.row, 1);
            matrixB = Matrix.formMatrixfrom1Col(m,m.col-1);
            Matrix sol = new Matrix();
            sol.setMatrix(m.row, 1);
            sol = Matrix.perkalianMatrix(inversed, matrixB);
            for (int i = 0; i < sol.row; i++) {
                System.out.printf("x%d = %.2f\n", i + 1, sol.matrix[i][0]);
            }}

            
    }


    public void formReglin(Matrix matrixResult,Matrix parxy){
        Matrix xy= new Matrix();
        int c,r;
        double sum;
        xy.setMatrix(parxy.row, parxy.col);
        xy = Matrix.copyMatrix(parxy);
        //k jadi c
        //n jadi r
        c = xy.col;
        r = xy.row;
        matrixResult.setMatrix(c, c+1);
        matrixResult.setMatrixValue(0,0,r);
        for(int i=1;i<c;i++){
            sum=0;
            for(int k=0;k<r;k++){
                sum+=xy.matrix[k][i-1];
            }
            matrixResult.setMatrixValue(i, 0, sum);
            matrixResult.setMatrixValue(0, i, sum);
        }
        for(int i=1;i<c;i++){
            for(int j=1;j<c;j++){
                sum=0;
                    for(int k=0;k<r;k++){
                        for(int l=0;l<r;l++){
                            sum+=(xy.matrix[k][i-1]*xy.matrix[l][j-1]);
                        }
                    }
                matrixResult.matrix[i][j]=sum;
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
            matrixResult.matrix[i][c]=sum;
        }
    }
    
}

