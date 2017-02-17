package com.youku.soku.export_iDB;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by ZeYu
 * Date: 2017/2/10.
 * Time: 22:45.
 * DESC: say something
 */
public class ExportRunner implements Runnable {
    private static final Logger logger = LogManager.getLogger(ExportRunner.class);
    private String SQL;
    private String APP_NAME;
    private String FILE_NAME;

    public ExportRunner(String SQL, String APP_NAME, String FILE_NAME) {
        this.SQL = SQL;
        this.APP_NAME = APP_NAME;
        this.FILE_NAME = FILE_NAME;
    }

    @Override
    public void run() {
        ExportTddlSql.searchIDB(SQL, APP_NAME, FILE_NAME);
    }
}
