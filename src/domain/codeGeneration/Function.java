package domain.codeGeneration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amirmhp on 7/2/2019.
 */
public class Function {
    private String name;
    private int index;
    private int codePointer;
    private List<Data> args = new ArrayList<>();
    private DataType returnType = DataType.VOID;
    private int addressOfReturnValue;
    private int returnSpecifierAddress;
    private int addressOfReturnCode;


    public DataType getReturnType() {
        return returnType;
    }

    public void setReturnType(DataType returnType) {
        this.returnType = returnType;
    }

    public int getAddressOfReturnValue() {
        return addressOfReturnValue;
    }

    public void setAddressOfReturnValue(int addressOfReturnValue) {
        this.addressOfReturnValue = addressOfReturnValue;
    }

    public void setReturnSpecifierAddress(int returnSpecifierAddress) {
        this.returnSpecifierAddress = returnSpecifierAddress;
    }

    public int getAddressOfReturnCode() {
        return addressOfReturnCode;
    }

    public void setAddressOfReturnCode(int addressOfReturnCode) {
        this.addressOfReturnCode = addressOfReturnCode;
    }

    public int getReturnSpecifierAddress() {
        return returnSpecifierAddress;
    }

    public void setReturnPlaceSpecifierAddress(int returnSpecifierAddress) {
        this.returnSpecifierAddress = returnSpecifierAddress;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCodePointer() {
        return codePointer;
    }

    public void setCodePointer(int codePointer) {
        this.codePointer = codePointer;
    }

    public List<Data> getArgs() {
        return args;
    }

    public void setArgs(List<Data> args) {
        this.args = args;
    }
}
