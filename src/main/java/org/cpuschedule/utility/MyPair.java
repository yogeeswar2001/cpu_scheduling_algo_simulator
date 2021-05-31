package org.cpuschedule.utility;

public class MyPair <T, U> {
    T first;
    U second;

    public MyPair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }
}
