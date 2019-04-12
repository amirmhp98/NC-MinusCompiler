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
        text = parserUtils.removeComment(text);
        text = parserUtils.replaceSymbolsAndWSs(text);
        for (int i = 0; i < text.size(); i++) {
            ParseResult parseResult = parserUtils.parseLineWords(parserUtils.splitToWords(text.get(i)));
            System.out.print((i+1) + "- ");
            for (int j = 0; j < parseResult.getTokens().size(); j++) {
                System.out.print("(" + parseResult.getTokens().get(j).getValue() + ", " + parseResult.getTokens().get(j).getType().toString() + ")");
            }
            System.out.println();
            System.out.print((i+1) + "- ");
            for (int j = 0; j < parseResult.getErrors().size(); j++) {
                System.out.print("(" + parseResult.getErrors().get(j).getValue() + ", " + parseResult.getErrors().get(j).getType().toString() + ")");
            }
            System.out.println();
        }
        return null;
    }

}
