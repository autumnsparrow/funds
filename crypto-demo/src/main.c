/*
 * main.c
 *
 *  Created on: Oct 9, 2013
 *      Author: sparrow
 */
#include "crypto_openssl.h"
#include "crypto_buffer.h"
#include "packet.h"
#include "util.h"
#include "net.h"

typedef struct _protocol_240001{
	char serial_number[20];
	char user_id[20];
	char transaction_time[20];
}protocol_240001;

#define PATH_MAX 1024

#define READ_WRITE_TIMEOUT 10000

int main(){
	//char* base_dir="/Users/sparrow/Documents/cworkshop/crypto-demo";
	char* base_dir="/Users/sparrow/Downloads/chongqing.svn/funds-proxy/crypto-demo";
	char* local_key_name="crts/bank_test_01/BT000001.key";
	char* local_passphase="bt000001";
	char* roo_ca_file_name="crts/chongqing_lottery_ca.crt";
	char* remote_crt_name= "crts/bank_test_01/P0000001.crt";
/*
	char* local_key_name="crts-local/B0000001/B0000001.key";
	char* local_passphase="00000000";
	char* roo_ca_file_name="crts-local/palm_commerce_ca.crt";
	char* remote_crt_name= "crts-local/T0000001/T0000001.crt";
	
*/
	char local_key[PATH_MAX];
	char roo_ca_file[PATH_MAX];
	char remote_crt[PATH_MAX];
	sprintf(local_key,"%s/%s",base_dir,local_key_name);
	sprintf(roo_ca_file,"%s/%s",base_dir,roo_ca_file_name);
	sprintf(remote_crt,"%s/%s",base_dir,remote_crt_name);


	char buffer[10];

	crypto_file_loader* file_loader=crypto_file_new();

	// loading the private key.
	(*(file_loader->load_rsa_private_key))(file_loader,local_key,local_passphase);
	(*(file_loader->load_rsa_remote_crt))(file_loader,remote_crt,roo_ca_file);

	// let's encrypt something
	memset(buffer,'b',sizeof(buffer));
	buffer[sizeof(buffer)-1]=0x0;
	crypto_debug("%s",buffer);
	crypto_buffer* plaintext_buffer=crypto_buffer_new_with_bytes(buffer,0,sizeof(buffer));

#ifdef ENCRYPT_DEMO
	crypto_buffer* encrypted_buffer=crypto_buffer_new(2048);
		crypto_buffer* decrypted_buffer=crypto_buffer_new(2048);
	crypto_buffer_hexdump(plaintext_buffer);
	(*(file_loader->encrypt))(file_loader,plaintext_buffer,encrypted_buffer);

	crypto_buffer_hexdump(encrypted_buffer);



	// dump the encrypted_buffer
	//BIO_dump(file_loader->bio_err,encrypted_buffer->buffer,encrypted_buffer->length);
	(*(file_loader->decrypt))(file_loader,encrypted_buffer,decrypted_buffer);
	crypto_buffer_hexdump(decrypted_buffer);
#endif

#ifdef DIGITAL_SIGN_DEMO
	(*(file_loader->digital_sign))(file_loader,plaintext_buffer);
	crypto_buffer_hexdump(plaintext_buffer);

	(*(file_loader->digital_verify))(file_loader,plaintext_buffer);
	crypto_buffer_hexdump(plaintext_buffer);
#endif

#ifdef PACKET_DEMO
	packet* request_packet=packet_new_with_protocol_content(file_loader,plaintext_buffer);

	packet_set_header(request_packet,"240001","BT000001","T0000002");
	//let's show how to covert the protocol content to the encrypted body.
	(*(request_packet->content_convert_protocol_to_crytpro))(request_packet->packet_body,file_loader);
	packet_destory(packet);

	#endif

#ifdef ENDIAN_TEST_DEMO
	int flag=is_big_endian();

	crypto_debug(" flag :%d litte-endian:%d big_endian:%d ",flag,LITTLE_ENDIAN,BIG_ENDIAN);
#endif



	/**
	 *  Here begins the real stuff.
	 *  Let's play!
	 *
	 */

	// steps.
	// 1. create the demo protocol content

	protocol_240001 p240001={0x0};
	get_transaction_time(p240001.transaction_time);
	get_serial_number(p240001.serial_number);
	crypto_buffer* protocol_content=crypto_buffer_new(100);
	//sprintf(protocol_content->buffer,"%s|2100000001|%s|",p240001.serial_number,p240001.transaction_time);
	sprintf(protocol_content->buffer,"%s|40018009|%s|",p240001.serial_number,p240001.transaction_time);
	protocol_content->length=strlen(protocol_content->buffer);

	//2. create the packet
	packet* request=packet_new_with_protocol_content(file_loader,protocol_content);

	// 3. set the header
	packet_set_header(request,"240001","BT000001","T0000002");
	//4. set the protocol content
	//request->packet_body.protocol_content=protocol_content;
	//5. conver the protocl content into crypto
	(*(request->content_convert_protocol_to_crytpro))(request);


	// here create
	// begin the network part
	// 6.connect the remote server
	int fd=connect_to("222.177.23.40",2023);
	//int fd=connect_to("127.0.0.1",9022);

	// create the write packet stream buffer.
	io_stream_buf* write_stream=create_io_stream_buf(fd);
	// set the write_stream to packet stream
	request->packet_stream=write_stream->buffer;

	//6. object to stream
	packet_object_to_stream(request);

	//let's dump the packet stream
	//crypto_buffer_hexdump(request->packet_stream);

	// write the buffer.
	size_t writed_bytes=write_buffer(write_stream,READ_WRITE_TIMEOUT);

	io_stream_buf* read_stream=create_io_stream_buf(fd);

	size_t readed_bytes=read_buffer(read_stream,READ_WRITE_TIMEOUT);

	//crypto_buffer_hexdump(read_stream->buffer);

	packet* response=packet_new(file_loader);
	response->packet_stream=read_stream->buffer;
	packet_stream_to_object(response);

	(*(request->content_convert_crypto_to_protocol))(response);

	char response_string[MAX_PACKET]={0x0};

	sprintf(response_string,"%s",response->packet_body.protocol_content->buffer);
	if(MAX_PACKET>response->packet_body.protocol_content->length)
	response_string[response->packet_body.protocol_content->length]=0x0;
	crypto_debug("%s",response_string);

	crypto_buffer_destory(plaintext_buffer);

	crypto_file_destory(file_loader);

	return 0;
}
