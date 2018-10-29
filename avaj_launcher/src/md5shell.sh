find . -name "*.java" > sources.txt
javac -sourcepath . @sources.txt
java temka.simulator.Simulator _scenario_md5.md5

