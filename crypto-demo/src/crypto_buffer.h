/*
 * cyrpto_buffer.h
 *
 *  Created on: Oct 11, 2013
 *      Author: sparrow
 */

#ifndef CYRPTO_BUFFER_H_
#define CYRPTO_BUFFER_H_
#include "crypto_config.h"
typedef struct _crypto_buffer {
	unsigned char * buffer;
	size_t offset;
	size_t length;
	size_t capcity;
	BIO* out;
} crypto_buffer;

/**
 *
 * @param size
 * @return
 */
crypto_buffer* crypto_buffer_new(size_t size);
/**
 *
 * @param buffer
 * @param bytes
 * @param offset
 * @param len
 */

void crypto_buffer_copy(crypto_buffer* buffer,unsigned char* bytes,int offset,size_t len);

/**
 *
 *
 * @param source_buffer
 * @param dist_buffer
 */
void crypto_buffer_replace(crypto_buffer* source_buffer,crypto_buffer* dist_buffer);

/**
 *
 * @param bytes
 * @param offset
 * @param len
 * @return
 */
crypto_buffer* crypto_buffer_new_with_bytes(unsigned char* bytes,int offset,int len);
/**
 *
 *
 * @param buffer
 */
void crypto_buffer_destory(crypto_buffer* buffer);

/**
 *
 *
 * @param buffer
 */
void crypto_buffer_hexdump(crypto_buffer* buffer);

/**
 *
 *
 * @param buffer
 * @param append_buffer
 */
void crypto_buffer_append(crypto_buffer* buffer,crypto_buffer* append_buffer);

/**
 *
 *
 * @param buffer
 */
void crypto_buffer_reset(crypto_buffer* buffer);


#endif /* CYRPTO_BUFFER_H_ */
