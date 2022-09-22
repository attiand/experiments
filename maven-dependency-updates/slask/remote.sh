#!/bin/bash

mvn -U versions:display-dependency-updates -Dmaven.version.rules=https://nya-artefact.its.umu.se/maven-release/version-rules.xml -Dversions.outputFile=target/deps.txt

cat target/deps.txt