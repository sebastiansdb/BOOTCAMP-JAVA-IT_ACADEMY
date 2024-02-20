package org.example.Classes;

import java.util.Scanner;

public class GetUserData {
    static Scanner sc = new Scanner (System.in);
    public static String getString(String message){
        System.out.println(message);
        return sc.nextLine();
    }
    public static int getInteger(String message){
        System.out.println(message);
        int ret = sc.nextInt();
        sc.nextLine();
        return ret;
    }
}
