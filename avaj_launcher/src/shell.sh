#!/usr/bin/env bash
find . -name "*.java" > sources.txt
javac -sourcepath . @sources.txt
java temka.simulator.Simulator scenario.txt