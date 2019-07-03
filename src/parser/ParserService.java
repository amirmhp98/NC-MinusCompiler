package parser;

import domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class ParserService {
    private ScannerService scannerService;
    private Stack<TransitionDiagram> parseStack;

    public ParserService(ScannerService scannerService) {
        this.scannerService = scannerService;
        parseStack = new Stack<>();
        parseStack.push(DataBase.makeTransitionDiagram(NonTerminalType.P));
    }

    public void tempRun() {
        ScanToken scanToken;
        MAIN:
        do {
            scanToken = scannerService.getNextToken();
            ParseToken inputParseToken = convertScanTokenToParseToken(scanToken);
            if (inputParseToken == null) {
                return;
            }
            System.out.println("-------------------------");
            System.out.println("scanToken = " + scanToken + " and top stack is: " + lastTransitionDiagram().getName() + " " + lastTransitionDiagram().getCurrentState());
            while (true) {
                String output = lastTransitionDiagram().containTerminal(inputParseToken);
                if (output != null) {
                    // todo see output for label
                    String[] outputs = output.split(",");
                    System.out.println("Change " + lastTransitionDiagram().getName() + " state to " + output);
                    for (int i = 0; i < outputs.length; i++) {
                        try {
                            int newState = Integer.parseInt(outputs[i]);
                            lastTransitionDiagram().setCurrentState(newState);
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                    }
                    break;
                }
                HashMap.Entry<NonTerminalType, String> nonTerminalType = lastTransitionDiagram().containNonTerminal(inputParseToken);
                if (nonTerminalType == null) {
                    if (inputParseToken.getTerminalType() == TerminalType.EOF) {
                        System.out.println("Syntax Error! Malformed Input");
                        break MAIN;
                    } else if (lastTransitionDiagram().haveTerminalWay() != null) {
                        System.out.println(inputParseToken.getLineNumber() + " : Syntax Error! Missing " + lastTransitionDiagram().haveTerminalWay());
                        continue MAIN;
                    } else if (lastTransitionDiagram().haveNonTerminalWay() != null) {
                        System.out.println(inputParseToken.getLineNumber() + " : Syntax Error! Unexpected " + inputParseToken.getTerminalType());

                        if (DataBase.IsInFollow(lastTransitionDiagram().getName(), inputParseToken.getTerminalType()) &&
                                !DataBase.IsInFirst(lastTransitionDiagram().getName(), TerminalType.EPSILON)) {
                            System.out.println(inputParseToken.getLineNumber() + " : Syntax Error! Missing " + lastTransitionDiagram().getName());
                            parseStack.pop();
                        }
                        continue MAIN;
                    } else {
                        break;
                    }
                } else {
                    NonTerminalType lastNonTerminalType = lastTransitionDiagram().getName();
                    // todo see output for label
                    String[] outputs = nonTerminalType.getValue().split(",");
                    for (int i = 0; i < outputs.length; i++) {
                        try {
                            int newState = Integer.parseInt(outputs[i]);
                            lastTransitionDiagram().setCurrentState(newState);
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                    }
                    parseStack.push(DataBase.makeTransitionDiagram(nonTerminalType.getKey()));
                    System.out.println(lastNonTerminalType + " (" + inputParseToken.getTerminalType() + ") -> " + lastTransitionDiagram().getName());
                    if (lastTransitionDiagram().checkEpsilon(inputParseToken)) {
                        System.out.println("Go to end of state by epsilon.");
                    }
                }
                while (true) {
                    if (lastTransitionDiagram().isInEnd()) {
                        System.out.println(lastTransitionDiagram().getName() + " inner popped and end.");
                        parseStack.pop();
                        if (parseStack.size() == 0) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            while (true) {
                if (lastTransitionDiagram().isInEnd()) {
                    System.out.println(lastTransitionDiagram().getName() + " popped and end.");
                    parseStack.pop();
                    if (parseStack.size() == 0) {
                        break;
                    }
                } else {
                    break;
                }
            }

        } while (scanToken.getType() != TokenType.EOF);
    }

//    public String run() {
//        ScanToken scanToken;
//        StringBuilder output = new StringBuilder();
//        int depth = 0;
//        MAIN:
//        do {
//            scanToken = scannerService.getNextToken();
//            System.out.println("scanToken = " + scanToken);
//            ParseToken inputParseToken = convertScanTokenToParseToken(scanToken);
//            if (inputParseToken == null) {
//                continue;
//            }
//            ParseToken currentParseToken = parseStack.pop();
////            if (currentParseToken.isTerminal()) {
////                while (currentParseToken.getTerminalType().equals(TerminalType.TREEFLAG)) {
////                    depth--;
////                    currentParseToken = parseStack.pop();
////                }
////            }
////            appendToResult(output, currentParseToken.getValue(),depth);
//            while (!currentParseToken.isTerminal()) {
//                appendToResult(output, currentParseToken.getValue(), depth, currentParseToken.getLineNumber());
//                ArrayList<ParseToken> parseTokens = expand(inputParseToken, currentParseToken);
////                System.out.println(scanToken.getLineNumber() + "=>" + currentParseToken.getNonTerminalType() + "-" + scanToken + " " + inputParseToken.getTerminalType());
//                for (int i = parseStack.size() - 1; i >= 0; i--) {
//                    if (parseStack.get(i).isTerminal()) {
////                        System.out.print(parseStack.get(i).getTerminalType() + "-");
//                    } else {
////                        System.out.print(parseStack.get(i).getNonTerminalType() + "-");
//                    }
//                }
////                System.out.println();
//                if (parseTokens == null) {
////                    System.out.println(inputParseToken.getLineNumber() + "Expected another terminal. not " + inputParseToken.getValue());
//                    break MAIN;
//                } else {
////                    parseStack.push(new ParseToken(TerminalType.TREEFLAG));
//                    depth++;
//                    for (int i = parseTokens.size() - 1; i >= 0; i--) {
//                        parseStack.push(parseTokens.get(i));
//                    }
//                }
//                currentParseToken = parseStack.pop();
//            }
//            if (currentParseToken.isTerminal()) {
////                while (currentParseToken.getTerminalType().equals(TerminalType.TREEFLAG)) {
////                    depth--;
////                    currentParseToken = parseStack.pop();
////                    if (!currentParseToken.isTerminal() || parseStack.size() == 0) {
////                        continue MAIN;
////                    }
////                }
//            }
//            if (currentParseToken.getTerminalType() != inputParseToken.getTerminalType()) { //terminal mismatch
////                System.out.println(inputParseToken.getLineNumber() + ": Syntax Error! Missing " + inputParseToken.getValue());
//            } else {  //terminal match
////                System.out.println(scanToken.getLineNumber() + "->" + currentParseToken.getTerminalType() + "-" + scanToken);
//                appendToResult(output, currentParseToken.getValue(), depth, currentParseToken.getLineNumber());
//            }
//        } while (scanToken.getType() != TokenType.EOF);
//        return output.toString();
//    }

//    public ParseResult parse() {
//        StringBuilder resultStringBuilder = new StringBuilder();
//        StringBuilder errorStringBuilder = new StringBuilder();
//        int depth = 0;
//        int scanIndex = 0;
//        ScanToken currentScanToken = scannerService.getNextToken();
//        if (currentScanToken == null) {
//            return new ParseResult(resultStringBuilder.toString(), errorStringBuilder.toString());
//        }
//        ParseToken inputParseToken = convertScanTokenToParseToken(currentScanToken);
//        ParseToken currentParseToken;
//        while (parseStack.size() > 0) {
//            currentParseToken = parseStack.pop();
//            if (currentParseToken.isTerminal()) {
////                if (currentParseToken.getTerminalType().equals(TerminalType.TREEFLAG)) {
//                if (currentParseToken.getTerminalType().equals(TerminalType.MINUS)) {
//                    depth--;
//                } else if ((inputParseToken.getTerminalType().equals(currentParseToken.getTerminalType()))) {
//                    appendToResult(resultStringBuilder, currentParseToken.getTerminalType().toString(), depth, inputParseToken.getLineNumber());
//                    try {
//                        currentScanToken = scannerService.getNextToken();
//                    } catch (Exception e) {
//                        if (!currentParseToken.getTerminalType().equals(TerminalType.EOF)) {
//                            errorStringBuilder.append(inputParseToken.getLineNumber() + ": Syntax Error! Malformed Input\n");
//                        }
//                    }
//                    inputParseToken = convertScanTokenToParseToken(currentScanToken);
//                } else {//Err
//                    errorStringBuilder.append(inputParseToken.getLineNumber() + ": Syntax Error! Missing " + currentParseToken.getTerminalType() + "\n");
//                }
//            } else {
//                ArrayList<ParseToken> expandResult = expand(inputParseToken, currentParseToken);
//                if (expandResult == null) { //Err
//                    System.out.println(resultStringBuilder.toString());
//                    errorStringBuilder.append(inputParseToken.getLineNumber() + ": Syntax Error! unexpected " + inputParseToken.getValue() + "\n");
//                    try {
//                        currentScanToken = scannerService.getNextToken();
//                    } catch (Exception e) {
//                        inputParseToken = convertScanTokenToParseToken(currentScanToken);
//                        if (currentScanToken == null) {
//                            return new ParseResult(resultStringBuilder.toString(), errorStringBuilder.toString());
//                        }
//                    }
//                } else if (expandResult.size() == 0) { //epsilon
//                    appendToResult(resultStringBuilder, currentParseToken.getNonTerminalType().toString(), depth, inputParseToken.getLineNumber());
//                } else { //
//                    appendToResult(resultStringBuilder, currentParseToken.getNonTerminalType().toString(), depth, inputParseToken.getLineNumber());
//                    depth++;
////                    parseStack.push(new ParseToken(TerminalType.TREEFLAG));
//                    parseStack.push(new ParseToken(TerminalType.MINUS));
//                    for (int i = expandResult.size() - 1; i >= 0; i--) {
//                        parseStack.push(expandResult.get(i));
//                    }
//                }
//            }
//        }
//        //write scan data one by one with line number and detect  // ??
//        return new ParseResult(resultStringBuilder.toString(), errorStringBuilder.toString());
//    }

    private void appendToResult(StringBuilder resultStringBuilder, String token, int depth, int lineNumber) {
        resultStringBuilder.append(lineNumber + ": ");
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

//    private boolean containDiagram(ParseToken parseToken) {
//        int state = lastTransitionDiagram().containTerminal(parseToken);
//        return true;
//    }

    private TransitionDiagram lastTransitionDiagram() {
        return parseStack.get(parseStack.size() - 1);
    }

    private ArrayList<ParseToken> expand(ParseToken inputToken, ParseToken currentToken) {
        if (!DataBase.getInstance().getTable().get(currentToken.getNonTerminalType()).containsKey(inputToken.getTerminalType())) {
            return null;
        }
        return (ArrayList<ParseToken>) DataBase.getInstance().getTable().get(currentToken.getNonTerminalType()).get(inputToken.getTerminalType());
    }

}
