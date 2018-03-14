/*
 * crypto_demo.h
 *
 *  Created on: Oct 9, 2013
 *      Author: sparrow
 */

#ifndef CRYPTO_DEMO_H_
#define CRYPTO_DEMO_H_
#include "crypto_config.h"
#include "crypto_buffer.h"
typedef struct pw_cb_data {
	const void *password;
	const char *prompt_info;
} PW_CB_DATA;



/**
 *
 */
typedef struct _crypto_file_loader {

	RSA * localKey;
	RSA * remoteKey;
	RSA * rootCa;
	EVP_PKEY * local_private_key;
	EVP_PKEY * remote_public_key;
	int e_trunk_size;
	int d_trunk_size;

	// ssl related

	unsigned char pad;
	BIO *bio_err;
	ENGINE * engine;
	/* load the crt */
	/**
	 *@param
	 *
	 */
	void (*load_rsa_private_key)(struct _crypto_file_loader* file_loader,
			const char* file , char* password);
	/**
	 *
	 * @param
	 * @param file
	 * @param rootcafile
	 */
	void (*load_rsa_remote_crt)(struct _crypto_file_loader* ,
			const char* file , const char* rootcafile);

	/*crypto method*/
	/**
	 *
	 *
	 * @param file_loader
	 * @param encrypted_buffer
	 * @param plaintext_buffer
	 */
	int (*crypto_decrypt)(struct _crypto_file_loader* file_loader,
			crypto_buffer * encrypted_buffer, crypto_buffer* plaintext_buffer);
	/**
	 *
	 * @param file_loader
	 * @param plaintext_buffer
	 * @param encrypted_buffer
	 */
	int (*crypto_encrypt)(struct _crypto_file_loader* file_loader,
			crypto_buffer *plaintext_buffer, crypto_buffer* encrypted_buffer);

	/**
	 *
	 * @param file_loader
	 * @param plaintext_buffer
	 * @param digital_signed_buffer
	 */
	int (*digital_sign)(struct _crypto_file_loader* file_loader,
			crypto_buffer* plaintext_buffer);

	/**
	 *
	 * @param file_loader
	 * @param plaintext_buffer
	 */
	int (*digital_verify)(struct _crypto_file_loader* file_loader,
			crypto_buffer* plaintext_buffer);

} crypto_file_loader;

/**
 *
 * @return
 */
crypto_file_loader * crypto_file_new();

/**
 *
 * @param file_loader
 */
void crypto_file_destory(crypto_file_loader* file_loader);

#endif /* CRYPTO_DEMO_H_ */
