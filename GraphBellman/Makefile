.SUFFIXES: .java
NAME1=Graph
NAME2=My_List
NAME3=Vertex
EXEC1=Graph
OBJS1=Graph.o

CO=javac

.PHONY: all
all: $(EXEC1)
%.o:%.java
	$(CO) $<
$(EXEC1): $(OBJS1)
	$(CO) $(NAME1).java
	$(CO) $(NAME2).java
	$(CO) $(NAME3).java
.PHONY: run1 run2
run1: $(EXEC1)
	java $(EXEC1) bellman.csv 1
run2: $(EXEC1)
	java $(EXEC1) bellman2.csv 1
.PHONY: clean tar
clean:
	rm -f *~ *.o *.a *.so *.x core core* a.out result.dot *class
tar: clean
	(cd ../; tar -cvzf GraphBellman.tar.gz GraphBellman)
