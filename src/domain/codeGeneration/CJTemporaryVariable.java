package domain.codeGeneration;

/**
 * Created by amirmhp on 7/2/2019.
 */
public class CJTemporaryVariable {
    private int index;
    private int value = 0;

    public CJTemporaryVariable() {
    }

    public CJTemporaryVariable(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
