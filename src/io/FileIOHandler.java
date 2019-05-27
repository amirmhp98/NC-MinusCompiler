package io;

import domain.ScanError;
import domain.ScanToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class FileIOHandler {

    private void writeIntoFile(String textToWrite, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(textToWrite);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeScanTokens(List<ScanToken> scanTokens, String fileName){

    }

    public void writeScanErrors(List<ScanError> scanErrors, String fileName){

    }
}
