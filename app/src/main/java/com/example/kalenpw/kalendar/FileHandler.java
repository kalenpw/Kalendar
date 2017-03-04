package com.example.kalenpw.kalendar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.File;

import android.os.Environment;


/**
 * Created by kalenpw on 3/2/17.
 */

public class FileHandler {
    private String _file;
    private String _path;
    private String _completePath;

    //Constructor
    public FileHandler(String completePath){
        _completePath = completePath;
    }
    public FileHandler(File file){
        _completePath = file.getAbsolutePath();
    }

    //Methods
    //Saves text to file
    public void saveFile(String contentToSave){
        String previousContent = getFileLines();
        contentToSave = previousContent + contentToSave;
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/kalendar");
        directory.mkdirs();
        File file = new File(directory, "Schedule.txt");

        try{
            FileOutputStream fileOut = new FileOutputStream(file);
            fileOut.write(contentToSave.getBytes());
            fileOut.close();

            Debugger.setDebugMode(true);
            Debugger.print(file.getAbsolutePath());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

//    public String readFileBeforeSaving(){
//        File sdCard;
//        java.nio.file.Files
//    }


    public String getFileLines(){
        String fileText = "Being: ";
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard + "/kalendar");
        File file = new File(directory, "Schedule.txt");

        try{
            BufferedReader br = new BufferedReader(new FileReader(file));

            while((fileText = br.readLine()) != null){
                fileText += fileText + "\n";

            }
            br.close();

        }
        catch(IOException e){
            e.printStackTrace();
        }
        return fileText;

    }





}
