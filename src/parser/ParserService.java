package parser;

import domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ParserService {
	private ParserUtils parserUtils;
	private Stack<ParseToken> parseStack;
	private int scanIndex = 0;

	public ParserService() {
		parserUtils = new ParserUtils();
	}

	public ParseResult parse(ScanData scanData) {
		StringBuilder resultStringBuilder = new StringBuilder();
		int depth = 0;
		scanIndex = 0;
		ScanToken currentScanToken = getNextToken(scanData, scanIndex);
		ParseToken currentParseToken;
		parseStack = new Stack<>();
		parseStack.push(new ParseToken(TerminalType.program, null, 0));
		while (parseStack.size() > 0) {
			currentParseToken = parseStack.pop();
			if (currentParseToken.isTerminal()) {
				if (currentParseToken.getTerminalType().equals(TerminalType.depthFlag)) {
					depth--;
				} else if (parserUtils.terminalValidation(currentScanToken, currentParseToken)) {
					appendToResult(resultStringBuilder, currentParseToken.getTerminalType().toString(), depth);
					currentScanToken = getNextToken(scanData, scanIndex);
				} else {//Err
					//todo throw respective exception
				}
			} else {
				ArrayList<ParseToken> expandResult = parserUtils.expand(currentScanToken, currentParseToken);
				if (expandResult == null) { //Err
					//todo throw respective exception
				} else if (expandResult.size() == 0) { //epsilon
					appendToResult(resultStringBuilder, currentParseToken.getTerminalType().toString(), depth);
					appendToResult(resultStringBuilder, TerminalType.eps.toString(), depth + 1);
				} else { //
					appendToResult(resultStringBuilder, currentParseToken.getTerminalType().toString(), depth);
					depth++;
					parseStack.push(new ParseToken(TerminalType.depthFlag, null, 0));
					for (int i = expandResult.size() - 1; i >= 0; i--) {
						parseStack.push(expandResult.get(i));
					}
				}
			}
		}
		//write scan data one by one with line number and detect  // ??
		return new ParseResult(resultStringBuilder.toString());
	}

	private void appendToResult(StringBuilder resultStringBuilder, String token, int depth) {
		for (int i = 0; i < depth; i++) {
			resultStringBuilder.append("|\t");
		}
		resultStringBuilder.append(token);
		resultStringBuilder.append("\n");
	}

	private ScanToken getNextToken(ScanData scanData, int scanIndex) {
		//todo perform pipeline
		//todo handle arrayOutOfBoundException
		this.scanIndex++;
		if (scanIndex<scanData.getScanTokens().size()){
		return scanData.getScanTokens().get(scanIndex);}
		else return new ScanToken(TokenType.EOF, "EOF");
	}

}
