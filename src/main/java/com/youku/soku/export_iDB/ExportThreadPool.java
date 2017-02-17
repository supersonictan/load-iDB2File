package com.youku.soku.export_iDB;


import com.youku.soku.SystemConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZeYu
 * Date: 2017/2/10.
 * Time: 23:48.
 * DESC: say something
 */
public class ExportThreadPool implements Runnable{

    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);;
    private static ExecutorService executorService = Executors.newFixedThreadPool(SystemConfig.entityList.size());

    /**开启 Export线程**/
    public static void startTimer(){
        /**等待各线程都执行结束后，再等待2min执行**/
        executor.scheduleWithFixedDelay(new ExportThreadPool(), 1, SystemConfig.LOAD_IDB_TIME_INTERVAL_MIN, TimeUnit.MINUTES);
    }

    @Override
    public void run() {
        for (ExportDataEntity dataEntity: SystemConfig.entityList) {
            executorService.submit(new ExportRunner(dataEntity.sql, dataEntity.appName, dataEntity.filePath));
        }
    }
}
