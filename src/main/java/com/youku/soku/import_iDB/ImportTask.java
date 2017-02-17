package com.youku.soku.import_iDB;


import com.youku.soku.SystemConfig;

import java.util.TimerTask;

/**
 * Created by ZeYu
 * Date: 2017/2/17.
 * Time: 13:55.
 * DESC: say something
 */
public class ImportTask extends TimerTask {

    public void run() {
        ImportTddlSql.importIDB("", SystemConfig.IMPORT_QUERY_MODIFICATION_APP_NAME, SystemConfig.IMPORT_QUERY_MODIFICATION_FILE_PATH);
    }
}
