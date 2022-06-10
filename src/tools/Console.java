/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.util.Scanner;

/**
 *
 * @author MSI
 */
public class Console {
    public static Scanner sc = new Scanner(System.in);

    public static double inputNumber(String msg) {
        System.out.print(msg);
        return Double.parseDouble(sc.nextLine());
    }

    public static double inputNumberInRange(String msg, double min, double max) {
        if(min>max) {
            double t=min; min=max; max=t;
        }

        double data;

        do {
            System.out.print(msg);
            data = Double.parseDouble(sc.nextLine());

        }  while (data<min || data>max);

        return data;
    }

    public static String inputStr(String msg) {
        System.out.print(msg);
        String data = sc.nextLine().trim();
        return data;
    }

    public static String inputNonBlankStr(String msg) {
        String data;
        do {
            System.out.print(msg);
            data = sc.nextLine().trim();
        } while(data.length()==0);

        return data;
    }

    public static String inputPattern(String msg, String pattern) {
        String data;
        do {
            System.out.print(msg);
            data = sc.nextLine().trim();
        } while (!data.matches(pattern));

        return data;
    }
}
