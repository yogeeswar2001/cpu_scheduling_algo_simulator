#include<iostream>
#include "cpu_schedule.h"

using namespace std;

int main() {
        int flag,x;
        cpu_schedule *cpu;

        flag = 1;
        do {
                cout<<"###################################################\n";
                cout<<"enter any of the below options\n1. fcfs\n2. sfj\n3. rr\n4. ps\nenter your choice: ";
                cin>>x;

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
                        default:
                                cout<<"enter a valid number\n";
                }

                cpu->get_input();
                cpu->calculate();
                cpu->output();

                cout<<"\n if u want to continue press 1, else 0: ";
                cin>>flag;

                cout<<"###################################################\n";
        } while( flag );
}
