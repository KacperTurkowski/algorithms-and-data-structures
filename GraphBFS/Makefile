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
.PHONY: run
run: $(EXEC1)
	java $(EXEC1) lab.csv 2 2 3 4 5 6 7 8 9
.PHONY: clean tar
clean:
	rm -f *~ *.o *.a *.so *.x core core* a.out result.dot *class
tar: clean
	(cd ../; tar -cvzf GraphBFS.tar.gz GraphBFS)
