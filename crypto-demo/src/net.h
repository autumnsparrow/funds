/**
 *
 */

#ifndef _NET_H
#define _NET_H

#include <stdlib.h>
#include <stdio.h>
#define __STDC_FORMAT_MACROS
#include <inttypes.h>
#include "crypto_openssl.h"
#include "packet.h"

#define READBUF_LEN 1024
#define NUM_PORT_CREATION_ATTEMPTS 10


/* buffered I/O on file descriptors

   Yes... this is basically re-implementing bits of a standard FILE.
   Unfortunately, trying to mix timeouts and FILE streams either
   a) doesn't work, or b) is fairly system specific */
typedef struct {
  int fd;
  crypto_buffer* buffer;
} io_stream_buf;


/* open a socket to hostname/port
   returns file descriptor on success, <0 on failure */
int connect_to( char *hostname, uint16_t port );

/* try opening a socket suitable for connecting to
   if *desiredPort>0, uses specified port, otherwise use a random port
   returns actual port in *desiredPort
   returns file descriptor for socket, or -1 on failure */
int getListen_socket( uint16_t *desiredPort );


/* create a read buffer structure
   returns 0 on failure */
io_stream_buf *create_io_stream_buf( int fd );

/* destroy a read buffer - like fdopen, it will close the file descriptor */
void io_stream_buf_destory( io_stream_buf *readBuf );

/* get a newline terminated line and place it as a string in 'line'
   terminates the string with a 0 character
   if timeoutMicros is non-negative, do not spend more than
   that number of microseconds waiting to read data
   return number of characters read (including newline, excluding 0)
   0 on end of file, or -1 on error or timeout */
ssize_t read_buffer( io_stream_buf *readBuf,int64_t timeoutMicros );

ssize_t write_buffer(io_stream_buf* write_buf,int64_t timeoutMicros);
#endif
