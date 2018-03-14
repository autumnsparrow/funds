#!/bin/sh
java=/home/cqtest/jdk1.6.0_12/bin/java
lib=../classes
classpath=$lib
for j in `ls ../repo/*.jar`
do
  classpath=$classpath:$j;
done
$java -cp $classpath Reconciliation $@