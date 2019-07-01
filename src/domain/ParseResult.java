package domain;

public class ParseResult {
    private String treeResult;
    private String errorResult;

    public String getTreeResult() {
        return treeResult;
    }

    public void setTreeResult(String treeResult) {
        this.treeResult = treeResult;
    }

    public String getErrorResult() {
        return errorResult;
    }

    public void setErrorResult(String errorResult) {
        this.errorResult = errorResult;
    }

    public ParseResult(String treeResult, String errorResult) {
        this.treeResult = treeResult;
        this.errorResult = errorResult;
    }
}
