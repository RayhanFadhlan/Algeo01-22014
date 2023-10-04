package src;
import java.io.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Math;
import javax.imageio.ImageIO;

public class Bonus {
    public static void main(String[] args) {
        // Provide the path to your image file
        String imagePath = "test\\image\\monal.jpg";

        try {
            BufferedImage image = ImageIO.read(new File(imagePath));

            // Example: Convert the image to a matrix
            int[][][] imageMatrix = imageToMatrix(image);

            // Example: Print the RGB values of the top-left pixel
            System.out.println("Top-left pixel RGB values:");
            System.out.println("Red: " + imageMatrix[0][0][0]);
            System.out.println("Green: " + imageMatrix[0][0][1]);
            System.out.println("Blue: " + imageMatrix[0][0][2]);

            // print upscale matrix
            int[][][] upscaleMatrix = upscale(imageMatrix, 2);
            matrixToTxt(upscaleMatrix);
            // convert rgb to grayscale
            // get width and height of imagematrix

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

                // Extract RGB values
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                // Store RGB values in the matrix
                imageMatrix[x][y][0] = red;
                imageMatrix[x][y][1] = green;
                imageMatrix[x][y][2] = blue;
            }
        }

        return imageMatrix;
    }

    // function to print the matrix
    public static void printMatrix(int[][][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j][0] + " ");
                System.out.print(matrix[i][j][1] + " ");
                System.out.print(matrix[i][j][2] + " ");
                System.out.println();
            }
        }
    }

    // function to convert matrix to image
    public static void matrixToImage(int[][][] matrix) {
        int width = matrix.length;
        int height = matrix[0].length;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Set RGB value for each pixel in the image
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                // Set RGB value
                int red = matrix[x][y][0];
                int green = matrix[x][y][1];
                int blue = matrix[x][y][2];
                int rgb = (red << 16) | (green << 8) | blue;
                image.setRGB(x, y, rgb);
            }
        }

        // Save the image
        try {
            ImageIO.write(image, "jpg", new File("test\\image\\output.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // function to convert rgb matrix to grayscale that return the grayscale matrix
    // with 3d sized array
    public static int[][] rgbToGrayscale(int[][][] matrix) {
        int width = matrix.length;
        int height = matrix[0].length;
        int[][] gray = new int[width][height];
        int[][][] gray3d = new int[width][height][3];
        int red, green, blue;
        int grayVal;
        // Set RGB value for each pixel in the image
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
        matrixToImage(gray3d);
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
        // return max index or min index if index out of bounds

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

    public void printMat(int[][][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j][0] + " ");
                System.out.print(mat[i][j][1] + " ");
                System.out.print(mat[i][j][2] + " ");
                System.out.println();
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
        // copy image so it have int [][][] but the last value is the same

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

        // create new matrix sized xNew x yNew
        int[][][] newMatrix = new int[xNew][yNew][3];

        for (int i = 0 + 1; i < xNew - 10; i++) {
            for (int j = 0 + 1; j < yNew - 10; j++) {
                for (int k = 0; k < 3; k++) {

                    double W = -(((i / xScale) - Math.floor(i / xScale)) - 1);
                    double H = -(((j / yScale) - Math.floor(j / yScale)) - 1);

                    double ix = i / xScale;
                    double iy = j / yScale;
                    int[] I11_index = { 1 + (int) Math.floor(ix), 1 + (int) Math.floor(iy) };
                    int[] I21_index = { 1 + (int) Math.floor(ix), 1 + (int) Math.ceil(iy) };
                    int[] I12_index = { 1 + (int) Math.ceil(ix), 1 + (int) Math.floor(iy) };
                    int[] I22_index = { 1 + (int) Math.ceil(ix), 1 + (int) Math.ceil(iy) };

                    // I21_index = [1+Math.floor(count1./x_scale),1+ceil(count2./y_scale)];
                    // I12_index = [1+ceil(count1./x_scale),1+Math.floor(count2./y_scale)];
                    // I22_index = [1+ceil(count1./x_scale),1+ceil(count2./y_scale)];

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
                    // transpose beta

                    // multiply minv with beta
                    double[][] alpha = multiplyMatrix(MInv, beta);

                    // //make code to print alpha
                    // for(int p = 0;p<16;p++){
                    // System.out.println(alpha[p][0]);}

                    double temp_p = 0;
                    for (int p = 1; p <= 16; p++) {
                        int w_temp = Math.floorDiv((p - 1), 4);
                        int h_temp = (p - 1) % 4;
                        temp_p = temp_p + alpha[p - 1][0] * Math.pow(1 - W, w_temp) * Math.pow(1 - H, h_temp);
                    }
                    int res = (int) Math.round(temp_p);
                    newMatrix[i + 1][j + 1][k] = res;
                }
            }
        }
        return newMatrix;

    }

    // function to ouput matrix to txt
    public static void matrixToTxt(int[][][] matrix) {
        int width = matrix.length;
        int height = matrix[0].length;
        String filename = "test\\image\\output.txt";
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
}
