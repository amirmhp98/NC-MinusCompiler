package parser;

import domain.ScanFinalResult;
import domain.ParseResult;
import domain.ScanData;
import domain.ScanResult;

import java.util.ArrayList;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class ScannerService {

    private ScannerUtils scannerUtils;
    private ParserUtils parserUtils;

    public ScannerService() {
        this.scannerUtils = new ScannerUtils();
        this.parserUtils = new ParserUtils();
    }

    public ScanFinalResult scan(ArrayList<String> text) {
        StringBuilder tokensBuilder = new StringBuilder();
        StringBuilder errorBuilder = new StringBuilder();
        text = scannerUtils.removeComment(text);
        text = scannerUtils.replaceSymbolsAndWSs(text);
        ScanData scanData = new ScanData();
        for (int i = 0; i < text.size(); i++) {
            //line number is: i + 1
            ScanResult scanResult = scannerUtils.scanLineWords(scannerUtils.splitToWords(text.get(i)));
            scanData.addTokenByLineNumber(scanResult.getScanTokens(), i + 1);
            if (!scanResult.getScanTokens().isEmpty()) {
                tokensBuilder.append(i + 1).append(". ");
                for (int j = 0; j < scanResult.getScanTokens().size(); j++) {
                    tokensBuilder.append("(").append(scanResult.getScanTokens().get(j).getType().toString()).append(", ").append(scanResult.getScanTokens().get(j).getValue()).append(") ");
                }
                tokensBuilder.append("\n");
            }
            if (!scanResult.getScanErrors().isEmpty()) {
                errorBuilder.append(i + 1).append(". ");
                for (int j = 0; j < scanResult.getScanErrors().size(); j++) {
                    errorBuilder.append("(").append(scanResult.getScanErrors().get(j).getValue()).append(", ").append(scanResult.getScanErrors().get(j).getType().toString().toLowerCase().replace("_", " ")).append(") ");
                }
                errorBuilder.append("\n");
            }
        }
        // scan result have parser result
        ScanFinalResult result = new ScanFinalResult();
        result.setTokens(tokensBuilder.toString());
        result.setErrors(errorBuilder.toString());
        result.setScanData(scanData);
        return result;
    }



}
