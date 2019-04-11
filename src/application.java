import domain.FinalResult;
import io.FileIOHandler;
import parser.ParserService;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class application {
    private static ParserService parserService = new ParserService();
    private static FileIOHandler io = new FileIOHandler();
    private static ArrayList<String> symbols = new ArrayList<>(Arrays.asList
            (";", ":", "[", "]", "(", ")", "{", "}", "+", "-", "+", "==", "*", "=", "<"));

    public static void main(String[] args) {
        String readingFileName = "";
        String text = io.readFromFile(readingFileName);
        FinalResult result = parserService.parse(text);
        io.writeIntoFile(result.getTokens(), "scanner.txt");
        io.writeIntoFile(result.getErrors(), "lexical_errors.txt");
    }


//    protected static List<String> seprateLines(String inputText) {
//        return Arrays.asList(inputText.split("\\r?\\n"));
//    }
//
//    private static void test() {
//        replaceSymbolsAndWSs("line1;\n" +
//                "line2\n" +
//                "l3\n" +
//                "lll555;");
//    }
//
//    protected static String replaceSymbolsAndWSs(String inputText) {
//        inputText = inputText.replace("\\s+", "~");
//        for (String symbol : symbols) {
//            inputText = inputText.replace(symbol, "~" + symbol + "~");
//        }
//        inputText = inputText.replace("~~", "~");
//        inputText = inputText.replace("~~", "~");
//        return inputText;
//    }


}
