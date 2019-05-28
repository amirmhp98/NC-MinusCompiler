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

    public void writeScanTokens(List<ScanToken> scanTokens, String fileName) {
//        for (int i = 0; i < scanTokens.size(); i++) {
//            System.out.println(scanTokens.get(i).getLineNumber()+"-"+scanTokens.get(i));
//        }
//        int currentLineNumber = 0;
//        for (int i = 0; i < scanTokens.size(); i++) {
//            if (currentLineNumber != scanTokens.get(i).getLineNumber()) {
//                currentLineNumber = scanTokens.get(i).getLineNumber();
//                writeIntoFile("\n" + currentLineNumber + ". ", fileName);
//            }
//            writeIntoFile(" " + scanTokens.get(i), fileName);
//        }
    }

    public void writeScanErrors(List<ScanError> scanErrors, String fileName) {
//        for (int i = 0; i < scanErrors.size(); i++) {
//            System.out.println(scanErrors.get(i).getLineNumber()+"-"+scanErrors.get(i).getValue());
//        }

    }
}
