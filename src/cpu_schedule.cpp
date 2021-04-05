#include<iostream>
#include<cstdlib>
#include<queue>
#include<utility>
#include<vector>
#include<algorithm>

#include "cpu_schedule.h"

using namespace std;

bool compare_sjf( pair<int,int> a, pair<int,int> b ) {
	return ( a.second < b.second );
}

bool compare_ps(pair<pair<int,int>,int> a, pair<pair<int,int>,int> b ) {
	return ( a.second < b.second );
}

void cpu_schedule::output() {
        for( int i=0;i<n;i++ )
              	cout<<arr[i][0]<<" "<<arr[i][1]<<" "<<arr[i][2]<<" "<<arr[i][3]<<"\n";
        cout<<avg_w<<"\n"<<avg_t<<"\n";
        for( auto i : chart ){
                cout<<i.first<<" ";
        }
        cout<<"\n";
        cout<<"0";
        for( auto i : chart )
                cout<<" "<<i.second;
}

void fcfs::get_input( char** argv ) {
        n = atoi(argv[2]);

        for(int j=3,i=0;i<n;i++,j+=2 ) {
                pid = atoi(argv[j]);
		btime = atoi(argv[j+1]);
                rq.push(make_pair(pid,btime));
        }
}

void fcfs::calculate() {
	avg_w=avg_t=0;
        for(int i=0;i<n;i++ ) {
                temp = rq.front();
                if( i==0 ){
                        arr[0][0]=rq.front().first;arr[0][1]=arr[0][3]=rq.front().second;arr[0][2]=0;
                }
                else {
                        arr[i][0] = temp.first;
                        arr[i][1] = temp.second;
                        arr[i][2] = arr[i-1][1] + arr[i-1][2];
                        arr[i][3] = arr[i][1] + arr[i][2];
                }
                rq.pop();
                avg_w += arr[i][2];
                avg_t += arr[i][3];

                chart.push_back(make_pair(temp.first,arr[i][3]));
        }
        avg_w /= n;
        avg_t /= n;
}

void sjf::get_input(char** argv) {
        n = atoi(argv[2]);

        for(int j=3,i=0;i<n;i++,j+=2 ) {
                pid = atoi(argv[j]);
		btime = atoi(argv[j+1]);       
                vec.push_back(make_pair(pid,btime));
        }
        //sorting
        sort(vec.begin(),vec.end(),compare_sjf);
        //converting to queue
        for( int i=0;i<n;i++ )
                rq.push(make_pair(vec[i].first,vec[i].second));
}

void rr::get_input(char** argv) {
        n = atoi(argv[2]);
	qtime = atoi(argv[3]);
        
	for(int j=4,i=0;i<n;i++,j+=2 ) {
                pid = atoi(argv[j]);
		btime = atoi(argv[j+1]);
                rq.push(make_pair(pid,btime));
                bt[i+1]=btime;
        }
}

void rr::calculate() {
	avg_w=avg_t=0;
        int i=0,ctime=0;
        while( !rq.empty() ) {

                temp = rq.front();
                if( qtime >= temp.second ){
                        ctime += temp.second;
                        if( i==0 ) {
                                arr[0][0]=temp.first;arr[0][1]=bt[temp.first];arr[0][2]=0;
                        }
                        else {
                                arr[i][0] = temp.first;
                                arr[i][1] = bt[temp.first];
                        }
                        arr[i][3] = ctime;
                        rq.pop();i++;
                }
                else {
                        ctime += qtime;
                        temp.second -= qtime;
                        rq.pop();
                        rq.push(temp);
               }
               chart.push_back(make_pair(temp.first,ctime));
        }
        for( int i=0;i<n;i++ ) {
                arr[i][2] = arr[i][3] - arr[i][1];
                avg_w += arr[i][2];
                avg_t += arr[i][3];
        }
        avg_w /= n;
        avg_t /= n;
}

void ps::get_input(char** argv) {
        n = atoi(argv[2]);

        for(int j=3,i=0;i<n;i++,j+=3 ) {
                pid = atoi(argv[j]);
		btime = atoi(argv[j+1]);
	       	p = atoi(argv[j+2]);	
                vec.push_back(make_pair(make_pair(pid,btime),p));
        }
        //sorting
        sort(vec.begin(),vec.end(),compare_ps);
        //converting to queue
        for( int i=0;i<n;i++ )
                rq.push(make_pair(vec[i].first.first,vec[i].first.second));
}
