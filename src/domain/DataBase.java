package domain;

import java.util.*;
import java.util.regex.Pattern;

public class DataBase {
    private static DataBase dataBase = new DataBase();
    private static HashMap<NonTerminalType, HashMap<TerminalType, List<ParseToken>>> table;
    private static HashMap<NonTerminalType, ArrayList<TerminalType>> first;
    private static HashMap<NonTerminalType, ArrayList<TerminalType>> follow;
    private static Pattern intPattern = Pattern.compile("[0-9]+");
    private static Pattern boolPattern = Pattern.compile("true|false");
    private static Pattern identifierPattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");
    private static ArrayList<String> keywords;
    private static HashMap<String, TerminalType> keywordsTable;
    private static ArrayList<String> symbols;
    private static HashMap<String, TerminalType> symbolsTable;

    private DataBase() {
        setKeywords();
        setSymbols();
        setFirstFollow();
    }

    private static void setKeywords() {
        keywords = new ArrayList<>(Arrays.asList
                ("if", "else", "void", "int", "while", "break", "continue", "switch", "default", "case", "return"));
        keywordsTable = new HashMap<>();
        keywordsTable.put("if", TerminalType.IF);
        keywordsTable.put("else", TerminalType.ELSE);
        keywordsTable.put("void", TerminalType.VOID);
        keywordsTable.put("int", TerminalType.INT);
        keywordsTable.put("while", TerminalType.WHILE);
        keywordsTable.put("break", TerminalType.BREAK);
        keywordsTable.put("continue", TerminalType.CONTINUE);
        keywordsTable.put("switch", TerminalType.SWITCH);
        keywordsTable.put("default", TerminalType.DEFAULT);
        keywordsTable.put("case", TerminalType.CASE);
        keywordsTable.put("return", TerminalType.RETURN);
    }

    private static void setSymbols() {
        symbols = new ArrayList<>(Arrays.asList
                (";", ":", "[", "]", "(", ")", "{", "}", "+", "-", "+", "==", "*", "=", "<"));
        symbolsTable = new HashMap<>();
        symbolsTable.put(":", TerminalType.COLON);
        symbolsTable.put(";", TerminalType.SEMICOLON);
        symbolsTable.put("[", TerminalType.BL);
        symbolsTable.put("]", TerminalType.BR);
        symbolsTable.put("(", TerminalType.PL);
        symbolsTable.put(")", TerminalType.PR);
        symbolsTable.put("{", TerminalType.KL);
        symbolsTable.put("}", TerminalType.KR);
        symbolsTable.put("+", TerminalType.PLUS);
        symbolsTable.put("-", TerminalType.MINUS);
        symbolsTable.put("==", TerminalType.EQ);
        symbolsTable.put("*", TerminalType.MULTIPLE);
        symbolsTable.put("=", TerminalType.ASSIGN);
        symbolsTable.put("<", TerminalType.LESS);
    }

    private static void setFirstFollow() {
        first = new HashMap<>();
        follow = new HashMap<>();

        first.put(NonTerminalType.P, new ArrayList<>(Arrays.asList(
                TerminalType.EOF,
                TerminalType.INT,
                TerminalType.VOID
        )));
        follow.put(NonTerminalType.P, new ArrayList<>());

        first.put(NonTerminalType.DL, new ArrayList<>(Arrays.asList(
                TerminalType.EPSILON,
                TerminalType.INT,
                TerminalType.VOID
        )));
        follow.put(NonTerminalType.DL, new ArrayList<>(Arrays.asList(
                TerminalType.EOF,
                TerminalType.KL,
                TerminalType.CONTINUE,
                TerminalType.BREAK,
                TerminalType.SEMICOLON,
                TerminalType.IF,
                TerminalType.WHILE,
                TerminalType.RETURN,
                TerminalType.SWITCH,
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM,
                TerminalType.KR
        )));

        first.put(NonTerminalType.D, new ArrayList<>(Arrays.asList(
                TerminalType.INT,
                TerminalType.VOID
        )));
        follow.put(NonTerminalType.D, new ArrayList<>(Arrays.asList(
                TerminalType.EOF,
                TerminalType.INT,
                TerminalType.VOID,
                TerminalType.KL,
                TerminalType.CONTINUE,
                TerminalType.BREAK,
                TerminalType.SEMICOLON,
                TerminalType.IF,
                TerminalType.WHILE,
                TerminalType.RETURN,
                TerminalType.SWITCH,
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM,
                TerminalType.KR
        )));

        first.put(NonTerminalType.TS, new ArrayList<>(Arrays.asList(
                TerminalType.INT,
                TerminalType.VOID
        )));
        follow.put(NonTerminalType.TS, new ArrayList<>(Collections.singletonList(
                TerminalType.ID
        )));

        first.put(NonTerminalType.VDFD, new ArrayList<>(Arrays.asList(
                TerminalType.SEMICOLON,
                TerminalType.BL,
                TerminalType.PL
        )));
        follow.put(NonTerminalType.VDFD, new ArrayList<>(Arrays.asList(
                TerminalType.EOF,
                TerminalType.INT,
                TerminalType.VOID,
                TerminalType.KL,
                TerminalType.CONTINUE,
                TerminalType.BREAK,
                TerminalType.SEMICOLON,
                TerminalType.IF,
                TerminalType.WHILE,
                TerminalType.RETURN,
                TerminalType.SWITCH,
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM,
                TerminalType.KR
        )));

        first.put(NonTerminalType.VD, new ArrayList<>(Arrays.asList(
                TerminalType.SEMICOLON,
                TerminalType.BL
        )));
        follow.put(NonTerminalType.VD, new ArrayList<>(Arrays.asList(
                TerminalType.EOF,
                TerminalType.INT,
                TerminalType.VOID,
                TerminalType.KL,
                TerminalType.CONTINUE,
                TerminalType.BREAK,
                TerminalType.SEMICOLON,
                TerminalType.IF,
                TerminalType.WHILE,
                TerminalType.RETURN,
                TerminalType.SWITCH,
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM,
                TerminalType.KR
        )));

        first.put(NonTerminalType.FD, new ArrayList<>(Collections.singletonList(
                TerminalType.PL
        )));
        follow.put(NonTerminalType.FD, new ArrayList<>(Arrays.asList(
                TerminalType.EOF,
                TerminalType.INT,
                TerminalType.VOID,
                TerminalType.KL,
                TerminalType.CONTINUE,
                TerminalType.BREAK,
                TerminalType.SEMICOLON,
                TerminalType.IF,
                TerminalType.WHILE,
                TerminalType.RETURN,
                TerminalType.SWITCH,
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM,
                TerminalType.KR
        )));

        first.put(NonTerminalType.PARS, new ArrayList<>(Arrays.asList(
                TerminalType.INT,
                TerminalType.VOID
        )));
        follow.put(NonTerminalType.PARS, new ArrayList<>(Collections.singletonList(
                TerminalType.PR
        )));

        first.put(NonTerminalType.VPAR, new ArrayList<>(Arrays.asList(
                TerminalType.ID,
                TerminalType.EPSILON
        )));
        follow.put(NonTerminalType.VPAR, new ArrayList<>(Collections.singletonList(
                TerminalType.PR
        )));

        first.put(NonTerminalType.PL, new ArrayList<>(Arrays.asList(
                TerminalType.COMMA,
                TerminalType.EPSILON
        )));
        follow.put(NonTerminalType.PL, new ArrayList<>(Collections.singletonList(
                TerminalType.PR
        )));

        first.put(NonTerminalType.BRCK, new ArrayList<>(Arrays.asList(
                TerminalType.BL,
                TerminalType.EPSILON
        )));
        follow.put(NonTerminalType.BRCK, new ArrayList<>(Arrays.asList(
                TerminalType.PR,
                TerminalType.COMMA
        )));

        first.put(NonTerminalType.CS, new ArrayList<>(Collections.singletonList(
                TerminalType.KL
        )));
        follow.put(NonTerminalType.CS, new ArrayList<>(Arrays.asList(
                TerminalType.EOF,
                TerminalType.INT,
                TerminalType.VOID,
                TerminalType.KL,
                TerminalType.CONTINUE,
                TerminalType.BREAK,
                TerminalType.SEMICOLON,
                TerminalType.IF,
                TerminalType.WHILE,
                TerminalType.RETURN,
                TerminalType.SWITCH,
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM,
                TerminalType.KR,
                TerminalType.ELSE,
                TerminalType.CASE,
                TerminalType.DEFAULT
        )));

        first.put(NonTerminalType.SL, new ArrayList<>(Arrays.asList(
                TerminalType.EPSILON,
                TerminalType.KL,
                TerminalType.CONTINUE,
                TerminalType.BREAK,
                TerminalType.SEMICOLON,
                TerminalType.IF,
                TerminalType.WHILE,
                TerminalType.RETURN,
                TerminalType.SWITCH,
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM
        )));
        follow.put(NonTerminalType.SL, new ArrayList<>(Arrays.asList(
                TerminalType.KR,
                TerminalType.CASE,
                TerminalType.DEFAULT
        )));

        first.put(NonTerminalType.S, new ArrayList<>(Arrays.asList(
                TerminalType.KL,
                TerminalType.CONTINUE,
                TerminalType.BREAK,
                TerminalType.SEMICOLON,
                TerminalType.IF,
                TerminalType.WHILE,
                TerminalType.RETURN,
                TerminalType.SWITCH,
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM
        )));
        follow.put(NonTerminalType.S, new ArrayList<>(Arrays.asList(
                TerminalType.KL,
                TerminalType.CONTINUE,
                TerminalType.BREAK,
                TerminalType.SEMICOLON,
                TerminalType.IF,
                TerminalType.WHILE,
                TerminalType.RETURN,
                TerminalType.SWITCH,
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM,
                TerminalType.KR,
                TerminalType.ELSE,
                TerminalType.CASE,
                TerminalType.DEFAULT
        )));

        first.put(NonTerminalType.ES, new ArrayList<>(Arrays.asList(
                TerminalType.CONTINUE,
                TerminalType.BREAK,
                TerminalType.SEMICOLON,
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM
        )));
        follow.put(NonTerminalType.ES, new ArrayList<>(Arrays.asList(
                TerminalType.KL,
                TerminalType.CONTINUE,
                TerminalType.BREAK,
                TerminalType.SEMICOLON,
                TerminalType.IF,
                TerminalType.WHILE,
                TerminalType.RETURN,
                TerminalType.SWITCH,
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM,
                TerminalType.KR,
                TerminalType.ELSE,
                TerminalType.CASE,
                TerminalType.DEFAULT
        )));

        first.put(NonTerminalType.SS, new ArrayList<>(Collections.singletonList(
                TerminalType.IF
        )));
        follow.put(NonTerminalType.SS, new ArrayList<>(Arrays.asList(
                TerminalType.KL,
                TerminalType.CONTINUE,
                TerminalType.BREAK,
                TerminalType.SEMICOLON,
                TerminalType.IF,
                TerminalType.WHILE,
                TerminalType.RETURN,
                TerminalType.SWITCH,
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM,
                TerminalType.KR,
                TerminalType.ELSE,
                TerminalType.CASE,
                TerminalType.DEFAULT
        )));

        first.put(NonTerminalType.IS, new ArrayList<>(Collections.singletonList(
                TerminalType.WHILE
        )));
        follow.put(NonTerminalType.IS, new ArrayList<>(Arrays.asList(
                TerminalType.KL,
                TerminalType.CONTINUE,
                TerminalType.BREAK,
                TerminalType.SEMICOLON,
                TerminalType.IF,
                TerminalType.WHILE,
                TerminalType.RETURN,
                TerminalType.SWITCH,
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM,
                TerminalType.KR,
                TerminalType.ELSE,
                TerminalType.CASE,
                TerminalType.DEFAULT
        )));

        first.put(NonTerminalType.RS, new ArrayList<>(Collections.singletonList(
                TerminalType.RETURN
        )));
        follow.put(NonTerminalType.RS, new ArrayList<>(Arrays.asList(
                TerminalType.KL,
                TerminalType.CONTINUE,
                TerminalType.BREAK,
                TerminalType.SEMICOLON,
                TerminalType.IF,
                TerminalType.WHILE,
                TerminalType.RETURN,
                TerminalType.SWITCH,
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM,
                TerminalType.KR,
                TerminalType.ELSE,
                TerminalType.CASE,
                TerminalType.DEFAULT
        )));

        first.put(NonTerminalType.RVAL, new ArrayList<>(Arrays.asList(
                TerminalType.SEMICOLON,
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM
        )));
        follow.put(NonTerminalType.RVAL, new ArrayList<>(Arrays.asList(
                TerminalType.KL,
                TerminalType.CONTINUE,
                TerminalType.BREAK,
                TerminalType.SEMICOLON,
                TerminalType.IF,
                TerminalType.WHILE,
                TerminalType.RETURN,
                TerminalType.SWITCH,
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM,
                TerminalType.KR,
                TerminalType.ELSE,
                TerminalType.CASE,
                TerminalType.DEFAULT
        )));

        first.put(NonTerminalType.SWS, new ArrayList<>(Collections.singletonList(
                TerminalType.SWITCH
        )));
        follow.put(NonTerminalType.SWS, new ArrayList<>(Arrays.asList(
                TerminalType.KL,
                TerminalType.CONTINUE,
                TerminalType.BREAK,
                TerminalType.SEMICOLON,
                TerminalType.IF,
                TerminalType.WHILE,
                TerminalType.RETURN,
                TerminalType.SWITCH,
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM,
                TerminalType.KR,
                TerminalType.ELSE,
                TerminalType.CASE,
                TerminalType.DEFAULT
        )));

        first.put(NonTerminalType.CASS, new ArrayList<>(Arrays.asList(
                TerminalType.CASE,
                TerminalType.EPSILON
        )));
        follow.put(NonTerminalType.CASS, new ArrayList<>(Arrays.asList(
                TerminalType.DEFAULT,
                TerminalType.KR
        )));

        first.put(NonTerminalType.DS, new ArrayList<>(Arrays.asList(
                TerminalType.DEFAULT,
                TerminalType.EPSILON
        )));
        follow.put(NonTerminalType.DS, new ArrayList<>(Collections.singletonList(
                TerminalType.KR
        )));

        first.put(NonTerminalType.E, new ArrayList<>(Arrays.asList(
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM
        )));
        follow.put(NonTerminalType.E, new ArrayList<>(Arrays.asList(
                TerminalType.SEMICOLON,
                TerminalType.PR,
                TerminalType.BR,
                TerminalType.COMMA
        )));

        first.put(NonTerminalType.EID, new ArrayList<>(Arrays.asList(
                TerminalType.ASSIGN,
                TerminalType.BL,
                TerminalType.PL,
                TerminalType.MULTIPLE,
                TerminalType.EPSILON,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.LESS,
                TerminalType.EQ
        )));
        follow.put(NonTerminalType.EID, new ArrayList<>(Arrays.asList(
                TerminalType.SEMICOLON,
                TerminalType.PR,
                TerminalType.BR,
                TerminalType.COMMA
        )));

        first.put(NonTerminalType.EID1, new ArrayList<>(Arrays.asList(
                TerminalType.ASSIGN,
                TerminalType.PL,
                TerminalType.MULTIPLE,
                TerminalType.EPSILON,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.LESS,
                TerminalType.EQ
        )));
        follow.put(NonTerminalType.EID1, new ArrayList<>(Arrays.asList(
                TerminalType.SEMICOLON,
                TerminalType.PR,
                TerminalType.BR,
                TerminalType.COMMA
        )));

        first.put(NonTerminalType.SE1, new ArrayList<>(Arrays.asList(
                TerminalType.EPSILON,
                TerminalType.LESS,
                TerminalType.EQ
        )));
        follow.put(NonTerminalType.SE1, new ArrayList<>(Arrays.asList(
                TerminalType.SEMICOLON,
                TerminalType.PR,
                TerminalType.BR,
                TerminalType.COMMA
        )));

        first.put(NonTerminalType.AE, new ArrayList<>(Arrays.asList(
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM
        )));
        follow.put(NonTerminalType.AE, new ArrayList<>(Arrays.asList(
                TerminalType.SEMICOLON,
                TerminalType.PR,
                TerminalType.BR,
                TerminalType.COMMA
        )));

        first.put(NonTerminalType.AE1, new ArrayList<>(Arrays.asList(
                TerminalType.EPSILON,
                TerminalType.PLUS,
                TerminalType.MINUS
        )));
        follow.put(NonTerminalType.AE1, new ArrayList<>(Arrays.asList(
                TerminalType.LESS,
                TerminalType.EQ,
                TerminalType.SEMICOLON,
                TerminalType.PR,
                TerminalType.BR,
                TerminalType.COMMA
        )));

        first.put(NonTerminalType.A, new ArrayList<>(Arrays.asList(
                TerminalType.PLUS,
                TerminalType.MINUS
        )));
        follow.put(NonTerminalType.A, new ArrayList<>(Arrays.asList(
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM
        )));

        first.put(NonTerminalType.R, new ArrayList<>(Arrays.asList(
                TerminalType.LESS,
                TerminalType.EQ
        )));
        follow.put(NonTerminalType.R, new ArrayList<>(Arrays.asList(
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM
        )));

        first.put(NonTerminalType.T, new ArrayList<>(Arrays.asList(
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM
        )));
        follow.put(NonTerminalType.T, new ArrayList<>(Arrays.asList(
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.SEMICOLON,
                TerminalType.PR,
                TerminalType.LESS,
                TerminalType.EQ,
                TerminalType.BR,
                TerminalType.COMMA
        )));

        first.put(NonTerminalType.T1, new ArrayList<>(Arrays.asList(
                TerminalType.MULTIPLE,
                TerminalType.EPSILON
        )));
        follow.put(NonTerminalType.T1, new ArrayList<>(Arrays.asList(
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.LESS,
                TerminalType.EQ,
                TerminalType.SEMICOLON,
                TerminalType.PR,
                TerminalType.BR,
                TerminalType.COMMA
        )));

        first.put(NonTerminalType.SF, new ArrayList<>(Arrays.asList(
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM
        )));
        follow.put(NonTerminalType.SF, new ArrayList<>(Arrays.asList(
                TerminalType.MULTIPLE,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.LESS,
                TerminalType.EQ,
                TerminalType.SEMICOLON,
                TerminalType.PR,
                TerminalType.BR,
                TerminalType.COMMA
        )));

        first.put(NonTerminalType.SF1, new ArrayList<>(Arrays.asList(
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM
        )));
        follow.put(NonTerminalType.SF1, new ArrayList<>(Arrays.asList(
                TerminalType.MULTIPLE,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.LESS,
                TerminalType.EQ,
                TerminalType.SEMICOLON,
                TerminalType.PR,
                TerminalType.BR,
                TerminalType.COMMA
        )));

        first.put(NonTerminalType.F, new ArrayList<>(Arrays.asList(
                TerminalType.PL,
                TerminalType.NUM,
                TerminalType.ID
        )));
        follow.put(NonTerminalType.F, new ArrayList<>(Arrays.asList(
                TerminalType.MULTIPLE,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.LESS,
                TerminalType.EQ,
                TerminalType.SEMICOLON,
                TerminalType.PR,
                TerminalType.BR,
                TerminalType.COMMA
        )));

        first.put(NonTerminalType.F1, new ArrayList<>(Arrays.asList(
                TerminalType.PL,
                TerminalType.NUM
        )));
        follow.put(NonTerminalType.F1, new ArrayList<>(Arrays.asList(
                TerminalType.MULTIPLE,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.LESS,
                TerminalType.EQ,
                TerminalType.SEMICOLON,
                TerminalType.PR,
                TerminalType.BR,
                TerminalType.COMMA
        )));

        first.put(NonTerminalType.VC, new ArrayList<>(Collections.singletonList(
                TerminalType.ID
        )));
        follow.put(NonTerminalType.VC, new ArrayList<>(Arrays.asList(
                TerminalType.MULTIPLE,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.LESS,
                TerminalType.EQ,
                TerminalType.SEMICOLON,
                TerminalType.PR,
                TerminalType.BR,
                TerminalType.COMMA
        )));

        first.put(NonTerminalType.VC1, new ArrayList<>(Arrays.asList(
                TerminalType.BL,
                TerminalType.PL,
                TerminalType.EPSILON
        )));
        follow.put(NonTerminalType.VC1, new ArrayList<>(Arrays.asList(
                TerminalType.MULTIPLE,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.LESS,
                TerminalType.EQ,
                TerminalType.SEMICOLON,
                TerminalType.PR,
                TerminalType.BR,
                TerminalType.COMMA
        )));

        first.put(NonTerminalType.VC2, new ArrayList<>(Arrays.asList(
                TerminalType.PL,
                TerminalType.EPSILON
        )));
        follow.put(NonTerminalType.VC2, new ArrayList<>(Arrays.asList(
                TerminalType.MULTIPLE,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.LESS,
                TerminalType.EQ,
                TerminalType.SEMICOLON,
                TerminalType.PR,
                TerminalType.BR,
                TerminalType.COMMA
        )));

        first.put(NonTerminalType.ARGS, new ArrayList<>(Arrays.asList(
                TerminalType.EPSILON,
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM
        )));
        follow.put(NonTerminalType.ARGS, new ArrayList<>(Collections.singletonList(
                TerminalType.PR
        )));

        first.put(NonTerminalType.ARL, new ArrayList<>(Arrays.asList(
                TerminalType.ID,
                TerminalType.PLUS,
                TerminalType.MINUS,
                TerminalType.PL,
                TerminalType.NUM
        )));
        follow.put(NonTerminalType.ARL, new ArrayList<>(Collections.singletonList(
                TerminalType.PR
        )));

        first.put(NonTerminalType.ARL1, new ArrayList<>(Arrays.asList(
                TerminalType.COMMA,
                TerminalType.EPSILON
        )));
        follow.put(NonTerminalType.ARL1, new ArrayList<>(Collections.singletonList(
                TerminalType.PR
        )));
    }

//    private static void setTable() {
//        table = new HashMap<>();
//        // Program
//        addSameConvertToTable(NonTerminalType.Program,
//                new TerminalType[]{
//                        TerminalType.VOID,
//                        TerminalType.INT,
//                        TerminalType.EOF,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Declarationlist),
//                        addParseToken(TerminalType.EOF),
//                });
//
//        // Declarationlist
//        addSameConvertToTable(NonTerminalType.Declarationlist,
//                new TerminalType[]{
//                        TerminalType.PL,
//                        TerminalType.ID,
//                        TerminalType.NUM,
//                        TerminalType.MINUS,
//                        TerminalType.PLUS,
//                        TerminalType.KR,
//                        TerminalType.KL,
//                        TerminalType.SWITCH,
//                        TerminalType.SEMICOLON,
//                        TerminalType.RETURN,
//                        TerminalType.WHILE,
//                        TerminalType.IF,
//                        TerminalType.CONTINUE,
//                        TerminalType.BREAK,
//                        TerminalType.EOF,
//                },
//                new ParseToken[]{
//                });
//        addSameConvertToTable(NonTerminalType.Declarationlist,
//                new TerminalType[]{
//                        TerminalType.VOID,
//                        TerminalType.INT,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Declaration),
//                        addParseToken(NonTerminalType.Declarationlist),
//                });
//
//        // E1
//        addConvertToTable(NonTerminalType.E1,
//                TerminalType.BL,
//                new ParseToken[]{
//                        addParseToken(TerminalType.BL),
//                        addParseToken(TerminalType.NUM),
//                        addParseToken(TerminalType.BR),
//                        addParseToken(TerminalType.SEMICOLON),
//                });
//        addConvertToTable(NonTerminalType.E1,
//                TerminalType.SEMICOLON,
//                new ParseToken[]{
//                        addParseToken(TerminalType.SEMICOLON),
//                });
//
//        // E2
//        addSameConvertToTable(NonTerminalType.E2,
//                new TerminalType[]{
//                        TerminalType.PR,
//                        TerminalType.COMMA,
//                },
//                new ParseToken[]{
//                });
//        addConvertToTable(NonTerminalType.E2,
//                TerminalType.BL,
//                new ParseToken[]{
//                        addParseToken(TerminalType.BL),
//                        addParseToken(TerminalType.BR),
//                });
//
//        // Compoundstmt
//        addConvertToTable(NonTerminalType.Compoundstmt,
//                TerminalType.KL,
//                new ParseToken[]{
//                        addParseToken(TerminalType.KL),
//                        addParseToken(NonTerminalType.Declarationlist),
//                        addParseToken(NonTerminalType.Statementlist),
//                        addParseToken(TerminalType.KR),
//                });
//
//        // Statementlist
//        addSameConvertToTable(NonTerminalType.Statementlist,
//                new TerminalType[]{
//                        TerminalType.PL,
//                        TerminalType.ID,
//                        TerminalType.NUM,
//                        TerminalType.MINUS,
//                        TerminalType.PLUS,
//                        TerminalType.KL,
//                        TerminalType.SWITCH,
//                        TerminalType.SEMICOLON,
//                        TerminalType.RETURN,
//                        TerminalType.WHILE,
//                        TerminalType.IF,
//                        TerminalType.CONTINUE,
//                        TerminalType.BREAK,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Statement),
//                        addParseToken(NonTerminalType.Statementlist),
//                });
//        addSameConvertToTable(NonTerminalType.Statementlist,
//                new TerminalType[]{
//                        TerminalType.DEFAULT,
//                        TerminalType.CASE,
//                        TerminalType.KR,
//                },
//                new ParseToken[]{
//                });
//
//        // Statement
//        addSameConvertToTable(NonTerminalType.Statement,
//                new TerminalType[]{
//                        TerminalType.PL,
//                        TerminalType.ID,
//                        TerminalType.NUM,
//                        TerminalType.MINUS,
//                        TerminalType.PLUS,
//                        TerminalType.SEMICOLON,
//                        TerminalType.CONTINUE,
//                        TerminalType.BREAK,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Expressionstmt),
//                });
//        addConvertToTable(NonTerminalType.Statement,
//                TerminalType.KL,
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Compoundstmt),
//                });
//        addConvertToTable(NonTerminalType.Statement,
//                TerminalType.SWITCH,
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Switchstmt),
//                });
//        addConvertToTable(NonTerminalType.Statement,
//                TerminalType.RETURN,
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Returnstmt),
//                });
//        addConvertToTable(NonTerminalType.Statement,
//                TerminalType.WHILE,
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Iterationstmt),
//                });
//        addConvertToTable(NonTerminalType.Statement,
//                TerminalType.IF,
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Selectionstmt),
//                });
//
//        // Expressionstmt
//        addSameConvertToTable(NonTerminalType.Expressionstmt,
//                new TerminalType[]{
//                        TerminalType.PL,
//                        TerminalType.ID,
//                        TerminalType.NUM,
//                        TerminalType.MINUS,
//                        TerminalType.PLUS,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Expression),
//                        addParseToken(TerminalType.SEMICOLON),
//                });
//        addConvertToTable(NonTerminalType.Expressionstmt,
//                TerminalType.SEMICOLON,
//                new ParseToken[]{
//                        addParseToken(TerminalType.SEMICOLON),
//                });
//        addConvertToTable(NonTerminalType.Expressionstmt,
//                TerminalType.CONTINUE,
//                new ParseToken[]{
//                        addParseToken(TerminalType.CONTINUE),
//                        addParseToken(TerminalType.SWITCH),
//                });
//        addConvertToTable(NonTerminalType.Expressionstmt,
//                TerminalType.BREAK,
//                new ParseToken[]{
//                        addParseToken(TerminalType.BREAK),
//                        addParseToken(TerminalType.SEMICOLON),
//                });
//
//        // Selectionstmt
//        addConvertToTable(NonTerminalType.Selectionstmt,
//                TerminalType.IF,
//                new ParseToken[]{
//                        addParseToken(TerminalType.IF),
//                        addParseToken(TerminalType.PL),
//                        addParseToken(NonTerminalType.Expression),
//                        addParseToken(TerminalType.PR),
//                        addParseToken(NonTerminalType.Statement),
//                        addParseToken(TerminalType.ELSE),
//                        addParseToken(NonTerminalType.Statement),
//                });
//
//        // Iterationstmt
//        addConvertToTable(NonTerminalType.Iterationstmt,
//                TerminalType.WHILE,
//                new ParseToken[]{
//                        addParseToken(TerminalType.WHILE),
//                        addParseToken(TerminalType.PL),
//                        addParseToken(NonTerminalType.Expression),
//                        addParseToken(TerminalType.PR),
//                        addParseToken(NonTerminalType.Statement),
//                });
//
//        // Returnstmt
//        addConvertToTable(NonTerminalType.Returnstmt,
//                TerminalType.RETURN,
//                new ParseToken[]{
//                        addParseToken(TerminalType.RETURN),
//                        addParseToken(NonTerminalType.E3),
//                });
//
//        // E3
//        addSameConvertToTable(NonTerminalType.E3,
//                new TerminalType[]{
//                        TerminalType.PL,
//                        TerminalType.ID,
//                        TerminalType.NUM,
//                        TerminalType.MINUS,
//                        TerminalType.PLUS,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Expression),
//                        addParseToken(TerminalType.SEMICOLON),
//                });
//        addConvertToTable(NonTerminalType.E3,
//                TerminalType.SEMICOLON,
//                new ParseToken[]{
//                        addParseToken(TerminalType.SEMICOLON),
//                });
//
//        // Switchstmt
//        addConvertToTable(NonTerminalType.Switchstmt,
//                TerminalType.SWITCH,
//                new ParseToken[]{
//                        addParseToken(TerminalType.SWITCH),
//                        addParseToken(TerminalType.PL),
//                        addParseToken(NonTerminalType.Expression),
//                        addParseToken(TerminalType.PR),
//                        addParseToken(TerminalType.KL),
//                        addParseToken(NonTerminalType.Casestmts),
//                        addParseToken(NonTerminalType.Defaultstmt),
//                        addParseToken(TerminalType.KR),
//                });
//
//        // Casestmts
//        addSameConvertToTable(NonTerminalType.Casestmts,
//                new TerminalType[]{
//                        TerminalType.DEFAULT,
//                        TerminalType.KR,
//                },
//                new ParseToken[]{
//                });
//        addConvertToTable(NonTerminalType.Casestmts,
//                TerminalType.CASE,
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Casestmt),
//                        addParseToken(NonTerminalType.Casestmts),
//                });
//
//        // Casestmt
//        addConvertToTable(NonTerminalType.Casestmt,
//                TerminalType.CASE,
//                new ParseToken[]{
//                        addParseToken(TerminalType.CASE),
//                        addParseToken(TerminalType.NUM),
//                        addParseToken(TerminalType.COLON),
//                        addParseToken(NonTerminalType.Statementlist),
//                });
//
//        // Defaultstmt
//        addConvertToTable(NonTerminalType.Defaultstmt,
//                TerminalType.DEFAULT,
//                new ParseToken[]{
//                        addParseToken(TerminalType.DEFAULT),
//                        addParseToken(TerminalType.COLON),
//                        addParseToken(NonTerminalType.Statementlist),
//                });
//        addConvertToTable(NonTerminalType.Defaultstmt,
//                TerminalType.KR,
//                new ParseToken[]{
//                });
//
//        // Expression
//        addSameConvertToTable(NonTerminalType.Expression,
//                new TerminalType[]{
//                        TerminalType.PL,
//                        TerminalType.NUM,
//                        TerminalType.MINUS,
//                        TerminalType.PLUS,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.NewSimpleexpression),
//                });
//        addConvertToTable(NonTerminalType.Expression,
//                TerminalType.ID,
//                new ParseToken[]{
//                        addParseToken(TerminalType.ID),
//                        addParseToken(NonTerminalType.EXEXEX),
//                });
//
//        // EXEXEX
//        addSameConvertToTable(NonTerminalType.EXEXEX,
//                new TerminalType[]{
//                        TerminalType.PR,
//                        TerminalType.PL,
//                        TerminalType.COMMA,
//                        TerminalType.BR,
//                        TerminalType.MINUS,
//                        TerminalType.PLUS,
//                        TerminalType.MULTIPLE,
//                        TerminalType.LESS,
//                        TerminalType.EQ,
//                        TerminalType.SEMICOLON,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.NewVarcall),
//                        addParseToken(NonTerminalType.F3),
//                        addParseToken(NonTerminalType.F2),
//                        addParseToken(NonTerminalType.E5),
//                });
//        addConvertToTable(NonTerminalType.EXEXEX,
//                TerminalType.BL,
//                new ParseToken[]{
//                        addParseToken(TerminalType.BL),
//                        addParseToken(NonTerminalType.Expression),
//                        addParseToken(TerminalType.BR),
//                        addParseToken(NonTerminalType.EZEZEZ),
//                });
//        addConvertToTable(NonTerminalType.EXEXEX,
//                TerminalType.ASSIGN,
//                new ParseToken[]{
//                        addParseToken(TerminalType.ASSIGN),
//                        addParseToken(NonTerminalType.Expression),
//                });
//
//        // EZEZEZ
//        addSameConvertToTable(NonTerminalType.EZEZEZ,
//                new TerminalType[]{
//                        TerminalType.PR,
//                        TerminalType.COMMA,
//                        TerminalType.BR,
//                        TerminalType.MINUS,
//                        TerminalType.PLUS,
//                        TerminalType.MULTIPLE,
//                        TerminalType.LESS,
//                        TerminalType.EQ,
//                        TerminalType.SEMICOLON,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.F3),
//                        addParseToken(NonTerminalType.F2),
//                        addParseToken(NonTerminalType.E5),
//                });
//        addConvertToTable(NonTerminalType.EZEZEZ,
//                TerminalType.BL,
//                new ParseToken[]{
//                        addParseToken(TerminalType.BL),
//                        addParseToken(NonTerminalType.Expression),
//                        addParseToken(TerminalType.BR),
//                });
//
//        // NewSimpleexpression
//        addSameConvertToTable(NonTerminalType.NewSimpleexpression,
//                new TerminalType[]{
//                        TerminalType.PL,
//                        TerminalType.NUM,
//                        TerminalType.MINUS,
//                        TerminalType.PLUS,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.NewAdditiveexpression),
//                        addParseToken(NonTerminalType.E5),
//                });
//
//        // E5
//        addSameConvertToTable(NonTerminalType.E5,
//                new TerminalType[]{
//                        TerminalType.PR,
//                        TerminalType.COMMA,
//                        TerminalType.BR,
//                        TerminalType.SEMICOLON,
//                },
//                new ParseToken[]{
//                });
//        addSameConvertToTable(NonTerminalType.E5,
//                new TerminalType[]{
//                        TerminalType.LESS,
//                        TerminalType.EQ,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Relop),
//                        addParseToken(NonTerminalType.Additiveexpression),
//                });
//
//        // Relop
//        addConvertToTable(NonTerminalType.Relop,
//                TerminalType.LESS,
//                new ParseToken[]{
//                        addParseToken(TerminalType.LESS),
//                });
//        addConvertToTable(NonTerminalType.Relop,
//                TerminalType.EQ,
//                new ParseToken[]{
//                        addParseToken(TerminalType.EQ),
//                });
//
//        // Additiveexpression
//        addSameConvertToTable(NonTerminalType.Additiveexpression,
//                new TerminalType[]{
//                        TerminalType.PL,
//                        TerminalType.NUM,
//                        TerminalType.MINUS,
//                        TerminalType.PLUS,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.NewTerm),
//                        addParseToken(NonTerminalType.F2),
//                });
//        addConvertToTable(NonTerminalType.Additiveexpression,
//                TerminalType.ID,
//                new ParseToken[]{
//                        addParseToken(TerminalType.ID),
//                        addParseToken(NonTerminalType.Varcall),
//                        addParseToken(NonTerminalType.F3),
//                        addParseToken(NonTerminalType.F2),
//                });
//
//        // NewAdditiveexpression
//        addSameConvertToTable(NonTerminalType.NewAdditiveexpression,
//                new TerminalType[]{
//                        TerminalType.PL,
//                        TerminalType.NUM,
//                        TerminalType.MINUS,
//                        TerminalType.PLUS,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.NewTerm),
//                        addParseToken(NonTerminalType.F2),
//                });
//
//        // F2
//        addSameConvertToTable(NonTerminalType.F2,
//                new TerminalType[]{
//                        TerminalType.PR,
//                        TerminalType.COMMA,
//                        TerminalType.BR,
//                        TerminalType.LESS,
//                        TerminalType.EQ,
//                        TerminalType.SEMICOLON,
//                },
//                new ParseToken[]{
//                });
//        addSameConvertToTable(NonTerminalType.F2,
//                new TerminalType[]{
//                        TerminalType.MINUS,
//                        TerminalType.PLUS,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Term),
//                        addParseToken(NonTerminalType.F2),
//                });
//
//        // Addop
//        addConvertToTable(NonTerminalType.Addop,
//                TerminalType.MINUS,
//                new ParseToken[]{
//                        addParseToken(TerminalType.MINUS),
//                });
//        addConvertToTable(NonTerminalType.Addop,
//                TerminalType.PLUS,
//                new ParseToken[]{
//                        addParseToken(TerminalType.PLUS),
//                });
//
//        // Term
//        addSameConvertToTable(NonTerminalType.Term,
//                new TerminalType[]{
//                        TerminalType.PL,
//                        TerminalType.NUM,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.NewFactor),
//                        addParseToken(NonTerminalType.F3),
//                });
//        addSameConvertToTable(NonTerminalType.Term,
//                new TerminalType[]{
//                        TerminalType.MINUS,
//                        TerminalType.PLUS,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.NewSignedfactor),
//                        addParseToken(NonTerminalType.F3),
//                });
//        addConvertToTable(NonTerminalType.Term,
//                TerminalType.ID,
//                new ParseToken[]{
//                        addParseToken(TerminalType.ID),
//                        addParseToken(NonTerminalType.Varcall),
//                        addParseToken(NonTerminalType.F3),
//                });
//
//        // NewTerm
//        addSameConvertToTable(NonTerminalType.NewTerm,
//                new TerminalType[]{
//                        TerminalType.PL,
//                        TerminalType.NUM,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.NewFactor),
//                        addParseToken(NonTerminalType.F3),
//                });
//        addSameConvertToTable(NonTerminalType.NewTerm,
//                new TerminalType[]{
//                        TerminalType.MINUS,
//                        TerminalType.PLUS,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.NewSignedfactor),
//                        addParseToken(NonTerminalType.F3),
//                });
//
//        // F3
//        addSameConvertToTable(NonTerminalType.F3,
//                new TerminalType[]{
//                        TerminalType.PR,
//                        TerminalType.COMMA,
//                        TerminalType.BR,
//                        TerminalType.MINUS,
//                        TerminalType.PLUS,
//                        TerminalType.LESS,
//                        TerminalType.EQ,
//                        TerminalType.SEMICOLON,
//                },
//                new ParseToken[]{
//                });
//        addConvertToTable(NonTerminalType.F3,
//                TerminalType.MULTIPLE,
//                new ParseToken[]{
//                        addParseToken(TerminalType.MULTIPLE),
//                        addParseToken(NonTerminalType.Signedfactor),
//                        addParseToken(NonTerminalType.F3),
//                });
//
//        // NewSignedfactor
//        addConvertToTable(NonTerminalType.NewSignedfactor,
//                TerminalType.MINUS,
//                new ParseToken[]{
//                        addParseToken(TerminalType.MINUS),
//                        addParseToken(NonTerminalType.Factor),
//                });
//        addConvertToTable(NonTerminalType.NewSignedfactor,
//                TerminalType.PLUS,
//                new ParseToken[]{
//                        addParseToken(TerminalType.PLUS),
//                        addParseToken(NonTerminalType.Factor),
//                });
//
//        // Signedfactor
//        addSameConvertToTable(NonTerminalType.Signedfactor,
//                new TerminalType[]{
//                        TerminalType.PL,
//                        TerminalType.ID,
//                        TerminalType.NUM,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Factor),
//                });
//        addConvertToTable(NonTerminalType.Signedfactor,
//                TerminalType.MINUS,
//                new ParseToken[]{
//                        addParseToken(TerminalType.MINUS),
//                        addParseToken(NonTerminalType.Factor),
//                });
//        addConvertToTable(NonTerminalType.Signedfactor,
//                TerminalType.PLUS,
//                new ParseToken[]{
//                        addParseToken(TerminalType.PLUS),
//                        addParseToken(NonTerminalType.Factor),
//                });
//
//        // Args
//        addSameConvertToTable(NonTerminalType.Args,
//                new TerminalType[]{
//                        TerminalType.PL,
//                        TerminalType.ID,
//                        TerminalType.NUM,
//                        TerminalType.MINUS,
//                        TerminalType.PLUS,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Arglist),
//                });
//        addConvertToTable(NonTerminalType.Args,
//                TerminalType.PR,
//                new ParseToken[]{
//                });
//
//        // Arglist
//        addSameConvertToTable(NonTerminalType.Arglist,
//                new TerminalType[]{
//                        TerminalType.PL,
//                        TerminalType.ID,
//                        TerminalType.NUM,
//                        TerminalType.MINUS,
//                        TerminalType.PLUS,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Expression),
//                        addParseToken(NonTerminalType.F4),
//                });
//
//        // F4
//        addConvertToTable(NonTerminalType.F4,
//                TerminalType.PR,
//                new ParseToken[]{
//                });
//        addConvertToTable(NonTerminalType.F4,
//                TerminalType.COMMA,
//                new ParseToken[]{
//                        addParseToken(TerminalType.COMMA),
//                        addParseToken(NonTerminalType.Expression),
//                });
//
//        // NewFactor
//        addConvertToTable(NonTerminalType.NewFactor,
//                TerminalType.PL,
//                new ParseToken[]{
//                        addParseToken(TerminalType.PL),
//                        addParseToken(NonTerminalType.Expression),
//                        addParseToken(TerminalType.PR),
//                });
//        addConvertToTable(NonTerminalType.NewFactor,
//                TerminalType.NUM,
//                new ParseToken[]{
//                        addParseToken(TerminalType.NUM),
//                });
//
//        // Factor
//        addConvertToTable(NonTerminalType.Factor,
//                TerminalType.PL,
//                new ParseToken[]{
//                        addParseToken(TerminalType.PL),
//                        addParseToken(NonTerminalType.Expression),
//                        addParseToken(TerminalType.PR),
//                });
//        addConvertToTable(NonTerminalType.Factor,
//                TerminalType.NUM,
//                new ParseToken[]{
//                        addParseToken(TerminalType.NUM),
//                });
//        addConvertToTable(NonTerminalType.Factor,
//                TerminalType.ID,
//                new ParseToken[]{
//                        addParseToken(TerminalType.ID),
//                        addParseToken(NonTerminalType.Varcall),
//                });
//
//        // NewVarcall
//        addSameConvertToTable(NonTerminalType.NewVarcall,
//                new TerminalType[]{
//                        TerminalType.PR,
//                        TerminalType.COMMA,
//                        TerminalType.BR,
//                        TerminalType.MINUS,
//                        TerminalType.PLUS,
//                        TerminalType.MULTIPLE,
//                        TerminalType.LESS,
//                        TerminalType.EQ,
//                        TerminalType.SEMICOLON,
//                },
//                new ParseToken[]{
//                });
//        addConvertToTable(NonTerminalType.NewVarcall,
//                TerminalType.PL,
//                new ParseToken[]{
//                        addParseToken(TerminalType.PL),
//                        addParseToken(NonTerminalType.Args),
//                        addParseToken(TerminalType.PR),
//                });
//
//        // Varcall
//        addSameConvertToTable(NonTerminalType.Varcall,
//                new TerminalType[]{
//                        TerminalType.PR,
//                        TerminalType.COMMA,
//                        TerminalType.BR,
//                        TerminalType.MINUS,
//                        TerminalType.PLUS,
//                        TerminalType.MULTIPLE,
//                        TerminalType.LESS,
//                        TerminalType.EQ,
//                        TerminalType.SEMICOLON,
//                },
//                new ParseToken[]{
//                });
//        addConvertToTable(NonTerminalType.Varcall,
//                TerminalType.PL,
//                new ParseToken[]{
//                        addParseToken(TerminalType.PL),
//                        addParseToken(NonTerminalType.Args),
//                        addParseToken(TerminalType.PR),
//                });
//        addConvertToTable(NonTerminalType.Varcall,
//                TerminalType.BL,
//                new ParseToken[]{
//                        addParseToken(TerminalType.BL),
//                        addParseToken(NonTerminalType.Expression),
//                        addParseToken(TerminalType.BR),
//                });
//
//        // Typespecifier
//        addConvertToTable(NonTerminalType.Typespecifier,
//                TerminalType.VOID,
//                new ParseToken[]{
//                        addParseToken(TerminalType.VOID),
//                });
//        addConvertToTable(NonTerminalType.Typespecifier,
//                TerminalType.INT,
//                new ParseToken[]{
//                        addParseToken(TerminalType.INT),
//                });
//
//        // Params
//        addConvertToTable(NonTerminalType.Params,
//                TerminalType.VOID,
//                new ParseToken[]{
//                        addParseToken(TerminalType.VOID),
//                        addParseToken(NonTerminalType.BB),
//                });
//        addConvertToTable(NonTerminalType.Params,
//                TerminalType.INT,
//                new ParseToken[]{
//                        addParseToken(TerminalType.INT),
//                        addParseToken(TerminalType.ID),
//                        addParseToken(NonTerminalType.Paramlist),
//                });
//
//        // BB
//        addConvertToTable(NonTerminalType.BB,
//                TerminalType.PR,
//                new ParseToken[]{
//                });
//        addConvertToTable(NonTerminalType.BB,
//                TerminalType.ID,
//                new ParseToken[]{
//                        addParseToken(TerminalType.ID),
//                        addParseToken(NonTerminalType.Paramlist),
//                });
//
//        // Paramlist
//        addSameConvertToTable(NonTerminalType.Paramlist,
//                new TerminalType[]{
//                        TerminalType.PR,
//                        TerminalType.COMMA,
//                        TerminalType.BL,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.E2),
//                        addParseToken(NonTerminalType.F1),
//                });
//
//        // F1
//        addConvertToTable(NonTerminalType.F1,
//                TerminalType.PR,
//                new ParseToken[]{
//                });
//        addConvertToTable(NonTerminalType.F1,
//                TerminalType.COMMA,
//                new ParseToken[]{
//                        addParseToken(TerminalType.COMMA),
//                        addParseToken(NonTerminalType.Typespecifier),
//                        addParseToken(TerminalType.ID),
//                        addParseToken(NonTerminalType.E2),
//                        addParseToken(NonTerminalType.F1),
//                });
//
//        // Declaration
//        addSameConvertToTable(NonTerminalType.Declaration,
//                new TerminalType[]{
//                        TerminalType.VOID,
//                        TerminalType.INT,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Typespecifier),
//                        addParseToken(TerminalType.ID),
//                        addParseToken(NonTerminalType.AA),
//                });
//
//        // AA
//        addSameConvertToTable(NonTerminalType.AA,
//                new TerminalType[]{
//                        TerminalType.BL,
//                        TerminalType.SEMICOLON,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Vardeclaration),
//                });
//        addConvertToTable(NonTerminalType.AA,
//                TerminalType.PL,
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.Fundeclaration),
//                });
//
//        // Vardeclaration
//        addSameConvertToTable(NonTerminalType.Vardeclaration,
//                new TerminalType[]{
//                        TerminalType.BL,
//                        TerminalType.SEMICOLON,
//                },
//                new ParseToken[]{
//                        addParseToken(NonTerminalType.E1),
//                });
//
//        // Fundeclaration
//        addConvertToTable(NonTerminalType.Fundeclaration,
//                TerminalType.PL,
//                new ParseToken[]{
//                        addParseToken(TerminalType.PL),
//                        addParseToken(NonTerminalType.Params),
//                        addParseToken(TerminalType.PR),
//                        addParseToken(NonTerminalType.Compoundstmt),
//                });
//
//    }

    private static void addSameConvertToTable(NonTerminalType nonTerminalType, TerminalType[] terminalTypes, ParseToken[] parseTokens) {
        for (TerminalType terminalType : terminalTypes) {
            addConvertToTable(nonTerminalType, terminalType, parseTokens);
        }
    }

    private static void addConvertToTable(NonTerminalType nonTerminalType, TerminalType terminalType, ParseToken[] parseTokens) {
        if (!table.containsKey(nonTerminalType)) {
            table.put(nonTerminalType, new HashMap<>());
        }
        table.get(nonTerminalType).put(terminalType, new ArrayList<>());
        for (ParseToken parseToken : parseTokens) {
            table.get(nonTerminalType).get(terminalType).add(parseToken);
        }
    }

    private static ParseToken addParseToken(TerminalType terminalType) {
        return new ParseToken(terminalType);
    }

    private static ParseToken addParseToken(NonTerminalType nonTerminalType) {
        return new ParseToken(nonTerminalType);
    }

    public static DataBase getInstance() {
        return dataBase;
    }

    public HashMap<NonTerminalType, HashMap<TerminalType, List<ParseToken>>> getTable() {
        return table;
    }

    public static ArrayList<String> getKeywords() {
        return keywords;
    }

    public static ArrayList<String> getSymbols() {
        return symbols;
    }

    public static HashMap<String, TerminalType> getKeywordsTable() {
        return keywordsTable;
    }

    public static HashMap<String, TerminalType> getSymbolsTable() {
        return symbolsTable;
    }

    public static Pattern getBoolPattern() {
        return boolPattern;
    }

    public static Pattern getIdentifierPattern() {
        return identifierPattern;
    }

    public static Pattern getIntPattern() {
        return intPattern;
    }

    public static TransitionDiagram makeTransitionDiagram(NonTerminalType nonTerminalType) {
        TransitionDiagram transitionDiagram = new TransitionDiagram(nonTerminalType);
        switch (nonTerminalType) {
            case P:
                transitionDiagram.addTransitionState(new TransitionState(0, NonTerminalType.DL, "1"));
                transitionDiagram.addTransitionState(new TransitionState(1, TerminalType.EOF, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, true));
                break;
            case DL:
                transitionDiagram.addTransitionState(new TransitionState(0, NonTerminalType.D, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.EPSILON, "2");
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.DL, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, true));
                break;
            case D:
                transitionDiagram.addTransitionState(new TransitionState(0, NonTerminalType.TS, "1," + LabelType.PID));
                transitionDiagram.addTransitionState(new TransitionState(1, TerminalType.ID, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, NonTerminalType.VDFD, "3"));
                transitionDiagram.addTransitionState(new TransitionState(3, true));
                break;
            case TS:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.INT, "1," + LabelType.TSINT));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.VOID, "1," + LabelType.TSVOID);
                transitionDiagram.addTransitionState(new TransitionState(1, true));
                break;
            case VDFD:
                transitionDiagram.addTransitionState(new TransitionState(0, NonTerminalType.VD, "1"));
                transitionDiagram.getLastTransitionStates().addState(NonTerminalType.FD, "1");
                transitionDiagram.addTransitionState(new TransitionState(1, true));
                break;
            case VD:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.BL, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.SEMICOLON, "4," + LabelType.VARDEC);
                transitionDiagram.addTransitionState(new TransitionState(1, TerminalType.NUM, "2," + LabelType.ARRDEC));
                transitionDiagram.addTransitionState(new TransitionState(2, TerminalType.BR, "3"));
                transitionDiagram.addTransitionState(new TransitionState(3, TerminalType.SEMICOLON, "4"));
                transitionDiagram.addTransitionState(new TransitionState(4, true));
                break;
            case FD:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.PL, LabelType.FUNDEC + ",1"));
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.PARS, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, TerminalType.PR, "3," + LabelType.FUNENDPARS));
                transitionDiagram.addTransitionState(new TransitionState(3, NonTerminalType.CS, "4," + LabelType.FUNJPCALLER));
                transitionDiagram.addTransitionState(new TransitionState(4, true));
                break;
            case PARS:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.INT, "1," + LabelType.TSINT));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.VOID, "5," + LabelType.TSVOID);
                transitionDiagram.addTransitionState(new TransitionState(1, TerminalType.ID, LabelType.PARID + ",2"));
                transitionDiagram.addTransitionState(new TransitionState(2, NonTerminalType.BRCK, "3"));
                transitionDiagram.addTransitionState(new TransitionState(3, NonTerminalType.PL, "4"));
                transitionDiagram.addTransitionState(new TransitionState(5, NonTerminalType.VPAR, "4"));
                transitionDiagram.addTransitionState(new TransitionState(4, true));
                break;
            case VPAR:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.ID, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.EPSILON, LabelType.SINGLEVOIDPAR + ",3");
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.BRCK, "2," + LabelType.VOIDPARERR));
                transitionDiagram.addTransitionState(new TransitionState(2, NonTerminalType.PL, "3"));
                transitionDiagram.addTransitionState(new TransitionState(3, true));
                break;
            case PL:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.COMMA, LabelType.SETPAR + ",1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.EPSILON, "5");
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.TS, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, TerminalType.ID, "3"));
                transitionDiagram.addTransitionState(new TransitionState(3, NonTerminalType.BRCK, "4"));
                transitionDiagram.addTransitionState(new TransitionState(4, NonTerminalType.PL, "5"));
                transitionDiagram.addTransitionState(new TransitionState(5, true));
                break;
            case BRCK:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.BL, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.EPSILON, "2");
                transitionDiagram.addTransitionState(new TransitionState(1, TerminalType.BR, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, true));
                break;
            case CS:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.KL, "1"));
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.DL, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, NonTerminalType.SL, "3"));
                transitionDiagram.addTransitionState(new TransitionState(3, TerminalType.KR, "4"));
                transitionDiagram.addTransitionState(new TransitionState(4, true));
                break;
            case SL:
                transitionDiagram.addTransitionState(new TransitionState(0, NonTerminalType.S, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.EPSILON, "2");
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.SL, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, true));
                break;
            case S:
                transitionDiagram.addTransitionState(new TransitionState(0, NonTerminalType.ES, "1"));
                transitionDiagram.getLastTransitionStates().addState(NonTerminalType.ES, "1");
                transitionDiagram.getLastTransitionStates().addState(NonTerminalType.CS, "1");
                transitionDiagram.getLastTransitionStates().addState(NonTerminalType.SS, "1");
                transitionDiagram.getLastTransitionStates().addState(NonTerminalType.IS, "1");
                transitionDiagram.getLastTransitionStates().addState(NonTerminalType.RS, "1");
                transitionDiagram.getLastTransitionStates().addState(NonTerminalType.SWS, "1");
                transitionDiagram.addTransitionState(new TransitionState(1, true));
                break;
            case ES:
                transitionDiagram.addTransitionState(new TransitionState(0, NonTerminalType.E, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.CONTINUE, "1");
                transitionDiagram.getLastTransitionStates().addState(TerminalType.BREAK, "1");
                transitionDiagram.getLastTransitionStates().addState(TerminalType.SEMICOLON, "2");
                transitionDiagram.addTransitionState(new TransitionState(1, TerminalType.SEMICOLON, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, true));
                break;
            case SS:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.IF, "1"));
                transitionDiagram.addTransitionState(new TransitionState(1, TerminalType.PL, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, NonTerminalType.E, "3"));
                transitionDiagram.addTransitionState(new TransitionState(3, TerminalType.PR, "4"));
                transitionDiagram.addTransitionState(new TransitionState(4, NonTerminalType.S, "5"));
                transitionDiagram.addTransitionState(new TransitionState(5, TerminalType.ELSE, "6"));
                transitionDiagram.addTransitionState(new TransitionState(6, NonTerminalType.S, "7"));
                transitionDiagram.addTransitionState(new TransitionState(7, true));
                break;
            case IS:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.WHILE, "1"));
                transitionDiagram.addTransitionState(new TransitionState(1, TerminalType.PL, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, NonTerminalType.E, "3"));
                transitionDiagram.addTransitionState(new TransitionState(3, TerminalType.PR, "4"));
                transitionDiagram.addTransitionState(new TransitionState(4, NonTerminalType.S, "5"));
                transitionDiagram.addTransitionState(new TransitionState(5, true));
                break;
            case RS:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.RETURN, "1"));
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.RVAL, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, true));
                break;
            case RVAL:
                transitionDiagram.addTransitionState(new TransitionState(0, NonTerminalType.E, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.SEMICOLON, "2");
                transitionDiagram.addTransitionState(new TransitionState(1, TerminalType.SEMICOLON, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, true));
                break;
            case SWS:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.SWITCH, "1"));
                transitionDiagram.addTransitionState(new TransitionState(1, TerminalType.PL, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, NonTerminalType.E, "3"));
                transitionDiagram.addTransitionState(new TransitionState(3, TerminalType.PR, "4"));
                transitionDiagram.addTransitionState(new TransitionState(4, TerminalType.KL, "5"));
                transitionDiagram.addTransitionState(new TransitionState(5, NonTerminalType.CASS, "6"));
                transitionDiagram.addTransitionState(new TransitionState(6, NonTerminalType.DS, "7"));
                transitionDiagram.addTransitionState(new TransitionState(7, TerminalType.KR, "8"));
                transitionDiagram.addTransitionState(new TransitionState(8, true));
                break;
            case CASS:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.CASE, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.EPSILON, "5");
                transitionDiagram.addTransitionState(new TransitionState(1, TerminalType.NUM, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, TerminalType.COLON, "3"));
                transitionDiagram.addTransitionState(new TransitionState(3, NonTerminalType.SL, "4"));
                transitionDiagram.addTransitionState(new TransitionState(4, NonTerminalType.CASS, "5"));
                transitionDiagram.addTransitionState(new TransitionState(5, true));
                break;
            case DS:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.DEFAULT, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.EPSILON, "3");
                transitionDiagram.addTransitionState(new TransitionState(1, TerminalType.COLON, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, NonTerminalType.SL, "3"));
                transitionDiagram.addTransitionState(new TransitionState(3, true));
                break;
            case E:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.ID, "1"));
                transitionDiagram.getLastTransitionStates().addState(NonTerminalType.SF1, "3");
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.EID, "2"));
                transitionDiagram.addTransitionState(new TransitionState(3, NonTerminalType.T1, "4"));
                transitionDiagram.addTransitionState(new TransitionState(4, NonTerminalType.AE1, "5"));
                transitionDiagram.addTransitionState(new TransitionState(5, NonTerminalType.SE1, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, true));
                break;
            case EID:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.ASSIGN, "1"));
                transitionDiagram.getLastTransitionStates().addState(NonTerminalType.VC2, "3");
                transitionDiagram.getLastTransitionStates().addState(TerminalType.BL, "6");
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.E, "2"));
                transitionDiagram.addTransitionState(new TransitionState(3, NonTerminalType.T1, "4"));
                transitionDiagram.addTransitionState(new TransitionState(4, NonTerminalType.AE1, "5"));
                transitionDiagram.addTransitionState(new TransitionState(5, NonTerminalType.SE1, "2"));
                transitionDiagram.addTransitionState(new TransitionState(6, NonTerminalType.E, "7"));
                transitionDiagram.addTransitionState(new TransitionState(7, TerminalType.BR, "8"));
                transitionDiagram.addTransitionState(new TransitionState(8, NonTerminalType.EID1, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, true));
                break;
            case EID1:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.ASSIGN, "1"));
                transitionDiagram.getLastTransitionStates().addState(NonTerminalType.T1, "3");
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.E, "2"));
                transitionDiagram.addTransitionState(new TransitionState(3, NonTerminalType.AE1, "4"));
                transitionDiagram.addTransitionState(new TransitionState(4, NonTerminalType.SE1, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, true));
                break;
            case SE1:
                transitionDiagram.addTransitionState(new TransitionState(0, NonTerminalType.R, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.EPSILON, "2");
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.AE, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, true));
                break;
            case AE:
                transitionDiagram.addTransitionState(new TransitionState(0, NonTerminalType.T, "1"));
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.AE1, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, true));
                break;
            case AE1:
                transitionDiagram.addTransitionState(new TransitionState(0, NonTerminalType.A, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.EPSILON, "2");
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.T, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, true));
                break;
            case A:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.PLUS, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.MINUS, "1");
                transitionDiagram.addTransitionState(new TransitionState(1, true));
                break;
            case R:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.LESS, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.EQ, "1");
                transitionDiagram.addTransitionState(new TransitionState(1, true));
                break;
            case T:
                transitionDiagram.addTransitionState(new TransitionState(0, NonTerminalType.SF, "1"));
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.T1, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, true));
                break;
            case T1:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.MULTIPLE, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.EPSILON, "2");
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.SF, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, true));
                break;
            case SF:
                transitionDiagram.addTransitionState(new TransitionState(0, NonTerminalType.VC, "1"));
                transitionDiagram.getLastTransitionStates().addState(NonTerminalType.SF1, "1");
                transitionDiagram.addTransitionState(new TransitionState(1, true));
                break;
            case SF1:
                transitionDiagram.addTransitionState(new TransitionState(0, NonTerminalType.F1, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.PLUS, "2");
                transitionDiagram.getLastTransitionStates().addState(TerminalType.MINUS, "2");
                transitionDiagram.addTransitionState(new TransitionState(2, NonTerminalType.F, "1"));
                transitionDiagram.addTransitionState(new TransitionState(1, true));
                break;
            case F:
                transitionDiagram.addTransitionState(new TransitionState(0, NonTerminalType.VC, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.PL, "2");
                transitionDiagram.getLastTransitionStates().addState(TerminalType.NUM, "1");
                transitionDiagram.addTransitionState(new TransitionState(2, NonTerminalType.E, "3"));
                transitionDiagram.addTransitionState(new TransitionState(3, TerminalType.PR, "1"));
                transitionDiagram.addTransitionState(new TransitionState(1, true));
                break;
            case F1:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.NUM, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.PL, "2");
                transitionDiagram.addTransitionState(new TransitionState(2, NonTerminalType.E, "3"));
                transitionDiagram.addTransitionState(new TransitionState(3, TerminalType.PR, "1"));
                transitionDiagram.addTransitionState(new TransitionState(1, true));
                break;
            case VC:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.ID, "1"));
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.VC1, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, true));
                break;
            case VC1:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.BL, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.PL, "4");
                transitionDiagram.getLastTransitionStates().addState(TerminalType.EPSILON, "3");
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.E, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, TerminalType.BR, "3"));
                transitionDiagram.addTransitionState(new TransitionState(4, NonTerminalType.ARGS, "4"));
                transitionDiagram.addTransitionState(new TransitionState(5, TerminalType.PR, "3"));
                transitionDiagram.addTransitionState(new TransitionState(3, true));
                break;
            case VC2:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.PL, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.EPSILON, "3");
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.ARGS, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, TerminalType.PR, "3"));
                transitionDiagram.addTransitionState(new TransitionState(3, true));
                break;
            case ARGS:
                transitionDiagram.addTransitionState(new TransitionState(0, NonTerminalType.ARL, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.EPSILON, "1");
                transitionDiagram.addTransitionState(new TransitionState(1, true));
                break;
            case ARL:
                transitionDiagram.addTransitionState(new TransitionState(0, NonTerminalType.E, "1"));
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.ARL1, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, true));
                break;
            case ARL1:
                transitionDiagram.addTransitionState(new TransitionState(0, TerminalType.COMMA, "1"));
                transitionDiagram.getLastTransitionStates().addState(TerminalType.EPSILON, "3");
                transitionDiagram.addTransitionState(new TransitionState(1, NonTerminalType.E, "2"));
                transitionDiagram.addTransitionState(new TransitionState(2, NonTerminalType.ARL1, "3"));
                transitionDiagram.addTransitionState(new TransitionState(3, true));
                break;

        }
        return transitionDiagram;
    }

    public static boolean IsInFirstFollow(NonTerminalType nonTerminalType, TerminalType terminalType) {
        if (first.get(nonTerminalType).contains(terminalType)) {
            return true;
        }
        if (first.get(nonTerminalType).contains(TerminalType.EPSILON)) {
            return follow.get(nonTerminalType).contains(terminalType);
        }
        return false;
    }

    public static boolean IsInFirst(NonTerminalType nonTerminalType, TerminalType terminalType) {
        return first.get(nonTerminalType).contains(terminalType);
    }

    public static boolean IsInFollow(NonTerminalType nonTerminalType, TerminalType terminalType) {
        return follow.get(nonTerminalType).contains(terminalType);
    }


}