/*
 * packet.h
 *
 *  Created on: Oct 11, 2013
 *      Author: sparrow
 */

#ifndef PACKET_H_
#define PACKET_H_
#include "crypto_openssl.h"
#include "crypto_buffer.h"

#define MAX_PACKET 1024*4

/**
 *
 * packet
 *
 * 	+-------------------------------------------------------+
 * 	| packet length                                         | packet.length=packet.header.length+packet.body.length
 * 	+-------------------------------------------------------+
 * 	+-------------------------------------------------------+
 * 	| 		transcode|fromcode|tocode |signature length     |    packet.header
 *  +-------------------------------------------------------+
 *          6 chars    8chars  8chars    integer(4)
 *  +=========================================================+   --> crypto_content.
 *   +------------------------------------------------------+
 *   |  protocol  content                                   |      |
 *   +------------------------------------------------------+      |     packet.body
 *   +------------------------------------------------------+      |
 *   | digital signature of protocol content (local rsa key)|              |   header.signature length
 *   +------------------------------------------------------+
 * +===========================================================+
 *
 *   encrypt by remote public key
 *
 *  packet.length= 26 ( header )
 *
 *
 */

#define PACKET_HEADER_LEN 26
typedef struct _packet_header{
	char trans_code[6];
	char from_code[8];
	char to_code[8];
	unsigned int signature_length;
}packet_header;


typedef struct _packet_body{
	crypto_buffer*  protocol_content;
	crypto_buffer*  crypto_content;

}packet_body;

typedef struct _packet{
	unsigned int packet_length;
	packet_header packet_header;
	packet_body packet_body;

	// convert the crypto to protocol content
	void (*content_convert_crypto_to_protocol)(struct _packet* packet);
	// convert the protocol to crypto
	void (*content_convert_protocol_to_crytpro)(struct _packet* packet);

	crypto_file_loader* loader;

	crypto_buffer*  packet_stream;

}packet;

 packet* packet_new(crypto_file_loader* loader);

/**
 *
 *
 * @param protocol_content
 * @return
 */
packet* packet_new_with_protocol_content(crypto_file_loader* loader,crypto_buffer* protocol_content);

/**
 *
 *
 * @param crypto_content
 * @return
 */
packet* packet_new_with_crypto_content(crypto_file_loader* loader,crypto_buffer* crypto_content);


/**
 *
 *
 * @param trans_code
 * @param from_code
 * @param to_code
 */
void packet_set_header(packet* packet,const char* trans_code,const char* from_code,const char* to_code);


/**
 *
 *
 * @param packet
 */
void packet_destory(packet* packet);

/**
 *
 *
 * @param packet
 */
void packet_object_to_stream(packet* packet);
/**
 *
 *
 * @param packet_stream
 */
void packet_stream_to_object(packet* packet);




int is_big_endian();


#endif /* PACKET_H_ */
