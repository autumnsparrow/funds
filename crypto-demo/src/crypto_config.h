/*
 * crypto_config.h
 *
 *  Created on: Oct 11, 2013
 *      Author: sparrow
 */

#ifndef CRYPTO_CONFIG_H_
#define CRYPTO_CONFIG_H_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <ctype.h>
#include <assert.h>



#include <openssl/bio.h>
#include <openssl/x509.h>
#include <openssl/lhash.h>
#include <openssl/conf.h>
#include <openssl/txt_db.h>
#ifndef OPENSSL_NO_ENGINE
#include <openssl/engine.h>
#endif
#ifndef OPENSSL_NO_OCSP
#include <openssl/ocsp.h>
#endif
#include <openssl/ossl_typ.h>


#include <openssl/err.h>
#include <openssl/x509.h>
#include <openssl/x509v3.h>
#include <openssl/pem.h>
#include <openssl/pkcs12.h>

#include <openssl/asn1.h>
#include <openssl/safestack.h>
#ifndef OPENSSL_NO_ENGINE
#include <openssl/engine.h>
#endif
#ifndef OPENSSL_NO_RSA
#include <openssl/rsa.h>
#endif
#include <openssl/bn.h>

#define RSA_SIGN 	1
#define RSA_VERIFY 	2
#define RSA_ENCRYPT 	3
#define RSA_DECRYPT 	4

#define KEY_PRIVKEY	1
#define KEY_PUBKEY	2
#define KEY_CERT	3

#define FORMAT_UNDEF    0
#define FORMAT_ASN1     1
#define FORMAT_TEXT     2
#define FORMAT_PEM      3
#define FORMAT_NETSCAPE 4
#define FORMAT_PKCS12   5
#define FORMAT_SMIME    6
#define FORMAT_ENGINE   7
#define FORMAT_IISSGC	8	/* XXX this stupid macro helps us to avoid
				 * adding yet another param to load_*key() */
#define crypto_debug(fmt, ...) fprintf(stderr,"DEBUG->%s : %d " fmt "\n",__FILE__,__LINE__,__VA_ARGS__)
#define crypto_debug_const(s) crypto_debug("%s",s)



typedef union _endian_tester{
	unsigned  int i;
	unsigned char s[4] ;
}endian_tester;




#endif /* CRYPTO_CONFIG_H_ */
