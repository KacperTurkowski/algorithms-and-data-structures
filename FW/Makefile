.SUFFIXES: .java
NAME1 = Graph
EXEC1 = Graph
CO = javac
.PHONY: all
all: $(EXEC1)
%.o:%.java
	$(CO) $(NAME1).java
$(EXEC1):
	$(CO) $(NAME1).java
.PHONY: run
run: $(EXEC1)
	java $(EXEC1) 10.csv '4,5'
	java $(EXEC1) 100.csv '4,5' '10,33'
	java $(EXEC1) 1000.csv '4,5' '20,780' '10,33'
	java $(EXEC1) 10000.csv '4,5' '20,780' '10,33'
.PHONY: clean tar
clean:
	rm -f *~ *.class
tar: clean
	(cd ../; tar -cvzf GraphFW.tar.gz FW)
