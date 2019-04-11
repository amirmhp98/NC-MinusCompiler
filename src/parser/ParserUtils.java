package parser;

import domain.*;
import domain.Error;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class ParserUtils {
    private ArrayList<String> keywords = new ArrayList<>(Arrays.asList
            ("if", "else", "void", "int", "while", "break", "continue", "switch", "default", "case", "return"));
    private ArrayList<String> symbols = new ArrayList<>(Arrays.asList
            (";", ":", "[", "]", "(", ")", "{", "}", "+", "-", "+", "*", "=", "<", "=="));

    /**
     * also think about linings while you remove comments
     */
    protected String removeComment(String inputText){
        //todo implement
        return null;
    }

    protected List<String> seprateLines(String inputText){
        return Arrays.asList(inputText.split("\\n"));
    }

    protected String replaceSymbolsAndWSs(String inputText){
        //todo implement
        return null;
    }

    /**
     * it's important to put the symbols in result list
     */
    protected List<String> splitBySymbols(String inputText){
        //todo implement
        return null;
    }

    private boolean isKeyword(String inputWord){
        return this.keywords.contains(inputWord);
    }

    private boolean isSymbol(String inputWord){
        return this.symbols.contains(inputWord);
    }

    private boolean isId(String inputWord){
        Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]*$");
        Matcher matcher = pattern.matcher(inputWord);
        return matcher.find();
    }

    private boolean isNum(String inputWord){
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(inputWord);
        return matcher.find();
    }

    private TokenType getTokenType(String inputWord){
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

    private ParseResult parseWord(String inputWord){
        ParseResult parseResult = new ParseResult();
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
                parseResult.addError(new Error(ErrorType.INVALID_INPUT, inputWord.substring(1, i + 1)));
                return parseResult;
            }
            last = tokenType;
        }
        return parseResult;
    }

    protected ParseResult parseLineWords(List<String> inputWords){
        //todo implement
        ParseResult parseResults = new ParseResult();
        for (String inputWord : inputWords) {
            ParseResult parseResult = this.parseWord(inputWord);
            parseResults.addTokens(parseResult.getTokens());
            parseResults.addErrors(parseResult.getErrors());
        }
        return parseResults;
    }

}
