package parser;

import domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ParserService {
    private ScannerService scannerService;
    private Stack<ParseToken> parseStack;

    public ParserService(ScannerService scannerService) {
        this.scannerService = scannerService;
        parseStack = new Stack<>();
        parseStack.push(new ParseToken(NonTerminalType.Program, null, 0));
    }

    public void run() {
        ScanToken scanToken;
        MAIN:
        do {
            scanToken = scannerService.getNextToken();
            ParseToken inputParseToken = convertScanTokenToParseToken(scanToken);
            if (inputParseToken == null) {
                continue;
            }
            ParseToken currentParseToken = parseStack.pop();
            while (!currentParseToken.isTerminal()) {
                ArrayList<ParseToken> parseTokens = expand(inputParseToken, currentParseToken);
                System.out.println(scanToken.getLineNumber()+"=>"+currentParseToken.getNonTerminalType()+"-"+scanToken + " " + inputParseToken.getTerminalType());
                for (int i = parseStack.size() - 1; i >= 0 ; i--) {
                    if (parseStack.get(i).isTerminal()){
                        System.out.print(parseStack.get(i).getTerminalType() + "-");
                    }else{
                        System.out.print(parseStack.get(i).getNonTerminalType() + "-");
                    }
                }
                System.out.println();
                if (parseTokens == null) {
                    System.out.println("00000000000000000000000000000000  Error in convert nunTerminal to termianl "+ inputParseToken.getLineNumber());
                    continue MAIN;
                } else {
                    for (int i = parseTokens.size() - 1; i >= 0; i--) {
                        parseStack.push(parseTokens.get(i));
                    }
                }
                currentParseToken = parseStack.pop();
            }
            if (currentParseToken.getTerminalType() != inputParseToken.getTerminalType()){
                System.out.println("00000000000000000000000000000  Error in terminal matches "+ inputParseToken.getLineNumber());
            } else {
                System.out.println(scanToken.getLineNumber()+"->"+currentParseToken.getTerminalType()+"-"+scanToken);
            }
        } while (scanToken.getType() != TokenType.EOF);
    }

    public ParseResult parse(ScanData scanData) {
        StringBuilder resultStringBuilder = new StringBuilder();
        int depth = 0;
        int scanIndex = 0;
        ScanToken currentScanToken = getNextToken(scanData, scanIndex);
        ParseToken currentParseToken;
        while (parseStack.size() > 0) {
            currentParseToken = parseStack.pop();
            if (currentParseToken.isTerminal()) {
//                if (currentParseToken.getTerminalType().equals(TerminalType.depthFlag)) {
//                    depth--;
//                } else if (parserUtils.terminalValidation(currentScanToken, currentParseToken)) {
//                    appendToResult(resultStringBuilder, currentParseToken.getTerminalType().toString(), depth);
//                    currentScanToken = getNextToken(scanData, scanIndex);
//                } else {//Err
//					todo throw respective exception
//                }
            } else {
//                ArrayList<ParseToken> expandResult = parserUtils.expand(currentScanToken, currentParseToken);
//                if (expandResult == null) { //Err
                    //todo throw respective exception
//                } else if (expandResult.size() == 0) { //epsilon
//                    appendToResult(resultStringBuilder, currentParseToken.getTerminalType().toString(), depth);
//                    appendToResult(resultStringBuilder, TerminalType.eps.toString(), depth + 1);
//                } else { //
//                    appendToResult(resultStringBuilder, currentParseToken.getTerminalType().toString(), depth);
//                    depth++;
//                    parseStack.push(new ParseToken(TerminalType.depthFlag, null, 0));
//                    for (int i = expandResult.size() - 1; i >= 0; i--) {
//                        parseStack.push(expandResult.get(i));
//                    }
//                }
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
//        this.scanIndex++;
//		if (scanIndex<scanData.getScanTokens().size()){
//		return scanData.getScanTokens().get(scanIndex);}
//		else
        return null;
    }

    private ParseToken convertScanTokenToParseToken(ScanToken scanToken) {
        if (scanToken.getType() == TokenType.ID) {
            return new ParseToken(TerminalType.ID, scanToken.getValue(), scanToken.getLineNumber());
        }
        if (scanToken.getType() == TokenType.NUM) {
            return new ParseToken(TerminalType.NUM, scanToken.getValue(), scanToken.getLineNumber());
        }
        if (scanToken.getType() == TokenType.EOF) {
            return new ParseToken(TerminalType.EOF, scanToken.getValue(), scanToken.getLineNumber());
        }
        if (scanToken.getType() == TokenType.SYMBOL) {
            return new ParseToken(DataBase.getSymbolsTable().get(scanToken.getValue()), scanToken.getValue(), scanToken.getLineNumber());
        }
        if (scanToken.getType() == TokenType.KEYWORD) {
            return new ParseToken(DataBase.getKeywordsTable().get(scanToken.getValue()), scanToken.getValue(), scanToken.getLineNumber());
        }
        return null;
    }

    private ArrayList<ParseToken> expand(ParseToken inputToken, ParseToken currentToken) {
        if (!DataBase.getInstance().getTable().get(currentToken.getNonTerminalType()).containsKey(inputToken.getTerminalType())) {
            return null;
        }
        return (ArrayList<ParseToken>) DataBase.getInstance().getTable().get(currentToken.getNonTerminalType()).get(inputToken.getTerminalType());
    }

}
