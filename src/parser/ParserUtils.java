package parser;

import domain.ParseResult;

import java.util.Arrays;
import java.util.List;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class ParserUtils {

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

    protected List<ParseResult> parseLineWords(List<String> inputWords){
        //todo implement
        return null;
    }

}
