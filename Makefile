#compiler
CC = g++

#target
TARGET = simulator

#directories, source, include, target, object
SRCDIR = src
INCDIR = include
BUILDDIR = obj
TARGETDIR = target

#includes
INC = -I$(INCDIR)
OBJ = -L$(BUILDDIR)

VPATH = src

all: directory $(TARGET)

directory:
	mkdir $(TARGETDIR)
	mkdir $(BUILDDIR)

$(TARGET): algo_sim.o cpu_schedule.o
	$(CC) $(OBJ) ./obj/algo_sim.o ./obj/cpu_schedule.o -o ./target/simulator

algo_sim.o: algo_sim.cpp include/cpu_schedule.h
	$(CC) $(INC) -c ./src/algo_sim.cpp -o ./obj/algo_sim.o

cpu_schedule.o: cpu_schedule.cpp include/cpu_schedule.h
	$(CC) $(INC) -c ./src/cpu_schedule.cpp -o ./obj/cpu_schedule.o

clean:
	rm -r $(BUILDDIR)
	rm -r $(TARGETDIR)

recompile: clean all
