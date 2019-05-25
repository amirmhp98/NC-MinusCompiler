package parser;

import domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParserUtils {
	// todo : write LL1 table here
	private Map table;

	public ParserUtils() {
		table = new HashMap();
		table.put(TerminalType.A, "salam");
	}

	//nemidumam in chie
	ParseResult parseTokens(ScanData scanData) {
		//write scan data one by one with line number and detect
		return null;
	}


	/**
	 * parser service call this method and pass token and a non-terminal to get right side of the grammar which is our current destination.
	 *
	 * @param inputToken
	 * @param currentToken -> non-terminal
	 * @return list of #Parsetokens of grammar's right hand side. CAUTION -> pay attention to order: most left character of Right hand side (grammar convert result) putted in least index.
	 */
	ArrayList<ParseToken> expand(ScanToken inputToken, ParseToken currentToken) {
		return null;
	}

	/**
	 * parser service call this method and pass token and a terminal to check whether they match or not.
	 *
	 * @param inputToken
	 * @param currentToken
	 * @return match result
	 */
	boolean terminalValidation(ScanToken inputToken, ParseToken currentToken) {
		return false;
	}

}
