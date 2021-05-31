package org.cpuschedule.utility;

public class InputData {
    private int pid;
    private int btime;
    private int priority;

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setBtime(int btime) {
        this.btime = btime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPid() {
        return pid;
    }

    public int getBtime() {
        return btime;
    }

    public int getPriority() {
        return priority;
    }

    public InputData(int pid, int btime) {
        this.pid = pid;
        this.btime = btime;
    }

    public InputData(int pid, int btime, int p) {
        this.pid = pid;
        this.btime = btime;
        this.priority = p;
    }

    public InputData() {}
}