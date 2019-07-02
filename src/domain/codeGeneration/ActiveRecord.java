package domain.codeGeneration;

/**
 * Created by amirmhp on 7/2/2019.
 */
public class ActiveRecord {
    private int parentARIndex;
    private int returnAddress;
    private int returnValue;
    private int indexOf;

    public int indexOf() {
        return indexOf;
    }

    public void setIndex(int indexOf) {
        this.indexOf = indexOf;
    }

    public int getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(int returnValue) {
        this.returnValue = returnValue;
    }

    public int getParentARIndex() {
        return parentARIndex;
    }

    public void setParentARIndex(int parentARIndex) {
        this.parentARIndex = parentARIndex;
    }

    public int getReturnAddress() {
        return returnAddress;
    }

    public void setReturnAddress(int returnAddress) {
        this.returnAddress = returnAddress;
    }
}
