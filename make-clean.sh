#!/bin/sh
dir=`pwd`
#projects=($dir/simplejmx $dir/funds-ca $dir/funds-ca-rest-client $dir/funds-ice $dir/funds-configuration $dir/funds-packet $dir/funds-protocol $dir/funds-connection $dir/funds-trade $dir/funds-id $dir/funds-bank $dir/funds-roo $dir/funds-server-proxy $dir/funds-server-trade $dir/funds-server-bank)

projects=($dir/simplejmx  $dir/funds-ca-rest-client $dir/funds-ice $dir/funds-configuration $dir/funds-packet $dir/funds-protocol $dir/funds-connection $dir/funds-trade $dir/funds-id $dir/funds-bank )
for prj in "${projects[@]}"
do
	echo "$prj -";
	echo "making clean install ....";
	cd $prj;
	mvn -Dmaven.test.skip=true clean
	#mvn -Dmaven.test.skip=true deploy; 
done;
#cd $dir/funds-ice
#make -Dmaven.test.skip=true clean install

