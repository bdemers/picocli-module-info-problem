#!/bin/bash

javac \
   -d target/classes \
   -classpath target/classes:${HOME}/.m2/repository/info/picocli/picocli-codegen/4.2.0/picocli-codegen-4.2.0.jar: \
   --module-path ${HOME}/.m2/repository/info/picocli/picocli/4.1.4/picocli-4.1.4.jar: \
   -sourcepath src/main/java:target/generated-sources/annotations: \
   -s target/generated-sources/annotations \
   -g -nowarn \
   -verbose \
   -target 11 \
   -source 11 \
   -encoding UTF-8 \
   --module-version 1.0-SNAPSHOT \
   src/main/java/org/example/problem/Main.java
