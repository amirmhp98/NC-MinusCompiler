import domain.ParseResult;
import domain.ScanFinalResult;
import io.FileIOHandler;
import parser.ParserService;
import parser.ScannerService;

import java.util.ArrayList;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class application {
	private static ScannerService scannerService = new ScannerService();
	private static FileIOHandler io = new FileIOHandler();
	private static ParserService parserService = new ParserService();

	public static void main(String[] args) {
		String readingFileName = "input.txt";
		ArrayList<String> text = io.readFromFile(readingFileName);
		ScanFinalResult scanResult = scannerService.scan(text);
		ParseResult parseResult = parserService.parse(scanResult.getScanData());
		io.writeIntoFile(parseResult.getTreeResult(), "parseTree.txt");
		io.writeIntoFile(scanResult.getErrors(), "lexical_errors.txt");
	}

}
