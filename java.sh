#!/bin/bash

JAR_FILE_NAME=$1
LOG_FILE_NAME=java.log
#sudo nohup java -jar $JAR_FILE_NAME --spring.profiles.active=production > $LOG_FILE_NAME &
sudo nohup java -jar $JAR_FILE_NAME --spring.profiles.active=production > -c $LOG_FILE_NAME &

