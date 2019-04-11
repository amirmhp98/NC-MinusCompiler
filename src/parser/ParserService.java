package parser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class ParserService {

    ArrayList<String> keywords = new ArrayList<>(Arrays.asList
            ("if", "else", "void", "int", "while", "break", "continue", "switch", "default", "case", "return"));
    ArrayList<String> symbols = new ArrayList<>(Arrays.asList
            (";", ":", "[", "]", "(", ")", "{", "}", "+", "-", "+", "*", "=", "<", "=="));


}
