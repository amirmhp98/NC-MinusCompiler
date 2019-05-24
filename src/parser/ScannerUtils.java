package parser;

import domain.ScanError;
import domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by amirmhp on 4/11/2019.
 */
class ScannerUtils {

    private ArrayList<String> keywords = new ArrayList<>(Arrays.asList
            ("if", "else", "void", "int", "while", "break", "continue", "switch", "default", "case", "return"));
    private ArrayList<String> symbols = new ArrayList<>(Arrays.asList
            (";", ":", "[", "]", "(", ")", "{", "}", "+", "-", "+", "==", "*", "=", "<"));
//    private ArrayList<String> WSs = new ArrayList<>(Arrays.asList(
//            " ", "\\r", "\\t", "\\v", "\\f"
//    ));

    private String removeMiddleComment(String inputText) {
        int indexBegin = inputText.indexOf("/*");
        if (indexBegin != -1) {
            int indexEnd = inputText.substring(indexBegin + 2).indexOf("*/");
            if (indexEnd != -1) {
                return inputText.substring(0, indexBegin) + this.removeMiddleComment(inputText.substring(indexEnd + indexBegin + 4));
            }
        }
        return inputText;
    }

    /**
     * also think about linings while you remove comments
     */
    ArrayList<String> removeComment(ArrayList<String> inputText) {
        boolean commentFlag = false;
        for (int i = 0; i < inputText.size(); i++) {
            if (!commentFlag) {
                int index = inputText.get(i).indexOf("//");
                if (index != -1) {
                    inputText.set(i, inputText.get(i).substring(0, index));
                    continue;
                }
                inputText.set(i, this.removeMiddleComment(inputText.get(i)));
                index = inputText.get(i).indexOf("/*");
                if (index != -1) {
                    commentFlag = true;
                    inputText.set(i, inputText.get(i).substring(0, index));
                }
            } else {
                int index = inputText.get(i).indexOf("*/");
                if (index == -1) {
                    inputText.set(i, "");
                    continue;
                }
                commentFlag = false;
                inputText.set(i, inputText.get(i).substring(index + 2));
                i--;
            }
        }

        return inputText;
    }

    ArrayList<String> replaceSymbolsAndWSs(ArrayList<String> inputText) {
        for (int i = 0; i < inputText.size(); i++) {

            String inputLine = inputText.get(i).replaceAll("(?:\\s|&nbsp;)+", " ");
            inputLine = inputLine.replaceAll(" +", "~");
            for (String symbol : symbols) {
                inputLine = inputLine.replace(symbol, "~" + symbol + "~");
            }
            inputLine = inputLine.replaceAll("~+", "~");
            inputText.set(i, inputLine);
        }
        return inputText;
    }

    /**
     * it's important to put the symbols in result list
     */
    String[] splitToWords(String inputText) {
        return inputText.split("~");
    }

    private boolean isKeyword(String inputWord) {
        return this.keywords.contains(inputWord);
    }

    private boolean isSymbol(String inputWord) {
        return this.symbols.contains(inputWord);
    }

    private boolean isId(String inputWord) {
        Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]*$");
        Matcher matcher = pattern.matcher(inputWord);
        return matcher.find();
    }

    private boolean isNum(String inputWord) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(inputWord);
        return matcher.find();
    }

    private TokenType getTokenType(String inputWord) {
        if (this.isKeyword(inputWord)) {
            return TokenType.KEYWORD;
        }
        if (this.isSymbol(inputWord)) {
            return TokenType.SYMBOL;
        }
        if (this.isId(inputWord)) {
            return TokenType.ID;
        }
        if (this.isNum(inputWord)) {
            return TokenType.NUM;
        }
        return TokenType.NOTHING;
    }

    private ScanResult scanWord(String inputWord) {
        ScanResult scanResult = new ScanResult();
        if (inputWord.length() == 0) {
            return scanResult;
        }
        TokenType tokenType = getTokenType(inputWord);
        if (!tokenType.equals(TokenType.NOTHING)) {
            scanResult.addToken(new ScanToken(tokenType, inputWord));
            return scanResult;
        }
        TokenType last = getTokenType(inputWord.substring(inputWord.length() - 1));
        if (last.equals(TokenType.NOTHING)) {
            scanResult.addError(new ScanError(ErrorType.INVALID_INPUT, inputWord));
            return scanResult;
        }
        for (int i = inputWord.length() - 2; i >= 0 && tokenType == TokenType.NOTHING; i--) {
            tokenType = getTokenType(inputWord.substring(i));
            if (tokenType.equals(TokenType.NOTHING)) {
                scanResult.addToken(new ScanToken(last, inputWord.substring(i + 1)));
                scanResult.addError(new ScanError(ErrorType.INVALID_INPUT, inputWord.substring(0, i + 1)));
                return scanResult;
            }
            last = tokenType;
        }
        return scanResult;
    }

    ScanResult scanLineWords(String[] inputWords) {
        ScanResult scanResults = new ScanResult();
        for (String inputWord : inputWords) {
            ScanResult scanResult = this.scanWord(inputWord);
            scanResults.addTokens(scanResult.getScanTokens());
            scanResults.addErrors(scanResult.getScanErrors());
        }
        return scanResults;
    }

}
