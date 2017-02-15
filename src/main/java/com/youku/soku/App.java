package com.youku.soku;

import com.taobao.tddl.common.utils.logger.Logger;
import com.taobao.tddl.common.utils.logger.LoggerFactory;

/**
 * Created by ZeYu
 * Date: 2017/2/10.
 * Time: 23:42.
 * DESC: say something
 */
public class App {
    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        ThreadPool.startTimer();
        try {
            /**当前线程一直运行**/
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            logger.error("Main Thread InterruptedException!", e);
        }
    }
}
