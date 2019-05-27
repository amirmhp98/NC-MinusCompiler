package parser;

import domain.*;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class ScannerService {

    private FileService fileService;
    private FileToken currentFileToken;
    private ScanResult scanResult;
    private boolean commentFlag;

    public ScannerService(FileService fileService) {
        this.fileService = fileService;
        this.currentFileToken = new FileToken("", 0);
        this.scanResult = new ScanResult();
        this.commentFlag = false;
    }

    ScanToken getNextToken() {
        // todo maybe this is not equal because string is space and etc. .
        if (this.currentFileToken.getLine().equals("")) {
            FileToken currentFileToken = fileService.getNextToken();
            if (currentFileToken == null) {
                return new ScanToken(TokenType.EOF, "EOF", this.currentFileToken.getLineNumber());
            } else {
                this.currentFileToken = currentFileToken;
            }
            String currentLine = this.currentFileToken.getLine();
            currentLine = removeComment(currentLine);
            currentLine = replaceSymbolsAndWSs(currentLine);
            this.currentFileToken = new FileToken(currentLine, this.currentFileToken.getLineNumber());
            if (this.currentFileToken.getLine().equals("")) {
                return getNextToken();
            }
        }
        String[] splitCurrentLine = splitToWords(currentFileToken.getLine());
        if (splitCurrentLine.length > 1) {
            this.currentFileToken = new FileToken(
                    String.join("~", Arrays.copyOfRange(splitCurrentLine, 1, splitCurrentLine.length)),
                    this.currentFileToken.getLineNumber());
        } else {
            this.currentFileToken = new FileToken("", this.currentFileToken.getLineNumber());
        }
        ScanResult scanResult = scanWord(splitCurrentLine[0], this.currentFileToken.getLineNumber());
        this.scanResult.addTokens(scanResult.getScanTokens());
        this.scanResult.addErrors(scanResult.getScanErrors());
        return scanResult.getScanTokens().get(0);
        // todo check current file token and get line and get first scan token. add this to scanResult and return scanToken.
    }

    private String removeComment(String inputText) {
        if (!this.commentFlag) {
            int index = inputText.indexOf("//");
            if (index != -1) {
                return inputText.substring(0, index);
            }
            inputText = removeMiddleComment(inputText);
            index = inputText.indexOf("/*");
            if (index != -1) {
                this.commentFlag = true;
                return inputText.substring(0, index);
            } else {
                return inputText;
            }
        } else {
            int index = inputText.indexOf("*/");
            if (index == -1) {
                return "";
            }
            this.commentFlag = false;
            return this.removeComment(inputText.substring(index + 2));
        }
    }

    private static String removeMiddleComment(String inputText) {
        int indexBegin = inputText.indexOf("/*");
        if (indexBegin != -1) {
            int indexEnd = inputText.substring(indexBegin + 2).indexOf("*/");
            if (indexEnd != -1) {
                return inputText.substring(0, indexBegin) + removeMiddleComment(inputText.substring(indexEnd + indexBegin + 4));
            }
        }
        return inputText;
    }

    private static String replaceSymbolsAndWSs(String inputText) {
        String inputLine = inputText.replaceAll("(?:\\s|&nbsp;)+", " ");
        inputLine = inputLine.replaceAll(" +", "~");
        for (String symbol : DataBase.getSymbols()) {
            inputLine = inputLine.replace(symbol, "~" + symbol + "~");
        }
        inputLine = inputLine.replaceAll("~+", "~");
        if (inputLine.charAt(0) == '~'){
            inputLine = inputLine.substring(1);
        }
        return inputLine;
    }

    private static String[] splitToWords(String inputText) {
        return inputText.split("~");
    }

    private static boolean isKeyword(String inputWord) {
        return DataBase.getKeywords().contains(inputWord);
    }

    private static boolean isSymbol(String inputWord) {
        return DataBase.getSymbols().contains(inputWord);
    }

    private static boolean isId(String inputWord) {
        Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]*$");
        Matcher matcher = pattern.matcher(inputWord);
        return matcher.find();
    }

    private static boolean isNum(String inputWord) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(inputWord);
        return matcher.find();
    }

    private static TokenType getTokenType(String inputWord) {
        if (isKeyword(inputWord)) {
            return TokenType.KEYWORD;
        }
        if (isSymbol(inputWord)) {
            return TokenType.SYMBOL;
        }
        if (isId(inputWord)) {
            return TokenType.ID;
        }
        if (isNum(inputWord)) {
            return TokenType.NUM;
        }
        return TokenType.NOTHING;
    }

    private static ScanResult scanWord(String inputWord, int currentLineNumber) {
        ScanResult scanResult = new ScanResult();
        if (inputWord.length() == 0) {
            return scanResult;
        }
        TokenType tokenType = getTokenType(inputWord);
        if (!tokenType.equals(TokenType.NOTHING)) {
            scanResult.addToken(new ScanToken(tokenType, inputWord, currentLineNumber));
            return scanResult;
        }
        TokenType last = getTokenType(inputWord.substring(inputWord.length() - 1));
        if (last.equals(TokenType.NOTHING)) {
            scanResult.addError(new ScanError(ErrorType.INVALID_INPUT, inputWord, currentLineNumber));
            return scanResult;
        }
        for (int i = inputWord.length() - 2; i >= 0 && tokenType == TokenType.NOTHING; i--) {
            tokenType = getTokenType(inputWord.substring(i));
            if (tokenType.equals(TokenType.NOTHING)) {
                scanResult.addToken(new ScanToken(last, inputWord.substring(i + 1), currentLineNumber));
                scanResult.addError(new ScanError(ErrorType.INVALID_INPUT, inputWord.substring(0, i + 1), currentLineNumber));
                return scanResult;
            }
            last = tokenType;
        }
        return scanResult;
    }

    public ScanResult getScanResult() {
        return scanResult;
    }
}
