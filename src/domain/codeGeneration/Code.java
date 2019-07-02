package domain.codeGeneration;

/**
 * Created by amirmhp on 7/2/2019.
 */
public class Code {
    private String func = "";
    private String arg1 = "";
    private String arg2 = "";
    private String arg3 = "";

    public Code() {
    }

    public Code(String func, String arg1, String arg2, String arg3) {
        this.func = func;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public String getArg3() {
        return arg3;
    }

    public void setArg3(String arg3) {
        this.arg3 = arg3;
    }
}
