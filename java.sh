#!/bin/bash

JAR_FILE_NAME=$1
LOG_FILE_NAME=java.log

export DB_ENDPOINT=customer-management-database-1.cluster-c37ecyruueoq.ap-northeast-1.rds.amazonaws.com
export DB_USERNAME=admin
export DB_PASSWORD=password
echo "set env"

if [ -e $LOG_FILE_NAME ]; then
  echo "file exists"
else
  touch $LOG_FILE_NAME
  sudo chmod 744 $LOG_FILE_NAME
  echo "create a log file"
fi

# 既に動いていたら切断
java_pid=`ps aux | grep java | grep -v grep | awk '{ print $2 }'`
echo $java_pid
if [ $java_pid ]; then
  kill -9 $java_pid
  echo "kill java process"
fi

nohup java -jar $JAR_FILE_NAME --spring.profiles.active=production > $LOG_FILE_NAME &
echo "java -jar"
