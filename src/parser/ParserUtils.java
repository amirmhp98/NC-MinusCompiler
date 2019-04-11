package parser;

import domain.Error;
import domain.ParseResult;
import domain.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class ParserUtils {

    private ArrayList<String> keywords = new ArrayList<>(Arrays.asList
            ("if", "else", "void", "int", "while", "break", "continue", "switch", "default", "case", "return"));
    private ArrayList<String> symbols = new ArrayList<>(Arrays.asList
            (";", ":", "[", "]", "(", ")", "{", "}", "+", "-", "+", "==", "*", "=", "<"));
    private ArrayList<String> WSs = new ArrayList<>(Arrays.asList(
            " ", "\\r", "\\t", "\\v", "\\f"
    ));

    /**
     * also think about linings while you remove comments
     */
    protected String removeComment(String inputText) {
        //todo implement
        return inputText;
    }

    protected List<String> seprateLines(String inputText) {
        return Arrays.asList(inputText.split("\\r?\\n"));
    }

    protected String replaceSymbolsAndWSs(String inputText) {
        inputText = inputText.replace("\\s+", "~");
        for (String symbol : symbols) {
            inputText = inputText.replace(symbol, "~" + symbol + "~");
        }
        inputText = inputText.replace("~~","~");
        inputText = inputText.replace("~~","~");
        return inputText;
    }

    /**
     * it's important to put the symbols in result list
     */
    protected List<String> splitToWords(String inputText) {
        return Arrays.asList(inputText.split("~"));
    }

    protected ParseResult parseLineWords(List<String> inputWords) {
        //todo implement
        return null;
    }

    protected String generateTokensString (ArrayList<List<Token>> parsedLines){
        //todo implement
        return null;
    }

    protected String generateErrorsString (ArrayList<List<Error>> errors){
        //todo implement
        return null;
    }

}
