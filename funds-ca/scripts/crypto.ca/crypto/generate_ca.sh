#!/bin/sh
# @author sparrow
# generate the ca path.
#
export CRYPTO_HOME=`pwd`
echo "mkdir $CRYPTO_HOME/ca.."
mkdir -p $CRYPTO_HOME/ca
echo "create the serial"
echo "0001">$CRYPTO_HOME/ca/serial
echo "create the index.txt "
touch $CRYPTO_HOME/ca/index.txt


export OPENSSL_CONF=$CRYPTO_HOME/openssl.cnf
export SELFSIGNED_CA=`head -1 $CRYPTO_HOME/ca.cnf`
cd $CRYPTO_HOME
#1. gernaret the ca private key
openssl genrsa  -out $CRYPTO_HOME/ca/$SELFSIGNED_CA.key 1024
#2.generate the ca csr
openssl req -new -key $CRYPTO_HOME/ca/$SELFSIGNED_CA.key -out $CRYPTO_HOME/ca/$SELFSIGNED_CA.csr

export OPENSSL_CONF=$CRYPTO_HOME/openssl.cnf
cd $CRYPTO_HOME
openssl x509 -req -days 365 \
	-in $CRYPTO_HOME/ca/$SELFSIGNED_CA.csr \
	-out $CRYPTO_HOME/ca/$SELFSIGNED_CA.crt \
	-signkey $CRYPTO_HOME/ca/$SELFSIGNED_CA.key
