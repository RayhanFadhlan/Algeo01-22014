package src;
import java.io.*;
import java.awt.image.BufferedImage;
import java.lang.Math;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class Image_upscale {

    public static void imageUpscale() {
       
        String inputpath = inputPathImg();
        
        String imagename = inputImageName(inputpath);
        String imagePath = inputpath + "\\" + imagename;
      
        System.out.println("Image Path: " + imagePath);        
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            int[][][] imageMatrix = imageToMatrix(image);
            System.out.println("Masukkan skala perbesaran: (contoh: 3))");
            Scanner sc = new Scanner(System.in);
            float scale = sc.nextFloat();
            
            System.out.println("Tunggu bentar yak, lagi ngeproses gambar");

            int[][][] upscaleMatrix = upscale(imageMatrix, scale);
            
            String outputpath = outputPathImg();
            String outputname = outputImageName();
            String outputImagePath = outputpath + "\\" + outputname;
            // print output image path
            System.out.println("Output Image Path: " + outputImagePath);
            matrixToImage(upscaleMatrix,outputImagePath);
        
    

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[][][] imageToMatrix(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][][] imageMatrix = new int[width][height][3]; // Assuming RGB image

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = image.getRGB(x, y);

              
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

               
                imageMatrix[x][y][0] = red;
                imageMatrix[x][y][1] = green;
                imageMatrix[x][y][2] = blue;
            }
        }

        return imageMatrix;
    }

  
    

 
    public static void matrixToImage(int[][][] matrix,String path) {
        int width = matrix.length;
        int height = matrix[0].length;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

     
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                int red = matrix[x][y][0];
                int green = matrix[x][y][1];
                int blue = matrix[x][y][2];
                int rgb = (red << 16) | (green << 8) | blue;
                image.setRGB(x, y, rgb);
            }
        }

        try {
            String pathoutput = path;
            ImageIO.write(image, "jpg", new File(pathoutput));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 
    public static int[][] rgbToGrayscale(int[][][] matrix) {
        int width = matrix.length;
        int height = matrix[0].length;
        int[][] gray = new int[width][height];
        int[][][] gray3d = new int[width][height][3];
        int red, green, blue;
        int grayVal;
       
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                red = matrix[x][y][0];
                green = matrix[x][y][1];
                blue = matrix[x][y][2];
                grayVal = (red + green + blue) / 3;
                gray[x][y] = grayVal;
                gray3d[x][y][0] = grayVal;
                gray3d[x][y][1] = grayVal;
                gray3d[x][y][2] = grayVal;
            }
        }
        return gray;
    }

    public int getWidth(int[][][] matrix) {
        return matrix.length;
    }

    public int getHeight(int[][][] matrix) {
        return matrix[0].length;
    }

    public static double fx(int x, int y, int[][][] matrix, int rgb) {
        if (y + 1 >= matrix[0].length) {
            return 0;
        } else if (y - 1 < 0) {
            return 0;
        } else
            return ((matrix[x][y + 1][rgb] - matrix[x][y - 1][rgb]) / 2.0);
        
    }

    public static double fy(int x, int y, int[][][] matrix, int rgb) {

        if (x + 1 >= matrix.length) {
            return 0;
        } else if (x - 1 < 0) {
            return 0;
        } else
            return ((matrix[x + 1][y][rgb] - matrix[x - 1][y][rgb]) / 2.0);

    }

    public static double fxy(int x, int y, int[][][] matrix, int rgb) {
        if (x + 1 >= matrix.length || y + 1 >= matrix[0].length) {
            return 0;
        } else if (x - 1 < 0 || y - 1 < 0) {
            return 0;
        } else

            return (((matrix[x - 1][y - 1][rgb] + matrix[x + 1][y + 1][rgb])
                    - (matrix[x - 1][y + 1][rgb] + matrix[x + 1][y - 1][rgb])) / 4.0);

    }

    public static double[][] multiplyMatrix(double[][] A, double[][] B) {
        double[][] C = new double[A.length][B[0].length];
        int temp = 0;
        for (int i = 0; i < A.length; i++) { // baris matriks A
            for (int j = 0; j < B[0].length; j++) { // kolom matriks B
                for (int k = 0; k < A[0].length; k++) { // baris matriks B
                    temp += A[i][k] * B[k][j];
                }
                C[i][j] = temp;
                temp = 0;
            }
        }
        return C;
    }

    public static int[][] transposeMatrix(int[][] matrix) {
        int[][] transpose = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < transpose.length; i++) {
            for (int j = 0; j < transpose[0].length; j++) {
                transpose[i][j] = matrix[j][i];
            }
        }
        return transpose;
    }

    

    public static int getIndex(double x,int upperbound,boolean isfloor){
        if(isfloor){
            if(x<0){
                return 0;
            }
            else if(x>upperbound){
                return upperbound;
            }
            else{
                return (int) Math.floor(x);
            }
        }
        else{
            if(x<0){
                return 0;
            }
            else if(x>upperbound){
                return upperbound;
            }
            else{
                return (int) Math.ceil(x);
            }
        }

    }
    public static int[][][] upscale(int[][][] inputImage, float ratio) {
        int width = inputImage.length;
        int height = inputImage[0].length;
        // copy input image
        int[][][] image = new int[width][height][3];
        // copy inputimage to image
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < 3; k++) {
                    image[i][j][k] = inputImage[i][j][k];
                }
            }
        }
        

        double[][] MInv = {
                { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { -3, 3, 0, 0, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 2, -2, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, -3, 3, 0, 0, -2, -1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 2, -2, 0, 0, 1, 1, 0, 0 },
                { -3, 0, 3, 0, 0, 0, 0, 0, -2, 0, -1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, -3, 0, 3, 0, 0, 0, 0, 0, -2, 0, -1, 0 },
                { 9, -9, -9, 9, 6, 3, -6, -3, 6, -6, 3, -3, 4, 2, 2, 1 },
                { -6, 6, 6, -6, -3, -3, 3, 3, -4, 4, -2, 2, -2, -2, -1, -1 },
                { 2, 0, -2, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 2, 0, -2, 0, 0, 0, 0, 0, 1, 0, 1, 0 },
                { -6, 6, 6, -6, -4, -2, 4, 2, -3, 3, -3, 3, -2, -1, -2, -1 },
                { 4, -4, -4, 4, 2, 2, -2, -2, 2, -2, 2, -2, 1, 1, 1, 1 }
        };
        int xNew, yNew;
        xNew = (int) Math.round(width * ratio);
        yNew = (int) Math.round(height * ratio);

        int xScale = xNew / (width - 1);
        int yScale = yNew / (height - 1);

        
        int[][][] newMatrix = new int[xNew+1][yNew+1][3];

        for (int i = 0 ; i < xNew ; i++) {
            for (int j = 0 ; j < yNew; j++) {
                for (int k = 0; k < 3; k++) {

                    double W = -(((i / xScale) - Math.floor(i / xScale)) - 1);
                    double H = -(((j / yScale) - Math.floor(j / yScale)) - 1);

                    double ix = i / xScale;
                    double iy = j / yScale;
                    int upperboundx = width-1;
                    int upperboundy = height-1;

                    int[] I11_index = { getIndex(ix,upperboundx,true), getIndex(iy, upperboundy, true) };
                    int[] I21_index = { getIndex(ix, upperboundx, true),getIndex(iy, upperboundy, false) };
                    int[] I12_index = { getIndex(ix, upperboundx, false),getIndex(iy, upperboundy, true) };
                    int[] I22_index = { getIndex(ix, upperboundx, false),getIndex(iy, upperboundy, false) };
                 

                    double I11 = inputImage[I11_index[0]][I11_index[1]][k];
                    double I21 = inputImage[I21_index[0]][I21_index[1]][k];
                    double I12 = inputImage[I12_index[0]][I12_index[1]][k];
                    double I22 = inputImage[I22_index[0]][I22_index[1]][k];

                    double Ix11 = fx(I11_index[0], I11_index[1], image, k);
                    double Ix21 = fx(I21_index[0], I21_index[1], image, k);
                    double Ix12 = fx(I12_index[0], I12_index[1], image, k);
                    double Ix22 = fx(I22_index[0], I22_index[1], image, k);

                    double Iy11 = fy(I11_index[0], I11_index[1], image, k);
                    double Iy21 = fy(I21_index[0], I21_index[1], image, k);
                    double Iy12 = fy(I12_index[0], I12_index[1], image, k);
                    double Iy22 = fy(I22_index[0], I22_index[1], image, k);

                    double Ixy11 = fxy(I11_index[0], I11_index[1], image, k);
                    double Ixy21 = fxy(I21_index[0], I21_index[1], image, k);
                    double Ixy12 = fxy(I12_index[0], I12_index[1], image, k);
                    double Ixy22 = fxy(I22_index[0], I22_index[1], image, k);

                    double[][] beta = { { I11 }, { I21 }, { I12 }, { I22 }, { Ix11 }, { Ix21 }, { Ix12 }, { Ix22 },
                            { Iy11 }, { Iy21 }, { Iy12 }, { Iy22 }, { Ixy11 }, { Ixy21 }, { Ixy12 }, { Ixy22 } };
   
                    double[][] alpha = multiplyMatrix(MInv, beta);

 

                    double temp_p = 0;
                    for (int p = 1; p <= 16; p++) {
                        int w_temp = Math.floorDiv((p - 1), 4);
                        int h_temp = (p - 1) % 4;
                        temp_p = temp_p + alpha[p - 1][0] * Math.pow(1 - W, w_temp) * Math.pow(1 - H, h_temp);
                    }
                    int res = (int) Math.round(temp_p);
                    newMatrix[i+1][j+1][k] = res;
                }
            }
            if(i%100==0){
                System.out.println("Progress: " + i + "/" + xNew);
            }
        }
        return newMatrix;

    }

    public static void matrixToTxt(int[][][] matrix) {
        int width = matrix.length;
        int height = matrix[0].length;
        String filename = "test\\image\\output2.txt";
        try {
            FileWriter fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println("width: " + width);
            pw.println("height: " + height);
            pw.println("matrix: ");
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {

                    pw.print(matrix[i][j][0] + " ");
                    pw.print(matrix[i][j][1] + " ");
                    pw.print(matrix[i][j][2] + " ");
                    pw.println();
                }
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean checkImageExist(String path){
        File f = new File(path);
        if(f.exists() && !f.isDirectory()) { 
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean checkFolderExist(String path){
        File f = new File(path);
        if(f.exists() && f.isDirectory()) { 
            return true;
        }
        else{
            return false;
        }
    }
    public static String inputPathImg(){
        Scanner sc = new Scanner(System.in);
        String newLine = System.lineSeparator();
        String path;
        System.out.println("Masukkan alamat (absolute path) folder  gambar yang ingin diubah");
        System.out.println("Contoh: C:\\Users\\Rayhan Fadhlan\\Documents");
        path = sc.nextLine();
        while(!checkFolderExist(path)){
            System.out.println("Folder tidak ditemukan, silahkan masukkan alamat file yang benar");
            path = sc.nextLine();
        }
        return path;
    }

    public static String outputPathImg(){
        Scanner sc = new Scanner(System.in);
        String newLine = System.lineSeparator();
        String path;
        System.out.println("Masukkan alamat (absolute path) folder untuk menyimpan gambar hasil");
        System.out.println("Contoh: C:\\Users\\Rayhan Fadhlan\\Documents");
        path = sc.nextLine();
        return path;
    }
    
    public static String inputImageName(String path){
        Scanner sc = new Scanner(System.in);
        String newLine = System.lineSeparator();
        String imgname;
        System.out.println("Masukkan nama file gambar yang ingin diubah");
        System.out.println("Contoh: kucing.jpg (jangan lupa extension)");
        imgname = sc.nextLine();
        String pathcek = path + "\\" + imgname;
        while(!checkImageExist(pathcek)){
            System.out.println("File tidak ditemukan, silahkan masukkan alamat file yang benar");
            //print pathcek
            System.out.println(pathcek);
            imgname = sc.nextLine();
            pathcek = path + "\\" + imgname;
        }
        return imgname;
    }

    public static String outputImageName(){
        Scanner sc = new Scanner(System.in);
        String newLine = System.lineSeparator();
        String path;
        System.out.println("Masukkan nama file gambar hasil");
        System.out.println("Contoh: kucing.jpg (jangan lupa extension)");
        path = sc.nextLine();
        return path;
    }

    

}
