import io.FileIOHandler;
import parser.FileService;
import parser.ParserService;
import parser.ScannerService;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class application {
	private static FileIOHandler fileIOHandler = new FileIOHandler();
	public static void main(String[] args) {
		String fileName = "input.txt";
		FileService fileService = new FileService(fileName);
		ScannerService scannerService = new ScannerService(fileService);
		ParserService parserService = new ParserService(scannerService);
		parserService.run();
		// todo change file name if you want
		// todo print parse tree
		fileIOHandler.writeScanTokens(scannerService.getScanResult().getScanTokens(), "lexical_scans.txt");
		fileIOHandler.writeScanErrors(scannerService.getScanResult().getScanErrors(), "lexical_errors.txt");
	}

}
