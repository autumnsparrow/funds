/*
 * packet.cpp
 *
 *  Created on: Oct 11, 2013
 *      Author: sparrow
 */
#include "packet.h"

#define IS_BIG_ENDIAN(i) ((i)==BIG_ENDIAN)

//
//
//
/**
 *
 * @return
 */

static void content_convert_p2c(packet* packet);
static void content_convert_c2p(packet * packet);

static int read_int(crypto_buffer* stream);
static size_t read_string(int length, char* entry, crypto_buffer* stream);
static size_t read_bytes(int length, crypto_buffer* bytes,
		crypto_buffer* stream);

static int check_read_buffer_overflow(int length, crypto_buffer*stream);
static int check_write_buffer_overflow(int length, crypto_buffer*stream);

static int write_int(int i, crypto_buffer* out_stream);
static size_t write_string(int length, char* entry, crypto_buffer* out_stream);
static size_t write_bytes(int length, crypto_buffer* bytes,
		crypto_buffer* out_stream);

/**
 *
 *
 * @param length
 * @param stream
 * @return
 */
int check_read_buffer_overflow(int length, crypto_buffer*stream) {

	return stream->length - stream->offset >= length ? 0 : 1;

}
/**
 *
 *
 * @param length
 * @param stream
 * @return
 */
int check_write_buffer_overflow(int length, crypto_buffer*stream) {

	return stream->capcity - stream->length > length ? 0 : 1;

}
/**
 *
 *
 * @param stream
 * @return
 */
int read_int(crypto_buffer* stream) {

	int i;
	char *p = (char *) &i;
	if (check_read_buffer_overflow(4, stream)) {
		return -1;
	}
	int count = 0;
		if (check_read_buffer_overflow(4, stream)) {
			return -1;
		}
		for (count = 0; count < 4; count++) {
			if (IS_BIG_ENDIAN(is_big_endian())) {
				*(p + count) =
						(char) (*(stream->buffer + stream->offset + count));
			} else {
				*(p + 4-1 - count) = (char) (*(stream->buffer
						+ stream->offset + count));
			}
		}
		stream->offset += 4;

		return i;


}
/**
 *
 *
 * @param length
 * @param entry
 * @param stream
 * @return
 */
size_t read_string(int length, char* entry, crypto_buffer* stream) {

	int count = 0;
	int ret=0;
	if (check_read_buffer_overflow(length, stream)) {
		ret= -1;
	}
	if(ret!=-1){
	for (count = 0; count < length; count++) {
#ifdef ENABLE_ENDIAN
		if (IS_BIG_ENDIAN(is_big_endian()))
#endif
		{
			*(entry + count) =
					(char) (*(stream->buffer + stream->offset + count));
		}
#ifdef ENABLE_ENDIAN
		else {
			*(entry + length-1 - count) = (char) (*(stream->buffer
					+ stream->offset + count));
		}
#endif
	}
	stream->offset += length;

		ret=length;
	}
	return ret;
}
/**
 *
 *
 * @param length
 * @param bytes
 * @param stream
 * @return
 */
size_t read_bytes(int length, crypto_buffer* bytes, crypto_buffer* stream) {
	int ret=0;
	int count = 0;
	if (check_read_buffer_overflow(length, stream)) {
		ret= -1;
	}
	if(ret!=-1){
	for (count = 0; count < length; count++) {
#ifdef ENABLE_ENDIAN
		if (IS_BIG_ENDIAN(is_big_endian()))
#endif
		{
			*(bytes->buffer + count) =
					*(stream->buffer + stream->offset + count);
		}
#ifdef ENABLE_ENDIAN
		else {
			*(bytes->buffer + length -1- count) = *(stream->buffer
					+ stream->offset + count);
		}
#endif
	}
	stream->offset += length;
	bytes->length=length;
	ret=length;
	}

	return ret;
}

/**
 *
 *
 * @param i
 * @param out_stream
 * @return
 */
int write_int(int i, crypto_buffer* out_stream) {
	// int i;
	char *p = (char *) &i;

	int count = 0;
		if (check_write_buffer_overflow(4, out_stream)) {
			return -1;
		}
		for (count = 0; count < 4; count++) {

			if (IS_BIG_ENDIAN(is_big_endian()))
			{
				*(out_stream->buffer + out_stream->length + count) =
						*(p + count);
			}
			else {
				*(out_stream->buffer + out_stream->length + count) = * (p+ 3- count);
			}
		}
		out_stream->length += 4;
		return count;
}
/**
 *
 *
 * @param length
 * @param entry
 * @param out_stream
 * @return
 */
size_t write_string(int length, char* entry, crypto_buffer* out_stream) {

	int count = 0;
	if (check_write_buffer_overflow(length, out_stream)) {
		return -1;
	}
	for (count = 0; count < length; count++) {
#ifdef ENABLE_ENDIAN
		if (IS_BIG_ENDIAN(is_big_endian()))
		{
#endif
			*(out_stream->buffer + out_stream->length + count) =
					*(entry + count);
#ifdef ENABLE_ENDIAN
		}
		else {
			*(out_stream->buffer + out_stream->length + count) = *(entry
					+ length -1- count);
		}
#endif
	}
	out_stream->length += length;
	return count;

}

/**
 *
 *
 * @param length
 * @param bytes
 * @param out_stream
 * @return
 */
size_t write_bytes(int length, crypto_buffer* bytes, crypto_buffer* out_stream) {
	int count = 0;
	if (check_write_buffer_overflow(length, out_stream)) {
		return -1;
	}
	for (count = 0; count < length; count++) {
#ifdef ENABLE_ENDIAN
		if (IS_BIG_ENDIAN(is_big_endian()))
#endif
		{
			*(out_stream->buffer + out_stream->length + count) =
					*(bytes->buffer + count);
		}
#ifdef ENABLE_ENDIAN
		else {
			*(out_stream->buffer + out_stream->length + count) = *(bytes->buffer
					+ length -1- count);
		}
#endif
	}
	out_stream->length += length;
	return count;
}

/**
 *
 *
 *
 * @return
 */
int is_big_endian() {
	// just chcking the first
	int endian_tester = 1, flag = LITTLE_ENDIAN;

	flag = ((*(char*) &endian_tester) == 0) ? BIG_ENDIAN : LITTLE_ENDIAN;
	return flag;
}

/**
 *define how convert the protocol content to crypto content.
 *
 * @param body
 * @param loader
 */
void content_convert_p2c(packet* packet) {
	// 1. digital signed the protocol content.
	packet_body* body = &(packet->packet_body);
	crypto_file_loader* loader = packet->loader;

	int flag = 0;
	flag = (*(loader->digital_sign))(loader, body->protocol_content);
	if (flag) {
		if (!body->crypto_content)
			body->crypto_content = crypto_buffer_new(MAX_PACKET);
		else
			crypto_buffer_reset(body->crypto_content);

		flag = (*(loader->crypto_encrypt))(loader, body->protocol_content,
				body->crypto_content);
		if (flag > 0) {
			packet->packet_header.signature_length = RSA_size(loader->localKey);
			packet->packet_length =PACKET_HEADER_LEN
					+ body->crypto_content->length;
		}
	}

}

/**
 *
 * @param body
 * @param loader
 */
void content_convert_c2p(packet* packet) {

	int flag = 0;
	packet_body* body = &(packet->packet_body);
	crypto_file_loader* loader = packet->loader;

	if (!body->protocol_content) {
		body->protocol_content = crypto_buffer_new(MAX_PACKET);
	} else {
		crypto_buffer_reset(body->protocol_content);
	}
	flag = (*(loader->crypto_decrypt))(loader, body->crypto_content,
			body->protocol_content);

	if (flag > 0) {
		// valid the data
		(*(loader->digital_verify))(loader, body->protocol_content);

	}

}

/**
 *
 *
 * @return
 */
packet* packet_new(crypto_file_loader* loader) {
	packet* p = (packet*) malloc(sizeof(packet));
	p->packet_length = 0;
	memset(p->packet_header.from_code, 0x0, sizeof(p->packet_header.from_code));
	memset(p->packet_header.to_code, 0x0, sizeof(p->packet_header.to_code));
	memset(p->packet_header.trans_code, 0x0,
			sizeof(p->packet_header.trans_code));

	p->packet_header.signature_length = 0;

	p->packet_body.crypto_content = NULL;
	p->packet_body.protocol_content = NULL;

	p->loader = loader;
	p->content_convert_crypto_to_protocol = content_convert_c2p;
	p->content_convert_protocol_to_crytpro = content_convert_p2c;

	return p;
}

/**
 *
 *
 * @param protocol_content
 * @return
 */
packet* packet_new_with_protocol_content(crypto_file_loader* loader,
		crypto_buffer* protocol_content) {
	packet* packet = packet_new(loader);
	packet->packet_body.protocol_content = protocol_content;
	return packet;
}

/**
 *
 *
 *
 * @param crypto_content
 * @return
 */
packet* packet_new_with_crypto_content(crypto_file_loader* loader,
		crypto_buffer* crypto_content) {
	packet* packet = packet_new(loader);
	packet->packet_body.crypto_content = crypto_content;
	return packet;

}

/**
 *
 * @param packet
 * @param trans_code
 * @param from_code
 * @param to_code
 */
void packet_set_header(packet* packet, const char* trans_code,
		const char* from_code, const char* to_code) {
	if (packet) {
		sprintf(packet->packet_header.trans_code, "%06s", trans_code);
		sprintf(packet->packet_header.from_code, "%08s", from_code);
		sprintf(packet->packet_header.to_code, "%08s", to_code);
//		snprintf(packet->packet_header.trans_code, 6, "%06s", trans_code);
//				snprintf(packet->packet_header.from_code, 8, "%08s", from_code);
//				snprintf(packet->packet_header.to_code, 8, "%08s", to_code);
	}
}

/**
 *
 *
 * @param packet
 */
void packet_destory(packet* packet) {
	if (packet) {
		if (packet->packet_body.crypto_content) {
			crypto_buffer_destory(packet->packet_body.crypto_content);
		}
		if (packet->packet_body.protocol_content) {
			crypto_buffer_destory(packet->packet_body.protocol_content);
		}
		free(packet);
	}
}

/**
 *
 *
 * @param packet
 */
void packet_object_to_stream(packet* packet) {

	// begin to serial the packet to stream.
	if (!packet->packet_stream) {
		packet->packet_stream = crypto_buffer_new(MAX_PACKET);
	} else {
		crypto_buffer_reset(packet->packet_stream);
	}

	// mashall the object
	//write the packet length
	write_int((packet->packet_length), packet->packet_stream);
	//write the packet header.

	write_string(6,
			packet->packet_header.trans_code, packet->packet_stream);
	write_string(8,
			packet->packet_header.from_code, packet->packet_stream);
	write_string(8,
			packet->packet_header.to_code, packet->packet_stream);
	write_int((packet->packet_header.signature_length),
			packet->packet_stream);
	//write the packet crypted body
	write_bytes(packet->packet_body.crypto_content->length,
			packet->packet_body.crypto_content, packet->packet_stream);

}
/**
 *
 *
 * @param packet_stream
 */
void packet_stream_to_object(packet* packet) {
	//read the packet length (crypto content)
	packet->packet_length = (read_int(packet->packet_stream));
	// read packet header
	read_string(6,
			packet->packet_header.trans_code, packet->packet_stream);
	read_string(8,
			packet->packet_header.from_code, packet->packet_stream);
	read_string(8,
			packet->packet_header.to_code, packet->packet_stream);
	packet->packet_header.signature_length = (
			read_int(packet->packet_stream));
	// read the
	packet->packet_body.crypto_content = crypto_buffer_new(
			packet->packet_length - PACKET_HEADER_LEN);

	// read the crypto content
	read_bytes(packet->packet_body.crypto_content->capcity,
			packet->packet_body.crypto_content, packet->packet_stream);

}
