/*
 * crypto_demo.c
 *
 *  Created on: Oct 9, 2013
 *      Author: sparrow
 */
#include "crypto_openssl.h"



//
// verity root ca
//

static int check(X509_STORE *ctx, char *file, STACK_OF(X509) *uchain,
STACK_OF(X509) *tchain, int purpose, ENGINE *e, BIO* bio_err);

static STACK_OF(X509) *load_untrusted(char *certfile, BIO* bio_err);
static int cb(int ok, X509_STORE_CTX *ctx);

static void nodes_print(BIO *out, const char *name,
STACK_OF(X509_POLICY_NODE) *nodes);
static void policies_print(BIO *out, X509_STORE_CTX *ctx);

//
// load rsa crt or key

static int password_callback(char *buf, int bufsiz, int verify, void *cb_tmp);
static int dump_cert_text(BIO *out, X509 *x);
static int load_pkcs12(BIO *err, BIO *in, const char *desc,
		pem_password_cb *pem_cb, void *cb_data, EVP_PKEY **pkey, X509 **cert,
		STACK_OF(X509) **ca);
static X509 *load_cert(BIO *err, const char *file, int format, const char *pass,
		ENGINE *e, const char *cert_descrip);
static EVP_PKEY *load_key(BIO *err, const char *file, int format,
		int maybe_stdin, const char *pass, ENGINE *e, const char *key_descrip);
static EVP_PKEY *load_pubkey(BIO *err, const char *file, int format,
		int maybe_stdin, const char *pass, ENGINE *e, const char *key_descrip);

static int crypto_rsa_digital_sign(EVP_PKEY* evp_key,
		crypto_buffer* plainttext_buffer, crypto_buffer* hash_buffer);
static int crypto_rsa_digital_verify(EVP_PKEY* evp_key,
		crypto_buffer* digital_buffer, crypto_buffer* plain_text_buffer);

// functions for the crypto_file_loader
static void load_rsa_private_key(crypto_file_loader* file_loader,
		const char* file, char* passwd);

static void load_rsa_remote_crt(crypto_file_loader* file_loader,
		const char* file, const char* rootcafile);
static int crypto_decrypt(crypto_file_loader* file_loader,
		crypto_buffer * encrypted_buffer, crypto_buffer* plaintext_buffer);

static int crypto_encrypt(crypto_file_loader* file_loader,
		crypto_buffer *plaintext_buffer, crypto_buffer* encrypted_buffer);
static int crypto_digital_sign(crypto_file_loader* file_loader,
		crypto_buffer* plaintext_buffer);
static int crypto_digital_verify(crypto_file_loader* file_loader,
		crypto_buffer* plaintext_buffer);

static int v_verbose = 0, vflags = 0;

/////////////////////////////////////////////////////////////////////////
//
// x509  root ca verity methods
/////////////////////////////////////////////////////////////////////////
#ifdef OPEN_SSL_0_9_8

/**
 *
 *
 * @param out
 * @param ctx
 */
void nodes_print(BIO *out, const char *name,
STACK_OF(X509_POLICY_NODE) *nodes) {
	X509_POLICY_NODE *node;
	int i;
	BIO_printf(out, "%s Policies:", name);
	if (nodes) {
		BIO_puts(out, "\n");
		for (i = 0; i < sk_X509_POLICY_NODE_num(nodes); i++) {
			node = sk_X509_POLICY_NODE_value(nodes, i);
			X509_POLICY_NODE_print(out, node, 2);
		}
	} else
		BIO_puts(out, " <empty>\n");
}
#endif

void policies_print(BIO *out, X509_STORE_CTX *ctx) {
#ifdef OPEN_SSL_0_9_8
	X509_POLICY_TREE *tree;
	int explicit_policy;
	int free_out = 0;
	if (out == NULL) {
		out = BIO_new_fp(stderr, BIO_NOCLOSE);
		free_out = 1;
	}
	tree = X509_STORE_CTX_get0_policy_tree(ctx);
	explicit_policy = X509_STORE_CTX_get_explicit_policy(ctx);

	BIO_printf(out, "Require explicit Policy: %s\n",
			explicit_policy ? "True" : "False");

	nodes_print(out, "Authority", X509_policy_tree_get0_policies(tree));
	nodes_print(out, "User", X509_policy_tree_get0_user_policies(tree));
	if (free_out)
		BIO_free(out);
#endif
}

/**
 *
 *
 */
int check(X509_STORE *ctx, char *file, STACK_OF(X509) *uchain,
STACK_OF(X509) *tchain, int purpose, ENGINE *e, BIO* bio_err) {
	X509 *x = NULL;
	int i = 0, ret = 0;
	X509_STORE_CTX *csc;

	x = load_cert(bio_err, file, FORMAT_PEM, NULL, e, "certificate file");
	if (x == NULL)
		goto end;
	fprintf(stdout, "%s: ", (file == NULL) ? "stdin" : file);

	csc = X509_STORE_CTX_new();
	if (csc == NULL) {
		ERR_print_errors(bio_err);
		goto end;
	}
	X509_STORE_set_flags(ctx, vflags);
	if (!X509_STORE_CTX_init(csc, ctx, x, uchain)) {
		ERR_print_errors(bio_err);
		goto end;
	}
	if (tchain)
		X509_STORE_CTX_trusted_stack(csc, tchain);
	if (purpose >= 0)
		X509_STORE_CTX_set_purpose(csc, purpose);
	i = X509_verify_cert(csc);
	X509_STORE_CTX_free(csc);

	ret = 0;
	end: if (i > 0) {
		fprintf(stdout, "OK\n");
		ret = 1;
	} else
		ERR_print_errors(bio_err);
	if (x != NULL)
		X509_free(x);

	return (ret);
}

/**
 *
 *
 */
STACK_OF(X509)* load_untrusted(char *certfile, BIO* bio_err) {
	STACK_OF(X509_INFO) *sk = NULL;
	STACK_OF(X509) *stack = NULL, *ret = NULL;
	BIO *in = NULL;
	X509_INFO *xi;

	if (!(stack = sk_X509_new_null())) {
		BIO_printf(bio_err, "memory allocation failure\n");
		goto end;
	}

	if (!(in = BIO_new_file(certfile, "r"))) {
		BIO_printf(bio_err, "error opening the file, %s\n", certfile);
		goto end;
	}

	/* This loads from a file, a stack of x509/crl/pkey sets */
	if (!(sk = PEM_X509_INFO_read_bio(in, NULL, NULL, NULL))) {
		BIO_printf(bio_err, "error reading the file, %s\n", certfile);
		goto end;
	}

	/* scan over it and pull out the certs */
	while (sk_X509_INFO_num(sk)) {
		xi = sk_X509_INFO_shift(sk);
		if (xi->x509 != NULL) {
			sk_X509_push(stack, xi->x509);
			xi->x509 = NULL;
		}
		X509_INFO_free(xi);
	}
	if (!sk_X509_num(stack)) {
		BIO_printf(bio_err, "no certificates in file, %s\n", certfile);
		sk_X509_free(stack);
		goto end;
	}
	ret = stack;

	end: BIO_free(in);
	sk_X509_INFO_free(sk);
	return (ret);
}

/**
 *
 *
 * @param ok
 * @param ctx
 * @return
 */
int cb(int ok, X509_STORE_CTX *ctx) {
	char buf[256];

	if (!ok) {
		if (ctx->current_cert) {
			X509_NAME_oneline(X509_get_subject_name(ctx->current_cert), buf,
					sizeof buf);
			printf("%s\n", buf);
		}
		printf("error %d at %d depth lookup:%s\n", ctx->error, ctx->error_depth,
				X509_verify_cert_error_string(ctx->error));
		if (ctx->error == X509_V_ERR_CERT_HAS_EXPIRED)
			ok = 1;
		/* since we are just checking the certificates, it is
		 * ok if they are self signed. But we should still warn
		 * the user.
		 */
		if (ctx->error == X509_V_ERR_DEPTH_ZERO_SELF_SIGNED_CERT)
			ok = 1;
		/* Continue after extension errors too */
		if (ctx->error == X509_V_ERR_INVALID_CA)
			ok = 1;
#ifdef OPEN_SSL_0_9_8
		if (ctx->error == X509_V_ERR_INVALID_NON_CA)
			ok = 1;
#endif
		if (ctx->error == X509_V_ERR_PATH_LENGTH_EXCEEDED)
			ok = 1;
		if (ctx->error == X509_V_ERR_INVALID_PURPOSE)
			ok = 1;
		if (ctx->error == X509_V_ERR_DEPTH_ZERO_SELF_SIGNED_CERT)
			ok = 1;
		if (ctx->error == X509_V_ERR_CRL_HAS_EXPIRED)
			ok = 1;
		if (ctx->error == X509_V_ERR_CRL_NOT_YET_VALID)
			ok = 1;
		if (ctx->error == X509_V_ERR_UNHANDLED_CRITICAL_EXTENSION)
			ok = 1;
#ifdef OPEN_SSL_0_9_8
		if (ctx->error == X509_V_ERR_NO_EXPLICIT_POLICY)
			policies_print(NULL, ctx);
#endif
		return ok;

	}
	if ((ctx->error == X509_V_OK) && (ok == 2))
		policies_print(NULL, ctx);
	if (!v_verbose)
		ERR_clear_error();
	return (ok);
}

/////////////////////////////////////////////////////////////////////////
//
// Rsa loader
/////////////////////////////////////////////////////////////////////////
/**
 *
 * @param buf
 * @param bufsiz
 * @param verify
 * @param cb_tmp
 * @return
 */
int password_callback(char *buf, int bufsiz, int verify, void *cb_tmp) {

	int res = 0;
	PW_CB_DATA *cb_data = (PW_CB_DATA *) cb_tmp;
	const char *prompt_info = NULL;
	const char *password = NULL;
	if (cb_data) {
		if (cb_data->password)
			password = cb_data->password;
		if (cb_data->prompt_info)
			prompt_info = cb_data->prompt_info;
	}

	if (password) {
		res = strlen(password);
		if (res > bufsiz)
			res = bufsiz;
		memcpy(buf, password, res);
		//return res;
	}

	return res;

}

/**
 *
 *
 *
 */

int load_pkcs12(BIO *err, BIO *in, const char *desc, pem_password_cb *pem_cb,
		void *cb_data, EVP_PKEY **pkey, X509 **cert, STACK_OF(X509) **ca) {
	const char *pass;
	char tpass[PEM_BUFSIZE];
	int len, ret = 0;
	PKCS12 *p12;
	p12 = d2i_PKCS12_bio(in, NULL);
	if (p12 == NULL) {
		BIO_printf(err, "Error loading PKCS12 file for %s\n", desc);
		goto die;
	}
	/* See if an empty password will do */
	if (PKCS12_verify_mac(p12, "", 0) || PKCS12_verify_mac(p12, NULL, 0))
		pass = "";
	else {
		if (!pem_cb)
			pem_cb = (pem_password_cb *) password_callback;
		len = pem_cb(tpass, PEM_BUFSIZE, 0, cb_data);
		if (len < 0) {
			BIO_printf(err, "Passpharse callback error for %s\n", desc);
			goto die;
		}
		if (len < PEM_BUFSIZE)
			tpass[len] = 0;
		if (!PKCS12_verify_mac(p12, tpass, len)) {
			BIO_printf(err,
					"Mac verify error (wrong password?) in PKCS12 file for %s\n",
					desc);
			goto die;
		}
		pass = tpass;
	}
	ret = PKCS12_parse(p12, pass, pkey, cert, ca);
	die: if (p12)
		PKCS12_free(p12);
	return ret;
}

/**
 *
 *
 * @param out
 * @param x
 * @return
 */
int dump_cert_text(BIO *out, X509 *x) {
	char *p;

	p = X509_NAME_oneline(X509_get_subject_name(x), NULL, 0);
	BIO_puts(out, "subject=");
	BIO_puts(out, p);
	OPENSSL_free(p);

	p = X509_NAME_oneline(X509_get_issuer_name(x), NULL, 0);
	BIO_puts(out, "\nissuer=");
	BIO_puts(out, p);
	BIO_puts(out, "\n");
	OPENSSL_free(p);

	return 0;
}

/**
 *
 * @param err
 * @param file
 * @param format
 * @param pass
 * @param e
 * @param cert_descrip
 * @return
 */
X509 *load_cert(BIO *err, const char *file, int format, const char *pass,
		ENGINE *e, const char *cert_descrip) {
	#ifdef ENABLE_NETSCAPE
	ASN1_HEADER *ah = NULL;
	#endif
	BUF_MEM *buf = NULL;
	X509 *x = NULL;
	BIO *cert;

	if ((cert = BIO_new(BIO_s_file())) == NULL) {
		ERR_print_errors(err);
		goto end;
	}

	if (file == NULL) {
		setvbuf(stdin, NULL, _IONBF, 0);
		BIO_set_fp(cert, stdin, BIO_NOCLOSE);
	} else {
		if (BIO_read_filename(cert,file) <= 0) {
			BIO_printf(err, "Error opening %s %s\n", cert_descrip, file);
			ERR_print_errors(err);
			goto end;
		}
	}

	if (format == FORMAT_ASN1)
		x = d2i_X509_bio(cert, NULL);
	
	#ifdef ENABLE_NETSCAPE
	else if (format == FORMAT_NETSCAPE) {
		const unsigned char *p, *op;
		int size = 0, i;

		/* We sort of have to do it this way because it is sort of nice
		 * to read the header first and check it, then
		 * try to read the certificate */
		buf = BUF_MEM_new();
		for (;;) {
			if ((buf == NULL) || (!BUF_MEM_grow(buf, size + 1024 * 10)))
				goto end;
			i = BIO_read(cert, &(buf->data[size]), 1024 * 10);
			size += i;
			if (i == 0)
				break;
			if (i < 0) {
				perror("reading certificate");
				goto end;
			}
		}
		p = (unsigned char *) buf->data;
		op = p;

		/* First load the header */
		if ((ah = d2i_ASN1_HEADER(NULL, &p, (long) size)) == NULL)
			goto end;

		/* header is ok, so now read the object */
		p = op;
		ah->meth = X509_asn1_meth();
		if ((ah = d2i_ASN1_HEADER(&ah, &p, (long) size)) == NULL)
			goto end;
		x = (X509 *) ah->data;
		ah->data = NULL;
	}
#endif
	 else if (format == FORMAT_PEM)
		x = PEM_read_bio_X509_AUX(cert, NULL,
				(pem_password_cb *) password_callback, NULL);
	else if (format == FORMAT_PKCS12) {
		if (!load_pkcs12(err, cert, cert_descrip, NULL, NULL,
		NULL, &x, NULL))
			goto end;
	} else {
		BIO_printf(err, "bad input format specified for %s\n", cert_descrip);
		goto end;
	}
	end: if (x == NULL) {
		BIO_printf(err, "unable to load certificate\n");
		ERR_print_errors(err);
	}

	#ifdef ENABLE_NETSCAPE
	if (ah != NULL)
		ASN1_HEADER_free(ah);
	#endif
	if (cert != NULL)
		BIO_free(cert);
	if (buf != NULL)
		BUF_MEM_free(buf);
	return (x);
}

/**
 *
 * @param err
 * @param file
 * @param format
 * @param maybe_stdin
 * @param pass
 * @param e
 * @param key_descrip
 * @return
 */
EVP_PKEY *load_key(BIO *err, const char *file, int format, int maybe_stdin,
		const char *pass, ENGINE *e, const char *key_descrip) {
	BIO *key = NULL;
	EVP_PKEY *pkey = NULL;
	PW_CB_DATA cb_data;

	cb_data.password = pass;
	cb_data.prompt_info = file;

	if (file == NULL && (!maybe_stdin || format == FORMAT_ENGINE)) {
		BIO_printf(err, "no keyfile specified\n");
		goto end;
	}

	key = BIO_new(BIO_s_file());
	if (key == NULL) {
		ERR_print_errors(err);
		goto end;
	}
	if (file == NULL && maybe_stdin) {
		setvbuf(stdin, NULL, _IONBF, 0);
		BIO_set_fp(key, stdin, BIO_NOCLOSE);
	} else if (BIO_read_filename(key,file) <= 0) {
		BIO_printf(err, "Error opening %s %s\n", key_descrip, file);
		ERR_print_errors(err);
		goto end;
	}
	if (format == FORMAT_ASN1) {
		pkey = d2i_PrivateKey_bio(key, NULL);
	} else if (format == FORMAT_PEM) {
		pkey = PEM_read_bio_PrivateKey(key, NULL,
				(pem_password_cb *) password_callback, &cb_data);
	}

	else if (format == FORMAT_PKCS12) {
		if (!load_pkcs12(err, key, key_descrip,
				(pem_password_cb *) password_callback, &cb_data, &pkey, NULL,
				NULL))
			goto end;
	} else {
		BIO_printf(err, "bad input format specified for key file\n");
		goto end;
	}
	end: if (key != NULL)
		BIO_free(key);
	if (pkey == NULL) {
		BIO_printf(err, "unable to load %s\n", key_descrip);
		ERR_print_errors(err);
	}
	return (pkey);
}

/**
 *
 *
 * @param err
 * @param file
 * @param format
 * @param maybe_stdin
 * @param pass
 * @param e
 * @param key_descrip
 * @return
 */
EVP_PKEY *load_pubkey(BIO *err, const char *file, int format, int maybe_stdin,
		const char *pass, ENGINE *e, const char *key_descrip) {
	BIO *key = NULL;
	EVP_PKEY *pkey = NULL;
	PW_CB_DATA cb_data;

	cb_data.password = pass;
	cb_data.prompt_info = file;

	if (file == NULL && (!maybe_stdin || format == FORMAT_ENGINE)) {
		BIO_printf(err, "no keyfile specified\n");
		goto end;
	}

	key = BIO_new(BIO_s_file());
	if (key == NULL) {
		ERR_print_errors(err);
		goto end;
	}
	if (file == NULL && maybe_stdin) {
		setvbuf(stdin, NULL, _IONBF, 0);
		BIO_set_fp(key, stdin, BIO_NOCLOSE);
	} else if (BIO_read_filename(key,file) <= 0) {
		BIO_printf(err, "Error opening %s %s\n", key_descrip, file);
		ERR_print_errors(err);
		goto end;
	}
	if (format == FORMAT_ASN1) {
		pkey = d2i_PUBKEY_bio(key, NULL);
	} else if (format == FORMAT_PEM) {
		pkey = PEM_read_bio_PUBKEY(key, NULL,
				(pem_password_cb *) password_callback, &cb_data);
	}

	else {
		BIO_printf(err, "bad input format specified for key file\n");
		goto end;
	}
	end: if (key != NULL)
		BIO_free(key);
	if (pkey == NULL)
		BIO_printf(err, "unable to load %s\n", key_descrip);
	return (pkey);
}

/**
 *
 * @param file_loader
 * @param file
 * @param passwd
 */
void load_rsa_private_key(crypto_file_loader* file_loader, const char* file,
		char* passwd) {

	EVP_PKEY * evp_key = load_key(file_loader->bio_err, file, FORMAT_PEM, 0,
			passwd, file_loader->engine, "Private Key");
	if (evp_key) {
		file_loader->local_private_key = evp_key;
		file_loader->localKey = EVP_PKEY_get1_RSA(evp_key);
		file_loader->d_trunk_size = RSA_size(file_loader->localKey);

		crypto_debug_const("rsa private key loaded");
		//BIO_dump(file_loader->bio_err,)
		//EVP_PKEY_free(evp_key);
	}
}

/**
 *
 *
 * @param file_loader
 * @param file
 * @param rootcafile
 */
void load_rsa_remote_crt(crypto_file_loader* file_loader, const char* file,
		const char* rootcafile) {

	EVP_PKEY* evp_key = NULL;
	X509_STORE *cert_ctx = NULL;
	X509_LOOKUP *lookup = NULL;
#ifdef OPEN_SSL_0_9_8
	X509_VERIFY_PARAM *vpm = NULL;
#endif
	STACK_OF(X509) *trusted = NULL;
	int i;
	int purpose = -1;
	int check_result;
	// verify the crt by root ca.
	cert_ctx = X509_STORE_new();
	if (cert_ctx == NULL) {
		crypto_debug_const("X509 store create failed");
		return;
	}
	X509_STORE_set_verify_cb_func(cert_ctx, cb);

	//ERR_load_crypto_strings();
#ifdef OPEN_SSL_0_9_8
	if (vpm)
		X509_STORE_set1_param(cert_ctx, vpm);
#endif

	lookup = X509_STORE_add_lookup(cert_ctx, X509_LOOKUP_file());
	if (lookup == NULL) {
		crypto_debug_const("X509 store lookup failed");

		ERR_print_errors(file_loader->bio_err);
		return;
	}
	i = X509_LOOKUP_load_file(lookup, rootcafile, X509_FILETYPE_PEM);
	if (!i) {
		crypto_debug_const("X509 lookup load file failed");
		BIO_printf(file_loader->bio_err, "Error loading file %s\n", rootcafile);

		ERR_print_errors(file_loader->bio_err);
		return;
	}

	char* crt = file;
	if (!(trusted = load_untrusted(crt, file_loader->bio_err))) {
		BIO_printf(file_loader->bio_err, "Error loading untrusted file %s\n",
				crt);
		ERR_print_errors(file_loader->bio_err);
		return;
	}

	check_result = check(cert_ctx, rootcafile, NULL, trusted, purpose,
			file_loader->engine, file_loader->bio_err);
	check_result = check(cert_ctx, file, NULL, trusted, purpose,
			file_loader->engine, file_loader->bio_err);
#ifdef OPEN_SSL_0_9_8
	if (check_result)
#endif
	{
		X509 * x = load_cert(file_loader->bio_err, file, FORMAT_PEM,
		NULL, file_loader->engine, "Certificate");

		dump_cert_text(file_loader->bio_err, x);
		if (x) {
			evp_key = X509_get_pubkey(x);
			X509_free(x);
		}
		if (evp_key) {
			int rsa_size = 0;
			file_loader->remote_public_key = evp_key;
			file_loader->remoteKey = EVP_PKEY_get1_RSA(evp_key);
			rsa_size = RSA_size(file_loader->remoteKey);
			rsa_size = rsa_size - rsa_size % 100;
			file_loader->e_trunk_size = rsa_size;
			//RSA_size(file_loader->remoteKey);
			crypto_debug_const("rsa remote crt loaded");
			//EVP_PKEY_free(evp_key);
		}
	}
}

/**
 *
 *
 *
 * @param evp_key
 * @param digital_buffer
 * @param plain_text_buffer
 */
int crypto_rsa_digital_verify(EVP_PKEY* evp_key, crypto_buffer* digital_buffer,
		crypto_buffer* plain_text_buffer) {
	int i;
	const EVP_MD* md = NULL;
	EVP_MD_CTX ctx;
	if (!(md = EVP_get_digestbyname("sha256"))) {
		ERR_print_errors(digital_buffer->out);
		return -1;
	}


	EVP_VerifyInit(&ctx,md);
	EVP_VerifyUpdate(&ctx,plain_text_buffer->buffer,plain_text_buffer->length);
	i=EVP_VerifyFinal(&ctx,digital_buffer->buffer,digital_buffer->length,evp_key);


		if(i==-1){
				ERR_print_errors(digital_buffer->out);
		}
//		if (bmd != NULL)
//			BIO_free(bmd);

	return i;
}
/**
 *
 * @param plainttext_buffer
 * @param hash_buffer
 */
int crypto_rsa_digital_sign(EVP_PKEY* evp_key,
		crypto_buffer* plainttext_buffer, crypto_buffer* hash_buffer) {
	int i=-1;
	/*const EVP_MD* md=NULL;
	 EVP_MD_CTX ctx;
	 if(!(md=EVP_get_digestbyname("sha256"))){
	 ERR_print_errors(plainttext_buffer->out);
	 return i;
	 }
	 EVP_DigestInit(&ctx,md);
	 EVP_DigestUpdate(&ctx,plainttext_buffer->buffer,plainttext_buffer->length);
	 EVP_DigestFinal(&ctx,hash_buffer->buffer,&(hash_buffer->length));

	 //show the result
	 crypto_buffer_hexdump(hash_buffer);*/

	const EVP_MD* md = NULL;
	EVP_MD_CTX ctx;
	if (!(md = EVP_get_digestbyname("sha256"))) {
		ERR_print_errors(plainttext_buffer->out);
		return i;
	}
	EVP_SignInit_ex(&ctx, md,NULL);
	EVP_SignUpdate(&ctx, plainttext_buffer->buffer, plainttext_buffer->length);
	i=EVP_SignFinal(&ctx, hash_buffer->buffer, &(hash_buffer->length), evp_key);
	//crypto_buffer_hexdump(hash_buffer);

	if(i==-1){
		ERR_print_errors(plainttext_buffer->out);
	}




//	 	EVP_MD_CTX	evp;
//	    EVP_MD_CTX_init (&evp);
//	    EVP_DigestInit_ex (&evp,EVP_sha256(),NULL);
//	    for (i=0;i<plainttext_buffer->length;i+=160)
//		EVP_DigestUpdate (&evp,	plainttext_buffer->buffer+i,
//					(plainttext_buffer->length-i)<160?plainttext_buffer->length-i:160);
//	    EVP_DigestFinal_ex (&evp,hash_buffer->buffer,&(hash_buffer->length));
//	    EVP_MD_CTX_cleanup (&evp);

	//crypto_buffer_hexdump(hash_buffer);

	return i;


}
/*crypto method*/
/**
 *
 *
 * @param file_loader
 * @param encrypted_buffer
 * @param plaintext_buffer
 */
int crypto_decrypt(crypto_file_loader* file_loader, crypto_buffer * encrypted_buffer,
		crypto_buffer* plaintext_buffer) {
	ERR_clear_error();
	// using the rsa private key decrypt
	int size = 0;
	int count = 0;

	crypto_debug("rsa key size decrypt:%d", file_loader->d_trunk_size);
	for (count = 0; count < encrypted_buffer->length;
			count += file_loader->d_trunk_size) {
		size = -1;
		size = RSA_private_decrypt(file_loader->d_trunk_size,
				encrypted_buffer->buffer + count,
				plaintext_buffer->buffer + plaintext_buffer->length,
				file_loader->localKey, file_loader->pad);
		if (size < 1)
			goto end;
		plaintext_buffer->length += size;

	}
	crypto_debug("decrypted packet size: %d", plaintext_buffer->length);
	end: if (size < 1) {
		ERR_print_errors(file_loader->bio_err);
	}

	return size;

}

/**
 *
 *
 * @param file_loader
 * @param plaintext_buffer
 * @param encrypted_buffer
 */
int crypto_encrypt(crypto_file_loader* file_loader, crypto_buffer *plaintext_buffer,
		crypto_buffer* encrypted_buffer) {
	ERR_clear_error();
	// using the rsa public key encrypt.
	int size = 0;
	int count = 0;
	int trunk_size = 0;
	crypto_debug("rsa key size encrypt:%d", file_loader->e_trunk_size);
	for (count = 0; count < plaintext_buffer->length;
			count += file_loader->e_trunk_size) {
		size = 0;
		trunk_size = 0;
		trunk_size =
				((plaintext_buffer->length - count) < file_loader->e_trunk_size) ?
						(plaintext_buffer->length - count) :
						file_loader->e_trunk_size;
		size = RSA_public_encrypt(trunk_size, plaintext_buffer->buffer + count,
				encrypted_buffer->buffer + encrypted_buffer->length,
				file_loader->remoteKey, file_loader->pad);
		if (size < 1) {
			goto end;
		}
//		else{
//			crypto_debug("encrypt trunk size :%d",size);
//			crypto_buffer_hexdump(encrypted_buffer);
//		}
		encrypted_buffer->length += size;
	}

	crypto_debug("encrypted packet size: %d", encrypted_buffer->length);

	end: if (size < 1) {
		ERR_print_errors(file_loader->bio_err);
	}
	return size;
}

/**
 *
 *SHA256withRSA
 * @param file_loader
 * @param plaintext_buffer
 * @param digital_signed_buffe
 */
int crypto_digital_sign(crypto_file_loader* file_loader,
		crypto_buffer* plaintext_buffer) {
	// using the rsa private encrypt the sha256 result of content.
	int flag=0;
	int signature_length=RSA_size(file_loader->localKey);
	crypto_buffer* digital_signed_buffe=crypto_buffer_new(signature_length);
	flag=crypto_rsa_digital_sign(file_loader->local_private_key, plaintext_buffer,
			digital_signed_buffe);
	crypto_buffer_append(plaintext_buffer,digital_signed_buffe);
	crypto_buffer_destory(digital_signed_buffe);

	return flag;
}
/**
 *
 * @param file_loader
 * @param plaintext_buffer
 */
int crypto_digital_verify(crypto_file_loader* file_loader,
		crypto_buffer* plaintext_buffer) {
	//using the rsa public key verify the sha256 result of content correct.
	int flag=0;
	int signature_length=RSA_size(file_loader->remoteKey);
	int offset=plaintext_buffer->length-signature_length;
	crypto_buffer* digital_buffer=crypto_buffer_new_with_bytes(plaintext_buffer->buffer,offset,signature_length);
	crypto_buffer* verify_plaintext_buffer=crypto_buffer_new_with_bytes(plaintext_buffer->buffer,0,offset);
	flag=crypto_rsa_digital_verify(file_loader->remote_public_key,digital_buffer,verify_plaintext_buffer);


	crypto_buffer_replace(plaintext_buffer,verify_plaintext_buffer);

	crypto_buffer_destory(digital_buffer);
	crypto_buffer_destory(verify_plaintext_buffer);

	//plaintext_buffer->length=offset;
	return flag;
}

/**
 *
 *
 * @return
 */
crypto_file_loader* crypto_file_new() {
	crypto_file_loader* file_loader = NULL;
	file_loader = (crypto_file_loader*) malloc(sizeof(crypto_file_loader));

	// mainly load the cert file and private keys.

	file_loader->load_rsa_private_key = load_rsa_private_key;
	file_loader->load_rsa_remote_crt = load_rsa_remote_crt;

	// mainly encrypt and decrypt method
	file_loader->crypto_decrypt = crypto_decrypt;
	file_loader->crypto_encrypt = crypto_encrypt;
	file_loader->digital_sign = crypto_digital_sign;
	file_loader->digital_verify = crypto_digital_verify;

	// reset local and remote key
	file_loader->remoteKey = NULL;
	file_loader->localKey = NULL;
	file_loader->rootCa = NULL;
	file_loader->e_trunk_size = 100;
	file_loader->d_trunk_size = 128;

	// loading the SSL context
	file_loader->pad = RSA_PKCS1_PADDING;

	if (!file_loader->bio_err)
		file_loader->bio_err = BIO_new_fp(stderr, BIO_NOCLOSE);
	ERR_load_crypto_strings();
	OpenSSL_add_all_algorithms();

	return file_loader;
}


/**
 *
 * @param file_loader
 */
void crypto_file_destory(crypto_file_loader* file_loader) {
	ERR_free_strings();
	if (file_loader->local_private_key) {
		EVP_PKEY_free(file_loader->local_private_key);
		file_loader->local_private_key = NULL;
	}
	if (file_loader->remote_public_key) {
		EVP_PKEY_free(file_loader->remote_public_key);
		file_loader->remote_public_key = NULL;
	}
	if (file_loader->localKey) {
		RSA_free(file_loader->localKey);
		file_loader->localKey = NULL;
	}

	if (file_loader->remoteKey) {
		RSA_free(file_loader->remoteKey);
		file_loader->remoteKey = NULL;
	}
	if (file_loader->bio_err) {
		BIO_free(file_loader->bio_err);
	}
	if (file_loader) {
		free(file_loader);
	}
}


