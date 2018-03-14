#!/bin/sh
#
# @author sparrow
#
export CRYPTO_HOME=`pwd`/../crypto.ca/crypto
export SELFSIGNED_CA=chongqing_lottery_ca
#chongqing_lottery_ca
export SELF_SIGNED=$1
echo "cd $SELF_SIGNED ...."
cd $SELF_SIGNED
echo "signing the $SELF_SIGNED.csr"
openssl x509 -req -days 365 \
	-in $SELF_SIGNED.csr -out $SELF_SIGNED.crt \
	-CA $CRYPTO_HOME/ca/$SELFSIGNED_CA.crt \
	-CAkey $CRYPTO_HOME/ca/$SELFSIGNED_CA.key \
	-CAserial $CRYPTO_HOME/ca/serial
