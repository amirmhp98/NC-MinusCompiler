package parser;

import domain.ParseResult;
import domain.ScanData;
import domain.TerminalType;

import java.util.HashMap;
import java.util.Map;

public class ParserUtils {
    // todo : write LL1 table here
    private Map table;

    public ParserUtils(){
        table = new HashMap();
        table.put(TerminalType.A, "salam");
    }

    ParseResult parseTokens(ScanData scanData){
        //write scan data one by one with line number and detect
        return null;
    }
}
