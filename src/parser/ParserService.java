package parser;

import domain.FinalResult;
import domain.ParseResult;

import java.util.ArrayList;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class ParserService {

    private ParserUtils parserUtils;

    public ParserService() {
        this.parserUtils = new ParserUtils();
    }

    public FinalResult parse(ArrayList<String> text) {
        StringBuilder tokensBuilder = new StringBuilder();
        StringBuilder errorBuilder = new StringBuilder();
        text = parserUtils.removeComment(text);
        text = parserUtils.replaceSymbolsAndWSs(text);
        for (int i = 0; i < text.size(); i++) {
            ParseResult parseResult = parserUtils.parseLineWords(parserUtils.splitToWords(text.get(i)));
            if (!parseResult.getTokens().isEmpty()) {
                tokensBuilder.append((i + 1) + ". ");
                for (int j = 0; j < parseResult.getTokens().size(); j++) {
                    tokensBuilder.append("(" + parseResult.getTokens().get(j).getType().toString() + ", " + parseResult.getTokens().get(j).getValue() + ") ");
                }
                tokensBuilder.append("\n");
            }
            if (!parseResult.getErrors().isEmpty()) {
                errorBuilder.append((i + 1) + ". ");
                for (int j = 0; j < parseResult.getErrors().size(); j++) {
                    errorBuilder.append("(" + parseResult.getErrors().get(j).getValue() + ", " + parseResult.getErrors().get(j).getType().toString().toLowerCase().replace("_", " ") + ") ");
                }
                errorBuilder.append("\n");
            }
        }
        FinalResult result = new FinalResult();
        result.setTokens(tokensBuilder.toString());
        result.setErrors(errorBuilder.toString());
        return result;
    }

}
