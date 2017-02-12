#!/bin/bash
#定位到当前脚本存放的位置
cd `dirname $0`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf

PIDS=`ps  --no-heading -C java -f --width 1000 | grep "$CONF_DIR" |awk '{print $2}'`
#if [ -n "$PIDS" ]; then
#    echo "ERROR: The $SERVER_NAME already started!"
#    echo "PID: $PIDS"
#    exit 1
#fi
PIDS=`ps -ef|grep touminghua|grep -v grep|awk '{print $2}'`
if [ -n "$PIDS" ]; then
    echo "touminghua processor is running......"
#    echo "ERROR: The $SERVER_NAME already started!"
#    echo "PID: $PIDS"
#    exit 1
#fi


LOGS_DIR= $DEPLOY_DIR + "/logs"

if [ ! -d $LOGS_DIR ]; then
	mkdir $LOGS_DIR
fi
STDOUT_FILE=$LOGS_DIR/stdout.log

LIB_DIR=$DEPLOY_DIR/lib
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

JAVA_MEM_OPTS=" -server -Xmx4096m -Xms4096m -Xmn512m -XX:PermSize=256m -Xss2048k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70"

echo -e "Starting the touminghua ...\c"
nohup java $JAVA_MEM_OPTS -classpath $CONF_DIR:$LIB_JARS com.youku.sok.App > $STDOUT_FILE 2>&1 &

#COUNT=0
#while [ $COUNT -lt 1 ]; do
#    echo -e ".\c"
#    sleep 1
#    	COUNT=`ps  --no-heading -C java -f --width 1000 | grep "$DEPLOY_DIR" | awk '{print $2}' | wc -l`
#	if [ $COUNT -gt 0 ]; then
#		break
#	fi
#done
#echo "OK!"
#PIDS=`ps  --no-heading -C java -f --width 1000 | grep "$DEPLOY_DIR" | awk '{print $2}'`
#echo "PID: $PIDS"
#echo "STDOUT: ${STDOUT_FILE}"