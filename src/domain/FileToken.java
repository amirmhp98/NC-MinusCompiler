package domain;

public class FileToken {
    private String line;
    private int lineNumber;

    public FileToken(String line, int lineNumber) {
        this.line = line;
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getLine() {
        return line;
    }
}
