#!/bin/sh
#
HOME=`pwd`
lib=$HOME/lib
classes=$HOME/classes
main=com.palmcommerce.funds.ca.client.execute.CommandLineTool
CLASSPATH=classes
for jar in `ls $HOME/lib/*.jar`;
do
	CLASSPATH=$CLASSPATH:$jar
done

java -cp $CLASSPATH $main

