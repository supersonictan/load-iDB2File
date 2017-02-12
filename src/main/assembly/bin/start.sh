#!/bin/bash
#定位到当前脚本存放的位置
cd `dirname $0`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf
DATA_DIR=$DEPLOY_DIR/data
LOGS_DIR=$DEPLOY_DIR/logs

PIDS=`ps -ef|grep touminghua|grep -v grep|awk '{print $2}'`
if [ -n "$PIDS" ]; then
    echo "touminghua processor is running......"
    echo "PIDS:$PIDS"
    exit 1
fi

if [ ! -d $DATA_DIR ]; then
    echo "mkdir $DATA_DIR"
	mkdir $DATA_DIR
fi

if [ ! -d $LOGS_DIR ]; then
    echo "mkdir $LOGS_DIR"
	mkdir $LOGS_DIR
fi
STDOUT_FILE=$LOGS_DIR/stdout.log

LIB_DIR=$DEPLOY_DIR/lib
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

JAVA_MEM_OPTS=" -server -Xmx4096m -Xms4096m -Xmn512m -XX:PermSize=256m -Xss2048k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70"

echo -e "Starting the touminghua ...\c"
nohup java $JAVA_MEM_OPTS -classpath $CONF_DIR:$LIB_JARS com.youku.soku.App > $STDOUT_FILE 2>&1 &