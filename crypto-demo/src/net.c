
#include <unistd.h>
#include <netdb.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/time.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include "net.h"

/**
 *
 * @param fd
 * @return
 */
io_stream_buf *create_io_stream_buf(int fd) {
	io_stream_buf *buf = (io_stream_buf*) malloc(sizeof(io_stream_buf));
	if (buf == 0) {

		return buf;
	}

	buf->fd = fd;
	buf->buffer = crypto_buffer_new(READBUF_LEN);
	return buf;
}

/**
 *
 *
 * @param read_buffer
 */
void io_stream_buf_destory(io_stream_buf *read_buffer) {
	if (read_buffer) {
		if (read_buffer->buffer) {
			crypto_buffer_destory(read_buffer->buffer);
		}
		close(read_buffer->fd);
		free(read_buffer);
	}
}

/**
 *
 * @param read_buf
 * @param timeoutMicros
 * @return
 */
ssize_t read_buffer(io_stream_buf *read_buf, int64_t timeoutMicros) {

	int haveStartTime, c;
	ssize_t len;
	fd_set fds;
	struct timeval start, tv;
	int ret;

	crypto_buffer_reset(read_buf->buffer);

	/* read the line */
	haveStartTime = 0;
	len = 0;

	if ((read_buf->buffer->length) < (read_buf->buffer->capcity)) {
		/* buffer is empty */

		if (timeoutMicros >= 0) {
			/* figure out how much time is left for reading */
			uint64_t timeLeft;

			timeLeft = timeoutMicros;
			if (haveStartTime) {

				gettimeofday(&tv, NULL);
				timeLeft -= (uint64_t) (tv.tv_sec - start.tv_sec) * 1000000
						+ (tv.tv_usec - start.tv_usec);
				if (timeLeft < 0) {

					timeLeft = 0;
				}
			} else {

				haveStartTime = 1;
				gettimeofday(&start, NULL);
			}
			tv.tv_sec = timeLeft / 1000000;
			tv.tv_usec = timeLeft % 1000000;

			/* wait for file descriptor to be ready */
			FD_ZERO(&fds);
			FD_SET(read_buf->fd, &fds);
			if (select(read_buf->fd + 1, &fds, NULL, NULL, &tv) < 1) {
				/* no input ready within time, or an actual error */

				//return -1;
				ret=-1;
			}
		}

		/* try reading a buffer full of data */

		read_buf->buffer->length = read(read_buf->fd, read_buf->buffer->buffer,
				read_buf->buffer->capcity);
		if (read_buf->buffer->length == 0) {
			//return -1;
			ret=-1;
		}
	}

	ret=read_buf->buffer->length;
	if(ret>0)
		crypto_buffer_hexdump(read_buf->buffer);
	return ret;

}
/**
 *
 *
 *
 * @param write_buf
 * @param timeoutMicros
 * @return
 */
ssize_t write_buffer(io_stream_buf* write_buf,int64_t timeoutMicros){
	int haveStartTime, c;
		ssize_t len;
		fd_set fds;
		struct timeval start, tv;

		crypto_buffer_hexdump(write_buf->buffer);

		/* read the line */
		haveStartTime = 0;
		len = 0;



			if (timeoutMicros >= 0) {
				/* figure out how much time is left for reading */
				uint64_t timeLeft;

				timeLeft = timeoutMicros;
				if (haveStartTime) {

					gettimeofday(&tv, NULL);
					timeLeft -= (uint64_t) (tv.tv_sec - start.tv_sec) * 1000000
							+ (tv.tv_usec - start.tv_usec);
					if (timeLeft < 0) {

						timeLeft = 0;
					}
				} else {

					haveStartTime = 1;
					gettimeofday(&start, NULL);
				}
				tv.tv_sec = timeLeft / 1000000;
				tv.tv_usec = timeLeft % 1000000;

				/* wait for file descriptor to be ready */
				FD_ZERO(&fds);
				FD_SET(write_buf->fd, &fds);
				if (select(write_buf->fd + 1, NULL,&fds,  NULL, &tv) < 1) {
					/* no input ready within time, or an actual error */

					return -1;
				}
			}

			/* try reading a buffer full of data */
			write_buf->buffer->offset=write(write_buf->fd,write_buf->buffer->buffer,write_buf->buffer->length);

			if (write_buf->buffer->length !=write_buf->buffer->offset) {
				return -1;
			}

		return write_buf->buffer->offset;
}

/**
 *
 *
 * @param hostname
 * @param port
 * @return
 */
int connect_to(char *hostname, uint16_t port) {
	int sock;
	struct hostent *hostent;
	struct sockaddr_in addr;

	hostent = gethostbyname(hostname);
	if (hostent == NULL) {

		fprintf( stderr, "ERROR: could not look up address for %s\n", hostname);
		return -1;
	}

	if ((sock = socket( AF_INET, SOCK_STREAM, 0)) < 0) {

		fprintf( stderr, "ERROR: could not open socket\n");
		return -1;
	}

	addr.sin_family = AF_INET;
	addr.sin_port = htons(port);
	memcpy(&addr.sin_addr, hostent->h_addr_list[0], hostent->h_length);

	if (connect(sock, (struct sockaddr *) &addr, sizeof(addr)) < 0) {

		fprintf( stderr, "ERROR: could not connect to %s:%"PRIu16"\n", hostname,
				port);
		return -1;
	}

	return sock;
}

/**
 *
 *
 * @param desiredPort
 * @return
 */
int getListen_socket(uint16_t *desiredPort) {
	int sock, t;
	struct sockaddr_in addr;

	if ((sock = socket( AF_INET, SOCK_STREAM, 0)) < 0) {

		return -1;
	}

	/* allow fast socket reuse - ignore failure */
	t = 1;
	setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &t, sizeof(int));

	/* bind the socket to the port */
	if (*desiredPort != 0) {

		addr.sin_family = AF_INET;
		addr.sin_port = htons(*desiredPort);
		addr.sin_addr.s_addr = htonl(INADDR_ANY);
		if (bind(sock, (struct sockaddr *) &addr, sizeof(addr)) < 0) {

			return -1;
		}
	} else {

		t = 0;
		while (1) {
			addr.sin_family = AF_INET;
			*desiredPort = (random() % 64512) + 1024;
			addr.sin_port = htons(*desiredPort);
			addr.sin_addr.s_addr = htonl(INADDR_ANY);
			if (bind(sock, (struct sockaddr *) &addr, sizeof(addr)) < 0) {

				if (t < NUM_PORT_CREATION_ATTEMPTS) {

					++t;
					continue;
				} else {

					return -1;
				}
			}

			break;
		}
	}

	/* listen on the socket */
	if (listen(sock, 8) < 0) {

		return -1;
	}

	return sock;
}
