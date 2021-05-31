package org.cpuschedule.algo;

import org.cpuschedule.utility.Pair;

import java.util.ArrayList;

public class Fcfs extends CpuAlgo{
    @Override
    public void get_input(ArrayList<Integer> input) {
        n = input.get(1);
        for (int j=2,i=0; i < n; i++,j+=2) {
            pid = input.get(j);
            btime = input.get(j+1);
            rq.add(new Pair(pid,btime));
        }
    }

    @Override
    public void calculate() {
        avg_t=avg_w=0;
        for (int i=0; i <n ; i++) {
            temp = rq.peek();
            if ( i==0 ) {
                arr[0][0]=rq.peek().getFirst(); arr[0][1]=arr[0][3]=rq.peek().getSecond(); arr[0][2]=0;
            }
            else {
                arr[i][0] = temp.getFirst();
                arr[i][1] = temp.getSecond();
                arr[i][2] = arr[i-1][1] + arr[i-1][2];
                arr[i][3] = arr[i][1] + arr[i][2];
            }
            rq.poll();
            avg_w += arr[i][2];
            avg_t += arr[i][3];

            chart.add(new Pair(temp.getFirst(), arr[i][3]));
        }
        avg_w /= n;
        avg_t /= n;
    }
}
