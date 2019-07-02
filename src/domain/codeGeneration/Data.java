package domain.codeGeneration;

/**
 * Created by amirmhp on 7/2/2019.
 */
public class Data {
    private DataType dataType;
    private int value;
    private int index;

    public Data(DataType dataType) {
        this.dataType = dataType;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
