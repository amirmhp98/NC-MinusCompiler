package parser;

import domain.FileToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileService {
    private Scanner scanner;
    private int currentLineNumber = 0;

    public FileService(String fileName) {
        try {
            this.scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    FileToken getNextToken() {
        if (scanner.hasNext()) {
            return new FileToken(this.scanner.nextLine(), ++this.currentLineNumber);
        } else {
            this.scanner.close();
            return null;
        }
    }

    String getNextLine() {
        if (scanner.hasNext()) {
            return this.scanner.nextLine();
        } else {
            this.scanner.close();
            return null;
        }
    }

    int getCurrentLineNumber() {
        return currentLineNumber;
    }
}