package com.youku.soku.import_iDB;

import com.youku.soku.SystemConfig;
import com.youku.soku.export_iDB.ExportRunner;
import com.youku.soku.export_iDB.ExportThreadPool;

import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZeYu
 * Date: 2017/2/17.
 * Time: 13:55.
 * DESC: say something
 */
public class ImportTask extends TimerTask {

    public void run() {
        ImportTddlSql.importIDB("", "BAD_CASE_APP", "/home/admin/querymodification/qmData/query_reformulation.bak");
    }
}
