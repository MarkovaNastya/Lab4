package Lab4;

import javafx.util.Pair;

public class ToDoJSCode {
    private final Pair<Integer, InputJSMessage > test;

    public ToDoJSCode(Integer id, InputJSMessage inputJSMessage) {
        this.test = new Pair<>(id, inputJSMessage);
    }

    public Pair<Integer, InputJSMessage> getTest() {
        return test;
    }
}
