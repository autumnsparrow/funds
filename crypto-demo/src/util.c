/*
 * util.cpp
 *
 *  Created on: Oct 11, 2013
 *      Author: sparrow
 */

#include "util.h"

#include <time.h>
#include <stdio.h>
#define MAX 1293849
void get_transaction_time(char* transaction_time) {
	time_t curtime;
	struct tm *loctime;

	/* Get the current time. */
	curtime = time(NULL);

	/* Convert it to local time representation. */
	loctime = localtime(&curtime);
	strftime(transaction_time, DATE_STRING_SIZE, "%Y-%m-%d %H:%M:%S",
			loctime);
}


void get_serial_number(char* serial_number) {
	char current_time[15]={0x0};

	time_t curtime;
	struct tm *loctime;

	/* Get the current time. */
	curtime = time(NULL);

	/* Convert it to local time representation. */
	loctime = localtime(&curtime);
	srand(time(NULL));
	int r = rand()/MAX;

	strftime(current_time, 15, "%Y%m%d%H%M%S",
			loctime);
	snprintf(serial_number,20,"%s%d",current_time,r);

}
