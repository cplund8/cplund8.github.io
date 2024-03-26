find . -name "*.java" > sources.txt
javac -target 1.8 -source 1.8 @sources.txt
find . -name "*.class" | xargs jar cfm HI.jar manifest.txt