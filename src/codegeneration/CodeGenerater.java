package codegeneration;

import domain.ScanToken;
import domain.codeGeneration.*;
import domain.exception.semanticException.AlreadyDefinedException;
import domain.exception.semanticException.ScopingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amirmhp on 7/2/2019.
 */
public class CodeGenerater {
    private Code[] code = new Code[300];
    private Data[] data = new Data[500];
    private int codePointer = 0;
    private int varPointer = 0;
    private int tempPointer = 500;
    private List<ActiveRecord> activeRecords = new ArrayList<>();
    private int currentActiveRecord = 0;
    private HashMap<String, SymbolRecord> symbolTable = new HashMap<>();
    private List<Integer> ss = new ArrayList<>();
    private String tempNameHolder = "";
    private DataType tempTypeHolder = DataType.INT;

    {
        ActiveRecord activeRecord = new ActiveRecord();
        activeRecord.setParentARIndex(-1);
        activeRecord.setReturnAddress(-1);
        activeRecords.add(activeRecord);
    }


    public void handleRoutine(ActionSymbol actionSymbol, ScanToken input) {
        switch (actionSymbol) {
            case pid:

                break;
            default:
        }
    }

    private Data getTemp() {
        Data temp = new Data(DataType.INT);
        temp.setIndex(tempPointer);
        data[tempPointer] = temp;
        tempPointer--;
        return temp;
    }

    private int findAddress(String name) throws ScopingException {
        ActiveRecord activeRecord = activeRecords.get(currentActiveRecord);
        String key;
        do {
            key = activeRecord.indexOf() + "." + name;
            if (symbolTable.containsKey(key)) {
                break;
            } else if (activeRecord.getParentARIndex() == -1) {
                throw new ScopingException(name);
            } else {
                activeRecord = activeRecords.get(activeRecord.getParentARIndex());
            }
        } while (true);
        return symbolTable.get(key).getAddress();
    }


    private int defineFunc(String name) throws AlreadyDefinedException {
        String key = currentActiveRecord + "." + name;
        if (symbolTable.containsKey(key)) {
            throw new AlreadyDefinedException(name);
        }
        symbolTable.put(key, new SymbolRecord(codePointer, IDType.FUNC));
        return codePointer;
    }

    private ActiveRecord deleteActiveRecord() { //hengaame return kardan
        ActiveRecord toBeDeletedAR = activeRecords.get(activeRecords.size() - 1);
        int index = toBeDeletedAR.indexOf();
        for (String s : symbolTable.keySet()) {
            if (s.startsWith(index + ".")) {
                symbolTable.remove(s);
            }
        }
        activeRecords.remove(activeRecords.size() - 1);
        currentActiveRecord = toBeDeletedAR.getParentARIndex();
        codePointer = toBeDeletedAR.getReturnAddress();
        return toBeDeletedAR;
    }

    private ActiveRecord gotoNewAR(int destAddress) { //baraaye seda kardan!
        ActiveRecord activeRecord = new ActiveRecord();
        activeRecord.setParentARIndex(currentActiveRecord);
        activeRecord.setReturnAddress(codePointer);
        codePointer = destAddress;
        activeRecords.add(activeRecord);
        activeRecord.setIndex(activeRecords.size() - 1);
        currentActiveRecord = activeRecord.indexOf();
        return activeRecord;
    }

    private void do_pid(String input) {
        tempNameHolder = input;
    }

    private void do_tsint(String input) {
        if (input.toLowerCase().equals("int")) {
            tempTypeHolder = DataType.INT;
        }
    }

    private void do_tsvoid(String input) {
        if (input.toLowerCase().equals("void")) {
            tempTypeHolder = DataType.VOID;
        }
    }

    private int do_vardec() throws AlreadyDefinedException { //only int.
        String key = currentActiveRecord + "." + tempNameHolder;
        if (symbolTable.containsKey(key)) {
            throw new AlreadyDefinedException(tempNameHolder);
        }
        symbolTable.put(key, new SymbolRecord(varPointer, IDType.INT));
        data[varPointer] = new Data(DataType.INT);
        varPointer++;
        return varPointer - 1;
    }

    private int do_arrdec(String input) throws AlreadyDefinedException {
        int count = Integer.parseInt(input);
        String key = currentActiveRecord + "." + tempNameHolder;
        if (symbolTable.containsKey(key)) {
            throw new AlreadyDefinedException(tempNameHolder);
        }
        symbolTable.put(key, new SymbolRecord(varPointer, IDType.ARR));
        for (int i = 0; i < count; i++) {
            data[varPointer + i] = new Data(DataType.INT);
        }
        varPointer += count;
        return varPointer - count;
    }

    private void do_fundec()  {

    }


}
