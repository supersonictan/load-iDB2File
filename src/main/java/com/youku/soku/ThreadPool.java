package com.youku.soku;

import com.youku.soku.constant.SystemConfig;

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
public class ThreadPool implements Runnable{

    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);;
    private static ExecutorService executorService = Executors.newFixedThreadPool(SystemConfig.APP_NAME_LIST.size());

    public static void startTimer(){
        /**等待各线程都执行结束后，再等待2min执行**/
        executor.scheduleWithFixedDelay(new ThreadPool(), 1, SystemConfig.LOAD_IDB_TIME_INTERVAL_MIN, TimeUnit.MINUTES);
    }

    @Override
    public void run() {
        for (int i = 0; i< SystemConfig.APP_NAME_LIST.size(); i++) {
            executorService.submit(new ExportRunner(
                    SystemConfig.SQL_LIST.get(i),
                    SystemConfig.APP_NAME_LIST.get(i),
                    SystemConfig.EXPORT_FILE_LIST.get(i)));
        }
    }
}
