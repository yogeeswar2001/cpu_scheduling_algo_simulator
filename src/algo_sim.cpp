#include<iostream>
#include<cstdlib>
#include "cpu_schedule.h"

using namespace std;

int main(int argc, char *argv[]) {
        int x;
        cpu_schedule *cpu;

        x = atoi(argv[1]);

        fcfs algo_fcfs;
        sjf algo_sfj;
        rr algo_rr;
        ps algo_ps;
        switch (x) {
                case 1:
                        cpu = &algo_fcfs;
                        break;
                case 2:
                        cpu = &algo_sfj;
                        break;
                case 3:
                        cpu = &algo_rr;
                        break;
                case 4:
                        cpu = &algo_ps;
                        break;
        }

        cpu->get_input(argv);
        cpu->calculate();
        cpu->output();
}
