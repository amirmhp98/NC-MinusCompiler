package parser;

import domain.Error;
import domain.FinalResult;
import domain.ParseResult;
import domain.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class ParserService {

    ParserUtils parserUtils = new ParserUtils();

    public FinalResult parse(String text) {
        String workingText = parserUtils.removeComment(text); //todo think about
        workingText = parserUtils.replaceSymbolsAndWSs(workingText);
        List<String> lines = parserUtils.seprateLines(workingText);
        ArrayList<List<Token>> tokenResult = new ArrayList<>(lines.size());
        ArrayList<List<Error>> errorResult = new ArrayList<>(lines.size());
        ParseResult tempParseResult;
        List<String> tempWords;
        for (int i = 0; i < lines.size(); i++) {
            tempWords = parserUtils.splitToWords(lines.get(i));
            tempParseResult = parserUtils.parseLineWords(tempWords);
            tokenResult.set(i, tempParseResult.getTokens());
            errorResult.set(i, tempParseResult.getErrors());
        }
        FinalResult finalResult = new FinalResult();
        finalResult.setTokens(parserUtils.generateTokensString(tokenResult));
        finalResult.setErrors(parserUtils.generateErrorsString(errorResult));
        return finalResult;
    }

}
