package org.cpuschedule.algo;

import org.cpuschedule.utility.Pair;
import java.util.ArrayList;

public class RoundRobin extends CpuAlgo {
    int qtime;
    int[] bt;

    @Override
    public void get_input(ArrayList<Integer> input) {
        n = input.get(1);
        qtime = input.get(2);

        for( int j=3,i=0;i<n;i++,j+=2 ) {
            pid = input.get(j);
            btime = input.get(j+1);
            rq.add( new Pair(pid, btime));
            bt[i+1] = btime;
        }
    }

    @Override
    public void calculate() {
        avg_t=avg_w=0;
        int i=0,ctime=0;

        while( rq.size()>0 ) {
            temp = rq.peek();
            if( qtime >= temp.getSecond()) {
                ctime += temp.getSecond();
                if( i==0 ) {
                    arr[0][0]=temp.getFirst(); arr[0][1]=bt[temp.getFirst()];arr[0][2]=0;
                }
                else {
                    arr[i][0] = temp.getFirst();
                    arr[i][1] = bt[temp.getFirst()];
                }
                arr[i][3] = ctime;
                rq.poll();
                i++;
            }
            else {
                ctime += qtime;
                temp.setSecond(temp.getSecond()-qtime);
                rq.poll();
                rq.add(temp);
            }
            chart.add(new Pair(temp.getFirst(), ctime));
        }
        for( i=0;i<n;i++ ){
            arr[i][2] = arr[i][3] - arr[i][1];
            avg_w += arr[i][2];
            avg_t += arr[i][3];
        }
        avg_t /= n;
        avg_w /= n;
    }

    public RoundRobin() {
        bt = new int[10];
    }
}
