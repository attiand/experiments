#!/bin/bash

mvn -U versions:display-dependency-updates -Dmaven.version.rules=file:///home/maan0496/Documents/maven-dependency-updates/slask/version-rules.xml -Dversions.outputFile=target/deps.txt

cat target/deps.txt