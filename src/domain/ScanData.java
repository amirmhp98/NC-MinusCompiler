package domain;

import java.util.ArrayList;
import java.util.List;

public class ScanData {
    private List<ScanToken> scanTokens;

    public ScanData(){
        scanTokens = new ArrayList<>();
    }

    public void addTokenByLineNumber(List<ScanToken> scanTokens, int lineNumber){
        for (ScanToken scanToken : scanTokens) {
            this.scanTokens.add(new ScanToken(scanToken.getType(), scanToken.getValue(), lineNumber));
        }
    }

    public List<ScanToken> getScanTokens() {
        return scanTokens;
    }
}
