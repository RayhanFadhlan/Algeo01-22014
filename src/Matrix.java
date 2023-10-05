package src;

import java.io.File;
import java.util.NoSuchElementException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;
import java.text.DecimalFormat;

public class Matrix {
    double[][] matrix;
    int row;
    int col;
    double tx, ty;

    // Constructor
    public Matrix() {
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

    public void initializeMatrix(int row, int col) {
        // Inisialisasi matriks
        this.matrix = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                this.matrix[i][j] = 0;
            }
        }
    }

    public void setMatrix(int nrow, int ncol) {
        // Memberikan matriks nilai baris dan kolom
        setRow(nrow);
        setCol(ncol);
        initializeMatrix(this.row, this.col);
    }

    public void setMatrixValue(int nrow, int ncol, double value) {
        // Memberikan matriks nilai untuk baris dan kolom
        this.matrix[nrow][ncol] = value;
    }

    // Methods

    /* OPERASI MATRIKS */
    public Matrix add(Matrix m) {
        // Menjumlahkan matrik dengan matriks lain
        Matrix result = new Matrix();
        result.setMatrix(row, col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result.setMatrixValue(i, j, matrix[i][j] + m.matrix[i][j]);
            }
        }
        return result;
    }

    public static Matrix perkalianMatrix(Matrix a, Matrix b) {
        // Perkalian matriks

        Matrix hasil = new Matrix();
        double sum = 0;
        hasil.setMatrix(a.getRow(), b.getCol());
        for (int i = 0; i < a.getRow(); i++) {
            for (int j = 0; j < b.getCol(); j++) {
                sum = 0;
                for (int k = 0; k < b.getRow(); k++) {
                    sum += (a.matrix[i][k] * b.matrix[k][j]);
                }
                hasil.matrix[i][j] = sum;
            }
        }
        return hasil;
    }

    public static void swaprow(Matrix m, int i, int j) {
        // Menukar baris matriks
        double temp[] = m.matrix[i];
        m.matrix[i] = m.matrix[j];
        m.matrix[j] = temp;
    }

    public static Matrix copyMatrix(Matrix m) {
        // Memberi salinan dari matriks m
        Matrix result = new Matrix();

        result.setMatrix(m.row, m.col);
        for (int i = 0; i < m.row; i++) {
            for (int j = 0; j < m.col; j++) {
                result.setMatrixValue(i, j, m.matrix[i][j]);
            }
        }
        return result;
    }

    public static Matrix transpose(Matrix m) {
        // Transpose matriks
        Matrix result = new Matrix();

        result.setMatrix(m.row, m.col);
        for (int i = 0; i < m.col; i++) {
            for (int j = 0; j < m.row; j++) {
                result.setMatrixValue(i, j, m.matrix[j][i]);
            }
        }
        return result;
    }

    public int swapZeroToBottom(int nrow) {
        // Menukar baris dengan nilai nol paling banyak ke bawah.
        for (int j = nrow; j < this.col; j++) {
            for (int i = nrow; i < this.row; i++) {
                if (this.matrix[i][j] != 0) {
                    swaprow(this, nrow, i);
                    return j;
                }
            }
        }
        return 0;
    }

    public void forwardOBE(int nrow, int ncol) {
        // OBE dengan langkah maju
        double scale;
        for (int i = nrow + 1; i < row; i++) {
            scale = matrix[i][ncol];
            for (int j = 0; j < col; j++) {
                matrix[i][j] -= ((matrix[nrow][j]) * scale);
            }
        }
    }

    public Matrix identity() {
        // Mengembalikan nilai matriks identitas NxN
        Matrix identitas = new Matrix();
        identitas.setMatrix(this.row, this.col);
        for (int i = 0; i < this.row; i++) {
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

    public static boolean isRow0(Matrix m, int nrow) {
        // Mengecek apakah nilai dari suatu baris bernilai 0
        for (int i = 0; i < m.col - 1; i++) {
            if (m.matrix[nrow][i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isRow0New(Matrix m, int nrow) {
        // Mengecek apakah nilai dari suatu baris bernilai 0 dengan mengganggap kolom
        // terakhir
        for (int i = 0; i < m.col; i++) {
            if (m.matrix[nrow][i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static Matrix gauss(Matrix m) {
        Matrix result = copyMatrix(m);
        for (int i = 0; i < result.row; i++) {
            int pivotCol = result.swapZeroToBottom(i);
            double pivot = result.matrix[i][pivotCol];
            if (!(isRow0(result, i))) {
                for (int j = pivotCol; j < result.col; j++) {
                    result.matrix[i][j] /= pivot;
                }
                result.forwardOBE(i, pivotCol);
            }
        }
        return result;
    }

    public void reverseOBE(int nrow, int ncol) {
        // Melakukan OBE dengan langkah mundur
        for (int i = 0; i < nrow; i++) {
            if (this.matrix[i][ncol] != 0) {
                double scale = this.matrix[i][ncol];
                for (int j = 0; j < this.col; j++) {
                    this.matrix[i][j] -= scale * this.matrix[nrow][j];
                }
            }
        }
    }

    public static boolean isLeading1Present(Matrix m, int nrow) {
        // Mengecek apakah nilai leading one ada pada suatu baris
        for (int i = 0; i < m.col - 1; i++) {
            if (m.matrix[nrow][i] == 1) {
                return true;
            }
        }
        return false;
    }

    public static int findLeading1(Matrix m, int nrow) {
        // Mencari nilai leading one
        for (int i = 0; i < m.col - 1; i++) {
            if (m.matrix[nrow][i] == 1) {
                return i;
            }
        }
        return 0;
    }

    public static Matrix gaussJordan(Matrix m) {
        Matrix result = copyMatrix(m);
        result = gauss(result);
        for (int i = result.row - 1; i >= 0; i--) {
            if (!isRow0(result, i)) {
                int leading1 = findLeading1(result, i);
                result.reverseOBE(i, leading1);
            }
        }
        return result;
    }

    public double pangkat(double a, double b) {
        if (a == 0) {
            if (b == 0)
                return 1;
            else
                return 0;
        } else {
            return (Math.pow(a, b));
        }
    }

    /* BACA ATAU TULIS MATRIKS */
    public void printMatriks() {
        // Read or Write Matrix
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.printf("%.2f ", matrix[i][j]); // Print dua angka dibelakang desimal
            }
            System.out.println();
        }
    }

    public void bacaMatriks(boolean bic) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter row and col: ");
        int row = sc.nextInt();
        int col = sc.nextInt();
        setMatrix(row, col);
        System.out.println("Enter matrix: ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                setMatrixValue(i, j, sc.nextDouble());
            }
            sc.nextLine();
        }
        if (bic) {
            System.out.println("Masukkan nilai x dan y yang ingin dicari:");
            tx = sc.nextDouble();
            ty = sc.nextDouble();
        }
    }


    public Matrix printInverseAdjoin(){
        if(!this.isSquare() || this.determinantGaussMatriks() == 0){
            System.out.println("Matriks tidak berukuran nxn. tidak dapat dicari invers.");
            return null;
        }
        else{
            Matrix result = new Matrix();
            result.setMatrix(this.row, this.col);
            result = this.getInverseADJ();
            result.printMatriks();
            return result;
        }
    }

    public Matrix printInverseCofactor(){
        if(!this.isSquare() || this.determinantGaussMatriks() == 0){
            System.out.println("Matriks tidak berukuran nxn. tidak dapat dicari invers.");
            return null;
        }
        else{
            Matrix result = new Matrix();
            result.setMatrix(this.row, this.col);
            result = this.getAdjoin();
            for (int i = 0; i < result.row; i++) {
                for (int j = 0; j < result.col; j++) {
                    result.matrix[i][j] /= this.determinantGaussMatriks();
                }
            }
            result.printMatriks();
            return result;
        }
    }

    /* MAIN OPERATION */
    public Matrix inverseMatrixIdentity() {
        // Metode Identitas
        Matrix m = new Matrix();
        m.setMatrix(this.row, this.col);
        m = copyMatrix(this);
        Matrix identMatrix;
        identMatrix = new Matrix();
        identMatrix.setMatrix(m.row, m.col);
        identMatrix = m.identity();
        for (int n = 0; n < this.row; n++) {
            double pivot = m.matrix[n][n];
            for (int i = n + 1; i < this.row; i++) {
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
            for (int j = 0; j < m.col; j++) {
                m.matrix[n][j] /= pivot;
                identMatrix.matrix[n][j] /= pivot;
            }
            double scale;
            for (int i = n + 1; i < m.row; i++) {
                scale = m.matrix[i][n];
                for (int j = 0; j < m.col; j++) {
                    m.matrix[i][j] -= ((m.matrix[n][j]) * scale);
                    identMatrix.matrix[i][j] -= ((identMatrix.matrix[n][j]) * scale);
                }

            }
        }
        for (int n = this.row - 1; n >= 0; n--) {
            for (int i = 0; i < n; i++) {
                if (m.matrix[i][n] != 0) {
                    double scale = m.matrix[i][n];
                    for (int j = 0; j < m.col; j++) {
                        m.matrix[i][j] -= scale * m.matrix[n][j];
                        identMatrix.matrix[i][j] -= scale * identMatrix.matrix[n][j];
                    }
                }
            }

        }
        return identMatrix;
    }

    public Matrix matrixBicubicSpline() {
        Matrix aBic = new Matrix();
        aBic.setMatrix(16, 16);
        int cnt;
        cnt = 0;
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                aBic.isiMatrixBicubic(j, i, cnt);
                cnt++;
            }
        }
        return aBic;
    }

    public void isiMatrixBicubic(int x, int y, int nrow) {
        int cnt = 0;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                this.setMatrixValue(nrow, cnt, pangkat(x, i) * pangkat(y, j));
                cnt++;
            }
        }
        cnt = 0;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                this.setMatrixValue(nrow + 4, cnt, i * pangkat(x, i - 1) * pangkat(y, j));
                cnt++;
            }
        }
        cnt = 0;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                this.setMatrixValue(nrow + 8, cnt, j * pangkat(x, i) * pangkat(y, j - 1));
                cnt++;

            }
        }
        cnt = 0;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                this.setMatrixValue(nrow + 12, cnt, i * j * pangkat(x, i - 1) * pangkat(y, j - 1));
                cnt++;
            }
        }
    }

    public boolean isSquare() {
        if (this.row != this.col) {
            return false;
        } else {
            return true;
        }
    }

    public static void buatFile(String namaFile) {
        File objekFile = new File("dataoutput/" + namaFile);
        System.out.println("File " + objekFile.getName() + " dibuat.");
    }

    public String matrixToString() {
        DecimalFormat df = new DecimalFormat("0.00");
        String basis = "";
        for (int i=0; i<this.row;i++){
            for (int j=0 ; j<this.col;j++){
                String elemen = df.format(this.matrix[i][j]);
                basis += elemen;
                basis += " ";
            }
            basis += "\n";
        }
        return basis;
    }

    public void matrixToFile() {
        String mat = this.matrixToString();
        writeStringToFile(mat);
    }

    public static void writeStringToFile(String valueString) {
        // Fungsi ini hanya digunakan untuk menulis string kedalam file baru.
        // Cara Penggunaan: Ubah output operasi menjadi kumpulan string, lalu panggil fungsi ini.
        try {
            String namaFile;
            System.out.println("Masukan nama file:");
            Scanner sc = new Scanner(System.in);
            namaFile = sc.nextLine();
            buatFile(namaFile);
            FileWriter penulis = new FileWriter("dataoutput/"+namaFile + ".txt");
            
            penulis.write(valueString);
            penulis.close();
        } catch (IOException e) {
            System.out.println("Input ada yang tidak jelas.");
            e.printStackTrace();
        }
    }

    public double determinantGaussMatriks() {
        // Mencari deteriminan menggunakan metode upper triangle
        double determinant = 1;
        Matrix result = new Matrix();
        result = copyMatrix(this);
        for (int n = 0; n < this.row; n++) {
            for (int i = n + 1; i < this.row; i++) {
                double pivot = result.matrix[n][n];
                int newN = i;

                if (pivot == 0) { // Cek dan tuker bila nilai pivotnya nol
                    while ((newN < this.row)) {
                        swaprow(result, newN, n);
                        pivot = result.matrix[n][n];
                        if (pivot != 0) {
                            break;
                        }
                    }
                }
                double scale = result.matrix[i][n] / pivot;
                for (int j = 0; j < this.col; j++) {
                    result.matrix[i][j] -= result.matrix[n][j] * scale;
                }
            }
        }
        for (int i = 0; i < this.row; i++) { // Mengalikan determinan secara diagonal
            determinant *= result.matrix[i][i];
        }
        if (determinant == 0) {
            determinant = 0;
        }
        return determinant;
    }

    public Matrix getMinor(int row, int col) {
        // Mencari minor dari matriks
        Matrix result = new Matrix();
        result.setMatrix(this.row - 1, this.col - 1);

        int cnt = 0;
        for (int i = 0; i < this.row; i++) {
            if (i != row) {
                for (int j = 0; j < this.col; j++) {
                    if (j != col) {
                        result.matrix[cnt / (this.col - 1)][cnt % (this.col - 1)] = this.matrix[i][j];
                        cnt++;
                    }
                }
            }
        }
        // transpose result using transpose functions already made

        return result;
    }

    public Matrix getAdjoin() {
        // Mencari adjoin dari matriks
        Matrix result = new Matrix();
        result.setMatrix(this.row, this.col);

        if (this.row == 1 && this.col == 1) {
            result.setMatrixValue(0, 0, 1);
            return result;
        } else {
            Matrix temp = new Matrix();
            temp.setMatrix(this.row - 1, this.col - 1);
            int sign = 1;
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.col; j++) {
                    sign = ((i + j) % 2 == 0) ? 1 : -1;
                    temp = this.getMinor(i, j);
                    result.matrix[i][j] = sign * temp.determinantGaussMatriks();
                }
            }

        }
        result = transpose(result);
        return result;
        // tes
    }

    public void bacaFileMatrix(String parlokasi, boolean parbic) {
        int nrow = 0, ncol = 0;
        String lokasi;
        boolean bic;
        Scanner sc = new Scanner(System.in);
        lokasi = parlokasi;
        bic = parbic;
        if (lokasi == "") {
            System.out.printf("Masukkan nama file: ");
            lokasi = sc.nextLine();
            lokasi = "datainput\\"+lokasi;
            File ada = new File(lokasi);
            while (!(ada.exists() && !ada.isDirectory())) { 
                System.out.println("File tidak ditemukan");
                System.out.printf("Masukkan nama file: ");
                lokasi = sc.nextLine();
                lokasi = "datainput\\"+lokasi;
                ada = new File(lokasi);
            }
        }
        if (bic){
           nrow--;
        }
        try {
            /* mengambil file */
            File inp = new File(lokasi);
            Scanner scf = new Scanner(inp);

            /* mengambil 1 baris dan menghitung kolom */
            Scanner cntcol = new Scanner(scf.nextLine());
            nrow++;
            while (cntcol.hasNextDouble()) {
                ncol++;
                cntcol.nextDouble();
            }

            /* menghitung baris */
            while (scf.hasNextLine()) {
                scf.nextLine();
                nrow++;
            }
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (bic){
            setMatrix(16, 1);
        }
        else{
            setMatrix(nrow, ncol);
        }
        try {
            File inp = new File(lokasi);
            Scanner scf = new Scanner(inp);
            int cnt = 0;
            for (int i = 0; i < nrow; i++) {
                Scanner srow = new Scanner(scf.nextLine());
                for (int j = 0; j < ncol; j++) {
                    if (bic){
                        this.matrix[cnt][0] = srow.nextDouble();
                    }
                    else{
                        // Apabila input kolom matriks tidak sesuai dengan ukurannya, maka secara otmatis
                        // kolom akan diisin dengan nol.
                        try {
                            this.matrix[i][j] = srow.nextDouble();
                        } catch (NoSuchElementException e) {
                            // Case Buat Interpolation
                            this.matrix[i][j] = 0;
                        }
                    }
                    cnt++;
                }
            }
            if (bic) {
                Scanner srow = new Scanner(scf.nextLine());
                this.tx = srow.nextDouble();
                this.ty = srow.nextDouble();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public double bicMeasure(Matrix a) {
        double hasil;
        int cnt;

        hasil = 0;
        cnt = 0;
        for (int j = 0; j <= 3; j++) {
            for (int i = 0; i <= 3; i++) {
                hasil += (a.matrix[cnt][0] * pangkat(this.tx, i) * pangkat(ty, j));
                cnt++;
            }
        }
        return hasil;
    }

    // mengganti satu kolom matriks dengan matriks lain

    // make cramer to get linear equation
    public Matrix getCramerSol() {

        Matrix square = new Matrix();
        square.setMatrix(this.row, this.col - 1);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col - 1; j++) {
                square.matrix[i][j] = this.matrix[i][j];
            }
        }

        Matrix result = new Matrix();
        result.setMatrix(square.row, 1);
        double det = square.determinantGaussMatriks();

        // replace column dgn solusi dan cari determinan
        for (int i = 0; i < square.row; i++) {
            Matrix temp = new Matrix();
            temp.setMatrix(square.row, square.col);
            temp = copyMatrix(square);
            for (int j = 0; j < this.row; j++) {
                temp.matrix[j][i] = this.matrix[j][this.col - 1];
            }
            result.matrix[i][0] = temp.determinantGaussMatriks() / det;
        }
        return result;
    }

    public double getDeterminantCofactor() {
        if (this.row == 1) {
            return this.matrix[0][0];
        } else {
            float det = 0;
            int i, j, k;
            for (i = 0; i < this.row; i++) {
                Matrix mTemp = new Matrix();
                mTemp.setMatrix(this.row - 1, this.col);

                for (j = 1; j < this.row; j++) {
                    for (k = 0; k < this.col; k++) {
                        if (k < i) {
                            mTemp.matrix[j - 1][k] = this.matrix[j][k];

                        } else if (k > i) {
                            mTemp.matrix[j - 1][k - 1] = this.matrix[j][k];

                        }
                    }
                }
                det += this.matrix[0][i] * mTemp.getDeterminantCofactor() * (i % 2 == 0 ? 1 : -1);

            }
            return det;
        }
    }

    public int getSumOfLeading1() {
        int sum = 0;
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col - 1; j++) {
                if (this.matrix[i][j] == 1) {
                    sum++;
                    break;
                } else if (this.matrix[i][j] != 0) {
                    break;
                }
            }
        }
        return sum;
    }

    public static int countNonZeroRow(Matrix m) {
        int count = 0;
        for (int i = 0; i < m.row; i++) {
            if (!isRow0New(m, i)) {
                count += 1;
            }
        }
        return count;
    }

    public boolean isSPLUnique(Matrix m) {
        return (m.getSumOfLeading1() >= m.col - 1);
    }

    public static boolean isRowInvalidSol(Matrix m, int row) {
        for (int i = 0; i < m.col - 1; i++) {
            if (m.matrix[row][i] != 0) {
                return false;
            }
        }
        if (m.matrix[row][m.col - 1] != 0) {
            return true;
        }
        return false;
    }

    public static boolean isSPLInfiniteSol(Matrix m) {
        if (m.row < m.col - 1) {
            return true;
        } else {
            for (int i = 0; i < m.col - 1; i++) {
                if (isRow0New(m, i) == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isSPLInvalidValue(Matrix m) {
        for (int i = 0; i < m.row; i++) {
            if (isRow0(m, i) == true && m.matrix[i][m.col - 1] != 0) {
                return true;
            }
        }
        return false;
    }

    public String printCramerSol() {
        String res = "";
        if (!this.isMatrixCramerable()) {
           
            res += "Matriks tidak dapat diselesaikan dengan metode Cramer, silakan menggunakan metode gauss atau gauss jordan.";
        } 
        
        else {

            Matrix result = new Matrix();
            result.setMatrix(this.row, 1);
            result = this.getCramerSol();
            for (int i = 0; i < result.row; i++) {
                System.out.printf("x%d = %.2f\n", i + 1, result.matrix[i][0]);
                res += "x" + (i + 1) + " = " + result.matrix[i][0] + "\n";
            }
        }
        return res;
    }

    public Matrix getEquationMat() {

        Matrix result = new Matrix();
        result.setMatrix(this.row, this.col - 1);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col - 1; j++) {
                result.matrix[i][j] = this.matrix[i][j];
            }
        }
        return result;
    }

    public boolean isMatrixCramerable() {
        if (this.row != this.col - 1) {
            return false;
        }

        else if (this.getEquationMat().getDeterminantCofactor() == 0) {
            return true;

        }
        return false;

    }

    public Matrix createNewRow(int jumlrowditambah) {
        Matrix result = new Matrix();
        int currentrow = jumlrowditambah + this.row;
        result.setMatrix(currentrow,this.col);
        for(int i = 0;i<this.row;i++){
            for(int j = 0;j<this.col;j++){
                result.matrix[i][j] = this.matrix[i][j];
            }
        }

        for (int i = this.row; i < currentrow; i++) {
            for (int j = 0; j < this.col; j++) {
                result.matrix[i][j] = 0;
            }
        }

        return result;
    }

 
    public int[] createArrayOfSol(int jumlsol) {
        int[] result = new int[jumlsol];
        for (int i = 0; i < jumlsol; i++) {
            result[i] = 0;
        }
        return result;
    }

    public String[] createArrayofParam() {
        // create array size 26 contain all aphabet starting from r
        String[] result = new String[26];
        char currentChar = 'r';
        for (int i = 0; i < 26; i++) {
            result[i] = String.valueOf(currentChar);
            currentChar++;
        }
        return result;
    }

    public boolean isSolParam(int row) {
        if (!isLeading1Present(this, row)) {
            return true;
        }
        for (int j = findLeading1(this, row); j < this.col - 1; j++) {
            if (this.matrix[row][j] != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isMatrixAugmented() {
        return (this.row >= this.col - 1);
    }

    public String printSPLInfSol() {
        int[] sol = createArrayOfSol(this.col - 1);
        String[] param = createArrayofParam();
        Matrix augmented = new Matrix();
        augmented = copyMatrix(this);
        String result = "";
        if (!this.isMatrixAugmented()) {
            augmented = createNewRow(augmented.col - 1 - (augmented.row));
        }
        for (int i = 0; i < augmented.col - 1; i++) {
            if (isLeading1Present(augmented, i) && findLeading1(augmented, i) != i) {
                swaprow(augmented, i, findLeading1(augmented, i));
            }
        }
        for (int i = 0; i < augmented.col - 1; i++) {
            if (isLeading1Present(augmented, i) && findLeading1(augmented, i) != i) {
                swaprow(augmented, i, findLeading1(augmented, i));
            }
        }
        for (int i = 0; i < augmented.col - 1; i++) {
            if (augmented.isSolParam(i)) {
                if(isRow0(augmented, i)){
                    System.out.printf("x%d = %s\n", i+1,param[i]);
                    result += "x" + (i+1) + " = " + param[i] + "\n";
                }
                else{
                    System.out.printf("x%d = ", i+1);
                    result += "x" + (i+1) + " = ";
                    for(int j = findLeading1(augmented,i)+1; j < this.col -1;j++){
                        int counter = 0;
                        if(augmented.matrix[i][j]!=0){
                            if(counter!=0){
                                System.out.print("+ ");
                                result += "+ ";
                            }
                            System.out.printf("%.2f%s ",augmented.matrix[i][j]*-1 ,param[j]);
                            result += augmented.matrix[i][j]*-1 + param[j] + " ";
                            counter++;
                        }
                        }
                    if(augmented.matrix[i][augmented.col-1]!= 0){
                        System.out.printf("+ %.2f",augmented.matrix[i][augmented.col-1]);
                        result += "+ " + augmented.matrix[i][augmented.col-1];
                    }
                    System.out.printf("\n");
                    result += "\n";
                }
                
            }
            else{
                System.out.printf("x%d = %f",i+1,augmented.matrix[i][augmented.col-1]);
                result += "x" + (i+1) + " = " + augmented.matrix[i][augmented.col-1] + "\n";
            }
        }
        return result;

    }

    public String printSPLSol(Matrix m) {
        String result = "";
         if (isSPLInvalidValue(m)) {
            System.out.println("SPL Tidak ada solusi.");
            result = "SPL Tidak ada solusi.";
            
        }


        else if (isSPLUnique(m)) {
            int solCounter = 1;
            
            for (int i = 0; i < m.col - 1; i++) {
                
                System.out.printf("x%d = %.2f\n", solCounter, m.matrix[i][m.col - 1]);
                solCounter++;
                result+= "x" + solCounter + " = " + m.matrix[i][m.col - 1] + "\n";
            }
        } 
        
        else  { // if SPL inf sol
            result =  m.printSPLInfSol();
            

            
            // int variable = 115;
            // Matrix freeVariables = new Matrix();
            // freeVariables.setMatrix(1, m.col);
            // for (int i = 0; i < m.col - 1; i++) {
            //     int idxLeadingOne = findLeading1(m, i);
            //     freeVariables.matrix[0][idxLeadingOne] += 1;
            //     printParametricValue(m, i);
            // }

            // for (int j = 0; j < m.col - 1; j++) {
            //     if (freeVariables.matrix[0][j] == 0) {
            //         System.out.printf("x%d = %c\n", j + 1, (char) variable + j);
            //         // variable += 1;
            //     }
            // }

            // variable = 115;
            // for (int j = 1; j < m.col - 1; j++) {
            //     if (freeVariables.matrix[0][j] == 0) {
            //         System.out.printf("%c ", (char) variable + j);
            //     }
            // }
            // System.out.printf("Memiliki nilai real yang bebas.");
        } 
        return result;
    }

    public static void printParametricValue(Matrix m, int nRow) {
        if (!isRow0New(m, nRow)) {
            int variable = 115;
            int leadingOneIdx = findLeading1(m, nRow);
            System.out.printf("x%d = ", leadingOneIdx + 1);
            for (int i = 0; i < m.col - 1; i++) {
                if (m.matrix[nRow][i] != 0 && i != leadingOneIdx) {
                    System.out.printf("(%.2f)%c + ", m.matrix[nRow][i], (char) variable + i - 2);
                }
                variable += 1;
            }
            System.out.printf("(%.2f)", m.matrix[nRow][m.col - 1]);
            System.out.println();
        }
    }

    public static Matrix multiplyAfterLeading1byNeg1(Matrix m) {
        for (int i = 0; i < m.row; i++) {
            for (int j = 0; j < m.col - 1; j++) {
                if (m.matrix[i][j] == 1) {
                    for (int k = j + 1; k < m.col - 1; k++) {
                        m.matrix[i][k] *= -1;
                    }
                    break;
                }
            }
        }
        return m;
    }

    public String  getSPLGauss() {
        Matrix m = new Matrix();
        m = copyMatrix(this);
        m = gauss(m);
        if (isSPLInvalidValue(m)) {
            String s = printSPLSol(m);
            return s;
        } else if (isSPLUnique(m)) {

            for (int i = m.row - 1; i >= 0; i--) {
                if (isLeading1Present(m, i)) {
                    int colLeading1 = findLeading1(m, i);
                    double valueofLeading1 = m.matrix[i][m.col - 1];
                    for (int j = i - 1; j >= 0; j--) {
                        if (m.matrix[j][colLeading1] != 0) {
                            m.matrix[j][m.col - 1] -= valueofLeading1 * m.matrix[j][colLeading1];
                            m.matrix[j][colLeading1] = 0;
                        }
                    }
                }
            }
           String s =  printSPLSol(m);
            return s;
        } else { // SPL inf sol

            for (int i = m.row - 1; i >= 0; i--) {
                if (!isRow0(m, i)) {
                    int leading1 = findLeading1(m, i);
                    m.reverseOBE(i, leading1);
                }
            }
            String s = printSPLSol(m);
            return s;

        }
    }

    public Matrix removeCol(int col) {
        Matrix result = new Matrix();
        result.setMatrix(this.row, this.col - 1);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < col; j++) {
                result.matrix[i][j] = this.matrix[i][j];
            }
            for (int j = col + 1; j < this.col; j++) {
                result.matrix[i][j - 1] = this.matrix[i][j];
            }
        }
        return result;
    }

    public boolean isSPLInverseable() {
        if (this.row != this.col - 1) {
            return false;
        } else if (this.getEquationMat().getDeterminantCofactor() == 0) {
            return false;
        } else {
            return true;
        }

    }

    public Matrix getInverseADJ() {
        Matrix result = new Matrix();

        result = this.getAdjoin();

        // print determinantcofac
        double detpowermin1 = 1 / this.getDeterminantCofactor();
        result = result.perkalianWithSkalar(detpowermin1);
        return result;
    }

    public Matrix perkalianWithSkalar(double num) {
        Matrix result = new Matrix();
        result = copyMatrix(this);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                // multiply each element of result with num
                result.matrix[i][j] *= num;
            }
        }
        return result;
    }

    public String printInverseSPLSol() {
        String result = "";
        if (!this.isSPLInverseable()) {
            System.out.println("Tidak dapat dicari invers dari matriks, gunakan metode gauss atau gauss jordan");
            result = "Tidak dapat dicari invers dari matriks, gunakan metode gauss atau gauss jordan";
        } else {
            Matrix inversed = new Matrix();
            inversed.setMatrix(this.row, this.col);
            inversed = copyMatrix(this);
            inversed = inversed.removeCol(inversed.col - 1);
            inversed = inversed.getInverseADJ();
            Matrix matrixB = new Matrix();
            matrixB.setMatrix(this.row, 1);
            matrixB = this.formMatrixfrom1Col(col - 1);
            Matrix sol = new Matrix();
            sol.setMatrix(this.row, 1);
            sol = perkalianMatrix(inversed, matrixB);
            sol.printMatriks();
            for (int i = 0; i < sol.row; i++) {
                System.out.printf("x%d = %.2f\n", i + 1, sol.matrix[i][0]);
                result += "x" + (i+1) + " = " + sol.matrix[i][0] + "\n";
            }
        }
        return result;

    }

    public Matrix formMatrixfrom1Col(int col) {
        Matrix result = new Matrix();
        result.setMatrix(this.row, 1);
        for (int i = 0; i < this.row; i++) {
            result.matrix[i][0] = this.matrix[i][col];
        }
        return result;
    }

    public String getSPLGaussJordan() {
        Matrix m = new Matrix();
        m = copyMatrix(this);
        m = gaussJordan(m);
        if (isSPLInvalidValue(m)) {
            String s = printSPLSol(m);
            return s;
        } else if (isSPLUnique(m)) {
            String s = printSPLSol(m);
            return s;
        } else { // SPL have inf sol
            String s = printSPLSol(m);
          
            return s;
        }
    }

    public String interpolasiToString(){
        DecimalFormat df = new DecimalFormat("0.00");
        Matrix interpMatrix = this;
        String base = "";

        // "(%.2f)x^%d"

        base += "f(x) = ";
        for (int i = 0; i < interpMatrix.col - 2; i++) {
            base += "(";
            base += df.format(interpMatrix.matrix[0][i]);
            base += ")x^";
            base += ""+i;
            if (!(i == interpMatrix.col - 3)){
                base += " + ";
            }

        }
        base += "\n";

        // f(nilai) = tafsir
        base += "f(";
        base += ""+interpMatrix.matrix[0][interpMatrix.col-2];
        base += ") = ";
        base += ""+interpMatrix.matrix[0][interpMatrix.col-1];


        return base;
    }


    public Matrix interpolasiPolinomial() {
        // try {
            // Deklarasi Class
            Scanner sc = new Scanner(System.in);
            DecimalFormat df = new DecimalFormat("0.00");
            int banyakTitik;
            String base = "";

            banyakTitik = this.row - 1;
            System.out.println("Banyaknya titik adalah : " + banyakTitik);
    
            Matrix tabelMatrix = new Matrix();
            // Matrix tabelMatrix = this;
            tabelMatrix.setMatrix(banyakTitik, banyakTitik + 1);
            for (int i = 0; i < banyakTitik; i++) {
                for (int j = 0; j < tabelMatrix.col; j++) {
                    if (j != tabelMatrix.col - 1) {
                        tabelMatrix.matrix[i][j] = Math.pow(this.matrix[i][0], j);
                    } else {
                        tabelMatrix.matrix[i][j] = this.matrix[i][1];
                    }
                }
            }


            Matrix resultMatrix = new Matrix();
            Matrix outputMatrix = new Matrix();
            resultMatrix = copyMatrix(tabelMatrix);
            resultMatrix = gaussJordan(tabelMatrix);
            outputMatrix.setMatrix(1, resultMatrix.row + 2);
    
            // Cetak Persamaan di Consolenya
            System.out.printf("f(x) = ");
            base += "f(x) = ";
            // penulis.write("f(x) = ");
            for (int i = 0; i < resultMatrix.row; i++) {
                for (int j = 0; j < resultMatrix.col - 1;j++) {
                    if (resultMatrix.matrix[i][j] != 0) {
                        System.out.printf("(%.2f)x^%d",resultMatrix.matrix[i][resultMatrix.col-1],j);
                        outputMatrix.matrix[0][j] = resultMatrix.matrix[i][resultMatrix.col-1];

                        String konstanta = df.format(resultMatrix.matrix[i][resultMatrix.col-1]);
                        String variabel = Integer.toString(j);
                        base += "(";
                        base += konstanta;
                        base += ")x^";
                        base += variabel;
                        // penulis.write("(");
                        // penulis.write(konstanta);
                        // penulis.write(")");
                        // penulis.write("x^");
                        // penulis.write(variabel);
                        if (i != resultMatrix.row -1) {
                            System.out.printf(" + ");
                            // penulis.write(" + ");
                            base += " + ";
                        }
                    }
                }
            }
            System.out.println();
            base += "\n";
            double inputX = this.matrix[this.row-1][0];
            System.out.println("Nilai yang ingin ditafsir adalah: " + inputX);

            outputMatrix.matrix[0][resultMatrix.row] = inputX;
            double result;
            result = 0;
            for (int i = 0; i < banyakTitik; i++) {
                result += resultMatrix.matrix[i][banyakTitik] * Math.pow(inputX, i);
            }
            System.out.printf("Result: %f\n", result);
            outputMatrix.matrix[0][resultMatrix.row + 1] = result;

            String inputXStr = df.format(inputX);
            String resultStr = df.format(result);
            base += "f(";
            base += inputXStr;
            base += ") = ";
            base += resultStr;
            // penulis.write("f(");
            // penulis.write(inputXStr);
            // penulis.write(") = ");
            // penulis.write(resultStr);

            // sc.nextLine();
            // penulis.close();
            return outputMatrix;
            // } catch(IOException e) {
            //     System.out.println("Input ada yang tidak jelas.");
            //     e.printStackTrace();
            // }
    };

    public void inverseSPL() {
        Matrix a, b;
        a = new Matrix();
        b = new Matrix();
        a.bacaMatriks(false);
        b.setMatrix(a.row, 1);
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < a.row; i++) {
            b.matrix[i][0] = sc.nextDouble();
        }

        Matrix inverseA = a.inverseMatrixIdentity();
        System.out.println("Inverse A:");
        inverseA.printMatriks();
        System.out.println("Matriks b:");
        b.printMatriks();

        Matrix result = perkalianMatrix(inverseA, b);
        System.out.println("Result Matriks:");
        result.printMatriks();
    }

    public void formReglin(Matrix parxy) {
        Matrix xy = new Matrix();
        int c, r;
        double sum;
        xy.setMatrix(parxy.row, parxy.col);
        xy = copyMatrix(parxy);
        // k jadi c
        // n jadi r
        c = xy.col;
        r = xy.row;
        setMatrix(c, c + 1);
        setMatrixValue(0, 0, r);
        for (int i = 1; i < c; i++) {
            sum = 0;
            for (int k = 0; k < r; k++) {
                sum += xy.matrix[k][i - 1];
            }
            setMatrixValue(i, 0, sum);
            setMatrixValue(0, i, sum);
        }
        for (int i = 1; i < c; i++) {
            for (int j = 1; j < c; j++) {
                sum = 0;
                for (int k = 0; k < r; k++) {
                    for (int l = 0; l < r; l++) {
                        sum += (xy.matrix[k][i - 1] * xy.matrix[l][j - 1]);
                    }
                }
                this.matrix[i][j] = sum;
            }
        }
        for (int i = 0; i < c; i++) {
            sum = 0;
            for (int j = 0; j < r; j++) {
                if (i == 0) {
                    sum += xy.matrix[j][c - 1];
                } else {
                    for (int k = 0; k < r; k++) {
                        sum += (xy.matrix[j][c - 1] * xy.matrix[k][i - 1]);
                    }
                }
            }
            this.matrix[i][c] = sum;
        }
    }

    public static Matrix chooseNGetMatrix(boolean bic) {
        String inp, newline;
        newline = System.lineSeparator();
        Scanner sc = new Scanner(System.in);
        Matrix result = new Matrix();
        boolean inpValid = false;
        while(!inpValid){
            System.out.println("Pilih cara input matriks" + newline + "1.File" + newline + "2.Keyboard");
            inp = sc.nextLine();
            switch (inp) {
                case "1":
                    result.bacaFileMatrix("", bic);
                    inpValid=true;
                    break;
                case "2":
                    System.out.println("========= README! =========\nApabila menggunakan operasi interpolasi polinom, pastikan col = 2 dan row sebanyak n + 1 titik.\nBaris terakhir merupakan nilai yang ingin diaproksimasi diikuti dengan nol\n===========================");
                    result.bacaMatriks(bic);
                    inpValid=true;
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    public void chooseWriteMatrix() {
        Scanner sc = new Scanner(System.in);
        int input;
        System.out.println("Apakah interpolasi ingin di cetak?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        input = sc.nextInt();
        if (input == 1) {
            this.matrixToFile();
        }
    }

    public String determinantToString(double determinan) {
        DecimalFormat df = new DecimalFormat("0.00");
        String determinantStr = df.format(determinan);
        return determinantStr;
    }

    public void chooseWriteDeterminant(double determinan){
        Scanner sc = new Scanner (System.in);
        int input;
        System.out.println("Apakah determinan ingin di cetak?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        input = sc.nextInt();
        if (input == 1) {
            writeStringToFile(determinantToString(determinan));
        }
    }

    public void chooseWriteInterpolasi(){
        Scanner sc = new Scanner (System.in);
        int input;
        System.out.println("Apakah interpolasi ingin di cetak?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        input = sc.nextInt();
        if (input == 1) {
            // interpolasiPolinomial(true);
            String base = this.interpolasiToString();
            writeStringToFile(base);   
        }
    }

    public static boolean isToFile(){
        Scanner sc = new Scanner(System.in);
        String sInp;
        while (true){
            System.out.println("Pilih media output\n1.File    2.Layar");
            sInp = sc.nextLine();
            switch (sInp){
                case "1":
                    return true;
                case "2":
                    return false;
                default :
                    System.out.println("Masukkan tidak valid");
            }
        }
    }

    public static String regMeasure(Matrix a,Matrix b){
        double sum = 0;
        sum += b.matrix[0][0];
        for(int i =1;i<a.col;i++){
            sum += (a.matrix[0][i]+b.matrix[i-1][i-1]);
        }
        return String.format("%.3f",sum);
    }

    public static String regToString(Matrix m){
        String str = "y = ";
        boolean pert = true;
        for(int i=0;i<m.row;i++){
            if(m.matrix[i][m.col-1]!=0&&i!=0){
                if(m.matrix[i][m.col-1]>0&&!pert) str += "+ ";
                if(m.matrix[i][m.col-1]<0) str += "- ";
                str+=(String.format("%.3f",Math.abs(m.matrix[i][m.col-1])));
                if(i!=0) str+=("x"+(i));
                str+=" ";
                pert=true;
            }
        }
        return str;
    }
    public static Matrix wantMeasure(Matrix m){
        Scanner sc = new Scanner(System.in);
        Matrix mMea = new Matrix();
        String sInp;
        while (true){
            System.out.println("Apakah ada titik yang ingin diperkirakan dari regresi ini?\n1.Ya    2.Tidak");
            sInp = sc.nextLine();
            switch (sInp){
                case "1":
                    System.out.println("Masukkan variable dalam bentuk matriks 1 x n"+
                    "\ndimana n adalah jumlah variable dari masukkan pertama");
                    while(mMea.row!=1||mMea.col!=(m.col-1)){
                        mMea=Matrix.chooseNGetMatrix(false);
                        if(mMea.row!=1||mMea.col!=(m.col-1)){
                            System.out.println("Ukuran Matriks tidak sesuai\nMencoba mengambil kembali matriks");
                        }
                        else{
                            return mMea;
                        }
                    }
                    break;
                case "2":
                    System.out.println("ini");
                    return mMea;
                default :
                    System.out.println("Masukkan tidak valid");
                    break;
            }
        }
    }

}
