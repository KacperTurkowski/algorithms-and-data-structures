.SUFFIXES: .cpp
NAME1 = HashTable
EXEC1 = $(NAME1).x
OBJS1 = $(NAME1).o
CFLAGS = -Wall -O -fPIC
LFLAGS = -O
CO = g++
LD = $(CO)
.PHONY: all
all: $(EXEC1)
%.o:%.cpp
	$(CO) $(CFLAGS) -c $<
$(EXEC1): $(OBJS1)
	$(CO) $(NAME1).cpp -o $(EXEC1) $(CFLAGS)
.PHONY: run
run: $(EXEC1)
	./$(EXEC1) integer 1 2 3 4 5
.PHONY: clean tar
clean:
	rm -f *~ *.o *.a *.so *.x core core* a.out;
tar: clean
	tar -cvzf $(NAME1).tar.gz $(NAME1).cpp Makefile
