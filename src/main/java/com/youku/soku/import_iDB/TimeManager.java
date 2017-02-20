package com.youku.soku.import_iDB;

import com.youku.soku.SystemConfig;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * Created by ZeYu
 * Date: 2017/2/17.
 * Time: 17:17.
 * DESC: say something
 */
public class TimeManager {

    private static final long PERIOD_DAY = SystemConfig.IMPORT_THREAD_TIME_INTERVAL * 60 * 1000;

    public static void startTimerManager(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 1); //凌晨1点
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date=calendar.getTime(); //第一次执行定时任务的时间

        //如果第一次执行定时任务的时间 小于当前的时间,此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
        /*if (date.before(new Date())) {
            date = addDay(date, 3);
        }*/

        Timer timer = new Timer();
        ImportTask task = new ImportTask();
        //安排指定的任务在指定的时间开始进行重复的固定延迟执行。
        timer.schedule(task, date, PERIOD_DAY);

    }
    public static Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, num);
        return startDT.getTime();
    }
}
