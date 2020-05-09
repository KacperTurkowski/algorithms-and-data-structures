.SUFFIXES: .java
NAME1 = Graph
EXEC1 = Graph
CO = javac
.PHONY: all
all: $(EXEC1)
%.o:%.java
	$(CO) $<
$(EXEC1):
	$(CO) $(NAME1).java
.PHONY: run
run: $(EXEC1)
	java $(EXEC1) 10.csv 1 2 3
	java $(EXEC1) 100.csv 1 2 24
	java $(EXEC1) 1000.csv 1 2 24
	java $(EXEC1) 10000.csv 1 2 24
.PHONY: clean tar
clean:
	rm -f *~ *.class;
tar: clean
	(cd ../; tar -cvzf GraphD.tar.gz Dijkstra )
