JC = javac

.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java
	
CLASSES = \
	markovModel/Matrix.java \
	markovModel/MatrixReader.java \
	markovModel/MatrixWriter.java \
	markovModel/MarkovModel.java \
	main.java
	
default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class markovModel/*.class