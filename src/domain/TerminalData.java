package domain;

import java.util.ArrayList;
import java.util.List;

public class TerminalData {
    private List<ParseToken> first;
    private List<ParseToken> follow;
    private List<ParseToken> table;

    public TerminalData() {
        first = new ArrayList<>();
        follow = new ArrayList<>();
        table = new ArrayList<>();
    }
}
