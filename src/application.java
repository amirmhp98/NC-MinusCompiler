import io.FileIOHandler;
import parser.FileService;
import parser.GrammarService;
import parser.ParserService;
import parser.ScannerService;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class application {
    private static FileIOHandler fileIOHandler = new FileIOHandler();

    public static void main(String[] args) {
        GrammarService grammarService = new GrammarService("Grammar.txt");
        String fileName = "Parser - test1.txt";
        FileService fileService = new FileService(fileName);
        ScannerService scannerService = new ScannerService(fileService);
        ParserService parserService = new ParserService(scannerService);
        parserService.tempRun();

        fileIOHandler.writeScanTokens(scannerService.getScanResult().getScanTokens(), "lexical_scans.txt");
        fileIOHandler.writeScanErrors(scannerService.getScanResult().getScanErrors(), "lexical_errors.txt");
//        System.out.println("0000000000 - PARSE RESULTS - 0000000000\n" + parseResult.getTreeResult());
//        System.out.println("0000000000 - PARSE ERRORS - 0000000000\n" + parseResult.getErrorResult());
//        fileIOHandler.writeIntoFile(parseResult.getTreeResult(), "parse_result.txt");
//        fileIOHandler.writeIntoFile(parseResult.getErrorResult(), "parse_errors.txt");
        // todo change file name if you want
        // todo print parse tree
    }

}
