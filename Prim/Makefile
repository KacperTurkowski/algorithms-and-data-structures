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
.PHONY: run run2 run3 run4 run5
run: $(EXEC1)
	java $(EXEC1) 10.csvo 1
run2: $(EXEC1)
	java $(EXEC1) 100.csvo 1
run3: $(EXEC1)
	java $(EXEC1) 1000.csvo 1
run4: $(EXEC1)
	java $(EXEC1) 10000.csvo 1
.PHONY: clean tar
clean:
	rm -f *~ *.o *.a *.so *.x core core* a.out result.csv *.class;
tar: clean
	(cd ../; tar -cvzf GraphPrim.tar.gz Prim)
