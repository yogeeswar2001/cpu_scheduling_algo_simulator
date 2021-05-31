package org.cpuschedule.algo;

import org.cpuschedule.utility.MyPair;
import org.cpuschedule.utility.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class sort_ps implements Comparator<MyPair<MyPair<Integer,Integer>,Integer>> {
    public int compare (MyPair<MyPair<Integer,Integer>,Integer> a, MyPair<MyPair<Integer,Integer>,Integer> b){
        return a.getSecond() - b.getSecond();
    }
}

public class PrioritySchedule extends Fcfs {
    int p;
    ArrayList<MyPair<MyPair<Integer,Integer>,Integer>> vec;

    @Override
    public void get_input(ArrayList<Integer> input) {
        n = input.get(1);
        MyPair<Integer,Integer> Pair_inner;
        MyPair<MyPair<Integer, Integer>, Integer> Pair_outer;

        for( int j=2,i=0;i<n;i++,j+=3 ){
            pid = input.get(j);
            btime = input.get(j+1);
            p = input.get(j+2);
            Pair_inner = new MyPair<Integer, Integer>(pid, btime);
            Pair_outer = new MyPair<MyPair<Integer, Integer>, Integer>(Pair_inner,p);
            vec.add(Pair_outer);
        }
        Collections.sort(vec, new sort_ps());
        for( int i=0;i<n;i++ ){
            rq.add(new Pair(vec.get(i).getFirst().getFirst(), vec.get(i).getFirst().getSecond()));
        }
    }

   public PrioritySchedule(){
        vec = new ArrayList<MyPair<MyPair<Integer,Integer>,Integer>>();
    }
}
