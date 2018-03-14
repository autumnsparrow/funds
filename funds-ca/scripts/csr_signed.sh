#!/bin/sh
#
# @author sparrow
#
export CRYPTO_HOME=`pwd`/crypto.ca/crypto
export CRYPTO_CSRS=`pwd`/crypto.csrs
export CRYPTO_CRTS=`pwd`/crypto.crts
export SELFSIGNED_CA=`head -1 $CRYPTO_HOME/ca.cnf`
export SELF_SIGNED=$1
#echo "cd $SELF_SIGNED ...."
#cd $SELF_SIGNED
echo "signing the $SELF_SIGNED.csr"
openssl x509 -req -days 365 \
	-in $CRYPTO_CSRS/$SELF_SIGNED.csr -out $CRYPTO_CRTS/$SELF_SIGNED.crt \
	-CA $CRYPTO_HOME/ca/$SELFSIGNED_CA.crt \
	-CAkey $CRYPTO_HOME/ca/$SELFSIGNED_CA.key \
	-CAserial $CRYPTO_HOME/ca/serial
