package domain;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class ScanFinalResult {
    private String tokens;
    private String errors;
    private ScanData scanData;

    public String getTokens() {
        return tokens;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public ScanData getScanData() {
        return scanData;
    }

    public void setScanData(ScanData scanData) {
        this.scanData = scanData;
    }
}
