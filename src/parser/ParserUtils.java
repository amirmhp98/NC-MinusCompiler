package parser;

import domain.Error;
import domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by amirmhp on 4/11/2019.
 */
class ParserUtils {

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

    private ParseResult parseWord(String inputWord) {
        ParseResult parseResult = new ParseResult();
        if (inputWord.length() == 0) {
            return parseResult;
        }
        TokenType tokenType = getTokenType(inputWord);
        if (!tokenType.equals(TokenType.NOTHING)) {
            parseResult.addToken(new Token(tokenType, inputWord));
            return parseResult;
        }
        TokenType last = getTokenType(inputWord.substring(inputWord.length() - 1));
        if (last.equals(TokenType.NOTHING)) {
            parseResult.addError(new Error(ErrorType.INVALID_INPUT, inputWord));
            return parseResult;
        }
        for (int i = inputWord.length() - 2; i >= 0 && tokenType == TokenType.NOTHING; i--) {
            tokenType = getTokenType(inputWord.substring(i));
            if (tokenType.equals(TokenType.NOTHING)) {
                parseResult.addToken(new Token(last, inputWord.substring(i + 1)));
                parseResult.addError(new Error(ErrorType.INVALID_INPUT, inputWord.substring(0, i + 1)));
                return parseResult;
            }
            last = tokenType;
        }
        return parseResult;
    }

    ParseResult parseLineWords(String[] inputWords) {
        ParseResult parseResults = new ParseResult();
        for (String inputWord : inputWords) {
            ParseResult parseResult = this.parseWord(inputWord);
            parseResults.addTokens(parseResult.getTokens());
            parseResults.addErrors(parseResult.getErrors());
        }
        return parseResults;
    }

}
