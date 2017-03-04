//Offloads various file actions to a sepaprate class

package com.example.kalenpw.kalendar;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.os.Environment;

public class FileHandler {
    private String _completePath;

    //Constructor
    public FileHandler(){
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard + "/kalendar");
        directory.mkdirs();
        File file =  new File(directory, "Schedule.ser");
        String path = file.getAbsolutePath();
        _completePath = path;
    }

    /**
     * Deserializes an object
     * @return ArrayList<Day> the previously saved entries
     */
    public ArrayList<Day> deserializeObject(){
        ArrayList<Day> list = new ArrayList<>();

        try {
            ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(_completePath));
            list = (ArrayList<Day>) objectIn.readObject();
            objectIn.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Serializes an object
     * @param objToSerialize - object to be serialized
     */
    public void serializeObject(Object objToSerialize){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(_completePath));
            outputStream.writeObject(objToSerialize);
            outputStream.close();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            outputStream = new ObjectOutputStream(bos);
            outputStream.writeObject(objToSerialize);
            outputStream.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
//
}
