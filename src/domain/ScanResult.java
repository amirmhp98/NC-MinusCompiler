package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amirmhp on 4/11/2019.
 */
public class ScanResult {
    private ArrayList<ScanToken> scanTokens;
    private ArrayList<ScanError> scanErrors;

    public ScanResult() {
        scanTokens = new ArrayList<>();
        scanErrors = new ArrayList<>();
    }

    public ArrayList<ScanToken> getScanTokens() {
        return scanTokens;
    }

    public void addToken(ScanToken scanToken) {
        this.scanTokens.add(scanToken);
    }

    public ArrayList<ScanError> getScanErrors() {
        return scanErrors;
    }

    public void addError(ScanError scanError) {
        this.scanErrors.add(scanError);
    }

    public void addTokens(List<ScanToken> scanTokens) {
        this.scanTokens.addAll(scanTokens);
    }

    public void addErrors(List<ScanError> scanErrors) {
        this.scanErrors.addAll(scanErrors);
    }
}
