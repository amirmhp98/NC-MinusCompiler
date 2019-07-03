package domain.codeGeneration;

/**
 * Created by amirmhp on 7/2/2019.
 */
public class Data {
    private DataType dataType;
    private int value;
    private int address;

    public Data(DataType dataType) {
        this.dataType = dataType;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
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
