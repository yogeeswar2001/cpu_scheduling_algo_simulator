package org.cpuschedule.algo;

import org.cpuschedule.utility.Pair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class sort_sjf implements Comparator<Pair> {
    public int compare(Pair a, Pair b) {
        return a.getSecond() - b.getSecond();
    }
}

public class Sjf extends Fcfs{
    private ArrayList<Pair> vec;
    public void get_input(ArrayList<Integer> input){
        n = input.get(1);
        for( int j=2,i=0;i<n;i++,j+=2 ){
            pid = input.get(j);
            btime = input.get(j+1);
            vec.add(new Pair(pid, btime));
        }
        //sorting
        Collections.sort(vec, new sort_sjf());

        for(int i=0;i<n;i++ ){
            rq.add(new Pair(vec.get(i).getFirst(), vec.get(i).getSecond()));
        }
    }
    public Sjf(){
        vec = new ArrayList<Pair>();
    }
}
