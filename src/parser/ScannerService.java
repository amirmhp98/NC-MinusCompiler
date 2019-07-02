package parser;

import domain.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class ScannerService {

    private FileService fileService;
    private String currentLine;
    private ArrayList<ScanToken> currentScanTokens;
    private ScanResult scanResult;
    private boolean commentFlag;

    public ScannerService(FileService fileService) {
        this.fileService = fileService;
        this.currentScanTokens = new ArrayList<>();
        this.scanResult = new ScanResult();
        this.commentFlag = false;
    }

    ScanToken getNextToken() {
        if (currentScanTokens.size() == 0) {
            FileToken newFileToken = fileService.getNextToken();
            if (newFileToken == null) {
                return new ScanToken(TokenType.EOF, "EOF", fileService.getCurrentLineNumber());
            }
            this.currentLine = newFileToken.getLine();
            setScanTokens();
            scanResult.addTokens(currentScanTokens);
            if (currentScanTokens.size() == 0) {
                return getNextToken();
            }
        }
        ScanToken scanToken = this.currentScanTokens.get(0);
        this.currentScanTokens.remove(0);
        return scanToken;
//        String[] splitCurrentLine = splitToWords(currentFileToken.getLine());
//        if (splitCurrentLine.length > 1) {
//            this.currentFileToken = new FileToken(
//                    String.join("~", Arrays.copyOfRange(splitCurrentLine, 1, splitCurrentLine.length)),
//                    this.currentFileToken.getLineNumber());
//        } else {
//            this.currentFileToken = new FileToken("", this.currentFileToken.getLineNumber());
//        }
//        ScanResult scanResult = scanWord(splitCurrentLine[0], this.currentFileToken.getLineNumber());
//        this.scanResult.addTokens(scanResult.getScanTokens());
//        this.scanResult.addErrors(scanResult.getScanErrors());
//        return scanResult.getScanTokens().get(0);
    }

    private void setScanTokens() {
        currentLine = removeComment(currentLine);
//        System.out.println("currentLine = " + currentLine);
        while (!currentLine.isEmpty()) {
            getNextOneToken();
        }
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


    private void getNextOneToken() {
//        System.out.println("currentLine(gtn) = " + currentLine);
        skipWhitespace();
        TokenType last = TokenType.NOTHING;
        for (int i = 1; i <= currentLine.length(); i++) {
//            System.out.println("sub(" + i + ") = " + currentLine.substring(0, i));
            TokenType tokenType = getTokenType(currentLine.substring(0, i));
//            System.out.println("tokenType = " + tokenType);
            if (!Character.isWhitespace(currentLine.charAt(i - 1)) && getTokenType(currentLine.substring(i - 1, i)) == TokenType.NOTHING) {
                System.out.println(currentLine.substring(0, i) + " is invalid string. parse out.");
                scanResult.addError(new ScanError(ErrorType.INVALID_INPUT, currentLine.substring(0, i), fileService.getCurrentLineNumber()));
                currentLine = currentLine.substring(i);
                break;
            }
            if (tokenType != TokenType.NOTHING) {
                last = tokenType;
//                System.out.println("Initial token: " + last);
            } else if (last != TokenType.NOTHING) {
                i--;
                currentScanTokens.add(new ScanToken(last, currentLine.substring(0, i), fileService.getCurrentLineNumber()));
                currentLine = currentLine.substring(i);
//                System.out.println("Exit from token(" + currentScanTokens.get(currentScanTokens.size() - 1).getValue().length() + ") = " + currentScanTokens.get(currentScanTokens.size() - 1));
                break;
            }
            if (i == currentLine.length()) {
                currentScanTokens.add(new ScanToken(last, currentLine.substring(0, i), fileService.getCurrentLineNumber()));
                currentLine = "";
//                System.out.println("add last (" + currentScanTokens.get(currentScanTokens.size() - 1).getValue().length() + ") = " + currentScanTokens.get(currentScanTokens.size() - 1));
                break;
            }
        }
        skipWhitespace();
    }

    private void skipWhitespace() {
        int i = 0;
        while (i < currentLine.length() && Character.isWhitespace(currentLine.charAt(i))) {
            i++;
        }
        currentLine = currentLine.substring(i);
    }

//    private boolean tryRegex(Pattern p, TokenType type) {
//        Matcher m = p.matcher(currentLine);
//        if (m.lookingAt()) {
    //checking if it is not a keyword:
//            DataBase.getKeywords().contains(inputWord);
//            for (int i = 0; i < DataBase.getKeywords().size(); i++) {
//
//            }
//            for (DataBase.getKeywords() keyworld  : .Type.values()){
//                if (tokenType.getGroup().equals("KEYWORD") &&
//                        m.group().equals(tokenType.getText())) {
//                    return false;
//                }
//            }
//
//            boolean thereIsError = checkInvalidity(m.group());
//            if (!thereIsError && addToken) {
//                result.add(new Token(type, m.group(), line, col));
//                consumeInput(m.group().length());
//            }
//
//            return true;
//        } else {
//            return false;
//        }
//    }

//    private boolean tryToken(Token.Type type, boolean addToken, int start) {
//        String text = type.getText();
//        if (input.substring(start).startsWith(text)) {
//            if (!addToken) return true;
//            else {
//                boolean thereIsError = checkInvalidity(text);
//                if (!thereIsError) {
//                result.add(new Token(type, text, line, col));
//                consumeInput(text.length());
//                }
//                return true;
//            }
//        } else {
//            return false;
//        }
//    }

//    private boolean tryManyTokens(boolean addToken, int start) {
//        boolean output = false;
//        for (Token.Type type : Token.Type.values()) {
//            String group = type.getGroup();
//            if (group.equals("KEYWORD") || group.equals("SYMBOL")) {
//                output = output || tryToken(type, addToken, start);
//            }
//        }
//        return output;
//    }

//    private boolean handleComment() {
//        boolean output = false;
//        int i = 0;
//        if (tryToken(Token.Type.OPEN_COMMENT, false, i)) {
//            i += Token.Type.OPEN_COMMENT.getText().length();
//            while (i <= input.length() - 1) {
//                if (tryToken(Token.Type.CLOSE_COMMENT, false, i++))
//                    break;
//            }
//            consumeInput(i - 1 + Token.Type.CLOSE_COMMENT.getText().length());
//            output = true;
//        }
//        i = 0;
//        if (tryToken(Token.Type.SINGLE_LINE_COMMENT, false, i)) {
//            i += Token.Type.SINGLE_LINE_COMMENT.getText().length();
//            while (i <= input.length() - 1) {
//                if (tryToken(Token.Type.NEWLINE, false, i++))
//                    break;
//            }
//            consumeInput(i - 1 + Token.Type.NEWLINE.getText().length());
//            output = true;
//        }
//
//        return output;
//    }

//    private boolean isInvalidCharacter(int i) {
//        return !Character.isWhitespace(input.charAt(i))
//                && !tryManyTokens(false, i)
//                && !identifierPattern.matcher(input.substring(i)).lookingAt()
//                && !intPattern.matcher(input.substring(i)).lookingAt();
//    }

//    private boolean checkInvalidity(String text) {
//        int i = text.length();
//        StringBuilder invalid_token = new StringBuilder(text);
//        boolean thereIsError = false;
//
//        while (i <= input.length() - 1 && isInvalidCharacter(i)) {
//
//            thereIsError = true;
//            invalid_token.append(input.charAt(i++));
//        }
//
//        if (thereIsError) {
//            lexical_errors.add(new Token(Token.Type.INVALID_INPUT, invalid_token.toString(), line, col));
//            consumeInput(invalid_token.length());
//        }
//
//        return thereIsError;
//    }

//    private static String replaceSymbolsAndWSs(String inputText) {
//        String inputLine = inputText.replaceAll("(?:\\s|&nbsp;)+", " ");
//
//        inputLine = inputLine.replaceAll(" +", "~");
//        inputLine = inputLine.replaceAll("==", "ج");
//        for (String symbol : DataBase.getSymbols()) {
//            inputLine = inputLine.replace(symbol, "~" + symbol + "~");
//        }
//        inputLine = inputLine.replaceAll("ج", "==");
//        inputLine = inputLine.replaceAll("~+", "~");
//        if (inputLine.length() > 0) {
//            if (inputLine.charAt(0) == '~') {
//                inputLine = inputLine.substring(1);
//            }
//        }
//        return inputLine;
//    }
//
//    private static String[] splitToWords(String inputText) {
//        return inputText.split("~");
//    }

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

//    private static ScanResult scanWord(String inputWord, int currentLineNumber) {
//        ScanResult scanResult = new ScanResult();
//        if (inputWord.length() == 0) {
//            return scanResult;
//        }
//        TokenType tokenType = getTokenType(inputWord);
//        if (!tokenType.equals(TokenType.NOTHING)) {
//            scanResult.addToken(new ScanToken(tokenType, inputWord, currentLineNumber));
//            return scanResult;
//        }
//        TokenType last = getTokenType(inputWord.substring(inputWord.length() - 1));
//        if (last.equals(TokenType.NOTHING)) {
//            scanResult.addError(new ScanError(ErrorType.INVALID_INPUT, inputWord, currentLineNumber));
//            return scanResult;
//        }
//        for (int i = inputWord.length() - 2; i >= 0 && tokenType == TokenType.NOTHING; i--) {
//            tokenType = getTokenType(inputWord.substring(i));
//            if (tokenType.equals(TokenType.NOTHING)) {
//                scanResult.addToken(new ScanToken(last, inputWord.substring(i + 1), currentLineNumber));
//                scanResult.addError(new ScanError(ErrorType.INVALID_INPUT, inputWord.substring(0, i + 1), currentLineNumber));
//                return scanResult;
//            }
//            last = tokenType;
//        }
//        return scanResult;
//    }

    public ScanResult getScanResult() {
        return scanResult;
    }
}
