package src;

import java.util.Scanner;

public class SistemPersamaanLinear {

    public static void splGauss(){
        Matrix fileMatrix = Main.masukMatrix();
        System.out.println("====== HASIL ======");
        MainOperation.getSPLGauss(fileMatrix);
    }

    public static void splGaussJordan(){
        Matrix fileMatrix = Main.masukMatrix();
        System.out.println("====== HASIL ======");
        MainOperation.getSPLGaussJordan(fileMatrix);
    }

    public static void splInverse(){
        Matrix fileMatrix = Main.masukMatrix();
        System.out.println("====== HASIL ======");
        MainOperation.inverseSPL(fileMatrix);
    }

    public static void splCramer(){
        Matrix fileMatrix = Main.masukMatrix();
        System.out.println("====== HASIL ======");
        MainOperation.getSPLCramer(fileMatrix);
    }
}
