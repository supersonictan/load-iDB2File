#!/bin/bash
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf

ps -ef|grep touminghua|grep -v touminghua|awk '{print "kill -9 "$2}'|sh