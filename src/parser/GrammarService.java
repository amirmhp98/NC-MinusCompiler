package parser;

import java.util.ArrayList;

public class GrammarService {
    FileService fileService;
    ArrayList<String> grammarText;

    public GrammarService(String fileName) {
        fileService = new FileService(fileName);
        String file = fileService.getNextLine();
        grammarText = new ArrayList<>();
        while (file != null){
            grammarText.add(file);
            file = fileService.getNextLine();
        }
    }
}
