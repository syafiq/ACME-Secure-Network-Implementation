#!/bin/sh

sshpass -p "banana132<>?" /usr/bin/rsync -a -t student-admin@192.168.100.54:/var/www/fileserver/user/ /home/scpuser/
