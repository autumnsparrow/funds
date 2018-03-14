/*
 * crypto_buffer.c
 *
 *  Created on: Oct 11, 2013
 *      Author: sparrow
 */

#include "crypto_buffer.h"
/**
 *
 *
 * @param size
 * @return
 */
crypto_buffer* crypto_buffer_new(size_t size) {
	crypto_buffer* buffer = (crypto_buffer*) malloc(sizeof(crypto_buffer));
	buffer->buffer=NULL;
	buffer->offset = 0;
	buffer->length = 0;
	buffer->capcity = size;

	if(size>0){
		buffer->buffer = (unsigned char*) malloc(size * sizeof(unsigned char));
		memset(buffer->buffer, 0x0, size);
	}
	buffer->out = BIO_new_fp(stderr, BIO_NOCLOSE);
	return buffer;
}

/**
 *
 * @param bytes
 * @param offset
 * @param len
 * @return
 */
crypto_buffer* crypto_buffer_new_with_bytes(unsigned char* bytes, int offset,
		int len) {
	crypto_buffer* buffer = crypto_buffer_new((size_t) (len));
	//memcpy(buffer->buffer,bytes+offset,(size_t)(len));
	crypto_buffer_copy(buffer, bytes, offset, len);

	return buffer;

}

/**
 *
 * @param buffer
 */
void crypto_buffer_destory(crypto_buffer* buffer) {
	if (buffer) {
		if (buffer->buffer) {
			free(buffer->buffer);
		}
		if (buffer->out) {
			BIO_free(buffer->out);
		}
		free(buffer);
	}
}

/**
 *
 *
 * @param buffer
 * @param bytes
 * @param offset
 * @param len
 */
void crypto_buffer_copy(crypto_buffer* buffer, unsigned char* bytes, int offset,
		size_t len) {
	// checking
	memcpy(buffer->buffer + buffer->length, bytes + offset, (size_t )(len));
	buffer->length += len;

}

/**
 *
 *
 * @param buffer
 */
void crypto_buffer_hexdump(crypto_buffer* buffer) {
	BIO_dump(buffer->out, buffer->buffer, buffer->length);
}

/**
 *
 *
 * @param buffer
 */
void crypto_buffer_reset(crypto_buffer* buffer){
	if(buffer->buffer){
		memset(buffer->buffer,0x0,buffer->capcity);
		buffer->length=0;
		buffer->offset=0;

	}
}


/**
 *
 * @param buffer
 * @param append_buffer
 */
void crypto_buffer_append(crypto_buffer* buffer, crypto_buffer* append_buffer) {
	if(((buffer->capcity)-(buffer->length))<(append_buffer->length)){
		unsigned char* new_buffer=(unsigned char*)malloc(buffer->capcity+append_buffer->length);
		memcpy(new_buffer,buffer->buffer,buffer->length);
		memcpy(new_buffer+buffer->length,append_buffer->buffer,append_buffer->length);
		if(buffer->buffer){
			free(buffer->buffer);
		}
		buffer->buffer=new_buffer;
		buffer->length=buffer->length+append_buffer->length;
		buffer->capcity=buffer->length;

	}else{
		memcpy(buffer->buffer+buffer->length,append_buffer->buffer,append_buffer->length);
		buffer->length+=append_buffer->length;
	}


}


/**
 * swap the source and dist
 *
 *
 * @param source_buffer
 * @param dist_buffer
 */
void crypto_buffer_replace(crypto_buffer* source_buffer,
		crypto_buffer* dist_buffer) {

	crypto_buffer dummy;
	dummy.buffer=source_buffer->buffer;
	dummy.capcity=source_buffer->capcity;
	dummy.length=source_buffer->length;
	dummy.offset=source_buffer->offset;
	dummy.out=source_buffer->out;

	source_buffer->buffer=dist_buffer->buffer;
	source_buffer->capcity=dist_buffer->capcity;
	source_buffer->length=dist_buffer->length;
	source_buffer->offset=dist_buffer->offset;
	source_buffer->out=dist_buffer->out;

	dist_buffer->buffer=dummy.buffer;
	dist_buffer->capcity=dummy.capcity;
	dist_buffer->length=dummy.length;
	dist_buffer->offset=dummy.offset;
	dist_buffer->out=dummy.out;






}

