package io;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class FileIOHandler {
    public ArrayList<String> readFromFile(String fileName){
        ArrayList<String> strings = new ArrayList<>();
        try {
            String line;
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                strings.add(line);
            }
            bufferedReader.close();
            fileReader.close();
            return strings;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean writeIntoFile(String textToWrite, String fileName){
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(textToWrite);
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
