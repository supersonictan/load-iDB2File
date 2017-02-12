package com.youku.soku;

/**
 * Created by ZeYu
 * Date: 2017/2/10.
 * Time: 23:42.
 * DESC: say something
 */
public class App {


    public static void main(String[] args) {


        ThreadPool.startTimer();
        try {
            /**当前线程一直运行**/
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
