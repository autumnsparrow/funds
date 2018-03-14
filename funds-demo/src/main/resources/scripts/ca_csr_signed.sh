#!/bin/sh
#
# @author sparrow

export CRYPTO_HOME=~/Projects/crypto
export OPENSSL_CONF=$CRYPTO_HOME/openssl.cnf
export SELFSIGNED_CA=palm_commerce_ca
cd $CRYPTO_HOME
openssl x509 -req -days 365 \
	-in $CRYPTO_HOME/ca/$SELFSIGNED_CA.csr \
	-out $CRYPTO_HOME/ca/$SELFSIGNED_CA.crt \
	-signkey $CRYPTO_HOME/ca/$SELFSIGNED_CA.key
