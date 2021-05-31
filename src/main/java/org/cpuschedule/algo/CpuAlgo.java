package org.cpuschedule.algo;

import org.cpuschedule.utility.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

abstract public class CpuAlgo {
    int n,pid,btime;
    float avg_w, avg_t;
    int[][] arr;
    Queue<Pair> rq;
    Pair temp;
    ArrayList<Pair> chart;

    abstract public void get_input(ArrayList<Integer> input);
    abstract public void calculate();

    public void set_output(ArrayList<Integer> out_table, ArrayList<Pair> out_chart){
        for( int i=0;i<n;i++ ){
            out_table.add(arr[i][0]);out_table.add(arr[i][1]);out_table.add(arr[i][2]);out_table.add(arr[i][3]);
        }
        for( Pair data : chart ) {
            out_chart.add( new Pair(data.getFirst(),data.getSecond()));
        }
    }

    public float getAvg_t() {
        return avg_t;
    }

    public float getAvg_w() {
        return avg_w;
    }

    public int[][] getArr() {
        return arr;
    }

    public ArrayList<Pair> getChart() {
        return chart;
    }

    public CpuAlgo() {
        rq = new LinkedList<Pair>();
        arr = new int[10][4];
        chart = new ArrayList<Pair>();
    }
}