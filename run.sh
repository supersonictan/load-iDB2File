#!/bin/bash
STDOUT_FILE="stdout.log"
JAVA_MEM_OPTS=" -server -Xmx4096m -Xms4096m -Xmn512m -XX:PermSize=256m -Xss2048k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70"
#nohup java $JAVA_MEM_OPTS -classpath touminghua-idb-export-1.0-SNAPSHOT-jar-with-dependencies.jar com.youku.soku.App> $STDOUT_FILE 2>&1 &
nohup java jar $JAVA_MEM_OPTS touminghua-idb-export-1.0-SNAPSHOT-jar-with-dependencies.jar