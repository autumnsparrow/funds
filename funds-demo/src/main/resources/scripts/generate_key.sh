#!/bin/sh
#
#
# generate the private key
#
export SELF_SIGNED=$1
mkdir $SELF_SIGNED
cd $SELF_SIGNED
#1. genarete the private key
echo "generate the private key..."
openssl genrsa -des3 -out $SELF_SIGNED.key 1024
# 2. generate the CSR
echo "generate the CSR"
openssl req -new -key $SELF_SIGNED.key -out $SELF_SIGNED.csr

