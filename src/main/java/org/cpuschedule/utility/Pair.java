package org.cpuschedule.utility;

public class Pair {
    private int first;
    private int second;

    public void setFirst(int first) {
        this.first = first;
    }
    public void setSecond(int second) {
        this.second = second;
    }
    public int getFirst() {
        return first;
    }
    public int getSecond() {
        return second;
    }
    public Pair(int a, int b){
        this.first = a;
        this.second = b;
    }
}
