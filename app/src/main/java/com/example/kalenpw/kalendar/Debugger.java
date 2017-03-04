package com.example.kalenpw.kalendar;


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
