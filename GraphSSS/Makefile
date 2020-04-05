.SUFFIXES: .java
NAME1 = Graph
EXEC1 = Graph
OBJS1 = Graph.o

CO = javac

.PHONY: all
all: $(EXEC1)
%.o:%.java
	$(CO) $<
$(EXEC1): $(OBJS1)
	$(CO) $(NAME1).java
.PHONY: run
run: $(EXEC1)
	java $(EXEC1) graph.csv
.PHONY: clean tar
clean:
	rm -f *~ *.o *.a *.so *.x core core* a.out result.dot *.class;
tar: clean
	(cd ../; tar -cvzf Graph.tar.gz GraphSSS)
