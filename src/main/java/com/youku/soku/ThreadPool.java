package com.youku.soku;

import com.youku.soku.constant.CommonConstant;

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
    private static ExecutorService executorService = Executors.newFixedThreadPool(CommonConstant.APP_NAME_LIST.size());

    public static void startTimer(){
        /**等待各线程都执行结束后，再等待2min执行**/
        executor.scheduleWithFixedDelay(new ThreadPool(), 0, 2, TimeUnit.MINUTES);
    }

    @Override
    public void run() {
        for (int i=0; i<CommonConstant.APP_NAME_LIST.size(); i++) {
            executorService.submit(new ExportRunner(
                    CommonConstant.SQL_LIST.get(i),
                    CommonConstant.APP_NAME_LIST.get(i),
                    CommonConstant.EXPORT_FILE_LIST.get(i)));
        }
    }
}
