import domain.ScanFinalResult;
import io.FileIOHandler;
import parser.ScannerService;

import java.util.ArrayList;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class application {
    private static ScannerService scannerService = new ScannerService();
    private static FileIOHandler io = new FileIOHandler();
 
    public static void main(String[] args) {
        String readingFileName = "input.txt";
        ArrayList<String> text = io.readFromFile(readingFileName);
        ScanFinalResult result = scannerService.scan(text);
        io.writeIntoFile(result.getTokens(), "scanner.txt");
        io.writeIntoFile(result.getErrors(), "lexical_errors.txt");
    }





}
