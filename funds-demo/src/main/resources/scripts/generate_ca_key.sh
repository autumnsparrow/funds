#!/bin/sh
#
# @author sparrow

export CRYPTO_HOME=~/Projects/crypto
export OPENSSL_CONF=$CRYPTO_HOME/openssl.cnf
export SELFSIGNED_CA=palm_commerce_ca
cd $CRYPTO_HOME
#1. gernaret the ca private key
openssl genrsa  -out $CRYPTO_HOME/ca/$SELFSIGNED_CA.key 1024
#2.generate the ca csr
openssl req -new -key $CRYPTO_HOME/ca/$SELFSIGNED_CA.key -out $CRYPTO_HOME/ca/$SELFSIGNED_CA.csr
