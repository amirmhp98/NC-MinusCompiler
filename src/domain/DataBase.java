package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class DataBase {
    private static DataBase dataBase = new DataBase();
    private static HashMap<NonTerminalType, HashMap<TerminalType, List<ParseToken>>> table;
    private static HashMap<NonTerminalType, List<TerminalType>> first;
    private static HashMap<NonTerminalType, List<TerminalType>> follow;
    private static Pattern intPattern = Pattern.compile("[0-9]+");
    private static Pattern boolPattern = Pattern.compile("true|false");
    private static Pattern identifierPattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");
    private static ArrayList<String> keywords;
    private static HashMap<String, TerminalType> keywordsTable;
    private static ArrayList<String> symbols;
    private static HashMap<String, TerminalType> symbolsTable;
    private DataBase() {
        setTable();
        setKeywords();
        setSymbols();
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
        symbolsTable.put("*", TerminalType.MULT);
        symbolsTable.put("=", TerminalType.ASGN);
        symbolsTable.put("<", TerminalType.LESS);
    }

    private static void setTable() {
        table = new HashMap<>();
        // Program
        addSameConvertToTable(NonTerminalType.Program,
                new TerminalType[]{
                        TerminalType.VOID,
                        TerminalType.INT,
                        TerminalType.EOF,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.Declarationlist),
                        addParseToken(TerminalType.EOF),
                });

        // Declarationlist
        addSameConvertToTable(NonTerminalType.Declarationlist,
                new TerminalType[]{
                        TerminalType.PL,
                        TerminalType.ID,
                        TerminalType.NUM,
                        TerminalType.MINUS,
                        TerminalType.PLUS,
                        TerminalType.KR,
                        TerminalType.KL,
                        TerminalType.SWITCH,
                        TerminalType.SEMICOLON,
                        TerminalType.RETURN,
                        TerminalType.WHILE,
                        TerminalType.IF,
                        TerminalType.CONTINUE,
                        TerminalType.BREAK,
                        TerminalType.EOF,
                },
                new ParseToken[]{
                });
        addSameConvertToTable(NonTerminalType.Declarationlist,
                new TerminalType[]{
                        TerminalType.VOID,
                        TerminalType.INT,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.Declaration),
                        addParseToken(NonTerminalType.Declarationlist),
                });

        // E1
        addConvertToTable(NonTerminalType.E1,
                TerminalType.BL,
                new ParseToken[]{
                        addParseToken(TerminalType.BL),
                        addParseToken(TerminalType.NUM),
                        addParseToken(TerminalType.BR),
                        addParseToken(TerminalType.SEMICOLON),
                });
        addConvertToTable(NonTerminalType.E1,
                TerminalType.SEMICOLON,
                new ParseToken[]{
                        addParseToken(TerminalType.SEMICOLON),
                });

        // E2
        addSameConvertToTable(NonTerminalType.E2,
                new TerminalType[]{
                        TerminalType.PR,
                        TerminalType.COMMA,
                },
                new ParseToken[]{
                });
        addConvertToTable(NonTerminalType.E2,
                TerminalType.BL,
                new ParseToken[]{
                        addParseToken(TerminalType.BL),
                        addParseToken(TerminalType.BR),
                });

        // Compoundstmt
        addConvertToTable(NonTerminalType.Compoundstmt,
                TerminalType.KL,
                new ParseToken[]{
                        addParseToken(TerminalType.KL),
                        addParseToken(NonTerminalType.Declarationlist),
                        addParseToken(NonTerminalType.Statementlist),
                        addParseToken(TerminalType.KR),
                });

        // Statementlist
        addSameConvertToTable(NonTerminalType.Statementlist,
                new TerminalType[]{
                        TerminalType.PL,
                        TerminalType.ID,
                        TerminalType.NUM,
                        TerminalType.MINUS,
                        TerminalType.PLUS,
                        TerminalType.KL,
                        TerminalType.SWITCH,
                        TerminalType.SEMICOLON,
                        TerminalType.RETURN,
                        TerminalType.WHILE,
                        TerminalType.IF,
                        TerminalType.CONTINUE,
                        TerminalType.BREAK,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.Statement),
                        addParseToken(NonTerminalType.Statementlist),
                });
        addSameConvertToTable(NonTerminalType.Statementlist,
                new TerminalType[]{
                        TerminalType.DEFAULT,
                        TerminalType.CASE,
                        TerminalType.KR,
                },
                new ParseToken[]{
                });

        // Statement
        addSameConvertToTable(NonTerminalType.Statement,
                new TerminalType[]{
                        TerminalType.PL,
                        TerminalType.ID,
                        TerminalType.NUM,
                        TerminalType.MINUS,
                        TerminalType.PLUS,
                        TerminalType.SEMICOLON,
                        TerminalType.CONTINUE,
                        TerminalType.BREAK,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.Expressionstmt),
                });
        addConvertToTable(NonTerminalType.Statement,
                TerminalType.KL,
                new ParseToken[]{
                        addParseToken(NonTerminalType.Compoundstmt),
                });
        addConvertToTable(NonTerminalType.Statement,
                TerminalType.SWITCH,
                new ParseToken[]{
                        addParseToken(NonTerminalType.Switchstmt),
                });
        addConvertToTable(NonTerminalType.Statement,
                TerminalType.RETURN,
                new ParseToken[]{
                        addParseToken(NonTerminalType.Returnstmt),
                });
        addConvertToTable(NonTerminalType.Statement,
                TerminalType.WHILE,
                new ParseToken[]{
                        addParseToken(NonTerminalType.Iterationstmt),
                });
        addConvertToTable(NonTerminalType.Statement,
                TerminalType.IF,
                new ParseToken[]{
                        addParseToken(NonTerminalType.Selectionstmt),
                });

        // Expressionstmt
        addSameConvertToTable(NonTerminalType.Expressionstmt,
                new TerminalType[]{
                        TerminalType.PL,
                        TerminalType.ID,
                        TerminalType.NUM,
                        TerminalType.MINUS,
                        TerminalType.PLUS,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.Expression),
                        addParseToken(TerminalType.SEMICOLON),
                });
        addConvertToTable(NonTerminalType.Expressionstmt,
                TerminalType.SEMICOLON,
                new ParseToken[]{
                        addParseToken(TerminalType.SEMICOLON),
                });
        addConvertToTable(NonTerminalType.Expressionstmt,
                TerminalType.CONTINUE,
                new ParseToken[]{
                        addParseToken(TerminalType.CONTINUE),
                        addParseToken(TerminalType.SWITCH),
                });
        addConvertToTable(NonTerminalType.Expressionstmt,
                TerminalType.BREAK,
                new ParseToken[]{
                        addParseToken(TerminalType.BREAK),
                        addParseToken(TerminalType.SEMICOLON),
                });

        // Selectionstmt
        addConvertToTable(NonTerminalType.Selectionstmt,
                TerminalType.IF,
                new ParseToken[]{
                        addParseToken(TerminalType.IF),
                        addParseToken(TerminalType.PL),
                        addParseToken(NonTerminalType.Expression),
                        addParseToken(TerminalType.PR),
                        addParseToken(NonTerminalType.Statement),
                        addParseToken(TerminalType.ELSE),
                        addParseToken(NonTerminalType.Statement),
                });

        // Iterationstmt
        addConvertToTable(NonTerminalType.Iterationstmt,
                TerminalType.WHILE,
                new ParseToken[]{
                        addParseToken(TerminalType.WHILE),
                        addParseToken(TerminalType.PL),
                        addParseToken(NonTerminalType.Expression),
                        addParseToken(TerminalType.PR),
                        addParseToken(NonTerminalType.Statement),
                });

        // Returnstmt
        addConvertToTable(NonTerminalType.Returnstmt,
                TerminalType.RETURN,
                new ParseToken[]{
                        addParseToken(TerminalType.RETURN),
                        addParseToken(NonTerminalType.E3),
                });

        // E3
        addSameConvertToTable(NonTerminalType.E3,
                new TerminalType[]{
                        TerminalType.PL,
                        TerminalType.ID,
                        TerminalType.NUM,
                        TerminalType.MINUS,
                        TerminalType.PLUS,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.Expression),
                        addParseToken(TerminalType.SEMICOLON),
                });
        addConvertToTable(NonTerminalType.E3,
                TerminalType.SEMICOLON,
                new ParseToken[]{
                        addParseToken(TerminalType.SEMICOLON),
                });

        // Switchstmt
        addConvertToTable(NonTerminalType.Switchstmt,
                TerminalType.SWITCH,
                new ParseToken[]{
                        addParseToken(TerminalType.SWITCH),
                        addParseToken(TerminalType.PL),
                        addParseToken(NonTerminalType.Expression),
                        addParseToken(TerminalType.PR),
                        addParseToken(TerminalType.KL),
                        addParseToken(NonTerminalType.Casestmts),
                        addParseToken(NonTerminalType.Defaultstmt),
                        addParseToken(TerminalType.KR),
                });

        // Casestmts
        addSameConvertToTable(NonTerminalType.Casestmts,
                new TerminalType[]{
                        TerminalType.DEFAULT,
                        TerminalType.KR,
                },
                new ParseToken[]{
                });
        addConvertToTable(NonTerminalType.Casestmts,
                TerminalType.CASE,
                new ParseToken[]{
                        addParseToken(NonTerminalType.Casestmt),
                        addParseToken(NonTerminalType.Casestmts),
                });

        // Casestmt
        addConvertToTable(NonTerminalType.Casestmt,
                TerminalType.CASE,
                new ParseToken[]{
                        addParseToken(TerminalType.CASE),
                        addParseToken(TerminalType.NUM),
                        addParseToken(TerminalType.COLON),
                        addParseToken(NonTerminalType.Statementlist),
                });

        // Defaultstmt
        addConvertToTable(NonTerminalType.Defaultstmt,
                TerminalType.DEFAULT,
                new ParseToken[]{
                        addParseToken(TerminalType.DEFAULT),
                        addParseToken(TerminalType.COLON),
                        addParseToken(NonTerminalType.Statementlist),
                });
        addConvertToTable(NonTerminalType.Defaultstmt,
                TerminalType.KR,
                new ParseToken[]{
                });

        // Expression
        addSameConvertToTable(NonTerminalType.Expression,
                new TerminalType[]{
                        TerminalType.PL,
                        TerminalType.NUM,
                        TerminalType.MINUS,
                        TerminalType.PLUS,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.NewSimpleexpression),
                });
        addConvertToTable(NonTerminalType.Expression,
                TerminalType.ID,
                new ParseToken[]{
                        addParseToken(TerminalType.ID),
                        addParseToken(NonTerminalType.EXEXEX),
                });

        // EXEXEX
        addSameConvertToTable(NonTerminalType.EXEXEX,
                new TerminalType[]{
                        TerminalType.PR,
                        TerminalType.PL,
                        TerminalType.COMMA,
                        TerminalType.BR,
                        TerminalType.MINUS,
                        TerminalType.PLUS,
                        TerminalType.MULT,
                        TerminalType.LESS,
                        TerminalType.EQ,
                        TerminalType.SEMICOLON,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.NewVarcall),
                        addParseToken(NonTerminalType.F3),
                        addParseToken(NonTerminalType.F2),
                        addParseToken(NonTerminalType.E5),
                });
        addConvertToTable(NonTerminalType.EXEXEX,
                TerminalType.BL,
                new ParseToken[]{
                        addParseToken(TerminalType.BL),
                        addParseToken(NonTerminalType.Expression),
                        addParseToken(TerminalType.BR),
                        addParseToken(NonTerminalType.EZEZEZ),
                });
        addConvertToTable(NonTerminalType.EXEXEX,
                TerminalType.ASGN,
                new ParseToken[]{
                        addParseToken(TerminalType.ASGN),
                        addParseToken(NonTerminalType.Expression),
                });

        // EZEZEZ
        addSameConvertToTable(NonTerminalType.EZEZEZ,
                new TerminalType[]{
                        TerminalType.PR,
                        TerminalType.COMMA,
                        TerminalType.BR,
                        TerminalType.MINUS,
                        TerminalType.PLUS,
                        TerminalType.MULT,
                        TerminalType.LESS,
                        TerminalType.EQ,
                        TerminalType.SEMICOLON,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.F3),
                        addParseToken(NonTerminalType.F2),
                        addParseToken(NonTerminalType.E5),
                });
        addConvertToTable(NonTerminalType.EZEZEZ,
                TerminalType.BL,
                new ParseToken[]{
                        addParseToken(TerminalType.BL),
                        addParseToken(NonTerminalType.Expression),
                        addParseToken(TerminalType.BR),
                });

        // NewSimpleexpression
        addSameConvertToTable(NonTerminalType.NewSimpleexpression,
                new TerminalType[]{
                        TerminalType.PL,
                        TerminalType.NUM,
                        TerminalType.MINUS,
                        TerminalType.PLUS,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.NewAdditiveexpression),
                        addParseToken(NonTerminalType.E5),
                });

        // E5
        addSameConvertToTable(NonTerminalType.E5,
                new TerminalType[]{
                        TerminalType.PR,
                        TerminalType.COMMA,
                        TerminalType.BR,
                        TerminalType.SEMICOLON,
                },
                new ParseToken[]{
                });
        addSameConvertToTable(NonTerminalType.E5,
                new TerminalType[]{
                        TerminalType.LESS,
                        TerminalType.EQ,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.Relop),
                        addParseToken(NonTerminalType.Additiveexpression),
                });

        // Relop
        addConvertToTable(NonTerminalType.Relop,
                TerminalType.LESS,
                new ParseToken[]{
                        addParseToken(TerminalType.LESS),
                });
        addConvertToTable(NonTerminalType.Relop,
                TerminalType.EQ,
                new ParseToken[]{
                        addParseToken(TerminalType.EQ),
                });

        // Additiveexpression
        addSameConvertToTable(NonTerminalType.Additiveexpression,
                new TerminalType[]{
                        TerminalType.PL,
                        TerminalType.NUM,
                        TerminalType.MINUS,
                        TerminalType.PLUS,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.NewTerm),
                        addParseToken(NonTerminalType.F2),
                });
        addConvertToTable(NonTerminalType.Additiveexpression,
                TerminalType.ID,
                new ParseToken[]{
                        addParseToken(TerminalType.ID),
                        addParseToken(NonTerminalType.Varcall),
                        addParseToken(NonTerminalType.F3),
                        addParseToken(NonTerminalType.F2),
                });

        // NewAdditiveexpression
        addSameConvertToTable(NonTerminalType.NewAdditiveexpression,
                new TerminalType[]{
                        TerminalType.PL,
                        TerminalType.NUM,
                        TerminalType.MINUS,
                        TerminalType.PLUS,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.NewTerm),
                        addParseToken(NonTerminalType.F2),
                });

        // F2
        addSameConvertToTable(NonTerminalType.F2,
                new TerminalType[]{
                        TerminalType.PR,
                        TerminalType.COMMA,
                        TerminalType.BR,
                        TerminalType.LESS,
                        TerminalType.EQ,
                        TerminalType.SEMICOLON,
                },
                new ParseToken[]{
                });
        addSameConvertToTable(NonTerminalType.F2,
                new TerminalType[]{
                        TerminalType.MINUS,
                        TerminalType.PLUS,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.Term),
                        addParseToken(NonTerminalType.F2),
                });

        // Addop
        addConvertToTable(NonTerminalType.Addop,
                TerminalType.MINUS,
                new ParseToken[]{
                        addParseToken(TerminalType.MINUS),
                });
        addConvertToTable(NonTerminalType.Addop,
                TerminalType.PLUS,
                new ParseToken[]{
                        addParseToken(TerminalType.PLUS),
                });

        // Term
        addSameConvertToTable(NonTerminalType.Term,
                new TerminalType[]{
                        TerminalType.PL,
                        TerminalType.NUM,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.NewFactor),
                        addParseToken(NonTerminalType.F3),
                });
        addSameConvertToTable(NonTerminalType.Term,
                new TerminalType[]{
                        TerminalType.MINUS,
                        TerminalType.PLUS,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.NewSignedfactor),
                        addParseToken(NonTerminalType.F3),
                });
        addConvertToTable(NonTerminalType.Term,
                TerminalType.ID,
                new ParseToken[]{
                        addParseToken(TerminalType.ID),
                        addParseToken(NonTerminalType.Varcall),
                        addParseToken(NonTerminalType.F3),
                });

        // NewTerm
        addSameConvertToTable(NonTerminalType.NewTerm,
                new TerminalType[]{
                        TerminalType.PL,
                        TerminalType.NUM,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.NewFactor),
                        addParseToken(NonTerminalType.F3),
                });
        addSameConvertToTable(NonTerminalType.NewTerm,
                new TerminalType[]{
                        TerminalType.MINUS,
                        TerminalType.PLUS,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.NewSignedfactor),
                        addParseToken(NonTerminalType.F3),
                });

        // F3
        addSameConvertToTable(NonTerminalType.F3,
                new TerminalType[]{
                        TerminalType.PR,
                        TerminalType.COMMA,
                        TerminalType.BR,
                        TerminalType.MINUS,
                        TerminalType.PLUS,
                        TerminalType.LESS,
                        TerminalType.EQ,
                        TerminalType.SEMICOLON,
                },
                new ParseToken[]{
                });
        addConvertToTable(NonTerminalType.F3,
                TerminalType.MULT,
                new ParseToken[]{
                        addParseToken(TerminalType.MULT),
                        addParseToken(NonTerminalType.Signedfactor),
                        addParseToken(NonTerminalType.F3),
                });

        // NewSignedfactor
        addConvertToTable(NonTerminalType.NewSignedfactor,
                TerminalType.MINUS,
                new ParseToken[]{
                        addParseToken(TerminalType.MINUS),
                        addParseToken(NonTerminalType.Factor),
                });
        addConvertToTable(NonTerminalType.NewSignedfactor,
                TerminalType.PLUS,
                new ParseToken[]{
                        addParseToken(TerminalType.PLUS),
                        addParseToken(NonTerminalType.Factor),
                });

        // Signedfactor
        addSameConvertToTable(NonTerminalType.Signedfactor,
                new TerminalType[]{
                        TerminalType.PL,
                        TerminalType.ID,
                        TerminalType.NUM,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.Factor),
                });
        addConvertToTable(NonTerminalType.Signedfactor,
                TerminalType.MINUS,
                new ParseToken[]{
                        addParseToken(TerminalType.MINUS),
                        addParseToken(NonTerminalType.Factor),
                });
        addConvertToTable(NonTerminalType.Signedfactor,
                TerminalType.PLUS,
                new ParseToken[]{
                        addParseToken(TerminalType.PLUS),
                        addParseToken(NonTerminalType.Factor),
                });

        // Args
        addSameConvertToTable(NonTerminalType.Args,
                new TerminalType[]{
                        TerminalType.PL,
                        TerminalType.ID,
                        TerminalType.NUM,
                        TerminalType.MINUS,
                        TerminalType.PLUS,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.Arglist),
                });
        addConvertToTable(NonTerminalType.Args,
                TerminalType.PR,
                new ParseToken[]{
                });

        // Arglist
        addSameConvertToTable(NonTerminalType.Arglist,
                new TerminalType[]{
                        TerminalType.PL,
                        TerminalType.ID,
                        TerminalType.NUM,
                        TerminalType.MINUS,
                        TerminalType.PLUS,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.Expression),
                        addParseToken(NonTerminalType.F4),
                });

        // F4
        addConvertToTable(NonTerminalType.F4,
                TerminalType.PR,
                new ParseToken[]{
                });
        addConvertToTable(NonTerminalType.F4,
                TerminalType.COMMA,
                new ParseToken[]{
                        addParseToken(TerminalType.COMMA),
                        addParseToken(NonTerminalType.Expression),
                });

        // NewFactor
        addConvertToTable(NonTerminalType.NewFactor,
                TerminalType.PL,
                new ParseToken[]{
                        addParseToken(TerminalType.PL),
                        addParseToken(NonTerminalType.Expression),
                        addParseToken(TerminalType.PR),
                });
        addConvertToTable(NonTerminalType.NewFactor,
                TerminalType.NUM,
                new ParseToken[]{
                        addParseToken(TerminalType.NUM),
                });

        // Factor
        addConvertToTable(NonTerminalType.Factor,
                TerminalType.PL,
                new ParseToken[]{
                        addParseToken(TerminalType.PL),
                        addParseToken(NonTerminalType.Expression),
                        addParseToken(TerminalType.PR),
                });
        addConvertToTable(NonTerminalType.Factor,
                TerminalType.NUM,
                new ParseToken[]{
                        addParseToken(TerminalType.NUM),
                });
        addConvertToTable(NonTerminalType.Factor,
                TerminalType.ID,
                new ParseToken[]{
                        addParseToken(TerminalType.ID),
                        addParseToken(NonTerminalType.Varcall),
                });

        // NewVarcall
        addSameConvertToTable(NonTerminalType.NewVarcall,
                new TerminalType[]{
                        TerminalType.PR,
                        TerminalType.COMMA,
                        TerminalType.BR,
                        TerminalType.MINUS,
                        TerminalType.PLUS,
                        TerminalType.MULT,
                        TerminalType.LESS,
                        TerminalType.EQ,
                        TerminalType.SEMICOLON,
                },
                new ParseToken[]{
                });
        addConvertToTable(NonTerminalType.NewVarcall,
                TerminalType.PL,
                new ParseToken[]{
                        addParseToken(TerminalType.PL),
                        addParseToken(NonTerminalType.Args),
                        addParseToken(TerminalType.PR),
                });

        // Varcall
        addSameConvertToTable(NonTerminalType.Varcall,
                new TerminalType[]{
                        TerminalType.PR,
                        TerminalType.COMMA,
                        TerminalType.BR,
                        TerminalType.MINUS,
                        TerminalType.PLUS,
                        TerminalType.MULT,
                        TerminalType.LESS,
                        TerminalType.EQ,
                        TerminalType.SEMICOLON,
                },
                new ParseToken[]{
                });
        addConvertToTable(NonTerminalType.Varcall,
                TerminalType.PL,
                new ParseToken[]{
                        addParseToken(TerminalType.PL),
                        addParseToken(NonTerminalType.Args),
                        addParseToken(TerminalType.PR),
                });
        addConvertToTable(NonTerminalType.Varcall,
                TerminalType.BL,
                new ParseToken[]{
                        addParseToken(TerminalType.BL),
                        addParseToken(NonTerminalType.Expression),
                        addParseToken(TerminalType.BR),
                });

        // Typespecifier
        addConvertToTable(NonTerminalType.Typespecifier,
                TerminalType.VOID,
                new ParseToken[]{
                        addParseToken(TerminalType.VOID),
                });
        addConvertToTable(NonTerminalType.Typespecifier,
                TerminalType.INT,
                new ParseToken[]{
                        addParseToken(TerminalType.INT),
                });

        // Params
        addConvertToTable(NonTerminalType.Params,
                TerminalType.VOID,
                new ParseToken[]{
                        addParseToken(TerminalType.VOID),
                        addParseToken(NonTerminalType.BB),
                });
        addConvertToTable(NonTerminalType.Params,
                TerminalType.INT,
                new ParseToken[]{
                        addParseToken(TerminalType.INT),
                        addParseToken(TerminalType.ID),
                        addParseToken(NonTerminalType.Paramlist),
                });

        // BB
        addConvertToTable(NonTerminalType.BB,
                TerminalType.PR,
                new ParseToken[]{
                });
        addConvertToTable(NonTerminalType.BB,
                TerminalType.ID,
                new ParseToken[]{
                        addParseToken(TerminalType.ID),
                        addParseToken(NonTerminalType.Paramlist),
                });

        // Paramlist
        addSameConvertToTable(NonTerminalType.Paramlist,
                new TerminalType[]{
                        TerminalType.PR,
                        TerminalType.COMMA,
                        TerminalType.BL,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.E2),
                        addParseToken(NonTerminalType.F1),
                });

        // F1
        addConvertToTable(NonTerminalType.F1,
                TerminalType.PR,
                new ParseToken[]{
                });
        addConvertToTable(NonTerminalType.F1,
                TerminalType.COMMA,
                new ParseToken[]{
                        addParseToken(TerminalType.COMMA),
                        addParseToken(NonTerminalType.Typespecifier),
                        addParseToken(TerminalType.ID),
                        addParseToken(NonTerminalType.E2),
                        addParseToken(NonTerminalType.F1),
                });

        // Declaration
        addSameConvertToTable(NonTerminalType.Declaration,
                new TerminalType[]{
                        TerminalType.VOID,
                        TerminalType.INT,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.Typespecifier),
                        addParseToken(TerminalType.ID),
                        addParseToken(NonTerminalType.AA),
                });

        // AA
        addSameConvertToTable(NonTerminalType.AA,
                new TerminalType[]{
                        TerminalType.BL,
                        TerminalType.SEMICOLON,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.Vardeclaration),
                });
        addConvertToTable(NonTerminalType.AA,
                TerminalType.PL,
                new ParseToken[]{
                        addParseToken(NonTerminalType.Fundeclaration),
                });

        // Vardeclaration
        addSameConvertToTable(NonTerminalType.Vardeclaration,
                new TerminalType[]{
                        TerminalType.BL,
                        TerminalType.SEMICOLON,
                },
                new ParseToken[]{
                        addParseToken(NonTerminalType.E1),
                });

        // Fundeclaration
        addConvertToTable(NonTerminalType.Fundeclaration,
                TerminalType.PL,
                new ParseToken[]{
                        addParseToken(TerminalType.PL),
                        addParseToken(NonTerminalType.Params),
                        addParseToken(TerminalType.PR),
                        addParseToken(NonTerminalType.Compoundstmt),
                });

    }

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
}