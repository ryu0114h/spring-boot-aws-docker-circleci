#!/bin/bash

JAR_FILE_NAME=$1
LOG_FILE_NAME=java.log

#　環境変数の設定
export DB_ENDPOINT=customer-management-database-1.cluster-c37ecyruueoq.ap-northeast-1.rds.amazonaws.com
export DB_USERNAME=admin
export DB_PASSWORD=password
echo "set env"

# ログファイルがなければ作成
if [ -e $LOG_FILE_NAME ]; then
  echo "file exists"
else
  touch $LOG_FILE_NAME
  sudo chmod 744 $LOG_FILE_NAME
  echo "create a log file"
fi

# 既に動いていたら切断
java_pid=`ps aux | grep 'java -jar customer-management-api-0.0.1-SNAPSHOT.jar --spring.profiles.active=production' | grep -v grep | awk '{ print $2 }'`
echo $java_pid
if [ $java_pid ]; then
  kill -9 $java_pid
  echo "kill java process"
fi

# Spring Boot を実行
nohup java -jar $JAR_FILE_NAME --spring.profiles.active=production > $LOG_FILE_NAME &
echo "run spring boot"
