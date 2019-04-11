package io;

import java.io.*;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class FileIOHandler {
    public String readFromFile(String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            return stringBuilder.toString();
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
