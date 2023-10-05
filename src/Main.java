package src;
import src.Function;
import java.util.Scanner;
import src.Matrix;
import src.Image_upscale;
public class Main {
    public static void main  (String[] args) {
        Scanner sc = new Scanner(System.in);
        String inpmain;

        System.out.println(Menu.MAIN);
        inpmain = sc.nextLine();
        while (true) {
            switch (inpmain) {
                case "1":
                    Function.spl();
                    break;
                case "2":
                    Function.determinant();
                    break;
                case "3":
                    Function.matBal();
                    break;
                case "4":
                    Function.intPol();
                    break;
                case "5":
                    Function.intBic();
                    break;
                case "6":
                    Function.regLin();
                    break;
                case "7":
                    Image_upscale.imageUpscale();
                    break;
                case "8":
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Masukkan tidak valid" + Menu.newline + "Kembali ke main menu" + Menu.newline);
            }
            System.out.println(Menu.MAIN);
            inpmain = sc.nextLine();
        }
    }
   
}