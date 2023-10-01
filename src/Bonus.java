package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bonus {
     public static void main(String[] args) {
        // Provide the path to your image file
        String imagePath = "test\\image\\orangmesir.png";

        try {
            BufferedImage image = ImageIO.read(new File(imagePath));

            // Example: Convert the image to a matrix
            int[][][] imageMatrix = imageToMatrix(image);

            // Example: Print the RGB values of the top-left pixel
            System.out.println("Top-left pixel RGB values:");
            System.out.println("Red: " + imageMatrix[0][0][0]);
            System.out.println("Green: " + imageMatrix[0][0][1]);
            System.out.println("Blue: " + imageMatrix[0][0][2]);

            printMatrix(imageMatrix);

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
    //function to print the matrix
    public static void printMatrix(int[][][] matrix){
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                System.out.print(matrix[i][j][0]+" ");
                System.out.print(matrix[i][j][1]+" ");
                System.out.print(matrix[i][j][2]+" ");
                System.out.println();
            }
        }
    }

    // function to convert matrix to image
    public static void matrixToImage(int[][][] matrix){
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
            ImageIO.write(image, "png", new File("test\\image\\orangmesirhasil.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

