#!/bin/sh

sshpass -p "banana132<>?" /usr/bin/rsync -a -t student-admin@192.168.100.52:/home/scpuser/ /var/www/fileserver/user/ 
