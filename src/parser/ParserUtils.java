package parser;

import domain.ParseResult;
import domain.ScanData;
import domain.TerminalData;
import domain.TerminalType;

import java.util.HashMap;

class ParserUtils {
    // todo : write LL1 table here
    private static HashMap<TerminalType, TerminalData> table = new HashMap<>();

    ParserUtils(){

        table.put(TerminalType.A, null);
    }

    ParseResult parseTokens(ScanData scanData){
        //write scan data one by one with line number and detect
        return null;
    }
}
