package codegeneration;

import domain.ScanToken;
import domain.codeGeneration.*;
import domain.exception.semanticException.AlreadyDefinedException;
import domain.exception.semanticException.ScopingException;
import domain.exception.semanticException.VarVoidingException;

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
    private List<Function> functionTable = new ArrayList<>();

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
        temp.setAddress(tempPointer);
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

    private int do_vardec() throws AlreadyDefinedException, VarVoidingException { //only int.
        String key = currentActiveRecord + "." + tempNameHolder;
        if (tempTypeHolder.equals(DataType.VOID)) {
            throw new VarVoidingException();
        }
        if (symbolTable.containsKey(key)) {
            throw new AlreadyDefinedException(tempNameHolder);
        }
        symbolTable.put(key, new SymbolRecord(varPointer, IDType.INT));
        data[varPointer] = new Data(DataType.INT);
        data[varPointer].setAddress(varPointer);
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
            data[varPointer + i].setAddress(varPointer + i);
        }
        varPointer += count;
        return varPointer - count;
    }

    private void do_fundec() throws AlreadyDefinedException {
        if (symbolTable.containsKey(tempNameHolder)) {
            throw new AlreadyDefinedException(tempNameHolder);
        }
        Function function = new Function();
        function.setName(tempNameHolder);
        function.setReturnType(tempTypeHolder);
        if (function.getReturnType().equals(DataType.INT)) {
            function.setAddressOfReturnValue(getTemp().getAddress());
        }
        function.setReturnPlaceSpecifierAddress(getTemp().getAddress());
        functionTable.add(function);
        function.setIndex(functionTable.size() - 1);
        symbolTable.put(function.getName(), new SymbolRecord(function.getIndex(), IDType.FUNC));

        ActiveRecord activeRecord = new ActiveRecord();
        activeRecord.setParentARIndex(currentActiveRecord);
        activeRecord.setIndex(activeRecords.size());
        activeRecords.add(activeRecord);
        currentActiveRecord = activeRecord.indexOf();

    }

    private void do_funendpars() {
        functionTable.get(functionTable.size() - 1).setCodePointer(codePointer);
    }

    private void do_funjpcaller() { //todo move this to return
        Function function = functionTable.get(functionTable.size() - 1);
        function.setAddressOfReturnCode(codePointer);
        Code retCode = new Code(Instruction.JP, data[data[function.getReturnSpecifierAddress()].getValue()].getValue() + ""
                , "", "");
        code[codePointer] = retCode;
        if (function.getReturnType().equals(DataType.INT)) {
            Data retData = new Data(DataType.INT);
            retData.setAddress(function.getAddressOfReturnValue());
            retData.setValue(ss.remove(ss.size()-1));
            data[function.getAddressOfReturnValue()] = retData;
        }
    }

    private void do_parid(String input) {
        tempNameHolder = input;
        tempTypeHolder = DataType.INT;
    }

    private void do_withoutbrck() {
        String key = currentActiveRecord + "." + tempNameHolder;
        symbolTable.put(key, new SymbolRecord(varPointer, IDType.INT));
        data[varPointer] = new Data(DataType.INT);
        data[varPointer].setAddress(varPointer);
        Function function = functionTable.get(functionTable.size() - 1);
        function.getArgs().add(data[varPointer]);
        varPointer++;
    }

    private void do_withbrck() {
        String key = currentActiveRecord + "." + tempNameHolder;
        symbolTable.put(key, new SymbolRecord(varPointer, IDType.ARR));
        data[varPointer] = new Data(DataType.PTR);
        data[varPointer].setAddress(varPointer);
        Function function = functionTable.get(functionTable.size() - 1);
        function.getArgs().add(data[varPointer]);
    }

    private void do_voidparerr() throws VarVoidingException {
        throw new VarVoidingException();
    }

    private void do_singlevoidpar() {

    }

    private void do_return() {
        //todo do nothing :)
    }




}

















