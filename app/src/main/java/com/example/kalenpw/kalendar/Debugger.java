package com.example.kalenpw.kalendar;

/**
 * Created by kalenpw on 3/2/17.
 */

public final class Debugger {
    public static boolean debugMode;

    public static void setDebugMode(boolean value){
        debugMode = value;
    }

    public static void print(String str){
        if(debugMode){
            System.out.println(str);
        }
    }
}
