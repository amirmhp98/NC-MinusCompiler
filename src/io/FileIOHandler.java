package io;

import domain.ScanError;
import domain.ScanToken;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class FileIOHandler {
    public void writeScanTokens(ArrayList<ScanToken> scanTokens, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            int currentLineNumber = 0;
            for (ScanToken scanToken : scanTokens) {
                if (scanToken.getLineNumber() != currentLineNumber) {
                    if (currentLineNumber != 0) {
                        bufferedWriter.write("\n");
                    }
                    currentLineNumber = scanToken.getLineNumber();
                    bufferedWriter.write(currentLineNumber + ". ");
                }
                bufferedWriter.write(scanToken + " ");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeScanErrors(List<ScanError> scanErrors, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            int currentLineNumber = 0;
            for (ScanError scanError : scanErrors) {
                if (scanError.getLineNumber() != currentLineNumber) {
                    if (currentLineNumber != 0) {
                        bufferedWriter.write("\n");
                    }
                    currentLineNumber = scanError.getLineNumber();
                    bufferedWriter.write(currentLineNumber + ". ");
                }
                bufferedWriter.write(scanError + " ");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
