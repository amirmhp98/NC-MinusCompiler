package domain.codeGeneration;

/**
 * Created by amirmhp on 7/2/2019.
 */
public class SymbolRecord {
    private int address;
    private IDType type;

    public SymbolRecord(int address, IDType type) {
        this.address = address;
        this.type = type;
    }

    public SymbolRecord() {
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public IDType getType() {
        return type;
    }

    public void setType(IDType type) {
        this.type = type;
    }
}
