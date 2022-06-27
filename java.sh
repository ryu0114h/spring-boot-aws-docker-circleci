#!/bin/bash

JAR_FILE_NAME=$1
LOG_FILE_NAME=java.log

export DB_ENDPOINT=customer-management-database-1.cluster-c37ecyruueoq.ap-northeast-1.rds.amazonaws.com
export DB_USERNAME=admin
export DB_PASSWORD=password

if [ -e $LOG_FILE_NAME ]; then
  echo "File exists."
else
  touch $LOG_FILE_NAME
  sudo chmod 777 $LOG_FILE_NAME
fi

sudo nohup java -jar $JAR_FILE_NAME --spring.profiles.active=production > $LOG_FILE_NAME &
