package codegeneration;

import domain.codeGeneration.ActionSymbol;
import domain.ScanToken;
import domain.codeGeneration.ActiveRecord;
import domain.codeGeneration.CJTemporaryVariable;
import domain.exception.semanticException.AlreadyDefinedException;
import domain.exception.semanticException.ScopingException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amirmhp on 7/2/2019.
 */
public class CodeGenerater {
    private int[] mem = new int[1000];
    private int codePointer = 0;
    private int varPointer = 300;
    private int tempPointer = 600;
    private List<ActiveRecord> activeRecords = new ArrayList<>();
    private int currentActiveRecord = 0;
    private HashMap<String, Integer> symbolTable = new HashMap<>();
    private List<Integer> ss = new ArrayList<>();

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

    private CJTemporaryVariable getTemp() {
        CJTemporaryVariable temp = new CJTemporaryVariable(tempPointer);
        tempPointer++;
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
        return symbolTable.get(key);
    }

    private int defineVar(String name) throws AlreadyDefinedException {
        String key = currentActiveRecord + "." + name;
        if (symbolTable.containsKey(key)){
            throw new AlreadyDefinedException(name);
        }
        symbolTable.put(key,varPointer);
        varPointer++;
        return varPointer-1;
    }

    private int defineFunc(String name) throws AlreadyDefinedException {
        String key = currentActiveRecord + "." + name;
        if (symbolTable.containsKey(key)){
            throw new AlreadyDefinedException(name);
        }
        symbolTable.put(key, codePointer);
        return codePointer;
    }

    private ActiveRecord deleteActiveRecord() { //hengaame return kardan
        ActiveRecord toBeDeletedAR = activeRecords.get(activeRecords.size() - 1);
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

    protected void pid(String input) {

    }


}
