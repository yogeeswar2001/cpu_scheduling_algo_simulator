#ifndef CPU_SCHEDULE
#define CPU_SCHEDULE

#include<vector>
#include<queue>
#include<utility>

class cpu_schedule {
	protected:
        	int n,pid,btime;
        	float avg_w,avg_t;
        	int arr[10][4]; //0-pid 1-btime 2-waiting time 3-turn around time

		std::queue<std::pair<int,int>> rq;
		std::pair<int,int> temp;
		std::vector<std::pair<int,int>> chart;
	public:
        	virtual void get_input( char** ) = 0;

        	virtual void calculate() = 0;
	
        	virtual void output();
};

class fcfs : public cpu_schedule {
	public:
		void get_input( char** );
		void calculate();
};

class sjf : public fcfs {
	private:
		std::vector<std::pair<int,int>> vec;
	public:
		void get_input( char** );
};

class rr : public cpu_schedule {
	int qtime,bt[10];

	public:
		void get_input( char** );
		void calculate();
};

class ps : public fcfs {
	private:
	        int p;
		std::vector<std::pair<std::pair<int,int>,int>> vec;		
	public:
		void get_input( char** );
};

bool compare_sjf( std::pair<int,int> , std::pair<int,int> );

bool compare_ps(std::pair<std::pair<int,int>,int> , std::pair<std::pair<int,int>,int> );

#endif
