package com.youku.soku.import_iDB;


import com.taobao.tddl.common.utils.logger.Logger;
import com.taobao.tddl.common.utils.logger.LoggerFactory;
import com.youku.soku.SystemConfig;

import java.io.File;
import java.util.TimerTask;

/**
 * Created by ZeYu
 * Date: 2017/2/17.
 * Time: 13:55.
 * DESC: say something
 */
public class ImportTask extends TimerTask {
    private static Logger logger = LoggerFactory.getLogger(ImportTask.class);

    public void run() {
        logger.error("Calling thread Load query_modification to iDB.....");
        if (needLoad2IDB()){
            ImportTddlSql.importIDB("", SystemConfig.IMPORT_QUERY_MODIFICATION_APP_NAME, SystemConfig.IMPORT_QUERY_MODIFICATION_FILE_PATH);
        }else {
            logger.error("needLoad2IDB:" + false);
        }
    }

    public static boolean needLoad2IDB() {
        File f = new File(SystemConfig.IMPORT_QUERY_MODIFICATION_FILE_PATH);
        if (!f.exists()){
            return false;
        }
        long t = f.lastModified();
        if (t != SystemConfig.lastModifiedTime){
            SystemConfig.lastModifiedTime = t;
            return true;
        }else {
            return false;
        }
    }
}
