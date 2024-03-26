# export JAVA_HOME=`/usr/libexec/java_home -v 1.8.0_402`
# javac '/Users/justinsMacbookPro/Desktop/CaCO3/CaCO3/CaCO3Decomposition.java'
find . -name "*.java" > sources.txt
javac -target 1.8 -source 1.8 @sources.txt
find . -name "*.class" | xargs jar cfm CaCO3.jar manifest.txt