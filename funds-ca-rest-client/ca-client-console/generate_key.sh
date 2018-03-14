#!/bin/sh
#
#
# generate the private key
#
export CRYPTO_KEYS=`pwd`
#/crypto.keys
export CRYPTO_CSRS=`pwd`
#/crypto.csrs
export SELF_SIGNED=$1

#mkdir $SELF_SIGNED
#cd $SELF_SIGNED
cd $CRYPTO_KEYS
#1. genarete the private key
echo "generate the private key..."
openssl genrsa -des3 -out $SELF_SIGNED.key 1024
# 2. generate the CSR
echo "generate the CSR"
openssl req -new -key $SELF_SIGNED.key -out $CRYPTO_CSRS/$SELF_SIGNED.csr

